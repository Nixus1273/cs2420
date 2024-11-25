package notes;

/**
 * Class to demonstrate two ways to reverse a linked list.
 *
 * @author Aaron Wood
 * @version 2024-10-15
 */
public class LinkedListReverser {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(5);
        linkedList.add(7);
        linkedList.add(2);
        linkedList.add(10);
        System.out.println("linked list: " + linkedList);

        System.out.println("\nreversing the linked list");
        linkedList.reverse1();
        System.out.println("linked list: " + linkedList);

        System.out.println("\nreversing the linked list");
        linkedList.reverse2();
        System.out.println("linked list: " + linkedList);
    }

    public static class LinkedList {
        private int length;
        private Node head;

        public LinkedList() {
            this.length = 0;
            this.head = null;
        }

        private static class Node {
            public int value;
            public Node next;

            public Node(int value, Node next) {
                this.value = value;
                this.next = next;
            }
        }

        /**
         * Adds value to front of the linked list.
         * @param value - value to add
         */
        public void add(int value) {
            head = new Node(value, head);
            length++;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            Node node = head;
            while (node != null) {
                builder.append(node.value);
                builder.append(" -> ");
                node = node.next;
            }
            builder.append("\0");
            return builder.toString();
        }

        /**
         * Reverse the list using O(N) storage.
         */
        public void reverse1() {
            // store values in temp array
            int[] values = new int[length];
            int index = 0;
            Node node = head;
            while (node != null) {
                values[index] = node.value;
                node = node.next;
                index++;
            }

            // set values of nodes from the back of the array to the front
            node = head;
            while (node != null) {
                index--;
                node.value = values[index];
                node = node.next;
            }
        }

        /**
         * Reverse the list using O(1) storage.
         */
        public void reverse2() {
            // keep track of prev and curr nodes
            Node prev = null;
            Node curr = head;

            // traverse the list
            while (curr != null) {
                // store node after curr
                Node next = curr.next;

                // reverse the reference to curr -> prev
                curr.next = prev;

                // advance the pair of nodes forward
                prev = curr;
                curr = next;
            }

            // set head to be the last node
            head = prev;
        }
    }
}
