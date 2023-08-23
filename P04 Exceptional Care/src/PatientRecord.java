//
// Title:    PatientRecord
// Course:   CS 300 Spring 2023
//
// Author:   Sharath Bhattiprolu
// Email:    sbhattiprolu@wisc.edu email address
// Lecturer: Hobbes LeGault
//
/////////////////////////////////////////////////////////////////////////////////
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////
public class PatientRecord {
    private int triage;
    public static final int RED = 0;
    public static final int YELLOW = 1;
    public static final int GREEN = 2;
    private static int patientCounter = 1;
    private char gender;
    private int age;
    private int orderOfArrival;
    private boolean hasBeenSeen;
    public final int CASE_NUMBER;


//    public enum priority {
//        RED
//    }

    /**
     * creates a five-digit case number for this patient from the reported gender and age.
     *
     * @param gender A single character that represents the patients reported gender
     * @param age    the age of the patient in years
     * @return The patients 5 digit case number
     */
    public static int generateCaseNumber(char gender, int age) {
        int caseNumber = 0;
        int Gender = 0;
        //int numPatient = 0;
        if (gender == 'F')
            Gender = 1;
        else if (gender == 'M')
            Gender = 2;
        else if (gender == 'X')
            Gender = 3;
        else
            Gender = 4;
        age = age % 100;

        caseNumber = Integer.parseInt(Integer.toString(Gender) + String.format("%02d", age) +
                String.format("%02d", patientCounter));
        patientCounter++;
        //System.out.println("Gender: " + gender);
        return caseNumber;
    }

    /**
     * makes a new patient record and assigns it a CASE_NUMBER
     *
     * @param gender - a single-character representation of this patient's reported gender
     * @param age    - the age of this patient in years
     * @param triage - the triage level of this patient
     */
    public PatientRecord(char gender, int age, int triage) {
        if (triage != RED && triage != GREEN && triage != YELLOW)
            throw new IllegalArgumentException("Invalid triage value!");
        this.gender = gender;
        this.age = age;
        this.triage = triage;
        this.orderOfArrival = patientCounter;
        this.CASE_NUMBER = generateCaseNumber(gender, age);
        this.hasBeenSeen = false; // check on this if it should be returning false and where it changes
    }

    /**
     * Sets patientCounter back to 1;
     */
    public static void resetCounter() {
        patientCounter = 1;
    }

    /**
     * gets the patients triage
     */
    public int getTriage() {
        return triage;
    }

    /**
     * gets the patients gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * gets the patients age
     */
    public int getAge() {
        return age;
    }

    /**
     * gets the patients arrival order number
     */
    public int getArrivalOrder() {
        return orderOfArrival;
    }

    /**
     * if the patient has been marked as seen, then it returns true and false otherwise
     *
     * @return true if patient has been seen and false otherwise
     */
    public boolean hasBeenSeen() {
        return hasBeenSeen;
    }

    /**
     * marks the patient as seen
     */
    public void seePatient() {
        hasBeenSeen = true;
    }

    /**
     * Creates and returns a string of this patients record and the Case number,age,gender,triage.
     *
     * @return A string of this Patients Record
     */
    @Override
    public String toString() {
        String color = null;
        String caseNum = Integer.toString(CASE_NUMBER);
        String ageGen = Integer.toString(age);
        if (triage == 0)
            color = "RED";
        else if (triage == 1)
            color = "YELLOW";
        else if (triage == 2)
            color = "GREEN";
        String PatientRecord = caseNum + ": " + ageGen + gender + " (" + color + ")";
        return PatientRecord;
    }

////    public static void main(String[] args) {
////        PatientRecord PR = new PatientRecord('F', 22, YELLOW);
////        System.out.println(PR.toString());
//    }
}

