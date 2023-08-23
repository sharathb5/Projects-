import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

//
// Title:    Exceptional Care
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
public class ExceptionalCareAdmissions {
    private PatientRecord[] patientsList;
    private int size;

    /**
     * Creates a new empty ExceptionalCareAdmissions object with the given capacity
     *
     * @param capacity
     */
    public ExceptionalCareAdmissions(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Invalid capacity value!");
        this.patientsList = new PatientRecord[capacity];
    }


    /**
     * Checks if the patientsList is full or not
     *
     * @return ture if full and false if not
     */
    public boolean isFull() {
        return size == patientsList.length;
    }

    /**
     * Checks the number of PatientRecord objects in the patientsList
     *
     * @return the number of patient record objects in the ExceptionalCareAdmissions object
     */
    public int size() {
        return size;
    }

    /**
     * Checks the number of Patients who have already been seen
     *
     * @return the number of patients who have been seen
     */
    public int getNumberSeenPatients() {
        int count = 0;
        for (int i = 0; i < patientsList.length; i++) {
            if (patientsList[i] != null && patientsList[i].hasBeenSeen()) {
                count++;
            }
        }
        return count;
    }

    /**
     * finds the index to insert the new patient record into patientsList,while maintaining triage priority order.
     *
     * @param rec -  the PatientRecord to be added to the list
     * @return the index of where the patient should be added.
     */
    public int getAdmissionIndex(PatientRecord rec) {
        int i;
        System.out.println(patientsList.length + " " +  size);
        System.out.println("Last " + patientsList[size-1].getTriage() + " " + rec.getTriage());
        if (size == 0)
            return 0;
        if (getNumberSeenPatients() == patientsList.length)
            throw new IllegalStateException("cleanPatientsList()");
        else if (patientsList.length == size)
            throw new IllegalStateException("Cannot admit new patients");
            // this checks the last value of the list and see if its less than value we are trying to input
         if (patientsList[size - 1].getTriage() <= rec.getTriage())
            return size;
        for (i = size - 1; i >= 0; i--) {
            if (patientsList[i].getTriage() <= rec.getTriage()) {
                return i+1;
            }
        }
        return 0;
    }

    /**
     * adds a patient at the provided position into the patientList without disrupting the rest of the order
     *
     * @param rec   -  the PatientRecord to be added to the list
     * @param index index of where the patient should be added.
     */
    public void addPatient(PatientRecord rec, int index) {
        //System.out.println("ADD PATIENT 1 " + patientsList.length + " " + size);
        if (getNumberSeenPatients() > 0)
            throw new IllegalStateException("cleanPatientsList()");
        if (patientsList.length == size) {
            //System.out.println("throwing exception ");
            throw new IllegalStateException("Cannot admit new patients");
        }
        for (int i = size; i > index; i--) {
            patientsList[i] = patientsList[i - 1];
        }

        patientsList[index] = rec;
        size++;
        //System.out.println("add PATIENT done ");
    }
//    for (i = size; i > index; i--) {
//        patientsList[i] = patientsList[i - 1];
//    }

    /**
     * marks a patient with a specific case ID "as seen" without modifying the list
     *
     * @param caseID - the case-number of the patent who is being marked as seen
     */
    public void seePatient(int caseID) {
        if (size == 0)
            throw new IllegalStateException();
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (i == caseID) {
                patientsList[i].seePatient();
                found = true;
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Creates a summary of the current Patient list which includes the number of patients
     * number of them who have been seen, as well as the triage levels.
     *
     * @return a string summary of the patient list
     */
    public String getSummary() {
        int redCount = 0;
        int YellowCount = 0;
        int greenCount = 0;
        String s1 = new String();
        for (int i = 0; i < size; i++) {
            if (patientsList[i].getTriage() == 0) {
                redCount = redCount + 1;
            }
            if (patientsList[i].getTriage() == 1) {
                YellowCount = YellowCount + 1;
            }
            if (patientsList[i].getTriage() == 2) {
                greenCount = greenCount + 1;
            }
        }

        s1 = "Total number of patients: " + size + "\nTotal number seen: " + getNumberSeenPatients() + "\nRED: "
                + redCount + "\nYELLOW: " + YellowCount + "\nGREEN: " +
                greenCount;
        System.out.println(s1);
        return s1;
    }
    /**
     * This method runs occasionally to record the current state of the patientsList and save any records for seen
     * patients to a file, while removing them from the patientsList to make more room.
     *
     * @param  file object to use for recording the data
     */
    public void cleanPatientsList(File file) {
        try {
            FileWriter myWriter = new FileWriter(file.getAbsolutePath());
            myWriter.write(getSummary());
            for (int i = 0; i < patientsList.length; i++) {
                if (patientsList[i] == null)
                    continue;
                if (patientsList[i].hasBeenSeen()) {
                    myWriter.write((patientsList[i].toString()));
                    patientsList[i] = null;
                    size--;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * For testing purposes: this method creates and returns a string representation of the
     * patientsList, as the in-order string representation of each patient in the list on a
     * separate line. If patientsList is empty, returns an empty string.
     *
     * @return a string representation of the contents of patientsList
     */
    public String toString() {
        String returnValue = "";
        for (PatientRecord r : patientsList) {
            returnValue += (r != null) ? r.toString() + "\n" : "";
        }
        return returnValue.trim();
    }


    // comment out when done!!
//    public static void main(String[] args) {
//        ExceptionalCareAdmissions eca = new ExceptionalCareAdmissions(1);
//        System.out.println(eca.patientsList.length);
//    }


}
