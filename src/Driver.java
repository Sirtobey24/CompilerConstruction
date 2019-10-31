import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Sean de Silva, Jonathan Smart, Alejandro Cruz
 * @project CompilerConstruction Project1
 * @date 10/13/2019
 */
public class Driver
{
    private static Scanner input = new Scanner(System.in);
    private static String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private static Set<Integer> state_set = new HashSet<>();
    private static Set<String> symbol_set = new HashSet<>();
    private static int numofstates;
    private static int numofsymbols;
    private static String[][] transitionFunctionGraph;

    public static void main(String[] args)
    {
        NFA();
        DFA();
    }

    private static void NFA()
    {
        do
        {
            System.out.println("Please enter number of states: ");
            numofstates = input.nextInt();
            for (int i = 1; i <= numofstates; i++)
            {
                state_set.add(i);
            }
            System.out.println("State set of the NFA = " + state_set.toString().replace("[", "{").replace("]", "}"));

            System.out.println("Please enter number of the symbols in the alphabet");
            numofsymbols = input.nextInt();

            symbol_set.addAll(Arrays.asList(alphabet).subList(0, numofsymbols));

            System.out.println("Alphabet of the NFA = " + symbol_set.toString().replace("[", "{").replace("]", "}"));
            //     symbol_set.add("ε"); //Adds the epsilon column for the transition functions graph

            transitionFunctionGraph = new String[numofstates][numofsymbols + 1];//creation of graph
            System.out.println("Enter the transition function result in set format: (1,2....) ***With 0 as empty***");

            input.nextLine(); //Clears the buffer0
            int p = 0;

            for (int i = 0; i < numofstates; i++)
            {
                String graphData;
                for (int j = 0; j < symbol_set.size(); j++)
                {
                    System.out.println("Delta(" + (i + 1) + "," + alphabet[j] + ")" + " =");
                    graphData = input.nextLine();
                    transitionFunctionGraph[i][j] = graphData;
                    //p=j;
                }

                System.out.println("Delta(" + (i + 1) + ",ε)" + " =");
                graphData = input.nextLine();
                transitionFunctionGraph[i][numofsymbols] = graphData;
                //p=0;
            }

            System.out.println("\nhere is the graph\n");
            //prints whats in the 2d Array
            for (int k = 0; k < numofstates; k++)
            {
                for (int l = 0; l < symbol_set.size() + 1; l++)
                {
                    System.out.print(transitionFunctionGraph[k][l] + "|");
                }
                System.out.println();
            }

            input.nextLine();
            System.out.print("Please enter your start state");
            int startState = input.nextInt();
            System.out.print("Please enter all final states on one line in format {1,2,....,n}");
            int endState = input.nextInt();

        } while (!input.nextLine().equals(""));

    }

    private static <T> Set<Set<T>> powerSet(Set<T> set)
    {

        T[] element = (T[]) set.toArray();
        final int set_length = 1 << element.length;
        Set<Set<T>> powerSet = new HashSet<>();
        for (int i = 0; i < set_length; i++)
        {
            Set<T> subset = new HashSet<>();
            for (int j = 0; j < element.length; j++)
            {
                int mask = 1 << j;
                if ((i & mask) != 0)
                {
                    subset.add(element[j]);
                    //System.out.println("checking" +element[j]);//powesetofnfa[y++] = (String) element[j];
                }
            }
            powerSet.add(subset);

        }
        return powerSet;

    }

//    private static <E> Set<E> union(Set<E> a, Set<E> b)
//    {
//        Set<E> tmp = new TreeSet<E>(a);
//        tmp.addAll(b);
//        return tmp;
//
//    }

    private static void DFAcreation(int col, int row)
    {
        int myCol;
        //char ch;

        String holder = transitionFunctionGraph[col][row];
        if (holder.length() == 1)
        {
            if (holder.charAt(0) == '0')
            {
                System.out.print(" = " + " empty");
            }
            else
            {
                myCol = (Integer.parseInt(holder));
                System.out.print("myCol: " + myCol);
                System.out.print(" = {" + holder + "," + transitionFunctionGraph[myCol - 1][numofsymbols] + "}");
            }
        }
        else
        {
            System.out.print(" = {");
            for (int y = 0; y < holder.length() - 1; y++)
            {
                if (holder.charAt(y) == ',')
                {
                    continue;
                }
                else
                {
                    //ch = holder.charAt(y);
                    myCol = (Character.getNumericValue(holder.charAt(y)));
                    // System.out.print("myCol else: " + myCol);
                    System.out.print(holder + "," + transitionFunctionGraph[myCol][numofsymbols] + " ");

                    //}
                }
                System.out.print("}");

            }

            //System.out.println("IN DFA creation");
            //for (int k = 0; k <= row; k++)
            //{
            //System.out.println("IN DFA creation For loop 1");
            //  for (int l = 0; l <= col; l++)
            //{
            //          System.out.print(" n= " + transitionFunctionGraph[col][row]); WORKS
            //}
            //      System.out.println(); WORKS
            //}
        }
    }

    private static void DFA()
    {
        System.out.println("\nEquivalent DFA: \n ");

        System.out.println("State set of the DFA = " + powerSet(state_set).toString().replace("[", "{").replace("]", "}"));

        System.out.println("Alphabet of the DFA = " + symbol_set.toString().replace("[", "{").replace("]", "}"));

        System.out.println("Transition function of DFA: \n");

        int row = 0, column = 0;

        String rowCatcher;

        for (Set<Integer> state : powerSet(state_set))
        {
            Object[] state_set_arr = powerSet(state_set).toArray();
            // System.out.println(state_set_arr[row++]);

            for (int j = 0; j < numofsymbols; j++)
            {

                System.out.print("Delta'(" + state.toString().replace("[", "{").replace("]", "}") + "," + alphabet[j] + ")");

                if (state.isEmpty())
                {
                    System.out.println("= empty ");
                }
                else
                {
                    rowCatcher = state_set_arr[j].toString();
                    if (rowCatcher.length() == 1)
                    {
                        column = Integer.parseInt(rowCatcher) - 1;

                    }

                /*    if (rowCatcher.length() > 1)
                    {

                        for (int y = 0; y < rowCatcher.length(); y++)
                        {
                            if (rowCatcher.charAt(y) == ',')
                            {
                                continue;
                            }

                            column =(Character.getNumericValue(rowCatcher.charAt(y)) -1) ;

                            DFAcreation(column, row);

                            row++;
                            //column++;
                            if (row == (numofsymbols))
                            {
                                row = 0;
                                column++;
                            }


                        }
                    }*/


                    DFAcreation(column, row);

                    row++;
                    //column++;
                    if (row == (numofsymbols))
                    {
                        row = 0;
                        column++;
                    }
//                    if (column == numofstates)
//                    {
//                        column = 0;
//                        row = 0;
//                    }

                }


                System.out.println("\n");
            }
        }


        //String[][] DFAGraphInfo = new String[state_set.size()][symbol_set.size()+1];//creation of graph for DFA

    }

}

    

