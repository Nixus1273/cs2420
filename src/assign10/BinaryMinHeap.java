package assign10;

import java.util.List;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * This class represents a priority queue using a binary min heap
 *
 * @author Ethan Laynor and Brandon Flickinger
 * @version November 20, 2024
 */
public class BinaryMinHeap<E> implements PriorityQueue<E> {
    private E[] array;
    private int size;
    private final Comparator<? super E> cmp;

    /**
     * Constructs BinaryMinHeap with no parameters.
     * If this constructor is used to create an empty binary heap,
     * it is assumed that the elements are ordered using their natural ordering
     */
    @SuppressWarnings("unchecked")
    public BinaryMinHeap() {
        this.array = (E[]) new Object[32];
        this.size = 0;
        this.cmp = null;
    }

    /**
     * Constructs BinaryMinHeap with comparator
     * If this constructor is used to create an empty binary heap,
     * it is assumed that the elements are ordered using the provided Comparator object.
     */
    @SuppressWarnings("unchecked")
    public BinaryMinHeap(Comparator<? super E> cmp){
        this.array = (E[]) new Object[32];
        this.size = 0;
        this.cmp = cmp;
    }

    /**
     * Constructs BinaryMinHeap with a List
     * If this constructor is used, then the binary heap is constructed from the given list.
     * Also, it is assumed that the elements are ordered using their natural ordering
     */
    @SuppressWarnings("unchecked")
    public BinaryMinHeap(List<? extends E> list) {
        this.array = (E[]) new Object[32];
        this.size = 0;
        this.cmp = null;
        buildHeap(list);
    }

    /**
     * Constructs BinaryMinHeap with a list and comparator
     * If this constructor is used, then the binary heap is constructed from the given list.
     * Also, it is assumed that the elements are ordered using the provided Comparator object.
     */
    @SuppressWarnings("unchecked")
    public BinaryMinHeap(List<? extends E> list, Comparator<? super E> cmp) {
        this.array = (E[]) new Object[32];
        this.size = 0;
        this.cmp = cmp;
        buildHeap(list);
    }

    /**
     * Adds the given element to this priority queue.
     * O(1) in the average case, O(log N) in the worst case
     *
     * @param element - element to be added to this priority queue
     */
    @Override
    @SuppressWarnings("unchecked")
    public void add(E element) {
        if (isEmpty()) {
            array[0] = element;
            size++;
        } else {
            if (size + 1 > array.length) {
                E[] temp = (E[]) new Object[array.length * 2];
                System.arraycopy(this.array, 0, temp, 0, this.size);
                this.array = temp;
            }
            array[size++] = element;
            percolateUp(size - 1);
        }
    }

    /**
     * Gets the highest-priority element this priority queue.
     * O(1)
     *
     * @return highest-priority element in this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        if (this.size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        return this.array[0];
    }

    /**
     * Gets and removes the highest-priority element this priority queue.
     * O(log N)
     *
     * @return highest-priority element in this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public E extract() throws NoSuchElementException {
        if (this.size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        E min = this.array[0];
        this.array[0] = array[this.size - 1];
        this.array[this.size - 1] = null;
        this.size--;
        percolateDown(0);
        return min;
    }

    /**
     * Gets the number of elements in this priority queue.
     * O(1)
     *
     * @return number of elements in this priority queue
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Determines whether this priority queue is empty.
     * O(1)
     *
     * @return true if this priority queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Empties this priority queue of elements.
     * O(1)
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        this.array = (E[]) new Object[32];
        this.size = 0;
    }

    /**
     * Generates an array of the elements in this priority queue,
     * in the same order they appear in the backing array.
     * O(N)
     * <p>
     * (NOTE: This method is used for grading. The root element
     * must be stored at index 0 in the returned array, regardless of
     * whether it is in stored there in the backing array.)
     */
    @Override
    public Object[] toArray() {
        Object[] temp = new Object[this.size];
        System.arraycopy(this.array, 0, temp, 0, this.size);
        return temp;
    }

    /**
     * Helper method to compare two items
     *
     * @param o1 - first object
     * @param o2 - second object
     * @return result of comparison
     */
    @SuppressWarnings("unchecked")
    private int innerCompare(E o1, E o2) {
        if (cmp == null) {
            return ((Comparable<? super E>) o1).compareTo(o2);
        }
        return this.cmp.compare(o1, o2);
    }

    /**
     * Percolate up the binary heap
     *
     * @param current - index to percolate
     */
    private void percolateUp(int current) {
        int parent = (current - 1) / 2;
        if (innerCompare(this.array[current], this.array[parent]) < 0) {
            swap(current, parent);
            percolateUp(parent);
        }
    }

    /**
     * Percolate down the binary heap
     *
     * @param current - index to percolate down
     */
    private void percolateDown(int current) {
        int left = current * 2 + 1;
        int right = current * 2 + 2;
        if (left < this.size && right < this.size) {
            if (innerCompare(array[current], array[left]) < 0 && innerCompare(array[current], array[right]) < 0) {
                return;
            }
            if (innerCompare(this.array[left], this.array[right]) < 0) {
                swap(current, left);
                percolateDown(left);
            } else {
                swap(current, right);
                percolateDown(right);
            }
        } else if (left < this.size - 1) {
            if (innerCompare(this.array[current], this.array[left]) < 0) {
                swap(current, left);
                percolateDown(left);
            }
        }
    }

    /**
     * Builds a binary min heap from a given list
     *
     * @param list - list to heapify
     */
    private void buildHeap(List<? extends E> list) {
        for (int i = 0; i < list.size(); i++) {
            this.array[i] = list.get(i);
            this.size++;
        }
        for (int i = list.size() / 2; i >= 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * Swap two entries
     *
     * @param idx1 - first index
     * @param idx2 - second index
     */
    private void swap(int idx1, int idx2) {
        E temp = array[idx1];
        this.array[idx1] = array[idx2];
        this.array[idx2] = temp;
    }
}
