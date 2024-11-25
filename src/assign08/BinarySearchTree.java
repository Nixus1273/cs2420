package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Binary search tree class that implements the sorted set interface
 *
 * @author Ethan Laynor and Brandon Flickinger
 * @version 10/31/2024
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type>{

    private BinaryNode<Type> root;
    private int size;


    /**
     * Constructs a binary search tree
     */
    public BinarySearchTree(){
        this.root = new BinaryNode<Type>(null);
        this.size = 0;
    }


    /**
     * Creates a binary node with two children, a previous, and some data
     */
    private static class BinaryNode<Type extends Comparable<? super Type>>{
        private Type data;
        private BinaryNode<Type> leftChild;
        private BinaryNode<Type> rightChild;
        private BinaryNode<Type> previous;


        /**
         * Creates a binary node with the given data and child references.
         *
         * @param data - data to be contained in this node
         * @param leftChild - reference to this node's left child
         * @param rightChild - reference to this node's right child
         */
        public BinaryNode(Type data, BinaryNode<Type> leftChild, BinaryNode<Type> rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.previous = this;
        }


        /**
         * Creates a binary node with the given data, and both child references set to null.
         *
         * @param data data to be contained in this node
         */
        public BinaryNode(Type data) {
            this(data, null, null);
        }


        /**
         * Gets the reference to the leftmost node in the binary tree rooted at this binary node.
         *
         * @return reference to the leftmost node in the binary tree rooted at this node
         */
        private BinaryNode<Type> getLeftmostNode() {
            if(this.leftChild == null)
                return this;

            return this.leftChild.getLeftmostNode();
        }


        /**
         * Gets the reference to the rightmost node in the binary tree rooted at this binary node.
         *
         * @return reference to the rightmost node in the binary tree rooted at this node
         */
        public BinaryNode<Type> getRightmostNode() {
            if(this.rightChild == null)
                return this;

            return this.rightChild.getRightmostNode();
        }


        /**
         * Gets the successor node, the leftmost node of its right child
         *
         * @return the successor node
         */
        public BinaryNode<Type> successor(){
            if(this.rightChild.getLeftmostNode() == null){
                return this.rightChild;
            } else {
                return this.rightChild.getLeftmostNode();
            }
        }


        /**
         * Removes a node with no children
         */
        public void removeLeafNode(){ // should this be in the node class?
            if(this.data.compareTo(this.previous.data) < 0){
                this.previous.leftChild = null;
            } else {
                this.rightChild = null;
            }
        }


        /**
         * Removes a node with one child
         */
        public void removeOneChildNode(){
            if(this.data.compareTo(this.previous.data) > 0){
                if(this.leftChild == null) {
                    this.previous.rightChild = this.rightChild; // replace previous right with rightChild
                } else {
                    this.previous.rightChild = this.leftChild; // replace previous right with leftChild
                }
            } else {
                if(this.rightChild == null) {
                    this.previous.leftChild = this.leftChild; // replace previous left with leftChild
                } else {
                    this.previous.leftChild = this.rightChild; // replace previous left with rightChild
                }
            }
        }


        /**
         * Removes a node with two children
         */
        public void removeTwoChildNode(){
            BinaryNode<Type> successor = this.successor();
            this.data = successor.data;
            if(successor.rightChild == null && successor.leftChild == null){
                successor.removeLeafNode();
            } else if(successor.leftChild != null){
                successor.removeOneChildNode();
            }
        }
    }


    /**
     * Traverse the node and locate the correct position it should be in, it will return a leaf node if it is not
     * found in the tree, must compare the data to assure its contained or not
     *
     * @param nodeData the desired data being searched for
     * @return the node where it should be or the leaf node
     */
    public BinaryNode<Type> nodeTraversal(Type nodeData){ // move to node class?
        BinaryNode<Type> current = this.root; // start at the root always
        while(current != null){
            if (current.data.compareTo(nodeData) == 0) {
                return current;
            }

            if (current.data.compareTo(nodeData) > 0) {
                if(current.leftChild == null){
                    return current; // this will give us the 'last' node in the line
                }
                current = current.leftChild;

            } else {
                if (current.rightChild == null) {
                    return current; // this will give us the 'last' node in the line
                }
                current = current.rightChild;
            }
        }
        return null;
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(Type item) {
        if(root.data == null){
            root.data = item;
            size++;
            return true;
        }

        BinaryNode<Type> location = nodeTraversal(item);
        if(location.data.equals(item)){
            return false;
        } else if(location.data.compareTo(item) > 0){
            location.leftChild = new BinaryNode<>(item, null, null);
            location.leftChild.previous = location;
        } else {
            location.rightChild = new BinaryNode<>(item, null, null);
            location.rightChild.previous = location;
        }
        this.size++;
        return true;
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     */
    @Override
    public boolean addAll(Collection<? extends Type> items) {
        boolean changed = false;
        for (Type element : items) {
            if (add(element))
                changed = true;
        }
        return changed;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     */
    @Override
    public boolean contains(Type item) {
        BinaryNode<Type> location = nodeTraversal(item);
        return location.data.equals(item);
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        for (Type element : items) {
            if (!contains(element))
                return false;
        }
        return true;
    }


    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }


    /**
     * Returns the smallest item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type min() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException("BinarySearchTree is empty...");
        return root.getLeftmostNode().data;
    }


    /**
     * Returns the largest item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Type max() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException("BinarySearchTree is empty...");
        return root.getRightmostNode().data;
    }


    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is,
     * if the input item was actually removed); otherwise, returns false
     * @throws UnsupportedOperationException if the {@code remove} operation is
     *                                       not supported by the derived class
     */
    @Override
    public boolean remove(Type item) {
        BinaryNode<Type> location = nodeTraversal(item);
        if(isEmpty() || !location.data.equals(item)){
            return false;
        }

        if(location.leftChild == null && location.rightChild == null){
            location.removeLeafNode();
        } else if(location.leftChild != null && location.rightChild == null ||
                location.leftChild == null && location.rightChild != null){
            location.removeOneChildNode();
        } else {
            location.removeTwoChildNode();
        }
        size--;
        return true;
    }


    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is,
     * if any item in the input collection was actually removed); otherwise,
     * returns false
     */
    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        boolean changed = false;
        for (Type element : items) {
            if (remove(element))
                changed = true;
        }
        return changed;
    }


    /**
     * Gets the size of the BST
     *
     * @return size
     */
    @Override
    public int size() {
        return this.size;
    }


    /**
     * Returns an ArrayList containing all the items in this set, in sorted order
     *
     * @return array in ascending order
     */
    @Override
    public ArrayList<Type> toArrayList() {
        ArrayList<Type> aux = new ArrayList<>();
        if (!isEmpty()) {
            inOrderTraversal(this.root, aux);
        }
        return aux;
    }


    /**
     * Runs an in order traversal of the binary search tree
     *
     * @param node - the current node
     * @param array - array to add elements to
     */
    public void inOrderTraversal(BinaryNode<Type> node, ArrayList<Type> array){
        if(node.leftChild != null){
            inOrderTraversal(node.leftChild, array);
        }
        array.add(node.data);
        if (node.rightChild != null) {
            inOrderTraversal(node.rightChild, array);
        } else {
            return;
        }
    }
}
