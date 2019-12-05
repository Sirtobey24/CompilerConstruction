package Project1;

import java.util.*;

/**
 * @author Sean de Silva, Jonathan Smart, Alejandro Cruz
 * @project CompilerConstruction Project1
 * @date 11/3/2019
 */
public class Driver
{
    private static Scanner input = new Scanner(System.in);
    private static String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private static Set<Integer> state_set = new HashSet<>();
    private static Set<String> symbol_set = new HashSet<>();
    private static int numofstates;
    private static int numofsymbols;
    private static int startState;
    private static int endState;
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
            System.out.println("{0}, {2} ,{3}, {2,3}, {3}, {0}, {1}, {0}, {0}  , {1start} {1 final} ");
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

            transitionFunctionGraph = new String[numofstates][numofsymbols + 1];//creation of graph
            System.out.println("Enter the transition function result in set format: (1,2....) ***With 0 as empty***");

            input.nextLine(); //Clears the buffer0

            for (int i = 0; i < numofstates; i++)
            {
                String graphData;
                for (int j = 0; j < symbol_set.size(); j++)
                {
                    System.out.println("Delta(" + (i + 1) + "," + alphabet[j] + ")" + " =");
                    graphData = input.nextLine();
                    transitionFunctionGraph[i][j] = graphData;
                }

                System.out.println("Delta(" + (i + 1) + ",ε)" + " =");
                graphData = input.nextLine();
                transitionFunctionGraph[i][numofsymbols] = graphData;

            }

            System.out.print("Please enter your start state ");
            startState = input.nextInt();
            System.out.print("Please enter all final states on one line in format {1,2,....,n} ");
            endState = input.nextInt();

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

        String holder = transitionFunctionGraph[col][row];
        if (holder.length() == 1)
        {
            if (holder.charAt(0) == '0')
            {
                System.out.print(" empty");
            }
            else
            {
                myCol = (Integer.parseInt(holder));
                System.out.print("{" + holder + "," + transitionFunctionGraph[myCol - 1][numofsymbols] + "}");
            }
        }
        else if (holder.length() == 3)
        {
            myCol = Integer.parseInt(String.valueOf(holder.charAt(0)));
            System.out.print("{" + holder + "," + transitionFunctionGraph[myCol - 1][numofsymbols] + "}");
            myCol = Integer.parseInt(String.valueOf(holder.charAt(2)));
            //  System.out.print(" {" + holder + "," + transitionFunctionGraph[myCol - 1][numofsymbols] + "}"); REPEATS PRINT OUT OF ABOVE LINE FOR SOME REASON
        }
        else
        {
            System.out.print("{");
            for (int y = 0; y < holder.length() - 1; y++)
            {
                if (holder.charAt(y) == ',')
                {
                    continue;
                }
                else
                {
                    myCol = Integer.parseInt(String.valueOf(holder.charAt(y)));
                    System.out.print(holder + "," + transitionFunctionGraph[myCol][numofsymbols] + " ");

                }
                System.out.print("}");

            }

        }
    }

    private static void DFA()
    {
        System.out.println("\n==========Equivalent DFA=========== \n ");

        System.out.println("State set of the DFA = " + powerSet(state_set).toString().replace("[", "{").replace("]", "}")
        );

        System.out.println("Alphabet of the DFA = " + symbol_set.toString().replace("[", "{").replace("]", "}"));

        System.out.println("Transition function of DFA: \n");

        int row = 0;

        String rowCatcher;
        String rowCatcher1;
        String rowCatcher2;
        String rowCatcher3;
        int mycol1;

        ArrayList<Set<Integer>> powerSetList = new ArrayList<>(powerSet(state_set));

        int k = 0;
        for (Set<Integer> state : powerSet(state_set))
        {

            for (int j = 0; j < numofsymbols; j++)
            {
                System.out.print("Delta'(" + state.toString().replace("[", "{").replace("]", "}") + "," + alphabet[j] + ") = ");


                rowCatcher = powerSetList.get(k).toString();

                if (rowCatcher.length() == 2)
                {
                    System.out.print("= empty");
                }
                if (rowCatcher.length() == 3)
                {
                    rowCatcher2 = String.valueOf(rowCatcher.charAt(1));
                    mycol1 = (Integer.parseInt(rowCatcher2) - 1);
                    DFAcreation(mycol1, row);
                }
                if (rowCatcher.length() == 6)
                {
                    rowCatcher1 = String.valueOf(rowCatcher.charAt(1));
                    mycol1 = (Integer.parseInt(rowCatcher1) - 1);
                    DFAcreation(mycol1, row);

                    rowCatcher2 = String.valueOf(rowCatcher.charAt(4));
                    mycol1 = (Integer.parseInt(rowCatcher2) - 1);
                    System.out.print(" UNION ");
                    DFAcreation(mycol1, row);

                }
                if (rowCatcher.length() == 9)
                {
                    rowCatcher1 = String.valueOf(rowCatcher.charAt(1));
                    mycol1 = (Integer.parseInt(rowCatcher1) - 1);
                    DFAcreation(mycol1, row);

                    rowCatcher2 = String.valueOf(rowCatcher.charAt(4));
                    mycol1 = (Integer.parseInt(rowCatcher2) - 1);
                    System.out.print(" UNION ");
                    DFAcreation(mycol1, row);

                    rowCatcher3 = String.valueOf(rowCatcher.charAt(7));
                    mycol1 = (Integer.parseInt(rowCatcher3) - 1);
                    System.out.print(" UNION ");
                    DFAcreation(mycol1, row);
                }

                System.out.println("\n");
                row++;
                if (row == (numofsymbols))
                {
                    row = 0;
                }
            }

            k++;

        }

        System.out.println("Here is the graph: \n");
        //prints whats in the 2d Array
        for (int m = 0; m < numofstates; m++)
        {
            for (int n = 0; n < symbol_set.size() + 1; n++)
            {
                System.out.print(transitionFunctionGraph[m][n] + "|");
            }
            System.out.println();
        }
        System.out.print("Start State of the DFA: ");
        System.out.println("{" + startState + "," + transitionFunctionGraph[startState - 1][numofsymbols] + "}");

        System.out.print("Final State set of the DFA: ");
        System.out.print(" {");
        for (int d = 0; d < powerSetList.size(); d++)
        {
            rowCatcher = powerSetList.get(d).toString();

            for (int a = 0; a < rowCatcher.length(); a++)
            {

                if (String.valueOf(rowCatcher.charAt(a)).equals(String.valueOf(endState)))
                {
                    System.out.print(rowCatcher.replace("[", "{").replace("]", "}") + " , ");
                    continue;
                }
            }

        }
        System.out.print("}");


    }

}



