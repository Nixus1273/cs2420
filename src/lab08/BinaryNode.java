package lab08;

/**
 * Represents a generically-typed binary tree node. Each binary node contains data, a 
 * reference to the left child, and a reference to the right child.
 * 
 * @author CS 2420 course staff
 * @version October 25, 2024
 */
public class BinaryNode<Type> {

	private Type data;
	private BinaryNode<Type> leftChild;
	private BinaryNode<Type> rightChild;

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
	}

	/**
	 * Creates a binary node with the given data, and both child references set to null.
	 * 
	 * @param data - data to be contained in this node
	 */
	public BinaryNode(Type data) {
		this(data, null, null);
	}

	/**
	 * Gets this binary node's data.
	 * 
	 * @return this node's data
	 */
	public Type getData() {
		return this.data;
	}

	/**
	 * (Re)sets this binary node's data.
	 * 
	 * @param data - data to be contained in this node 
	 */
	public void setData(Type data) {
		this.data = data;
	}

	/**
	 * Gets the reference to this binary node's left child.
	 * 
	 * @return reference to this node's left child
	 */
	public BinaryNode<Type> getLeftChild() {
		return this.leftChild;
	}
	
	/**
	 * Gets the reference to this binary node's right child.
	 * 
	 * @return reference to this node's right child
	 */
	public BinaryNode<Type> getRightChild() {
		return this.rightChild;
	}

	/**
	 * Sets this binary node's left child reference.
	 * 
	 * @param leftChild - reference for setting this node's left child
	 */
	public void setLeftChild(BinaryNode<Type> leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * Sets this binary node's right child reference.
	 * 
	 * @param rightChild - reference for setting this node's right child
	 */
	public void setRightChild(BinaryNode<Type> rightChild) {
		this.rightChild = rightChild;
	}

	/**
	 * Gets the reference to the leftmost node in the binary tree rooted at this binary node.
	 * 
	 * @return reference to the leftmost node in the binary tree rooted at this node
	 */
	public BinaryNode<Type> getLeftmostNode() {
		if (getLeftChild() == null) {
			return this;
		}
		return getLeftChild().getLeftmostNode();
	}

	/**
	 * Gets the reference to the rightmost node in the binary tree rooted at this binary node.
	 * 
	 * @return reference to the rightmost node in the binary tree rooted at this node
	 */
	public BinaryNode<Type> getRightmostNode() {
		if (getRightChild() == null) {
			return this;
		}
		return getRightChild().getRightmostNode();
	}

	/**
	 * Gets the height of the binary tree rooted at this binary node, where the height of a 
	 * tree is the length of the longest path to a leaf node (e.g., a tree with a single 
	 * node has a height of zero).
	 * 
	 * @return the height of the binary tree rooted at this node
	 */
	public int height() {
		if (getLeftChild() == null && getRightChild() == null) {
			return 0;
		} else if (getLeftChild() == null && getRightChild() != null) {
			return getRightChild().height() + 1;
		} else if (getLeftChild() != null && getRightChild() == null) {
			return getLeftmostNode().height() + 1;
		} else {
			return Math.max(getLeftChild().height(), getRightChild().height()) + 1;
		}
	}

	/**
	 * Gets the successor of a Node for a remove method
	 *
	 * @return the successor Node
	 */
	public BinaryNode<Type> successor() {
		if (getRightChild().getLeftmostNode() == null) {
			return getRightChild();
		} else {
			return getRightChild().getLeftmostNode();
		}
	}
}