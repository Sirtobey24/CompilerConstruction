package Project2;

import java.util.Scanner;

/**
 * @author Sean de Silva, Jonathan Smart
 * @project CompilerConstruction Project2
 * @date 11/14/2019
 */

public class CFG_Simplification
{
    private static Scanner input = new Scanner(System.in);
    private static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static String lang, lang2;
    private static char[] AlphabetHolder;
    private static int[] posHolder;
    private static char deletme;
    private static String[] Language;
    private static int mysize;

    public static void main(String[] args)
    {
        CFG();
        USELESS();
        E_PRODUCTION();
    }

    private static void USELESS()
    {
        System.out.println("\nNow using the useless rules we have: ");

        int loopCount = 0;
        while (loopCount < mysize)
        {
            int mycount1 = 0;
            posHolder = new int[5];
            int word = Language[0].length();
            String myLang = Language[0];

            for (int j = 1; j < 4; j++)
            {
                for (int k = 0; k < word; k++)
                {
                    if (AlphabetHolder[j] == myLang.charAt(k))
                    {
                        // System.out.println("|" + AlphabetHolder[j]);
                        posHolder[mycount1] = j;
                        mycount1++;
                        break;
                    }
                }
            }

            int numCount = 1;

            for (int i = 0; i < posHolder.length; i++)
            {
                if (numCount == posHolder[i])
                {
                    numCount++;
                    continue;
                }
                else
                {
                    deletme = AlphabetHolder[numCount];
                }
            }

//        System.out.println("num count: "+ deletme +" , "+ AlphabetHolder[numCount]);

            Language[numCount] = " ";

            myLang = Language[1];
            for (int n = 0; n < Language[1].length(); n++)
            {
                if (AlphabetHolder[numCount] == myLang.charAt(n))
                {
                    System.out.println("Needs replacement :" + Language[1]);
                }
            }

            myLang = Language[2];
            for (int n = 0; n < Language[2].length(); n++)
            {
                if (AlphabetHolder[numCount] == myLang.charAt(n))
                {
                    String[] arrOfStr = Language[2].split("\\|", 5);
//                System.out.println("in the if");

//                for (String a : arrOfStr) {
//                    System.out.println(a + " | ");
//                }

                    int countholder = -1;

                    for (int i = 0; i < arrOfStr.length; i++)
                    {
                        for (int j = 0; j < arrOfStr[i].length(); j++)
                        {
                            if (AlphabetHolder[numCount] == arrOfStr[i].charAt(j))
                            {
                                countholder = i;
                            }
                        }
                    }

                    arrOfStr[countholder] = "";
                    Language[2] = " ";
                    for (int i = 0; i < arrOfStr.length; i++)
                    {
                        //  System.out.println("** "+arrOfStr[i] + " **");
                        Language[2] += arrOfStr[i];
                    }

                    break;
                }
            }
            // PrintLanaguague();
            //System.out.println("REmoved\n");

            for (int k = 1; k < numCount + 1; k++)
            {
                word = Language[k].length();
                myLang = Language[k];
                for (int n = 0; n < word; n++)
                {
                    if (AlphabetHolder[k] == myLang.charAt(n))
                    {
                        if ('0' == myLang.charAt(word - 1))
                        {
                            break;
                        }
                        else
                        {
                            deletme = AlphabetHolder[k];
                            Language[k] = " ";

                            //   System.out.print("NEeds deletion: "+ deletme);
                        }
                    }
                }
            }

            word = Language[0].length();
            myLang = Language[0];
            for (int a = 1; a < numCount; a++)
            {
                for (int b = 0; b < word; b++)
                {
                    if (AlphabetHolder[a] == myLang.charAt(b))
                    {
                        if (AlphabetHolder[a] == '\u0000')
                        {
                            break;
                        }
                        else
                        {
                            String[] arrOfStr2 = Language[0].split("\\|", 5);
//                        for (String a2 : arrOfStr2) {
//                          System.out.println(a2 + " | ");
//                     }

                            int countholder = -1;
                            for (int i = 0; i < arrOfStr2.length; i++)
                            {
                                for (int j = 0; j < arrOfStr2[i].length(); j++)
                                {
                                    if (deletme == arrOfStr2[i].charAt(j))
                                    {
                                        countholder = i;
                                    }
                                }
                            }

                            if (countholder > 0)
                            {
                                arrOfStr2[countholder] = "";
                                Language[0] = " ";
                                for (int i = 0; i < arrOfStr2.length; i++)
                                {
                                    // System.out.println("**** "+arrOfStr2[i] + " ****");
                                    Language[0] += arrOfStr2[i] + "|";
//                      	if(arrOfStr[i+1] != " ") {
//                      		Language[0]+=  "|";
//                      	}
                                }
                            }
                        }
                    }
                }
            }
            loopCount++;
        }

        PrintLanaguague();

    }

    private static void E_PRODUCTION()
    {
        System.out.println("this is the E-production rule");
        int NmunberCount;

        for (int j = 0; j < Language.length; j++)
        {
            if (Language[j] == " " || Language[j] == null)
            {
                continue;
            }
            String holder2 = " ";
            String myLang2 = Language[j];
            int myword2 = Language[j].length();
            //String myLang2 = ;
            //for(int k = 0; k < myword2;k++){
            if (myLang2.charAt(myword2 - 1) == '0')
            {
                String[] arrOfStr2 = Language[j].split("\\|", 5);
                NmunberCount = j;
//                for (String a2 : arrOfStr2) {
//                    System.out.println(a2 + " | ");
//                }

                for (int l = 0; l < arrOfStr2.length; l++)
                {
                    String holder = arrOfStr2[l];
                    int sizeOfHolder = holder.length();

                    for (int m = 0; m < sizeOfHolder; m++)
                    {
                        if (holder.charAt(m) == '0')
                        {
                            continue;
                        }
                        if (Character.isLowerCase(holder.charAt(m)))
                        {
                            holder2 += holder.charAt(m);

                        }
                    }

                }

                for (int n = 0; n < arrOfStr2.length; n++)
                {

//                    System.out.println("HOLDER: " + holder2);
                    if (arrOfStr2[n].charAt(0) == '0')
                    {
                        arrOfStr2[n] = holder2;
                    }
                }
                Language[NmunberCount] = " ";
                for (int p = 0; p < arrOfStr2.length; p++)
                {
                    //                  System.out.println("**** "+arrOfStr2[p] + " ****");
                    Language[NmunberCount] += arrOfStr2[p] + "|";
                }

                PrintLanaguague();
            }
        }
    }

    private static void CFG()
    {
        AlphabetHolder = new char[26];
        Language = new String[26];
        // TODO Auto-generated method stub
        System.out.println("aA|aB, aaA|0, bB|bbC, B");
        System.out.println("Enter a CFG language, use the symbol '|' to denote or and 0 to denote Epsilon");
        System.out.println("S ->");
        lang = input.next();
        input.nextLine();
        Language[0] = lang;
        AlphabetHolder[0] = 'S';


        int mylength = lang.length();
        int count = 1;

        System.out.println("How many other Variables would you like to enter? ");
        mysize = input.nextInt();

        for (int j = 0; j < mysize; j++)
        {
            AlphabetHolder[count++] = alphabet[j];
        }

        //PrintLanaguague();
        int track = 1;
        while (!(AlphabetHolder[track] == '\u0000'))
        {

            System.out.println(AlphabetHolder[track] + "-> ");
            lang2 = input.next();
            Language[track] = lang2;
            input.nextLine(); //Clears the buffer0
            track++;

            int mylength2 = lang2.length();
            //System.out.println("SIZE: " + mylength2);
            String hold;
            for (int l = 0; l < lang2.length(); l++)
            {
                if (Character.isUpperCase(lang2.charAt(l)))
                {
                    hold = String.valueOf(lang2.charAt(l));
                    if (!(new String(AlphabetHolder).contains(hold)))
                    {
                        AlphabetHolder[count] = lang2.charAt(l);
                        count++;
                    }
                }
            }

            PrintLanaguague();
        }
    }

    static void PrintLanaguague()
    {

        for (int k = 0; k < AlphabetHolder.length; k++)
        {
            if (AlphabetHolder[k] == ' ' || Language[k] == null || Language[k].equals(" "))
            {
                continue;
            }
            else
            {
                System.out.println(AlphabetHolder[k] + "-> " + Language[k]);
            }
        }
    }
}
