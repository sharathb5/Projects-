//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    tester file
// Course:   CS 300 Spring 2023
//
// Author:   Sharath Bhattiprolu
// Email:    sbhattiprolu@wisc.edu email address
// Lecturer:  Hobbes LeGault
//
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         I recvied help on the loopup method and the remove password tester from CS consulting hours
// Online Sources:  I revcieed no online help
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;

public class PasswordCrackingTester {

    /**
     * Validates the constructor and accessor methods of PasswordStorage, specifically the
     * getComparisonCriteria(), size(), and isEmpty() methods, as well as accessing the
     * protected data field root.
     * <p>
     * Be sure to try making multiple PasswordStorage objects with different Attributes.
     *
     * @return true if the basic accessor methods work as expected, false otherwise
     */
    public static boolean testBasicPasswordStorageMethods() {
        try {
            PasswordStorage storage = new PasswordStorage(Attribute.OCCURRENCE);
            if (!storage.isEmpty()) {
                System.out.println("storage size" + storage.size());
                return false;
            }
            if (storage.getComparisonCriteria() != Attribute.OCCURRENCE)
                return false;
            if (storage.size() != 0)
                return false;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates the Password class compareTo() method. Create at least two DIFFERENT
     * Password objects and compare them on each of the Attribute values. See the writeup
     * for details on how the various comparisons are expected to work.
     *
     * @return true if Password's compareTo() works as expected, false otherwise
     */
    public static boolean testPasswordCompareTo() {
        // Test 1: Compare password with lower occurrences to one with higher occurrences
        Password p1 = new Password("password1", 10);
        Password p2 = new Password("password2", 20);

        if (p1.compareTo(p2, Attribute.OCCURRENCE) >= 0) {
            System.out.println("Test 1 failed!");
            return false;
        }

// Test 2: Compare password with higher occurrences to one with lower occurrences
        Password p3 = new Password("password1", 30);
        Password p4 = new Password("password2", 5);

        if (p3.compareTo(p4, Attribute.HASHED_PASSWORD) <= 0) {
            System.out.println("Test 2 Failed!");
            return false;
        }

// Test 3: Compare password with same occurrences but lower strength rating to one with higher strength rating
        Password p5 = new Password("StronkPass12#", 15);
        Password p6 = new Password("“password”!", 15);
        if (p5.compareTo(p6, Attribute.STRENGTH_RATING) <= 0) {
            System.out.println("Test 3 failed!");
            return false;
        }

// Test 4: Compare password with same occurrences and strength rating to itself
        Password p7 = new Password("password7", 5);
        Password p8 = new Password("password7", 5);

        if (p7.compareTo(p8, Attribute.STRENGTH_RATING) != 0) {
            System.out.println("Test 4 failed!");
            return false;
        }
        return true;
    }

    /**
     * Validates the incomplete methods in PasswordNode, specifically isLeafNode(),
     * numberOfChildren(), hasLeftChild() and hasRightChild(). Be sure to test all
     * possible configurations of a node in a binary tree!
     *
     * @return true if the status methods of PasswordNode work as expected, false otherwise
     */
    public static boolean testNodeStatusMethods() {
        Password p1 = new Password("P1", 10);
        Password p2 = new Password("P2", 11);
        Password p3 = new Password("P3", 12);
        Password p4 = new Password("P4", 13);
        Password p5 = new Password("P5", 14);

        PasswordNode node2 = new PasswordNode(p2);
        PasswordNode node3 = new PasswordNode(p3);
        PasswordNode node4 = new PasswordNode(p4);
        PasswordNode node5 = new PasswordNode(p5);
        PasswordNode node1 = new PasswordNode(p1, node2, node3);
        if (node1.isLeafNode())
            return false;

        if (!node1.hasLeftChild())
            return false;

        if (!node1.hasRightChild())
            return false;

        if (node1.numberOfChildren() != 2)
            return false;

        if (!node2.isLeafNode())
            return false;

        if (node2.hasLeftChild())
            return false;

        if (node2.hasRightChild())
            return false;

        if (node2.numberOfChildren() != 0)
            return false;

        if (!node3.isLeafNode())
            return false;

        if (node3.hasLeftChild())
            return false;

        if (node3.hasRightChild())
            return false;

        if (node3.numberOfChildren() != 0)
            return false;

        node2.setRight(node4);

        if (!node2.hasRightChild())
            return false;

        if (node2.hasLeftChild())
            return false;

        if (node2.numberOfChildren() != 1)
            return false;

        node3.setLeft(node5);

        if (!node3.hasLeftChild())
            return false;

        if (node3.hasRightChild())
            return false;

        if (node3.numberOfChildren() != 1)
            return false;
//        Password password = new Password("pass", 55);
//        Password passwordLeft = new Password("leftPass", 20);
//        Password passwordRight = new Password("leftPass", 20);
//        PasswordNode node = new PasswordNode(password);
//        PasswordNode nodeLeft = new PasswordNode(passwordLeft);
//        PasswordNode nodeRight = new PasswordNode(passwordRight);
//        PasswordNode testNode1Left = new PasswordNode(password);
//        PasswordNode testNode2Left = new PasswordNode(password, null, nodeRight);
//        PasswordNode testNode3Left = new PasswordNode(password, nodeLeft, nodeRight);
//        {
//            boolean actual = testNode1Left.hasLeftChild();
//            boolean expected = false;
//            if (actual != expected) {
//                return false;
//            }
//        }
//        {
//            boolean actual = testNode2Left.hasLeftChild();
//            boolean expected = false;
//            if (actual != expected) {
//                return false;
//            }
//        }
//        {
//            boolean actual = testNode1Left.hasLeftChild();
//            boolean expected = true;
//            if (actual != expected) {
//                return false;
//            }
//        }
//
//
//        PasswordNode testNode1 = new PasswordNode(password);
//        PasswordNode testNode2 = new PasswordNode(password, nodeLeft, null);
//        PasswordNode testNode3 = new PasswordNode(password, nodeLeft, nodeRight);
//        {
//            boolean actual = testNode1.hasRightChild();
//            boolean expected = false;
//            if (actual != expected) {
//                return false;
//            }
//        }
//        {
//            boolean actual = testNode2.hasRightChild();
//            boolean expected = false;
//            if (actual != expected) {
//                return false;
//            }
//        }
//        {
//            boolean actual = testNode1.hasRightChild();
//            boolean expected = true;
//            if (actual != expected) {
//                return false;
//            }
//        }

//         if (node.numberOfChildren() != 0)
//            return false;
//        if (!node.isLeafNode())
//            return false;
//        node.setLeft(nodeLeft);
//        if (!node.hasLeftChild())
//            return false;
//        node.setRight(nodeRight);
//        if (!node.hasRightChild())
//            return false;
//        if (node.numberOfChildren() != 2)
//            return false;


        return true;
    }

    // GIVE TO STUDENTS
    public static boolean testToString() {
        try {
            PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

            // empty is empty string
            String expected = "";
            String actual = bst.toString();
            if (!actual.equals(expected)) {
                //System.out.println("toString() does not return the proper value on an empty tree!");
                //return false;
            }

            // size one only returns 1 thing
            Password p = new Password("1234567890", 15000);
            PasswordNode rootNode = new PasswordNode(p);

            bst.root = rootNode; // here I am manually building the tree by editing the root node
            // directly to be the node of my choosing

            expected = p.toString() + "\n";
            actual = bst.toString();
            if (!actual.equals(expected)) {
                System.out.println("Size 1 is failing: " + actual + "expected: " + expected);
                return false;
            }

            // big tree returns in-order traversal
            Password p2 = new Password("test", 500);
            Password p3 = new Password("iloveyou", 765);
            Password p4 = new Password("qwerty", 250);
            Password p5 = new Password("admin", 1002);
            Password p6 = new Password("password", 2232);
            Password p7 = new Password("abc123", 2090);

            PasswordNode p4Node = new PasswordNode(p4);
            PasswordNode p3Node = new PasswordNode(p3);
            PasswordNode p7Node = new PasswordNode(p7);
            PasswordNode p6Node = new PasswordNode(p6, p7Node, null);
            PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
            PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
            rootNode = new PasswordNode(p, p2Node, p5Node);
            bst.root = rootNode;

            expected = p4.toString() + "\n" + p2.toString() + "\n" + p3.toString() + "\n" + p.toString()
                    + "\n" + p5.toString() + "\n" + p7.toString() + "\n" + p6.toString() + "\n";
            actual = bst.toString();

            if (!actual.equals(expected)) {
                System.out.println("Big tree travesel not working: " + actual);
                System.out.println("expected: " + expected);
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // GIVE TO STUDENTS
    public static boolean testIsValidBST() {
        try {
            PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

            // empty tree is a valid bst
            /*
             * String expected = ""; String actual = bst.toString();
             */
            if (!bst.isValidBST()) {
                System.out.println("isValidBST() says that an empty tree is not a valid BST!");
                return false;
            }

            // size one is a bst
            Password p = new Password("1234567890", 1000);
            PasswordNode rootNode = new PasswordNode(p);

            bst.root = rootNode; // here I am manually building the tree by editing the root node
            // directly to be the node of my choosing
            rootNode.setRight(null);
            rootNode.setLeft(null);

            if (!bst.isValidBST()) {
                System.out.println("isValidBST() says that a tree of size 1 is not a valid BST!");
                return false;
            }

            Password p2 = new Password("test", 500);
            Password p3 = new Password("iloveyou", 765);
            Password p4 = new Password("qwerty", 250);
            Password p5 = new Password("admin", 1002);
            Password p6 = new Password("password", 2232);
            Password p7 = new Password("abc123", 2090);

            // works on indentifying small obviously invalid tree
            PasswordNode p7Node = new PasswordNode(p7);
            PasswordNode p3Node = new PasswordNode(p3);
            rootNode = new PasswordNode(p, p7Node, p3Node);
            bst.root = rootNode;
            if (bst.isValidBST())
                return false;

            // tree with only one subtree being valid, other subtree has a violation a couple layers deep


            PasswordNode p4Node = new PasswordNode(p4);
            p7Node = new PasswordNode(p7);
            p3Node = new PasswordNode(p3);
            PasswordNode p6Node = new PasswordNode(p6, null, p7Node);
            PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
            PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
            rootNode = new PasswordNode(p, p2Node, p5Node);
            bst.root = rootNode;

            if (bst.isValidBST()) {
                System.out
                        .println("isValidBST() says that a tree with only one valid subtree is a valid bst");
                return false;
            }


            // works on valid large tree
            p4Node = new PasswordNode(p4);
            p3Node = new PasswordNode(p3);
            p7Node = new PasswordNode(p7);
            p6Node = new PasswordNode(p6, p7Node, null);
            p5Node = new PasswordNode(p5, null, p6Node);
            p2Node = new PasswordNode(p2, p4Node, p3Node);
            rootNode = new PasswordNode(p, p2Node, p5Node);
            bst.root = rootNode;

            if (!bst.isValidBST())
                return false;

            PasswordNode one = new PasswordNode(p4);
            PasswordNode three = new PasswordNode(p3, one, null);
            PasswordNode two = new PasswordNode(p2, null, three);
            bst.root = two;

            if (bst.isValidBST()) {
                System.out.println("bad bst is valid");
                return false;
            }


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * tests the lookup method and its edge cases
     *
     * @return true if all the cases pass and false otherwise
     */
    public static boolean testLookup() {
        try {
            PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);
            Password p1 = new Password("1234567890", 1000);
            PasswordNode rootNode = new PasswordNode(p1);
            Password p2 = new Password("test", 500);
            Password p3 = new Password("iloveyou", 765);
            Password p4 = new Password("qwerty", 250);
            Password p5 = new Password("admin", 1002);
            Password p6 = new Password("password", 2232);
            Password p7 = new Password("abc123", 2090);
            Password p8 = new Password("thisSucks", 12928);
            PasswordNode p4Node = new PasswordNode(p4);
            PasswordNode p3Node = new PasswordNode(p3);
            PasswordNode p7Node = new PasswordNode(p7);
            PasswordNode p6Node = new PasswordNode(p6, p7Node, null);
            PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
            PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
            rootNode = new PasswordNode(p1, p2Node, p5Node);
            bst.root = rootNode;
            Password result1 = bst.lookup(p1);
            if (!result1.equals(p1)) {
                System.out.println("Test 1 failed!");
                return false;
            }

            Password result2 = bst.lookup(p2);
            if (!result2.equals(p2)) {
                System.out.println("Test 2 failed!");
                System.out.println("result2: " + result2);
                System.out.println("expected: " + p2);
                return false;
            }

            Password result3 = bst.lookup(p3);
            if (!result3.equals(p3)) {
                System.out.println("Test 3 failed!");
                return false;
            }
            //System.out.println("===============");

            // Test 4: Lookup password that does not exist in the BST
            Password result4 = bst.lookup(p8);
            if (result4 != null) {
                System.out.println("Test 4 failed!" + result4);
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * tests the add password method and its edge cases
     *
     * @return true if all the cases pass and false otherwise
     */
    public static boolean testAddPassword() {
        try {
            Password p1 = new Password("password1", 5);
            Password p2 = new Password("password2", 10);
            Password p3 = new Password("password3", 15);
            String expected = "";
            String actual = "";

            PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);
            bst.addPassword(p1);
            bst.addPassword(p2);
            bst.addPassword(p3);
            expected = p1.toString() + "\n" + p2.toString() + "\n" + p3.toString() + "\n";
            actual = bst.toString();
            if (!actual.equals(expected)) {
                System.out.println("actual: " + actual);
                System.out.println("expected: " + expected);
                return false;
            }
            PasswordStorage bst1 = new PasswordStorage(Attribute.OCCURRENCE);
            bst1.addPassword(p1);
            bst1.addPassword(p2);
            bst1.addPassword(p3);
            try {
                bst1.addPassword(p3);
                return false;
            }catch (IllegalArgumentException e){
            }


            expected = p1.toString() + "\n" + p2.toString() + "\n" + p3.toString() + "\n";
            actual = bst1.toString();
            if (!actual.equals(expected)) {
                System.out.println("actual: " + actual);
                System.out.println("expected: " + expected);
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * tests the remove password method and its edge cases
     *
     * @return true if all the cases pass and false otherwise
     */



    public static boolean testRemovePassword() {
        try {
            String expected = "";
            String actual = "";

            Password p1 = new Password("1234567890", 1000);
            Password p2 = new Password("test", 500);
            Password p3 = new Password("iloveyou", 765);
            Password p4 = new Password("qwerty", 250);
            Password p5 = new Password("admin", 1002);
            Password p6 = new Password("password", 2232);
            Password p7 = new Password("abc123", 2090);
            PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);
            bst.addPassword(p1);
            bst.addPassword(p2);
            bst.addPassword(p3);
            bst.addPassword(p4);
            bst.addPassword(p5);
            bst.addPassword(p6);
            bst.addPassword(p7);
            bst.removePassword(p2);

            PasswordStorage bst1 = new PasswordStorage(Attribute.OCCURRENCE);
            bst1.addPassword(p1);
            bst1.addPassword(p3);
            bst1.addPassword(p4);
            bst1.addPassword(p5);
            bst1.addPassword(p6);
            bst1.addPassword(p7);
            expected = bst1.toString();
            actual = bst.toString();
            if (!actual.equals(expected)) {
//                System.out.println("actual: " + actual);
//                System.out.println("expected: " + expected);
                return false;
            }
            expected = p1.toString();
            actual = bst.root.getPassword().toString();
            if(!actual.equals(expected))
                return false;
            bst.removePassword(p1);
            bst1.removePassword(p1);
            expected = bst.toString();
            actual = bst1.toString();
            if(!actual.equals(expected)) {
                System.out.println("expected: " + expected);
                System.out.println("actual: " + actual);
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        runAllTests();
    }

    public static boolean runAllTests() {
        boolean compareToPassed = testPasswordCompareTo();
        boolean nodeStatusPassed = testNodeStatusMethods();
        boolean basicMethodsPassed = testBasicPasswordStorageMethods();
        boolean toStringPassed = testToString();
        boolean isValidBSTPassed = testIsValidBST();
        boolean lookupPassed = testLookup();
        boolean addPasswordPassed = testAddPassword();
        boolean removePasswordPassed = testRemovePassword();

        System.out.println("Password compareTo: " + (compareToPassed ? "PASS" : "FAIL"));
        System.out.println("PasswordNode Status Methods: " + (nodeStatusPassed ? "PASS" : "FAIL"));
        System.out.println("PasswordStorage Basic Methods: " + (basicMethodsPassed ? "PASS" : "FAIL"));
        System.out.println("PasswordStorage toString: " + (toStringPassed ? "PASS" : "FAIL"));
        System.out.println("PasswordStorage isValidBST: " + (isValidBSTPassed ? "PASS" : "FAIL"));
        System.out.println("PasswordStorage lookup: " + (lookupPassed ? "PASS" : "FAIL"));
        System.out.println("PasswordStorage addPassword: " + (addPasswordPassed ? "PASS" : "FAIL"));
        System.out.println("PasswordStorage removePassword: " + (removePasswordPassed ? "PASS" : "FAIL"));

        // AND ANY OTHER ADDITIONAL TEST METHODS YOU MAY WANT TO WRITE!

        return compareToPassed && nodeStatusPassed && basicMethodsPassed && toStringPassed
                && isValidBSTPassed && lookupPassed && addPasswordPassed && removePasswordPassed;
    }

}
