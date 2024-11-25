package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that represents an ordered collection (singly linked list),
 * in which there is a first element, a second element, and so on.
 *
 * @author Brandon Flickinger
 * @version October 17, 2024
 */
public class SinglyLinkedList<E> implements List<E> {
    Node<E> head;
    Node<E> tail;
    int size;

    public SinglyLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * The node class for storing data and pointers in the singly linked list
     */
    public static class Node<E> {
        private final E data;
        private Node<E> next;

        /**
         * Constructor for the Node class
         *
         * @param element - data in the element
         * @param next - next node in the list
         */
        public Node(E element, Node<E> next) {
            this.data = element;
            this.next = next;
        }
    }

    /**
     * Iterator class to move through the singly linked list
     */
    private class ListIterator implements Iterator<E>  {
        private Node<E> current;
        private Node<E> next;
        private Node<E> previous;

        /**
         * Constructor for the ListIterator class
         */
        public ListIterator() {
            this.current = null;
            this.next = head;
        }

        @Override
        public boolean hasNext() {
            return (this.next != null);
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.previous = this.current;
            this.current = this.next;
            this.next = next.next;

            return this.current.data;
        }

        @Override
        public void remove() {
            if (this.current == null) {
                throw new IllegalStateException();
            }
            if (size == 1) {
                this.current = null;
                head = null;
                tail = null;
            } else if (this.previous == null) {
                head = this.next;
                this.next = null;
                this.current = null;
            } else {
                this.previous.next = this.next;
                if (this.next == null) {
                    tail = this.previous;
                }
                this.current = null;
            }
            size--;
        }
    }

    /**
     * Removes all the elements from this list.
     * O(1) running-time behavior for a singly-linked list.
     */
    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Deletes and returns the element at a specific position in this list.
     * O(N) running-time behavior for a singly-linked list, in the average case.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public E delete(int index) throws IndexOutOfBoundsException {
        if(index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException("index out of bounds for list of size " + this.size);
        }
        E deletedElement = null;
        if (index == 0) {
            deleteFirst();
        } else {
            Node<E> node = this.head;
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            if (index == this.size - 1) {
                deletedElement = node.next.data;
                node.next = null;
                this.tail = node;
            } else {
                deletedElement = node.next.data;
                node.next = node.next.next;
            }
            this.size--;
        }
        return deletedElement;
    }

    /**
     * Deletes and returns the first element from this list.
     * O(1) running-time behavior for a singly-linked list.
     *
     * @return the first element
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public E deleteFirst() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        Node<E> deletedElement = this.head;
        this.head = this.head.next;
        this.size--;
        return deletedElement.data;
    }

    /**
     * Gets the element at a specific position in this list.
     * O(N) running-time behavior for a singly-linked list, in the average case.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("index out of bounds for list of size " + this.size);
        }
        if (index == 0) {
            return this.head.data;
        } else if (index == this.size) {
            return this.tail.data;
        }
        Node<E> node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    /**
     * Gets the first element in this list.
     * O(1) running-time behavior for a singly-linked list.
     *
     * @return the first element in this list
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public E getFirst() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        return this.head.data;
    }

    /**
     * Gets the last element in this list.
     * O(1) running-time behavior for a singly-linked list with an end/tail reference.
     *
     * @return the last element in this list
     * @throws NoSuchElementException if this list is empty
     */
    @Override
    public E getLast() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        return this.tail.data;
    }

    /**
     * Determines the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     * O(N) running-time behavior for a singly-linked list, in the average case.
     *
     * @param element - the element to search for
     * @return the index of the first occurrence; -1 if the element is not found
     */
    @Override
    public int indexOf(E element) {
        Node<E> node = this.head;
        for (int i = 0; i < this.size; i++) {
            if (node.data.equals(element)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    /**
     * Inserts an element at a specific position in this list.
     * O(N) running-time behavior for a singly-linked list, in the average case.
     *
     * @param index - the specified position
     * @param element - the element to add
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
     */
    @Override
    public void insert(int index, E element) throws IndexOutOfBoundsException {
        if(index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("index out of bounds for list of size " + size());
        }
        if (index == 0) {
            insertFirst(element);
        } else if (index >= size() - 1) {
            insertLast(element);
        } else {
            Node<E> node = this.head;
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            node.next = new Node<>(element, node.next);
            this.size++;
        }
    }

    /**
     * Inserts an element at the beginning of this list.
     * O(1) running-time behavior for a singly-linked list.
     *
     * @param element - the element to add
     */
    @Override
    public void insertFirst(E element) {
        if (this.size == 0) {
            this.head = new Node<>(element, null);
            this.tail = this.head;
        } else if (this.size == 1) {
            this.head = new Node<>(element, this.tail);
        } else {
            this.head = new Node<>(element, this.head);
        }
        this.size++;
    }

    /**
     * Inserts an element at the end of this list.
     * O(1) running-time behavior for a singly-linked list with an end/tail reference.
     *
     * @param element - the element to add
     */
    @Override
    public void insertLast(E element) {
        if (this.size == 0) {
            this.tail = new Node<>(element, null);
            this.head = this.tail;
        } else {
            this.tail.next = new Node<>(element, null);
            this.tail = tail.next;
        }
        this.size++;
    }

    /**
     * Indicates whether this list is empty.
     * O(1) running-time behavior for a singly-linked list.
     *
     * @return true if this list contains no elements; false, otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Creates and returns an iterator for this list.
     *
     * @return an iterator over the elements in this list in proper sequence (from first
     * element to last element)
     */
    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    /**
     * Indicates the number of elements in this list.
     * O(1) running-time behavior for a singly-linked list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Generates an array containing all the elements in this list in proper sequence
     * (from first element to last element).
     * O(N) running-time behavior for a singly-linked list.
     *
     * @return an array containing all the elements in this list, in order
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        Node<E> node = this.head;
        for (int i = 0; i < this.size; i++) {
            array[i] = node.data;
            node = node.next;
        }
        return array;
    }
}