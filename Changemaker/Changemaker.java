//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Change Maker
// Course: CS 300 Spring 2023
//
// Author: Sharath Bhattiprolu
// Email: sbhattiprolu@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Manan Chand
// Partner Email: mtchand@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: TAs at the CS 1350 (there were three did not get their names)
// Online Sources: ChatGPT - helped with understanding how to start the minCoins method and what it should return
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

public class Changemaker {
    /**
     * find the number of ways to make change for a certain value
     *
     * @param denominations  an array that has describes the value of each type of coin in the register
     * @param coinsRemaining quantity of each type of coin
     * @param value          total amount of change to be dispensed
     * @return
     */
    public static int count(int[] denominations, int[] coinsRemaining, int value) {

        if (value == 0) {
            return 1;
        }

        if (value < 0) {
            return 0;
        }

        int numWays = 0;

        for (int i = 0; i < denominations.length; i++) {
            if (coinsRemaining[i] > 0 && denominations[i] <= value) {
                coinsRemaining[i]--;
                numWays += count(denominations, coinsRemaining, value - denominations[i]);
                coinsRemaining[i]++;
            }
        }

        return numWays;
    }

    /**
     * a recursive method that finds the smallest combination of coins from the denominations array to return to the
     * customer
     *
     * @param denominations  an array that has describes the value of each type of coin in the register
     * @param coinsRemaining quantity of each type of coin
     * @param value          total amount of change to be dispensed
     * @return minCoinsNeeded - an int value with the smallest number of coins used to return the change,
     * if not possible will return -1
     */
    public static int minCoins(int[] denominations, int[] coinsRemaining, int value) {
        int minCoinsNeeded = -1;
        int i = 0;
        //System.out.println("value is: " + value);
        if (value == 0)
            return 0;
        if (value < 0 || denominations.length == 0 || coinsRemaining.length == 0)
            return -1;
        for (i = denominations.length - 1; i >= 0; i--) {
            if (denominations[i] > value) {
                continue;
            }
            // coinsRemaining[i] because the i of that and denominations correspond
            if (coinsRemaining[i] <= 0) {
                continue;
            }
            //System.out.println("using " + denominations[i]);
            coinsRemaining[i]--;
            int coinsNeeded = minCoins(denominations, coinsRemaining, value - denominations[i]);
            if (coinsNeeded == -1) {
                continue;
            }
            coinsRemaining[i]++;
            if (minCoinsNeeded == -1 || minCoinsNeeded > coinsNeeded + 1) {
                minCoinsNeeded = coinsNeeded + 1;
            }
        }
        //System.out.println("minCoinsNeeded: " + minCoinsNeeded);
        return minCoinsNeeded;
    }

    /**
     * Checks whether this CoinChangeSolution object is equal to a given object.
     *
     * @param coins - an array that holds the number of coins and its values.
     * @return the sum of all the values in the array.
     */
    private static int sum(int[] coins) {
        int sum = 0;
        for (int i = 0; i < coins.length; i++) {
            sum += coins[i];
        }
        return sum;
    }

    /**
     * @param denominations  an array that has describes the value of each type of coin in the register
     * @param coinsRemaining quantity of each type of coin
     * @param value          the number we to find the change for
     * @return coinsUsed - an array that holds the number of coins of each denomination that adds up to the change value
     */
    public static int[] makeChange(int[] denominations, int[] coinsRemaining, int value) {
        int[] coinsUsed = new int[denominations.length];

        if (value == 0) {
            return coinsUsed;
        }
        if (value < 0 || denominations.length == 0 || coinsRemaining.length == 0) {
            return null;
        }

        int minCoinsNeeded = -1;
        int i = 0;
        for (i = denominations.length - 1; i >= 0; i--) {
            if (denominations[i] > value || coinsRemaining[i] <= 0) {
                continue;
            }

            coinsRemaining[i]--;
            int[] coinsUsedRecursive = makeChange(denominations, coinsRemaining, value - denominations[i]);
            if (coinsUsedRecursive == null) {
                coinsRemaining[i]++;
                continue;
            }

            int coinsNeeded = sum(coinsUsedRecursive) + 1;
            if (minCoinsNeeded == -1 || coinsNeeded < minCoinsNeeded) {
                minCoinsNeeded = coinsNeeded;
                coinsUsed = coinsUsedRecursive.clone();
                coinsUsed[i]++;
            }

            coinsRemaining[i]++;
        }

        if (minCoinsNeeded == -1) {
            return null;
        }
        return coinsUsed;
    }

}
