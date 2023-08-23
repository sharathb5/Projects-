//
// Title:    Exceptional Care Tester
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


public class ExceptionalCareTester {

    /**
     * This test method is provided for you in its entirety, to give you a model for testing
     * an instantiable class. This method verifies the correctness of your PatientRecord class.
     * <p>
     * In this test, we create two PatientRecords with different information and use the accessor
     * methods to verify that both contain the correct information and have the correct String
     * representation.
     *
     * @return true if and only if all scenarios pass, false otherwise
     * @author hobbes
     */
    public static boolean testPatientRecord() {
        // FIRST: reset the patient counter so this tester method can be run independently
        PatientRecord.resetCounter();
        // (1) create two PatientRecords with different, valid input
        // no exceptions should be thrown, so let's be safe:
        PatientRecord test1 = null, test2 = null;
        try {
            test1 = new PatientRecord('M', 17, PatientRecord.YELLOW);
            test2 = new PatientRecord('X', 21, PatientRecord.GREEN);
        } catch (Exception e) {
            return false;
        }

        // (2) verify their data fields:
        {
            // CASE_NUMBER
            int expected1 = 21701;
            int expected2 = 32102;
            if (test1.CASE_NUMBER != expected1 || test2.CASE_NUMBER != expected2) return false;
        }
        {
            // triage
            int expected1 = PatientRecord.YELLOW;
            int expected2 = PatientRecord.GREEN;
            if (test1.getTriage() != expected1 || test2.getTriage() != expected2) return false;
        }
        {
            // gender
            char expected1 = 'M';
            char expected2 = 'X';
            if (test1.getGender() != expected1 || test2.getGender() != expected2) return false;
        }
        {
            // age
            int expected1 = 17;
            int expected2 = 21;
            if (test1.getAge() != expected1 || test2.getAge() != expected2) return false;
        }
        {
            // orderOfArrival
            int expected1 = 1;
            int expected2 = 2;
            if (test1.getArrivalOrder() != expected1 ||
                    test2.getArrivalOrder() != expected2) return false;
        }
        {
            // hasBeenSeen - try the mutator too
            if (test1.hasBeenSeen() || test2.hasBeenSeen()) return false;
            test1.seePatient();
            if (!test1.hasBeenSeen() || test2.hasBeenSeen()) return false;
        }

        // (3) verify their string representations
        {
            String expected1 = "21701: 17M (YELLOW)";
            String expected2 = "32102: 21X (GREEN)";
            if (!test1.toString().equals(expected1) || !test2.toString().equals(expected2)) return false;
        }

        // (4) finally, verify that the constructor throws an exception for an invalid triage value
        try {
            new PatientRecord('F', 4, -17);
            // if we get here, no exception was thrown and the test fails
            return false;
        } catch (IllegalArgumentException e) {
            // correct exception type, but it should have a message:
            if (e.getMessage() == null || e.getMessage().isBlank()) return false;
        } catch (Exception e) {
            // incorrect exception type
            return false;
        }

        // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
        return true;
    }

    public static boolean testAdmissionsConstructorValid() {
        PatientRecord.resetCounter();
//        // (1) verify that a normal, valid-input constructor call does NOT throw an exception
        boolean result = true;
        try {
            ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(10);
        } catch (Exception e) {
            result = false;
        }
        // (2) verify that a just-created object has size 0, is not full, has no seen patients, and
//        // its toString() is an empty string
        try {

            ExceptionalCareAdmissions admissions0 = new ExceptionalCareAdmissions(0);
            // checks if size is = to 0
            if (admissions0.size() != 0) {
                result = false;
            }
            // checks if admissions is full
            if (admissions0.isFull()) {
                return false;
            }
            // checks if there are no seen patients
            if (admissions0.getNumberSeenPatients() != 0)
                return false;
            //checks for a blank toString
            if (!admissions0.toString().isBlank())
                return false;

            return result;
        } catch (IllegalArgumentException e) {
            result = false;
        }
        return true;
    }


    //
    public static boolean testAdmissionsConstructorError() {
        PatientRecord.resetCounter();
//        // (1) verify that calling the constructor with capacity <= 0 causes an IllegalArgumentException
        boolean result = true;
        try {
            ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(0);
            result = false;
        } catch (IllegalArgumentException e) {
        }
        return result;
    }

    //
    public static boolean testAddPatientValid() {
        PatientRecord.resetCounter();
        //System.out.println("DID YOU GET HERE");
//        // (1) add a new patient to an empty list - since you cannot use Arrays.deepEquals() here
//        // anymore, verify the contents of the patientsList using ExceptionalCareAdmissions.toString()
        ExceptionalCareAdmissions admissions1 = new ExceptionalCareAdmissions(5);
        PatientRecord newPatient = new PatientRecord('F', 27, 1);
        admissions1.addPatient(newPatient, 0);
        int expected = 1;
        // System.out.println(admissions1.getAdmissionIndex(newPatient));
        if (admissions1.getAdmissionIndex(newPatient) != expected)
            return false;
        //System.out.println("FIRST ADD TEST PASSED");
        //Go to getAdmissionIndex code and see if it should be i+1 or not and what should the output be

//
        // (2) add a new patient to the end of the list
        PatientRecord newPatientEnd = new PatientRecord('F', 27, 2);
        admissions1.addPatient(newPatientEnd, admissions1.getAdmissionIndex(newPatientEnd));
        int expected2 = 2;
        //System.out.println(admissions1.getAdmissionIndex(newPatientEnd));
        if (admissions1.getAdmissionIndex(newPatientEnd) != expected2)
            return false;
        //System.out.println("SECOND ADD TEST PASSED");
//
//        // (3) add a new patient to the beginning of the list
        //System.out.println("LEAVING!2");
        PatientRecord newPatientBeg = new PatientRecord('F', 27, 0);
        admissions1.addPatient(newPatientBeg, admissions1.getAdmissionIndex(newPatientBeg));
        //System.out.println("   MESSAGE "+ admissions1.getAdmissionIndex(newPatientBeg));
        // System.out.println(admissions1.getSummary());
        int expected3 = 1;
        //System.out.println(admissions1.getAdmissionIndex(newPatientBeg));
        if (admissions1.getAdmissionIndex(newPatientBeg) != expected3)
            return false;
        //System.out.println("THIRD ADD TEST PASSED");

        return true;
    }


    //
//    /**
//     * This test method is provided for you in its entirety, to give you a model for verifying a
//     * method which throws exceptions. This method tests addPatient() with two different, full
//     * patientsList arrays; one contains seen patients and one does not.
//     * <p>
//     * We assume for the purposes of this method that the ExceptionalCareAdmissions constructor
//     * and PatientRecord constructor are working properly.
//     * <p>
//     * This method must NOT allow ANY exceptions to be thrown from the tested method.
//     *
//     * @return true if and only if all scenarios pass, false otherwise
//     * @author hobbes
//     */
    public static boolean testAddPatientError() {
////        // FIRST: reset the patient counter so this tester method can be run independently
        PatientRecord.resetCounter();
////
////        ////// (1) a full Admissions object that contains no seen patients
////
////        // create a small admissions object and fill it with patients. i'm filling the list
////        // in decreasing order of triage, so the addPatient() method has to do the least
////        // amount of work.
        ExceptionalCareAdmissions full = new ExceptionalCareAdmissions(3);
        full.addPatient(new PatientRecord('M', 18, PatientRecord.RED), 0);
        full.addPatient(new PatientRecord('M', 5, PatientRecord.YELLOW), 1);
////        // saving one patient in a local variable so we can mark them "seen" later
        PatientRecord seenPatient = new PatientRecord('F', 20, PatientRecord.GREEN);
        full.addPatient(seenPatient, 2);
////
        try {
            full.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 3);
            // if we get here, no exception was thrown and the test fails
            return false;
        } catch (IllegalStateException e) {
//            // this is the correct type of exception, but for this method we expect a specific
            // error message so we have one more step to verify:
            String message = e.getMessage();
            String expected = "Cannot admit new patients";
            if (!message.equals(expected)) return false;
        } catch (Exception e) {
//            // this is the incorrect exception type, and we can just fail the test now
            return false;
        }
//
//        ////// (2) a full Admissions object that contains at least one seen patient
//
//        // since we have a reference to the patient at index 2, we'll just mark them seen here
        seenPatient.seePatient();
//
        try {
            full.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 3);
//            // if we get here, no exception was thrown and the test fails
            return false;
        } catch (IllegalStateException e) {
//            // this is the correct type of exception again, but we expect a different error
            // message this time:
            String message = e.getMessage();
            String expected = "cleanPatientsList()";
            if (!message.equals(expected)) return false;
        } catch (Exception e) {
//            // this is the incorrect exception type, and the test fails here
            return false;
        }
//
//        // if we've gotten this far, we haven't failed either of the scenarios, so our test passes!
        return true;

    }

    //
    public static boolean testGetIndexValid() {
        PatientRecord.resetCounter();
//////        // create an Admissions object and add a few Records to it, leaving some space
        ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(10);
        admissions.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 0);
        admissions.addPatient(new PatientRecord('M', 22, PatientRecord.YELLOW), 0);
        admissions.addPatient(new PatientRecord('X', 22, PatientRecord.YELLOW), 1);
        PatientRecord newPatient = new PatientRecord('X', 75, PatientRecord.GREEN);
        PatientRecord newPatient2 = new PatientRecord('X', 75, PatientRecord.RED);
        PatientRecord newPatient3 = new PatientRecord('X', 75, PatientRecord.RED);


        // (1) get the index of a PatientRecord that should go at the END
        System.out.println("  MESSAGE " + admissions.size());
        if (admissions.getAdmissionIndex(newPatient) != 3)
            return false;
//        // (2) get the index of a PatientRecord that should go at the BEGINNING
        if (admissions.getAdmissionIndex(newPatient2) != 0)
            return false;
//        // (3) get the index of a PatientRecord that should go in the MIDDLE
//        System.out.println( " SOMEthing  "+ admissions.getAdmissionIndex(newPatient3));
//        if(admissions.getAdmissionIndex(newPatient3) != 1)
//            return false;

        return true;

    }

    //
    public static boolean testGetIndexError() {
        PatientRecord.resetCounter();
//        // create an Admissions object and add Records to it until it is full, as in testAddPatientError
        ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(3);
        admissions.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 0);
        PatientRecord seen = new PatientRecord('M', 22, PatientRecord.YELLOW);
        admissions.addPatient(seen, 0);
        admissions.addPatient(new PatientRecord('X', 22, PatientRecord.RED), 0);
        // (1) verify the exception when there are no patients who have been seen in the list
        try {
            admissions.addPatient(new PatientRecord('F', 18, PatientRecord.RED), 1);
        } catch (IllegalStateException e) {
            String message = e.getMessage();
            String expected = "Cannot admit new patients";
            if (!message.equals(expected)) return false;
        }
        seen.seePatient();
//        // (2) verify the exception when there is at least one patient who has been seen
        try {
            admissions.addPatient(new PatientRecord('F', 18, PatientRecord.RED), 1);
        } catch (IllegalStateException e) {
            String message = e.getMessage();
            String expected = "cleanPatientsList()";
            if (!message.equals(expected)) return false;
        }
        return true;
    }

    //
    public static boolean testAdmissionsBasicAccessors() {
        PatientRecord.resetCounter();
//        // (1) verify isFull() on a non-full and a full Admissions object
        ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(3);
        if (admissions.size() != 0)
            return false;
        admissions.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 0);
        PatientRecord seen = new PatientRecord('M', 22, PatientRecord.YELLOW);
        admissions.addPatient(seen, 0);
        if (admissions.isFull())
            return false;
        if (admissions.size() != 2)
            return false;
        admissions.addPatient(new PatientRecord('X', 22, PatientRecord.RED), 0);
        if (!admissions.isFull())
            return false;
//        // (2) verify size() before and after adding a PatientRecord
//        // (3) verify getNumberSeenPatients() before and after seeing a patient
//        // (see testAddPatientError for a model of how to do this while bypassing seePatient())
        if (admissions.getNumberSeenPatients() != 0)
            return false;
        seen.seePatient();
        if (admissions.getNumberSeenPatients() != 1)
            return false;
        return true;
    }

    //
    public static boolean testSeePatientValid() {
//        // create an Admissions object and add a few Records to it, saving a shallow copy of
//        // at least one of the PatientRecord references
        PatientRecord.resetCounter();
        ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(3);
        admissions.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 0);
        PatientRecord seen = new PatientRecord('M', 22, PatientRecord.YELLOW);
        admissions.addPatient(seen, 0);
//
//        // (1) call seePatient() on the caseID of your saved reference and verify that its
//        // hasBeenSeen() accessor return value changes

//
//        // (2) verify getNumberSeenPatients() before and after seeing a different patient
//        return false;
        return false;
    }

    //
    public static boolean testSeePatientError() {
//        // (1) verify that seeing a caseID for a patient not in the list causes an IllegalArgumentException
        PatientRecord.resetCounter();
        ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(3);
        admissions.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 0);
        PatientRecord seen = new PatientRecord('M', 22, PatientRecord.YELLOW);
        try {
            admissions.seePatient(seen.CASE_NUMBER);
        } catch (IllegalArgumentException e) {
            return true;
        }
        return false;
    }

    //
    public static boolean testGetSummary() {
//        // (1) choose one getSummary() test from P01; this method has not changed much
        ExceptionalCareAdmissions admissions = new ExceptionalCareAdmissions(3);
        admissions.addPatient(new PatientRecord('F', 17, PatientRecord.GREEN), 0);
        PatientRecord seen = new PatientRecord('M', 22, PatientRecord.YELLOW);

        int size = 0;
        String expected = "Total number of patients: " + size + "\nTotal number seen: " +
                admissions.getNumberSeenPatients() + "\nRED: "
                + 0 + "\nYELLOW: " + 1 + "\nGREEN: " + 1;
        String sum = admissions.getSummary();
        if (!sum.equals(expected)) {
            return false;
        }
        return true;
    }
//
//    public static boolean testCleanList() {
//        // create an Admissions object and add a few Records to it

//        // (1) using ExceptionalCareAdmissions.toString(), verify that a patientsList with NO seen
//        // patients does not change after calling cleanPatientsList()

//        // (2) call seePatient() for at least two of the records in your patientsList, and use toString()
//        // to verify that they have been removed after calling cleanPatientsList()

    //        // NOTE: you do NOT need to verify file contents in this test method; please do so manually
//        return false;
    //}
//
//    /**
//     * Runs each of the tester methods and displays the result. Methods with two testers have
//     * their output grouped for convenience; a failed test is displayed as "X" and a passed test
//     * is displayed as "pass"
//     *
//     * @param args unused
//     * @author hobbes
//     */
    public static void main(String[] args) {
        System.out.println("PatientRecord: " + (testPatientRecord() ? "pass" : "X"));
        System.out.println("Admissions Constructor: " + (testAdmissionsConstructorValid() ? "pass" : "X") + ", " +
                (testAdmissionsConstructorError() ? "pass" : "X"));
        //System.out.println("IS THIS WORKING1");
        System.out.println("Add Patient: " + (testAddPatientValid() ? "pass" : "X") + ", " +
                (testAddPatientError() ? "pass" : "X"));
        //System.out.println("IS THIS WORKING2");
        System.out.println("Get Admission Index: " + (testGetIndexValid() ? "pass" : "X") + ", " +
                (testGetIndexError() ? "pass" : "X"));
        System.out.println("Basic Accessors: " + (testAdmissionsBasicAccessors() ? "pass" : "X"));
        System.out.println("See Patient: " + (testSeePatientValid() ? "pass" : "X") + ", " +
                (testSeePatientError() ? "pass" : "X"));
        System.out.println("Get Summary: " + (testGetSummary() ? "pass" : "X"));
        // System.out.println("Clean List: " + (testCleanList() ? "pass" : "X"));
    }

}

