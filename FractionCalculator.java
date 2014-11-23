// Fraction Calculator by Annabel Jump for Programming in Java coursework 2
import java.util.Scanner;

public class FractionCalculator {
    public static void main(String[] args) {
        FractionCalculator calculator = new FractionCalculator();
        calculator.letsGo();
    }
    public void letsGo() {
        boolean finished = false;

        // Welcome message
        System.out.println("Welcome to Annabel Jump's fraction calculator!");
        System.out.println("Still not complete, but please check it out!");
        while (!finished) {
            System.out.println("Please enter your calculation:");
            Fraction fraction = new Fraction(0, 1);
            //Scanner to receive user input
            Scanner sc = new Scanner(System.in);
            String inputCalculation = sc.nextLine();
            //check if quit command given, if not, evaluate
            if (inputCalculation.equalsIgnoreCase("q") || inputCalculation.equalsIgnoreCase("quit")) {
                System.out.print("Goodbye");
                finished = true;
            } else {
                fraction = evaluate(fraction, inputCalculation);
                System.out.println("Your current answer is: " + fraction);
            }
        }
    }
        public Fraction evaluate(Fraction firstFraction, String inputString) {
            String storedOperator = "";
            boolean operatorStored = false;
            boolean isFirstFractionComplete = false;
            Fraction firstFraction = new Fraction(0, 1);
            Fraction secondFraction = new Fraction(0, 1);
            //split up string to parts so as can evaluate
            String delims = "[ ]+";
            String[] inputs = inputString.split(delims);
            for (int i = 0; i < inputs.length; i++) {
               // determines what sort of input is in array and acts accordingly
                if (anOperator(inputs[i])) {
                    storedOperator = inputs[i];
                    operatorStored = true;

                } else if (isAFraction(inputs[i]) && !operatorStored && !isFirstFractionComplete) {
                    //assigns values to the first fraction only if it is actually the first fraction
                    String x = inputs[i].substring(0);
                    String y = inputs[i].substring(2);
                    int a = Integer.parseInt(x);
                    int b = Integer.parseInt(y);
                    firstFraction.setNumerator(a);
                    firstFraction.setDenominator(b);
                    isFirstFractionComplete = true;

                } else if (isAFraction(inputs[i]) && operatorStored) {
                    //logically if the input is a fraction and an operator has been stored, it is not the first fraction
                    String x = inputs[i].substring(0);
                    String y = inputs[i].substring(2);
                    int a = Integer.parseInt(x);
                    int b = Integer.parseInt(y);
                    secondFraction.setNumerator(a);
                    secondFraction.setDenominator(b);
                    firstFraction = execute(firstFraction, secondFraction);

                } else if ((inputs[i].equalsIgnoreCase("a") || inputs[i].equalsIgnoreCase("abs"))
                        && isFirstFractionComplete) {
                    //returns absolute of fraction
                    firstFraction = absValue(firstFraction);

                } else if ((inputs[i].equalsIgnoreCase("n") || inputs[i].equalsIgnoreCase("neg"))
                        && isFirstFractionComplete) {
                    //negates fraction
                    firstFraction = negate(firstFraction);

                } else if ((inputs[i].equalsIgnoreCase("c") || inputs[i].equalsIgnoreCase("clear"))) {
                    //clears calculator
                    storedOperator = "";
                    operatorStored = false;
                    Fraction clearedFraction = new Fraction(0,1);
                    firstFraction = clearedFraction;
                    System.out.println("All values cleared.");

                } else if (isInteger(inputs[i])) {
                    //if input is none of above and is an integer, it is a whole number
                    int f = Integer.parseInt(inputs[i]);
                    Fraction wholeNumber = new Fraction(f, 1);
                    if (!isFirstFractionComplete) {
                        firstFraction = wholeNumber;
                    } else {
                        secondFraction = wholeNumber;
                        firstFraction = execute(firstFraction, secondFraction);
                    }

                } else {
                    //clears calculator as is invalid input if none of above applies
                    storedOperator = "";
                    operatorStored = false;
                    Fraction clearedFraction = new Fraction(0, 1);
                    firstFraction = clearedFraction;
                    System.out.println("Invalid input. Please start again.");
                }
            } return firstFraction;
        }

            public boolean anOperator(String s) {
                if (s.length() == 1) {
                    if (s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*")) {
                        return true;
                    }
                } else {
                    return false;
                }
            }

            public boolean isAFraction(String str) {
                if (str.length() == 3) {
                   if (str.charAt(1) == '/') {
                       return true;
                   }
                } else {
                    return false;
                }
            }

            public Fraction execute(Fraction fr1, Fraction fr2) {
                if (storedOperator == "+") {
                    return fr1.add(fr2);
                } else if (storedOperator == "-") {
                    return fr1.subtract(fr2);
                } else if (storedOperator == "/") {
                    return fr1.divide(fr2);
                } else if (storedOperator == "*") {
                    return fr1.multiply(fr2);
                } else {
                    return fr1;
                }
            }

            //isInteger method to determine if the string can be parsed as an integer
            public static boolean isInteger(String str) {
                int l = str.length();
                if (l == 0) {
                    return false;
                }
                int i = 0;
                if (str.charAt(0) == '-') {
                    if (l == 1) {
                    return false;
                    }
                i = 1;
                }
                for (; i < l; i++) {
                    char c = str.charAt(i);
                    if (c <= '/' || c >= ':') {
                    return false;
                    }
                }
                return true;
            }
        }

