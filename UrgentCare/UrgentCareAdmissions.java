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

/**
 * this class checks and manipulates the array regarding various changes in the patients ordering.
 */
public class UrgentCareAdmissions {
    public static final int RED = 0;
    public static final int YELLOW = 1;
    public static final int GREEN = 2;


    public enum priority {
        RED
    }


    /**
     * //A helper method to find the correct index to insert a new patient at the given triage level. This should be the
     *     // index AFTER the last index currently occupied by a patient at that level.
     * @param triage - the priority ranking of the patient
     * @param patientList -the current list of the patients waiting
     * @param size - the number of patients
     * @return the index of where the patient should be inserted
     */
    public static int getAdmissionIndex(int triage, int patientList[][], int size) {
        int i;
        //System.out.println(" patient list length: " + patientList.length);
        //System.out.println("size: " + size);
        if (size == 0)
            return 0;
        if (patientList.length == size)
            return -1;
// this checks the last value of the code and see if its less than value we are trying to input, if it is then
        if (patientList[size - 1][2] <= triage)
            return size;
        for (i = size - 1; i >= 0; i--) {
            //System.out.println("i = " + i + " " + patientList[i][2]);
            if (patientList[i][2] <= triage) {
                return i + 1;
            }
        }
        return 0;
    }
    /**
     * //adds a patient record to the correct spot
     * @param index - the priority ranking of the patient
     * @param patientRecord -the current list of the patients waiting
     * @param size - the number of patients
     * @param patientsList - the current number of patients
     * @return number of patients in patientsList after the method
     */
    public static int addPatient(int[] patientRecord, int index, int[][] patientsList, int size) {
        int i;
        if (patientsList.length == size) {
            return size;
        }
        if (index == -1 || index >= patientsList.length) {
            return size;
        }
        // System.out.println("index is: " + index);
        //System.out.println("size is: " + size);
        //System.out.println("length is: " + patientsList.length);
        for (i = size; i > index; i--) {
            patientsList[i] = patientsList[i - 1];
        }
        patientsList[i] = patientRecord;
        return size + 1;
    }

    /**
     * //Removes the patient at index 0
     * @param size - the number of patients
     * @param patientsList - the current number of patients
     * @return number of patients in patientsList after the method
     */
    public static int removeNextPatient(int[][] patientsList, int size) {
        if (size == 0) {
            return size;
        }
        for (int i = 0; i < size; i++) {
            patientsList[i] = patientsList[i + 1];
        }
        patientsList[size - 1] = null;
        return size - 1;
    }

    /**
     //Find the index of the patient given their case ID number
     * @param size - the number of patients
     * @param caseID - a five digit case number assigned to each patient
     * @param patientsList - the current number of patients
     * @return number of patients in patientsList after the method
     */
    public static int getPatientIndex(int caseID, int[][] patientsList, int size) {
        if (size == 0)
            return -1;
        for (int i = 0; i < size; i++) {
            if (caseID == patientsList[i][0]) {
                return i;
            }
        }
        return -1;
    }
    /**
     //Find the index of the patient given their case ID number
     * @param size - the number of patients
     * @param patientsList - the current number of patients
     * @return index number of patients in patientsList with the smallest value in their arrival integer or -1 if the
     * list is empty.
     */
    public static int getLongestWaitingPatientIndex(int[][] patientsList, int size) {
        int min;
        //System.out.println("Length: " + patientsList.length + "Size: " + size);
        if (size == 0) {
            return -1;
        }
        int minIndex = 0;
        min = patientsList[0][1];
        //cycles through the patientList and finds the patient with the lowest index value
        for (int i = 1; i < size; i++) {
            if (min > patientsList[i][1]) {
                min = patientsList[i][1];
                minIndex = i;
            }
        }
        return minIndex;
    }
    /**
     //Prints the number of total patients and how many are at each triage level.
     * @param size - the number of patients
     * @param patientsList - the current number of patients
     * @return A string summering the patients list
     */
    public static String getSummary(int[][] patientsList, int size) {
        int redCount = 0;
        int YellowCount = 0;
        int greenCount = 0;
        String s1 = new String();
        for (int i = 0; i < size; i++) {
            if (patientsList[i][2] == 0) {
                redCount = redCount + 1;
            }
            if (patientsList[i][2] == 1) {
                YellowCount = YellowCount + 1;
            }
            if (patientsList[i][2] == 2) {
                greenCount = greenCount + 1;
            }
        }
        s1 = "Total number of patients: " + size + "\nRED: " + redCount + "\nYELLOW: " + YellowCount + "\nGREEN: " +
                greenCount;
        // System.out.println(s1);
        return s1;
    }
}
