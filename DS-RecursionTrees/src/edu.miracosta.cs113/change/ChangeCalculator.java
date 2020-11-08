package edu.miracosta.cs113.change;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 *
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 *
 * NOTE: An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to
 * verify that all given coin combinations are unique.
 */
public class ChangeCalculator {

    static int[] possible = new int[]{0,0,0,0};
    static ArrayList<String> combos = new ArrayList<>();

    /**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
     *
     * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
    public static int calculateChange(int cents) { // cent = 41
        // TODO:
        // Implement a recursive solution following the given documentation.

        if(cents > 0){

            // Penny
            if(cents < 5){
                possible[3] += cents;
                int result = calculateChange(0);
                possible[3] -= cents;
            }
            possible[3] += 5;
            int result = calculateChange(cents - 5);
            possible[3] -= 5;

            // Nickel
            if(cents >= 5){
                possible[2] ++;
                result += calculateChange(cents - 5);
                possible[2] --;
            }

            // Dime
            if(cents >= 10){
                possible[1] ++;
                result += calculateChange(cents - 10);
                possible[1] --;
            }

            // Quarter
            if(cents >= 25){
                possible[0] ++;
                result += calculateChange(cents - 25);
                possible[0] --;
            }
            return result;
        }
        else {
            String input = "[" + possible[0] + ", " + possible[1] + ", " + possible[2] + ", " + possible[3] + "]";
            if(combos.contains(input)){     // if repeat, don't count and add
                return 0;
            }
            else{
                combos.add(input);          // if not, count as 1 and add combo
                return 1;
            }
        }
    }

    /**
     * Calls upon calculateChange(int) to calculate and print all possible unique combinations of quarters, dimes,
     * nickels, and pennies that equal the given value in cents.
     *
     * Similar to calculateChange's function in printing each combination to the console, this method will also
     * produce a text file named "CoinCombinations.txt", writing each combination to separate lines.
     *
     * @param cents a monetary value in cents
     */
    public static void printCombinationsToFile(int cents) {
        // TODO:
        // This when calculateChange is complete. Note that the text file must be created within this directory.
        combos = new ArrayList<>();
        possible = new int[]{0,0,0,0};

        calculateChange(cents);

        try {
            File file = new File(System.getProperty("user.dir") + "/src/edu.miracosta.cs113/change/CoinCombinations.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            for(int i = 0; i < combos.size(); i ++){
                pw.println(combos.get(i));
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

} // End of class ChangeCalculator