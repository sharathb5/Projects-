//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Urgent Care Admissions
// Course:   CS 300 Spring 2023
//
// Author:   Sharath Bhattiprolu
// Email:    sbhattiprolu@wisc.edu email address
// Lecturer:  Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.sql.SQLOutput;
import java.util.Arrays; // consider using Arrays.deepEquals() to test the contents of a 2D array

// Javadoc style class header comes here
public class UrgentCareAdmissionsTester {

    /**
     * This test method is provided for you in its entirety, to give you a model for the rest of this
     * class. This method tests the getAdmissionIndex() method on a non-empty, non-full array of
     * patient records which we create and maintain here.
     * <p>
     * This method tests three scenarios:
     * <p>
     * 1. Adding a patient with a HIGHER triage priority than any currently present in the array.
     * To do this, we create an array with no RED priority patients and get the index to add a RED
     * priority patient. We expect this to be 0, so if we get any other value, the test fails.
     * <p>
     * 2. Adding a patient with a LOWER triage priority than any currently present in the array.
     * To do this, we create an array with no GREEN priority patients and get the index to add a
     * GREEN priority patient. We expect this to be the current size of the oversize array, so if
     * we get any other value, the test fails.
     * <p>
     * 3. Adding a patient with the SAME triage priority as existing patients. New patients at the
     * same priority should be added AFTER any existing patients. We test this for all three triage
     * levels on an array containing patients at all three levels.
     *
     * @return true if and only if all test scenarios pass, false otherwise
     * @author hobbes
     */

    /**
     * This helper method test various scenarios of to see if the code is able to follow procedures.
     * @return it returns true if the tests passes or false if it does not go through.
     */
    public static boolean testGetIndex() {

        // The non-empty, non-full oversize arrays to use in this test.
        // Note that we're using the UrgentCareAdmissions named constants to create these test records,
        // rather than their corresponding literal int values.
        // This way if the numbers were to change in UrgentCareAdmissions, our test will still be valid.

        int[][] patientsListAllLevels = new int[][]{
                {32702, 3, UrgentCareAdmissions.RED},
                {21801, 2, UrgentCareAdmissions.YELLOW},
                {22002, 4, UrgentCareAdmissions.YELLOW},
                {11901, 5, UrgentCareAdmissions.YELLOW},
                {31501, 1, UrgentCareAdmissions.GREEN},

                null, null, null
        };

        int allLevelsSize = 5;

        int[][] patientsListOnlyYellow = new int[][]{
                {21801, 2, UrgentCareAdmissions.YELLOW},
                {22002, 4, UrgentCareAdmissions.YELLOW},
                {11901, 5, UrgentCareAdmissions.YELLOW},
                null, null, null, null, null
        };
        int onlyYellowSize = 3;


        // scenario 1: add a patient with a higher priority than any existing patient
        {
            int expected = 0;
            int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED,
                    patientsListOnlyYellow, onlyYellowSize);
            //System.out.println("expected #1 " + expected);
            //System.out.println("actual " + actual);
            if (expected != actual) return false;
        }

        // scenario 2: add a patient with a lower priority than any existing patient
        {
            int expected = onlyYellowSize;
            int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN,
                    patientsListOnlyYellow, onlyYellowSize);
            //System.out.println("expected #2 " + expected);
            //System.out.println("actual " + actual);
            if (expected != actual) return false;
        }

        // scenario 3: verify that a patient with the same priority as existing patients gets
        // added after all of those patients
        {
            int expected = 1;
            int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED,
                    patientsListAllLevels, allLevelsSize);
            //System.out.println("expected #3 " + expected);
            //System.out.println("actual " + actual);
            if (expected != actual)
                return false;

            expected = 4;
            actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.YELLOW,
                    patientsListAllLevels, allLevelsSize);
            //System.out.println("expected #4 " + expected);
            //System.out.println("actual " + actual);
            if (expected != actual) return false;

            expected = allLevelsSize;
            actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN,
                    patientsListAllLevels, allLevelsSize);
            //System.out.println("expected #5 " + expected);
            //System.out.println("actual " + actual);
            if (expected != actual) return false;
        }

        // and finally, verify that the arrays were not changed at all
        {
            int[][] allLevelsCopy = new int[][]{
                    {32702, 3, UrgentCareAdmissions.RED},
                    {21801, 2, UrgentCareAdmissions.YELLOW},
                    {22002, 4, UrgentCareAdmissions.YELLOW},
                    {11901, 5, UrgentCareAdmissions.YELLOW},
                    {31501, 1, UrgentCareAdmissions.GREEN},
                    null, null, null
            };
            if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy)) return false;

            int[][] onlyYellowCopy = new int[][]{
                    {21801, 2, UrgentCareAdmissions.YELLOW},
                    {22002, 4, UrgentCareAdmissions.YELLOW},
                    {11901, 5, UrgentCareAdmissions.YELLOW},
                    null, null, null, null, null
            };
            if (!Arrays.deepEquals(patientsListOnlyYellow, onlyYellowCopy)) return false;
        }

        return true;
    }


    // Tests the behavior of the addPatient method using a non-empty, non-full array. Each test
    // should verify that the returned size is as expected and that the array has been updated
    // (or not) appropriately
    /**
     *  tests to see if the method is able to add patietns to the array in various scenarios.
     * @return true if the code is able to add the patient to the array and false if its not able to
     */
    public static boolean testAddPatient() {
        // (1) add a patient to the END of the patientsList
        int[][] patientsListAllLevels = new int[][]{
                {32702, 3, UrgentCareAdmissions.RED},
                {21801, 2, UrgentCareAdmissions.YELLOW},
                {22002, 4, UrgentCareAdmissions.YELLOW},
                {11901, 5, UrgentCareAdmissions.YELLOW},
                {31501, 1, UrgentCareAdmissions.GREEN},

                null, null, null, null, null, null
        };
        //System.out.println("-End-");
        int size = 5;
        int[] patientRecord = {42343, 6, UrgentCareAdmissions.GREEN};
        int index = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, patientsListAllLevels, size);
        int newSize = UrgentCareAdmissions.addPatient(patientRecord, index, patientsListAllLevels, size);
        if (newSize != 6) {
            return false;
        }
        // (2) add a patient to the MIDDLE of the patientsList

        //System.out.println("-Middle-");
        size = 6;
        int[] patientRecord2 = {12345, 7, UrgentCareAdmissions.RED};
        index = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, patientsListAllLevels, size);
        int newSize2 = UrgentCareAdmissions.addPatient(patientRecord2, index, patientsListAllLevels, size);
        if (newSize2 != 7) {
            return false;
        }
        // (3) add a patient using an invalid (out-of-bounds) index
        size = 7;
        index = 22;
        int[] patientRecord3 = {12376, 8, UrgentCareAdmissions.RED};
        int newSize3 = UrgentCareAdmissions.addPatient(patientRecord3, index, patientsListAllLevels, size);
        if (newSize3 != 7) {
            return false;
        }
        //for (int i = 0; i < newSize; i++) {
        //System.out.println(patientsListAllLevels[i][0] + " " + patientsListAllLevels[i][2]);

        return true;

    }

    // Tests the behavior of the removeNextPatient method using a non-empty, non-full array. Each
    // test should verify that the returned size is as expected and that the array has been updated
    // (or not) appropriately
    /**
     *  tests to see if the method is able to remove patietns to the array in various scenarios.
     * @return true if the code is able to add the patient to the array and false if its not able to
     */
    public static boolean testRemovePatient() {
        int[][] patientsListAllLevels = new int[][]{
                {32702, 3, UrgentCareAdmissions.RED},
                {21801, 2, UrgentCareAdmissions.YELLOW},
                {22002, 4, UrgentCareAdmissions.YELLOW},
                {11901, 5, UrgentCareAdmissions.YELLOW},
                {31501, 1, UrgentCareAdmissions.GREEN},

                null, null, null, null, null, null
        };
        // (1) remove a patient from a patientsList containing more than one record
        int size = 5;
        size = UrgentCareAdmissions.removeNextPatient(patientsListAllLevels, size);
        if (size != 4) {
            return false;
        }

        // (2) remove a patient from a patientsList containing only one record
        int[][] patientsListAllLevels2 = new int[][]{
                {32702, 3, UrgentCareAdmissions.RED},
                null, null, null, null, null, null
        };
        size = 1;
        size = UrgentCareAdmissions.removeNextPatient(patientsListAllLevels2, size);
        if (size != 0) {
            return false;
        }
        //System.out.println("size: " + size);
        return true;
    }
    /**
     Tests the behavior of the getPatientIndex method using a non-empty, non-full array.
     * @return true if the code is able to add the patient to the array and false if its not able to
     */
    //
    public static boolean testGetPatientIndex() {
        // (1) look for a patient at the end of the list
        int[][] patientsListAllLevels = new int[][]{
                {32702, 3, UrgentCareAdmissions.RED},
                {21801, 2, UrgentCareAdmissions.YELLOW},
                {22002, 4, UrgentCareAdmissions.YELLOW},
                {11901, 5, UrgentCareAdmissions.YELLOW},
                {31501, 1, UrgentCareAdmissions.GREEN},

                null, null, null, null, null, null
        };
        int size = 5;
        int find = UrgentCareAdmissions.getPatientIndex(31501, patientsListAllLevels, size);
        if (find != 4) {
            return false;
        }

        // (2) look for a patient in the middle of the list
        find = UrgentCareAdmissions.getPatientIndex(22002, patientsListAllLevels, size);
        if (find != 2) {
            return false;
        }
        // (3) look for a patient not present in the list
        find = UrgentCareAdmissions.getPatientIndex(56776, patientsListAllLevels, size);
        if (find != -1) {
            return false;
        }
        return true;
    }
    /**
     // Tests the getLongestWaitingPatientIndex method using a non-empty, non-full array. When
     // designing these tests, recall that arrivalOrder values will all be unique!
     * @return true if the code is able to add the patient to the array and false if its not able to

     */
    public static boolean testLongestWaitingPatient() {
        // (1) call the method on a patientsList with only one patient
        int[][] patientsListAllLevels = new int[][]{
                {32702, 3, UrgentCareAdmissions.RED},
                null, null, null, null, null, null
        };
        int size = 1;
        int wait = UrgentCareAdmissions.getLongestWaitingPatientIndex(patientsListAllLevels, size);
        if (wait != 0) {
            return false;
        }
        // (2) call the method on a patientsList with at least three patients
        int[][] patientsListAllLevels2 = new int[][]{
                {32702, 3, UrgentCareAdmissions.RED},
                {21801, 2, UrgentCareAdmissions.YELLOW},
                {22002, 4, UrgentCareAdmissions.YELLOW},
                {11901, 5, UrgentCareAdmissions.YELLOW},
                {31501, 1, UrgentCareAdmissions.GREEN},

                null, null, null, null, null, null
        };
        size = 5;
        wait = UrgentCareAdmissions.getLongestWaitingPatientIndex(patientsListAllLevels2, size);
        if (wait != 4) {
            return false;
        }
        return true;
    }

    /**
     // // Tests the edge case behavior of the UrgentCareAdmissions methods using empty and full arrays
     //    // (1) test getAdmissionIndex using an empty patientsList array and any triage level
     * @return true if the code is able to add the patient to the array and false if its not able to

     */

    public static boolean testEmptyAndFullArrays() {
        int[][] patientsListAllLevels = new int[][]{
                null, null, null, null, null, null
        };
        int size = 0;
        {
            int expected = 0;
            int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, patientsListAllLevels, size);
            //System.out.println("expected #1 " + expected);
            //System.out.println("actual " + actual);
            //System.out.println("FAILED:");
            if (expected != actual) {
                return false;
            }
        }
        // (2) test getAdmissionIndex using a full patientsList array and any triage level
        int[][] patientsListOnlyYellow = new int[][]{
                {21801, 2, UrgentCareAdmissions.YELLOW},
                {22002, 4, UrgentCareAdmissions.YELLOW},
                {11901, 5, UrgentCareAdmissions.YELLOW},
        };
        int onlyYellowSize = 3;
        int expectedFull = -1;
        int actualFull = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.YELLOW, patientsListOnlyYellow, onlyYellowSize);
        if (expectedFull != actualFull) {
           // System.out.println("ERROR");
            //System.out.println("actual: " + actualFull);
            return false;
        }

        // (3) test addPatient using a full patientsList array
     int[][] patientsListOnlyYellowAdd = new int[][]{
                {21801, 1, UrgentCareAdmissions.YELLOW},
                {22002, 2, UrgentCareAdmissions.YELLOW},
                {11901, 3, UrgentCareAdmissions.YELLOW},
        };
        int onlyYellowSizeAdd = 3;
        int expectedFullAdd = onlyYellowSizeAdd;
        int[] patientRecord = {12345, 4, UrgentCareAdmissions.YELLOW};
        int index = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.YELLOW, patientsListOnlyYellowAdd,
                size);
        //System.out.println("index: " + index);
        int actualFullAdd = UrgentCareAdmissions.addPatient(patientRecord, index, patientsListOnlyYellowAdd,
                onlyYellowSizeAdd);
        //System.out.println("Actual: " + actualFullAdd);
        if (expectedFullAdd != actualFullAdd) {
            return false;
        }
        // (4) test removeNextPatient using an empty patientsList array
        int[][] patientsListAllLevelsR = new int[][]{
        };
        int allLevelsSize = 0;
        size = UrgentCareAdmissions.removeNextPatient(patientsListAllLevelsR,size);
        if (size != 0)
            return false;
        // (5) test getPatientIndex using an empty patientsList array
        int[][] patientsListAllLevelsGPI = new int[][]{
        };
        int sizeGPI = 0;
        int find = UrgentCareAdmissions.getPatientIndex(31501, patientsListAllLevelsGPI, sizeGPI);
        if (find != -1) {
            return false;
        }
        // (6) test getLongestWaitingPatientIndex using an empty patientsList array
        int[][] patientsListAllLevelsLWPI = new int[][]{
                null, null, null, null, null, null
        };
        int sizeLWPI = 0;
        //System.out.println("TEST HEREEE");
        int wait = UrgentCareAdmissions.getLongestWaitingPatientIndex(patientsListAllLevelsLWPI,sizeLWPI);
        if (wait != -1) {
            return false;
        }
        return true;
    }
    /**
     // Tests the getSummary method using arrays in various states
     * @return true if the code is able to add the patient to the array and false if its not able to

     */
    public static boolean testGetSummary() {
        // (1) test getSummary using an empty patientsList
        int[][] patientsListAllLevels = new int[][]{
                null, null, null, null, null, null
        };
        int size = 0;
        String expected = "Total number of patients: 0\nRED: 0\nYELLOW: 0\nGREEN: 0";
        String sum = UrgentCareAdmissions.getSummary(patientsListAllLevels, size);
        if (!sum.equals(expected)) {
            //System.out.println(expected);
            //System.out.println("failed");
            System.out.println("sum1: " + sum);
            System.out.println("expected1: " + expected);
            return false;
        }
        // (2) test getSummary using a patientsList with multiple patients at a single triage level
        int[][] patientsListAllLevelsGS = new int[][]{
                {32702, 3, UrgentCareAdmissions.RED},
                {21801, 2, UrgentCareAdmissions.YELLOW},
                {22002, 4, UrgentCareAdmissions.YELLOW},
                {11901, 5, UrgentCareAdmissions.YELLOW},
                {31501, 1, UrgentCareAdmissions.GREEN},
                null, null, null, null, null, null
        };
        size = 5;
        String expected2 = "Total number of patients: 5 \nRED: 1 \nYELLOW: 3 \nGREEN: 1";
        String sum2 = UrgentCareAdmissions.getSummary(patientsListAllLevelsGS, size);
        if (!sum.equals(expected)) {
            //System.out.println(expected);
            //System.out.println("failed");
            System.out.println(sum2);
            System.out.println(expected2);
            return false;
        }
        // (3) test getSummary using a patientsList with patients at all triage levels
        int[][] patientsListAllLevelsG = new int[][]{
                {32702, 3, UrgentCareAdmissions.RED},
                {21801, 2, UrgentCareAdmissions.YELLOW},
                {31501, 1, UrgentCareAdmissions.GREEN},
                null, null, null, null, null, null
        };
        size = 3;
        String expected3 = "Total number of patients: 3 \nRED: 1 \nYELLOW: 1 \nGREEN: 1";
        String sum3 = UrgentCareAdmissions.getSummary(patientsListAllLevelsGS, size);
        if (!sum.equals(expected)) {
            //System.out.println(expected);
            //System.out.println("failed");
            System.out.println(sum3);
            System.out.println(expected3);
            return false;
        }
        return true;
    }

    /**
     * Runs each of the tester methods and displays their result
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Get Index: " + testGetIndex());
        System.out.println("Add Patient: " + testAddPatient());
        System.out.println("Remove Patient: " + testRemovePatient());
        System.out.println("Get Patient: " + testGetPatientIndex());
        System.out.println("Longest Wait: " + testLongestWaitingPatient());
        System.out.println("Empty/Full: " + testEmptyAndFullArrays());
        System.out.println("Get Summary: " + testGetSummary());
    }

}



