import java.util.LinkedList;
import java.util.Scanner;

public class Test implements Polynomial{

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        LinkedList<Term> one = new LinkedList<>();
        LinkedList<Term> two = new LinkedList<>();
        int choice = 0;
        int coefficient, exponent;

        do {
            System.out.println( "Please select a action: (input the index)\n" +
                                "1. Edit the first polynomial\n" +
                                "2. Edit the second polynomial\n" +
                                "3. Adding the two polynomials\n" +
                                "4. Exit");
            choice = scanner.nextInt();

            switch(choice){
                case 1:
                    System.out.println( "Please select a action: (input the index)\n" +
                                        "1. Add\n" +
                                        "2. Clear");
                    choice = scanner.nextInt();
                    if(choice == 1){
                        System.out.println( "Please input the term in the format\n"+"" +
                                            "ax^b,  a as coefficient and b as exponent"+
                                            "2x^0 + 3x^1 etc,  connect terms with \"+\", Don't care about spaces");
                        String input = scanner.nextLine();
                        scanner.nextLine();
                        one = Polynomial.addTerm(one,input);
                    }
                    else if(choice == 2){
                        Polynomial.clear(one);
                        System.out.println("Success\n");
                    }
                    break;
                case 2:
                    System.out.println( "Please select a action: (input the index)\n" +
                            "1. Add\n" +
                            "2. Clear");
                    choice = scanner.nextInt();
                    if(choice == 1){
                        System.out.println( "Please input the term in the format\n"+"" +
                                "ax^b,  a as coefficient and b as exponent"+
                                "2x^0 + 3x^1 etc,  connect terms with \"+\", Don't care about spaces");
                        String input = scanner.nextLine();
                        scanner.nextLine();
                        two = Polynomial.addTerm(two,input);
                    }
                    else if(choice == 2){
                        Polynomial.clear(two);
                        System.out.println("Success\n");
                    }
                    break;
                case 3:
                    System.out.println("The result is: "+Polynomial.addTerm(one,two));
            }

        }while(choice != 4);

    }


}
