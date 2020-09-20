package edu.miracosta.cs113;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

// TestClue.java : Eliminate all error options one by one

public class TestClue {

    /*
     * ALGORITHM:
     *
     * PROMPT "Which theory to test? (1, 2, 3[random]): "
     * READ answerSet
     * INSTANTIATE jack = new AssistantJack(answerSet)
     * DO
     *      weapon = random int between 1 and 6
     *      location = random int between 1 and 10
     *      murder = random int between 1 and 6
     *      solution = jack.checkAnswer(weapon, location, murder)
     * WHILE solution != 0
     *
     * OUTPUT "Total checks = " + jack.getTimesAsked()
     * IF jack.getTimesAsked() is greater than 20 THEN
     *      OUTPUT "FAILED"
     * ELSE
     *      OUTPUT "PASSED"
     * END IF
     */

    public static void main(String args[]) {

        int answerSet, solution = -1, murder = 1, weapon = 1, location = 1;

        Theory answer;
        AssistantJack jack;
        Scanner keyboard = new Scanner(System.in);

        // INPUT
        System.out.print("Which theory would like you like to test? (1, 2, 3[random]): ");
        answerSet = keyboard.nextInt();
        keyboard.close();

        //PROCESSING
        jack = new AssistantJack(answerSet);
        System.out.println(jack.getCorrectTheory());                                                                    // random result checking

        /**
        *   each time for each wrong value add 1 to try a new value.
        */
        do{
            solution = jack.checkAnswer(weapon, location, murder);

            if(solution == 1){
                weapon ++;
            }
            if(solution == 2){
                location ++;
            }
            if(solution == 3){
                murder ++;
            }
        }while(solution != 0);


        answer = new Theory(weapon, location, murder);

        // OUTPUT
        System.out.println("\nTotal Checks = " + jack.getTimesAsked() + ", \nSolution " + answer);

        if (jack.getTimesAsked() > 20) {
            System.out.println("FAILED!! You're a horrible Detective...");
        } else {
            System.out.println("WOW! You might as well be called Batman!");
        }

    }



}
