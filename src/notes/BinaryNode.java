package notes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Class to represent a generic binary node.
 *
 * @param <T> - data type of the binary node
 *
 * @author Aaron Wood
 * @version 2024-10-24
 */
public class BinaryNode<T> {
    private T data;
    private BinaryNode<T> leftChild;
    private BinaryNode<T> rightChild;

    public BinaryNode(T data) {
        this(data, null, null);
    }

    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data;
        this.leftChild = left;
        this.rightChild = right;
    }

    /**
     * @return - list of data in the node's subtree in pre-order.
     */
    public List<T> getPreOrder() {
        List<T> list = new ArrayList<>();
        list.add(data);
        if (leftChild != null) {
            list.addAll(leftChild.getPreOrder());
        }
        if (rightChild != null) {
            list.addAll(rightChild.getPreOrder());
        }
        return list;
    }

    /**
     * @return - list of data in the node's subtree in post-order.
     */
    public List<T> getPostOrder() {
        List<T> list = new ArrayList<>();
        if (leftChild != null) {
            list.addAll(leftChild.getPostOrder());
        }
        if (rightChild != null) {
            list.addAll(rightChild.getPostOrder());
        }
        list.add(data);
        return list;
    }

    /**
     * @return - list of data in the node's subtree in in-order.
     */
    public List<T> getInOrder() {
        List<T> list = new ArrayList<>();
        if (leftChild != null) {
            list.addAll(leftChild.getInOrder());
        }
        list.add(data);
        if (rightChild != null) {
            list.addAll(rightChild.getInOrder());
        }
        return list;
    }

    /**
     * @return - list of data in the node's subtree in level-order.
     */
    public List<T> getLevelOrder() {
        List<T> list = new ArrayList<>();
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            BinaryNode<T> node = queue.poll();
            list.add(node.data);
            if (node.leftChild != null) {
                queue.offer(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.offer(node.rightChild);
            }
        }
        return list;
    }

    /**
     * @return - DOT representation of node's subtree
     */
    public String generateDotString() {
        return "digraph Tree {\n\tnode [shape=record]" + generateInnerDotString() + "\n}";
    }

    /**
     * Recursive method to build the inner portion of DOT representation.
     * @return - inner DOT representation of node's subtree
     */
    private String generateInnerDotString() {
        return dotNode() + dotChild(leftChild, "f0") + dotChild(rightChild, "f2");
    }

    /**
     * @return DOT display for the node
     */
    private String dotNode() {
        return "\n\t"
            + this.data
            + " [label = \"<f0> |<f1> "
            + this.data
            + "|<f2> \"]";
    }

    /**
     * Generate the DOT representation from node to its child
     * @param child - child to generate
     * @param from - label to track whether left or right child
     * @return - DOT representation for child
     */
    private String dotChild(BinaryNode<T> child, String from) {
        if (child == null) {
            return "";
        }
        return "\n\t"
            + this.data
            + ":"
            + from
            + " -> "
            + child.data
            + ":f1"
            + child.generateInnerDotString();
    }
}
