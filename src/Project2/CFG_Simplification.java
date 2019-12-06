package Project2;

        import java.util.Scanner;

/**
 * @author Sean de Silva, Jonathan Smart
 * @project CompilerConstruction Project2
 * @date 11/14/2019
 */

public class CFG_Simplification
{
    //private static final char deletme = 0;
    private static Scanner input = new Scanner(System.in);
    private static char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static String lang, lang2;
    private static char[] AlphabetHolder;
    private static int [] posHolder;
    private static char deletme;
    private static String[] Language;


    public static void main(String[] args)
    {
        CFG();
        USELESS();
        E_PRODUCTION();
    }

    private static void USELESS()
    {
        int mycount1=0;
        posHolder = new int [5];
        System.out.println("Now using the useless rules we have: ");

        int word = Language[0].length();
        String myLang = Language[0];
        int numcount = 0;

        for (int j = 1; j < 4; j++)
        {
            for (int k = 0; k < word; k++)
            {
                if (AlphabetHolder[j] == myLang.charAt(k))
                {
                    System.out.println("|" + AlphabetHolder[j]);
                    posHolder[mycount1] = j;
                    mycount1++;
                    break;
                }

            }
        }

        int numCount =1;

        for(int i =0; i< posHolder.length;i++) {
            if(numCount == posHolder[i]) {
                numCount++;
                continue;
            }
            else {
                deletme = AlphabetHolder[numCount];
            }
        }


        System.out.println("num count: "+ deletme +" , "+ AlphabetHolder[numCount]);

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
                String[] arrOfStr = Language[2].split("\\|",5);
                System.out.println("in the if");

                for (String a : arrOfStr) {
                    System.out.println(a + " | ");
                }

                int countholder = -1;

                for(int i =0; i< arrOfStr.length; i++) {
                    for(int j=0; j < arrOfStr[i].length(); j++) {
                        if(AlphabetHolder[numCount] == arrOfStr[i].charAt(j)) {
                            countholder = i;
                        }
                    }
                }

                arrOfStr[countholder] = "";
                Language[2] = " ";
                for(int i =0; i< arrOfStr.length; i++) {
                    System.out.println("** "+arrOfStr[i] + " **");
                    Language[2]+= arrOfStr[i];
                }


                break;

            }

        }
        PrintLanaguague();
        System.out.println("REmoved\n");

        for (int k = 1; k < numCount+1; k++)
        {
            word = Language[k].length();

            for (int n = 0; n < word; n++)
            {
                if (AlphabetHolder[k] == myLang.charAt(n))
                {
                    if ('0' == myLang.charAt(word-1))
                    {
                        break;
                    }
                    else
                    {deletme = AlphabetHolder[k];
                        Language[k]= " ";

                        System.out.print("NEeds deletion: "+ deletme);
                    }
                }
            }
        }


        word = Language[0].length();
        myLang = Language[0];
        for (int a = 1; a < numCount; a++) {
            for (int b = 0; b < word; b++) {
                if (AlphabetHolder[a] == myLang.charAt(b))
                {
                    System.out.print("Balls deep\n");
                    if (AlphabetHolder[a] == '\u0000')
                    {
                        break;
                    }
                    else {
                        String[] arrOfStr2 = Language[0].split("\\|",5);
                        for (String a2 : arrOfStr2) {
                            System.out.println(a2 + " | ");
                        }

                        int countholder = -1;
                        for(int i =0; i< arrOfStr2.length; i++) {
                            System.out.print("Balls deeper\n");

                            for(int j=0; j < arrOfStr2[i].length(); j++) {
                                if(deletme == arrOfStr2[i].charAt(j)) {
                                    countholder = i;
                                }
                            }
                        }

                        if(countholder > 0) {
                            arrOfStr2[countholder] = "";
                            Language[0] = " ";
                            for(int i =0; i< arrOfStr2.length; i++) {
                                System.out.println("**** "+arrOfStr2[i] + " ****");
                                Language[0]+= arrOfStr2[i] + "|";
//                      	if(arrOfStr[i+1] != " ") {
//                      		Language[0]+=  "|";
//                      	}
                            }
                        }
                    }
                }

            }

        }


        PrintLanaguague();
//        for (int k = 0; k < AlphabetHolder.length; k++)
//        {
//        	if (AlphabetHolder[k] == ' ' || Language[k] == " " || Language[k] == null )
//            {
//                continue;
//            }
//            else
//            System.out.println(AlphabetHolder[k] + "-> " + Language[k]);
//        }
//



    }

    private static void E_PRODUCTION()
    {

    }


    private static void CFG()
    {
        AlphabetHolder = new char[26];
        Language = new String[26];
        // TODO Auto-generated method stub
        System.out.println("aA|aB, aaA|0, bB|bbC, B");
        System.out.println("Enter a CFG language");
        System.out.println("S ->");
        lang = input.next();
        input.nextLine();
        Language[0] = lang;
        AlphabetHolder[0] = 'S';


        int mylength = lang.length();
        int count = 1;

        System.out.println("How mmny Variables would you like to enter? ");
        int mysize = input.nextInt();

        for (int j = 0; j < mysize; j++)
        {
            AlphabetHolder[count++]= alphabet[j];
        }

/*
        for (int j = 0; j < mylength; j++)
        {
            if (Character.isUpperCase(lang.charAt(j)))
            {
                String hold = String.valueOf(lang.charAt(j));
                if (!(new String(AlphabetHolder).contains(hold)))
                {
                    // do something
                    AlphabetHolder[count] = lang.charAt(j);
                    count++;
                }
//            	AlphabetHolder[count] = lang.charAt(j);
//            	count++;
            }
        }

     */
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
                        // do something
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
            if (AlphabetHolder[k] == ' ' || Language[k] == null || Language[k] == " ")
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
