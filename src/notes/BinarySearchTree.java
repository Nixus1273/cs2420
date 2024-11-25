package notes;

/**
 * Class to represent a generic binary search tree.
 *
 * @implNote For every node n
 *      - all nodes in the left subtree of n have data smaller than n.data
 *      - all nodes in the right subtree of n have data larger than n.data
 * @param <E> - data type for the binary search tree
 *
 * @author Aaron Wood
 * @version 2024-10-24
 */
public class BinarySearchTree<E extends Comparable<? super E>> {
    private BinaryNode<E> root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * @return the largest value in the BST.
     * @implSpec runs in O(tree height)
     */
    public E findMax() {
        /*
        Recursive implementation: use this method as a driver and add a recursive findMax method in the BinaryNode class
            - what is the base case?
            - what is the recursive call?

        Iterative implementation:
            - set the current node to be the root
            - check if current node is null
                - what if it is null?
            - while current node has a right child, set current node to right child
            - return data at current node
         */

        return null;
    }

    /**
     * @return the smallest value in the BST.
     * @implSpec runs in O(tree height)
     */
    public E findMin() {
        // similar to findMax, but uses left child instead of right child

        return null;
    }

    /**
     * Determine whether an item is contained in the BST.
     * @param item - item to search for
     * @return true if item is present, false otherwise
     * @implSpec runs in O(tree height)
     */
    public boolean contains(E item) {
        /*
        Recursive implmentation: use this method as a driver and add a recursive contains method in the BinaryNode class
            - what is the base case?
            - what is the recursive call?

        Iterative implementation:
            - set the current node to be the root
            - check if current node is null
                - what if it is null?
            - compare item to current node data
                - if smaller, ???
                - if larger, ???
                - if equal, ???
         */
        return false;
    }

    /**
     * Add a new item to the BST if it is not present.
     * @param item - item to add
     * @implSpec runs in O(tree height)
     */
    public void add(E item) {
        /*
        Recursive implmentation: use this method as a driver and add a recursive add method in the BinaryNode class
            - what is the base case?
            - what is the recursive call?

        Iterative implementation:
            - set the current node to be the root
            - check if current node is null
                - what if it is null?
            - compare item to current node data
                - if smaller
                    - if current node has a left child, set current node to the left child
                    - else build new node to be the left child
                - if larger
                    - if current node has a right child, set current node to the right child
                    - else build new node to be the right child
                - if equal, do nothing (item is a duplicate)
         */
    }

    /**
     * Remove an item from the BST if it is present.
     * @param item - item to remove
     * @implSpec runs in O(tree height)
     */
    public void remove(E item) {
        /*
        Recursive implmentation: use this method as a driver and add a recursive remove method in the BinaryNode class
            - what is the base case?
            - what is the recursive call?

        Iterative implementation:
            - set the current node to be the root
            - check if current node is null
                - what if it is null?
            - compare item to current node data
                - if smaller
                    - if current node has a left child, set current node to the left child
                    - else return since the item was not found
                - if larger
                    - if current node has a right child, set current node to the right child
                    - else return since the item was not found
                - if equal, remove the current node

        How to remove a node
            - CASE A: node is a leaf
                - delete node by setting the parent's appropriate child reference to null
            - CASE B: node has one child
                - bypass node by setting the parent's appropriate child reference to node's only child
            - CASE C: node has two children
                - find successor node (smallest node in node's right subtree)
                - replace node data with successor node data
                - remove the successor node (will be either CASE A or CASE B)
         */
    }

    /**
     * @return the DOT representation of the BST
     */
    public String generateDotString() {
        if (root == null) {
            return "digraph Tree {\n}";
        }
        return root.generateDotString();
    }
}
