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
// Persons: TAs at the CS 1350 (there were three, did not get their names)
// Online Sources: ChatGPT - helped with understanding how to start the minCoins method and what it should return
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

public class ChangemakerTester {
    /**
     * a helper method to print out the array list
     *
     * @param arr - an array that holds the number of coins and its values.
     * @return each value within the array list
     */
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
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
     * This method tests the base cases of the CountBase method and returns true if passed, or false if a specific test
     * fails
     *
     * @return true if all the tests pass or if a test fails it will return an error message saying which test failed
     */
    public static boolean testCountBase() {
        // when the value is negative
        int[] denominations1 = {1, 5, 10, 25};
        int[] coinsRemaining1 = {2, 1, 1, 2};
        int value1 = -10;
        int expected1 = 0;
        int result1 = Changemaker.count(denominations1, coinsRemaining1, value1);
        if (result1 != expected1) {
            return false;
        }

        // when their arent enough coins to make change
        int[] denominations2 = {1, 5, 10, 25};
        int[] coinsRemaining2 = {0, 0, 0, 0};
        int value2 = 30;
        int expected2 = 0;
        int result2 = Changemaker.count(denominations2, coinsRemaining2, value2);
        if (result2 != expected2) {
            return false;
        }
        // when the value is 0
        int[] denominations3 = {1, 5, 10, 25};
        int[] coinsRemaining3 = {2, 1, 1, 2};
        int value3 = 0;
        int expected3 = 1;
        int result3 = Changemaker.count(denominations3, coinsRemaining3, value3);
        if (result3 != expected3) {
            return false;
        }

        System.out.println("testCBase passed.");
        return true;

    }

    /**
     * This method tests the recursive cases of the CountRecursive method and returns true if all tests passed,
     * or false if a specific test fails
     *
     * @return true if all the tests pass or if a test fails it will return an error message saying which test failed
     */
    public static boolean testCountRecursive() {
        // when there are at least 3 coin denoms
        int[] denominations1 = {1, 5, 10, 25};
        int[] coinsRemaining1 = {1, 1, 1, 1};
        int value1 = 36;
        int expected1 = 6;
        int result1 = Changemaker.count(denominations1, coinsRemaining1, value1);
        if (result1 != expected1) {
            return false;
        }

        // when the optimal option can have 2 possibilites
        int[] denominations2 = {2, 5, 7, 10};
        int[] coinsRemaining2 = {1, 1, 1, 1};
        int value2 = 12;
        int expected2 = 4;
        int result2 = Changemaker.count(denominations2, coinsRemaining2, value2);
        if (result2 != expected2) {
            return false;
        }

        // greedy choice of when the largest denom picked first
        int[] denominations3 = {1, 5, 6, 9};
        int[] coinsRemaining3 = {2, 1, 1, 1};
        int value3 = 11;
        int expected3 = 5;
        int result3 = Changemaker.count(denominations3, coinsRemaining3, value3);
        if (result3 != expected3) {
            return false;
        }

        System.out.println("testMinCRecursive passed.");
        return true;
    }

    /**
     * This method tests the base cases of the minCoin method and returns true if passed, or false if a specific test
     * fails
     *
     * @return true if all the tests pass or if a test fails it will return an error message saying which test failed
     */
    public static boolean testMinCoinsBase() {
        int[] denominations = {1, 5, 10, 25};
        int[] coinsRemaining = {4, 0, 2, 0};

        int result1 = Changemaker.minCoins(denominations, coinsRemaining, -1);
        if (result1 != -1) {
            System.out.println("Test case 1 failed");
            return false;

        }
        int result2 = Changemaker.minCoins(denominations, coinsRemaining, 25);
        // System.out.println("result 2: " + result2);
        if (result2 != -1) {
            System.out.println("Test case 2 failed");
            return false;
        }

        int result3 = Changemaker.minCoins(denominations, coinsRemaining, 0);
        if (result3 != 0) {
            System.out.println("Test case 3 failed");
            return false;
        }

        System.out.println("testMinCoinsBase passed.");
        return true;
    }

    /**
     * This method tests the recursive cases of the MinCoins method and returns true if all tests passed,
     * or false if a specific test fails
     *
     * @return true if all the tests pass or if a test fails it will return an error message saying which test failed
     */
    public static boolean testMinCoinsRecursive() {
        //uses 3 different coins
        int[] denominations = {1, 5, 10, 25};
        int[] coinsRemaining = {10, 1, 5, 2};
        int result1 = Changemaker.minCoins(denominations, coinsRemaining, 45);
        // System.out.println("result1 : " + result1);
        if (result1 != 3) {
            System.out.println("testMinCoinsRecursive1 failed");
            return false;
        }
        //2 + ways done
        int[] denominations2 = {1, 5, 10, 25};
        int[] coinsRemaining2 = {10, 1, 4, 2};
        int result2 = Changemaker.minCoins(denominations2, coinsRemaining2, 45);
        if (result2 != 3) {
            System.out.println("testMinCoinsRecursive2 failed");
            return false;
        }
        // Greedy solution
        int[] denominations3 = {1, 5, 6, 9};
        int[] coinsRemaining3 = {2, 1, 1, 1};
        int result3 = Changemaker.minCoins(denominations3, coinsRemaining3, 11);
        //System.out.println(result3);
        if (result3 != 2) {
            System.out.println("testMinCoinsRecursive3 failed");
            return false;
        }
        System.out.println("testMinCoinsRecursive passed.");
        return true;
    }

    /**
     * This method tests the base cases of the Make change method and returns true if passed, or false if a specifc test
     * fails
     *
     * @return true if all the tests pass or if a test fails it will return an error message saying which test failed
     */
    public static boolean testMakeChangeBase() {
        int i = 0;
        int[] denominations = {1, 5, 10, 25};
        int[] coinsRemaining = {4, 0, 2, 0};
        int[] result1;
        int[] result2;
        int[] result3;
        result1 = Changemaker.makeChange(denominations, coinsRemaining, -1);
        //System.out.println(result1);
        if (result1 != null) {
            System.out.println("Test case 1 failed");
            return false;
        }
        result2 = Changemaker.makeChange(denominations, coinsRemaining, 26);
        //printArray(result2);
        if (result2 != null) {
            System.out.println("Test case 2 failed");
            return false;
        }
        result3 = Changemaker.makeChange(denominations, coinsRemaining, 0);
        //printArray(result3);
        for (i = 0; i < result3.length; i++) {
            if (result3[i] != 0) {
                System.out.println("test case 3 failed");
                break;
            }
        }
        System.out.println("testMakeChangeBase passed.");
        return true;
    }

    /**
     * This method tests the recursive properties of the Make change method and returns true if passed,
     * or false if a specific test fails
     *
     * @return true if all the tests pass or if a test fails it will return an error message saying which test failed
     */
    public static boolean testMakeChangeRecursive() {

        int i = 0;
        int[] denominations = {1, 5, 10, 25};
        int[] coinsRemaining = {4, 5, 2, 2};
        int[] result1;
        //In which three coins are being used
        result1 = Changemaker.makeChange(denominations, coinsRemaining, 47);
        //printArray(result1);
        if (result1 == null) {
            System.out.println("Test case 1 failed");
            return false;
        }
        if (sum(result1) != 5) {
            System.out.println("unexpected sum: " + sum(result1));
            return false;
        }
        //when there are two optimal choices
        int[] denominations2 = {2, 5, 7, 10};
        int[] coinsRemaining2 = {1, 1, 1, 1};
        int[] result2;
        result2 = Changemaker.makeChange(denominations2, coinsRemaining2, 12);
        //printArray(result2);
        if (result2 == null) {
            System.out.println("Test case 2 failed");
            return false;
        }
        if (sum(result2) != 2) {
            System.out.println("unexpected sum: " + sum(result2));
            return false;
        }
//        //Greedy solution
        int[] denominations3 = {1, 6, 6, 10};
        int[] coinsRemaining3 = {2, 1, 1, 1};
        int[] result3;
        result3 = Changemaker.makeChange(denominations3, coinsRemaining3, 12);
        //printArray(result3);
        if (result3 == null) {
            System.out.println("Test case 3 failed");
            return false;
        }
        if (sum(result2) != 2) {
            System.out.println("unexpected sum: " + sum(result3));
            return false;
        }
        System.out.println("testMakeChangeRecursive passed.");
        return true;
    }

    public static void main(String[] args) {
        testCountBase();
        testCountRecursive();
        testMinCoinsBase();
        testMinCoinsRecursive();
        testMakeChangeBase();
        testMakeChangeRecursive();
    }
}


