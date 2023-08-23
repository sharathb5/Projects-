//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Fucntions of Password BST
// Course:   CS 300 Spring 2023
//
// Author:   Sharath Bhattiprolu
// Email:    sbhattiprolu@wisc.edu email address
// Lecturer:  Hobbes LeGault
//
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         I recvied help on the remove helper method from CS consulting hours
// Online Sources:  I revcieed no online help
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;

public class PasswordStorage {

    protected PasswordNode root; //the root of this BST that contains passwords
    private int size; //how many passwords are in the BST
    private final Attribute COMPARISON_CRITERIA; //what password information to use to determine order in the tree

    /**
     * Constructor that creates an empty BST and sets the comparison criteria.
     *
     * @param comparisonCriteria, the Attribute that will be used to determine order in the tree
     */
    public PasswordStorage(Attribute comparisonCriteria) {
        this.root = root;
        this.size = 0;
        this.COMPARISON_CRITERIA = comparisonCriteria;

        //TODO
    }

    /**
     * Getter for this BST's criteria for determining order in the three
     *
     * @return the Attribute that is being used to make comparisons in the tree
     */
    public Attribute getComparisonCriteria() {
        return this.COMPARISON_CRITERIA;
    }

    /**
     * Getter for this BST's size.
     *
     * @return the size of this tree
     */
    public int size() {
        return this.size;
    }

    /**
     * Determines whether this tree is empty.
     *
     * @return true if it is empty, false otherwise
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Provides in-order String representation of this BST, with each Password on its own line. The
     * String representation ends with a newline character ('\n')
     *
     * @return this BST as a string
     */
    @Override
    public String toString() {
        return toStringHelper(this.root);
    }

    /**
     * Recursive method the uses an in-order traversal to create the string representation of this tree.
     *
     * @param currentNode, the root of the current tree
     * @return the in-order String representation of the tree rooted at current node
     */
    private String toStringHelper(PasswordNode currentNode) {
        //THIS MUST BE IMPLEMENTED RECURSIVELY
        if (currentNode == null)
            return "";
        else
            return toStringHelper(currentNode.getLeft()) + currentNode.getPassword().toString() + "\n" +
                    toStringHelper(currentNode.getRight());
    }

    /**
     * Determines whether this tree is actually a valid BST.
     *
     * @return true if it is a BST, false otherwise
     */
    public boolean isValidBST() {
        return isValidBSTHelper(this.root, Password.getMinPassword(), Password.getMaxPassword());
    }

    /**
     * Recurisvely determines if the tree rooted at the current node is a valid BST. That is, every
     * value to the left of currentNode is "less than" the value in currentNode and every value to the
     * right of it is "greater than" it.
     *
     * @param currentNode, the root node of the current tree
     * @param lowerBound,  the smallest possible password
     * @param upperBound,  the largest possible password
     * @return true if the tree rooted at currentNode is a BST, false otherwise
     */
    private boolean isValidBSTHelper(PasswordNode currentNode, Password lowerBound, Password upperBound) {
        //MUST BE IMPLEMENTED RECURSIVELY
        //   BASE CASE 1: the tree rooted at currentNode is empty, which does not violate any BST rules
        if (currentNode == null)
            return true;
        // BASE CASE 2: the current Password is outside the upper OR lower bound for this subtree, which is against
        //              the rules for a valid BST
        if (currentNode.getPassword().compareTo(lowerBound, COMPARISON_CRITERIA) <
                0 || currentNode.getPassword().compareTo(upperBound, COMPARISON_CRITERIA) > 0)
            return false;
        // If we do not have a base case situation, we must use recursion to verify currentNode's child subtrees
        // RECURSIVE CASE 1: Check that the left subtree contains only Passwords greater than lowerBound and less than
        // currentNode's Password; return false if this property is NOT satisfied
        if (!isValidBSTHelper(currentNode.getLeft(), lowerBound, currentNode.getPassword()))
            return false;
        // RECURSIVE CASE 2: Check that the right subtree contains only Passwords greater than currentNode's Password
        //                   and less than upperBound; return false if this property is NOT satisfied
        if (!isValidBSTHelper(currentNode.getRight(), currentNode.getPassword(), upperBound))
            return false;
        // COMBINE RECURSIVE CASE ANSWERS: this is a valid BST if and only if both case 1 and 2 are valid


        return true;
    }

    /**
     * Returns the password that matches the criteria of the provided key.
     * Ex. if the COMPARISON CRITERIA is OCCURRENCE and the key has an occurrence of 10
     * it will return the password stored in the tree with occurrence of 10
     *
     * @param key, the password that contains the information for the password we are searching for
     * @return the Password that matches the search criteria, if it does not exist in the tree it this will be null
     */
    public Password lookup(Password key) {
        return lookupHelper(key, root);
    }

    /**
     * Recursive helper method to find the matching password in this BST
     *
     * @param key,         password containing the information we are searching for
     * @param currentNode, the node that is the current root of the tree
     * @return the Password that matches the search criteria, if it does not exist in the tree it this will be null
     */
    private Password lookupHelper(Password key, PasswordNode currentNode) {
        //THIS MUST BE IMPLEMENTED RECURSIVELY
        if (currentNode == null) {
            //System.out.println("ARE WE RETURNTNG null");
            return null;
        }
        //System.out.println("comparing");
        // System.out.println(currentNode.getPassword());
        //System.out.println(key);
        int compare = key.compareTo(currentNode.getPassword(), this.getComparisonCriteria());
        if (compare == 0)
            return currentNode.getPassword();
        if (compare > 0)
            return lookupHelper(key, currentNode.getRight());
        if (compare < 0)
            return lookupHelper(key, currentNode.getLeft());
        else {
            key = null;
            return key;
        }
    }


    /**
     * Returns the best (max) password in this BST
     *
     * @return the best password in this BST
     * @throws NoSuchElementException if the BST is empty
     */
    public Password getBestPassword() {
        if (root == null)
            throw new NoSuchElementException();
        PasswordNode current = root;
        while (current.hasRightChild())
            current = current.getRight();
        return current.getPassword();
    }

    /**
     * Returns the worst password in this BST
     *
     * @return the worst password in this BST
     * @throws NoSuchElementException if the BST is empty
     */
    public Password getWorstPassword() {
        if (root == null)
            throw new NoSuchElementException();
        PasswordNode current = root;
        while (current.hasLeftChild())
            current = current.getLeft();
        return current.getPassword();
    }

    /**
     * Adds the Password to this BST.
     *
     * @param toAdd, the password to be added to the tree
     * @throws IllegalArgumentException if the (matching) password object is already in the tree
     */
    public void addPassword(Password toAdd) {
        if (toAdd == null)
            throw new IllegalArgumentException();
        if (root == null)
            root = new PasswordNode(toAdd);
        else {
            if (!addPasswordHelper(toAdd, root))
                throw new IllegalArgumentException();
        }
    }

    /**
     * Recursive helper that traverses the tree and adds the password where it belongs
     *
     * @param toAdd,       the password to add to the tree
     * @param currentNode, the node that is the current root of the (sub)tree
     * @return true if it was successfully added, false otherwise
     */
    private boolean addPasswordHelper(Password toAdd, PasswordNode currentNode) {
        //THIS MUST BE IMPLEMENTED RECURSIVELY
        PasswordNode toAddNode = new PasswordNode(toAdd);
        if (currentNode.getPassword() == toAdd)
            return false;
        int compare = toAdd.compareTo(currentNode.getPassword(), this.getComparisonCriteria());
        if (compare < 0) {
            if (!currentNode.hasLeftChild()) {
                currentNode.setLeft(toAddNode);
                return true;
            } else {
                return addPasswordHelper(toAdd, currentNode.getLeft());
            }
        } else {
            if (!currentNode.hasRightChild()) {
                currentNode.setRight(toAddNode);
                return true;
            } else {
                return addPasswordHelper(toAdd, currentNode.getRight());
            }
        }
    }

    /**
     * Removes the matching password from the tree
     *
     * @param toRemove, the password to be removed from the tree
     * @throws NoSuchElementException if the password is not in the tree
     */
    public void removePassword(Password toRemove) {
        int compare = 0;
        PasswordNode pred;
        if (root == null)
            return;
        compare = root.getPassword().compareTo(toRemove, COMPARISON_CRITERIA);
        if (compare == 0) {
            if (root.hasRightChild() && root.hasLeftChild()) {
                pred = findPred(root);
                PasswordNode copy = new PasswordNode(pred.getPassword());
                copy.setLeft(root.getLeft());
                copy.setRight(root.getRight());
                copy.setLeft(removePasswordHelper(pred.getPassword(), root.getLeft()));
                root = copy;
            } else if (root.hasLeftChild()) {
                root = root.getLeft();
            } else
                root = root.getRight();
            return;
        }
        root = removePasswordHelper(toRemove, root);
    }

    /**
     * a helper method that finds the predecessor
     *
     * @param currentNode, the root of the tree we are removing from
     * @return the predecessor
     */
    private PasswordNode findPred(PasswordNode currentNode) {
        if (currentNode.hasLeftChild()) {
            currentNode = currentNode.getLeft();
        } else {
            return null;
        }
        while (currentNode.hasRightChild())
            currentNode = currentNode.getRight();
        return currentNode;
    }

    /**
     * Recursive helper method to that removes the password from this BST.
     *
     * @param toRemove,    the password to be removed from the tree
     * @param currentNode, the root of the tree we are removing from
     * @return the PasswordNode representing the NEW root of this subtree now that toRemove
     * has been removed. This may still be currentNode, or it may have changed!
     */
    private PasswordNode removePasswordHelper(Password toRemove, PasswordNode currentNode) {
        PasswordNode pred;
        PasswordNode removeNode = currentNode;
        boolean leftchild = true;
        int numberofchildren;
        int compare = toRemove.compareTo(currentNode.getPassword(), this.getComparisonCriteria());
        //TODO: MUST BE IMPLEMENTED RECURSIVELY
        //BASE CASE: current tree is empty
        if (currentNode == null)
            return null;
        //BASE CASE: current node has no children
        if (!currentNode.hasLeftChild() && !currentNode.hasRightChild())
            return null;
//        if (compare == 0 && currentNode == root) {
//            if (currentNode.hasLeftChild()) {
//                root = currentNode.getLeft();
//            } else if (currentNode.hasRightChild()) {
//                root = currentNode.getRight();
//            } else
//                root = null;
//            return root;
//        }
        //RECURSIVE CASE: toRemove is in the left subtree, continue searching
        //RECURSIVE CASE: toRemove is in the right subtree, continue searching
        if (compare < 0){
            currentNode.setLeft(removePasswordHelper(toRemove, currentNode.getLeft()));
            return currentNode;
        }
        else if (compare > 0) {
            currentNode.setRight(removePasswordHelper(toRemove, currentNode.getRight()));
            return currentNode;
        }

        //otherwise we found the node to remove!
        //If the node to remove is a leaf node
//        if (currentNode.hasLeftChild() && currentNode.getLeft().getPassword() == toRemove) {
//            removeNode = currentNode.getLeft();
//            leftchild = true;
//        } else if (currentNode.hasRightChild() && currentNode.getRight().getPassword() == toRemove) {
//            removeNode = currentNode.getLeft();
//            leftchild = false;
//        } else {
//            compare = currentNode.getPassword().compareTo(toRemove, COMPARISON_CRITERIA);
//            if (compare < 0) {
//                return removePasswordHelper(toRemove, currentNode.getLeft());
//            } else if (compare > 0) {
//                return removePasswordHelper(toRemove, currentNode.getRight());
//
//            }
//        }

        //BASE CASE(S): current node has one child (one for the left and right respectively)
        numberofchildren = removeNode.numberOfChildren();
        if (numberofchildren == 1) {
            if (removeNode.hasLeftChild()) {
                return removeNode.getLeft();
            } else {

                return removeNode.getRight();
            }
            //RECURSIVE CASE: currentNode has 2 children
            //1)Find the predecessor password [HINT: Write a private helper method!]
            //2)Make new node for the predecessor password. It should have same left and right subtree as the current node.
        } else if (currentNode.hasRightChild() && currentNode.hasLeftChild()) {
            pred = findPred(currentNode);
            PasswordNode copy = new PasswordNode(pred.getPassword());
            //3)Replace currentNode with the new predecessor node
            copy.setLeft(currentNode.getLeft());
            copy.setRight(currentNode.getRight());
            //4)Remove the (duplicate) predecessor from the current tree and update the left subtree
            copy.setLeft(removePasswordHelper(pred.getPassword(), currentNode.getLeft()));
            currentNode = copy;
//            if (leftchild == true) {
//                currentNode.setLeft(pred);
//            } else currentNode.setRight(pred);
        } else {
            if (leftchild == true)
                currentNode.setLeft(null);
            else {
                currentNode.setRight(null);
            }
            return root;
        }
        return currentNode; //LEAVE THIS LINE AS IS
    }
}
