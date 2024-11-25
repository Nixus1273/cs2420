package assign03;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class creates a sorted array list that holds generic types. The class has methods that allow you to insert,
 * sort, clear, count entries, and find the max, median, min, and size of the array.
 *
 * @author Brandon Flickinger and Ethan Laynor
 * @version September 11, 2024
 */
public class SortedArrayList<E> implements SortedList<E>, Iterable<E> {

    private E[] array;
    private final Comparator<? super E> cmp;
    private int size;

    /**
     * Constructs a new SortedArrayList object
     */
    @SuppressWarnings("unchecked")
    public SortedArrayList() {
        this.array = (E[]) new Object[16];
        this.cmp = null;
        this.size = 0;
    }

    /**
     * Constructs a new SortedArrayList object using a comparator
     *
     * @param cmp the comparator to be used
     */
    @SuppressWarnings("unchecked")
    public SortedArrayList(Comparator<? super E> cmp) {
        this.array = (E[]) new Object[16];
        this.cmp = cmp;
        this.size = 0;
    }

    /**
     * Uses a binary search on the array to find the index where the key is or should be
     *
     * @param key the desired element
     * @return the index of where the key is or should be
     */
    private int binarySearch(E key) {
        if (key == null)
            throw new IllegalArgumentException("Cannot contain null value");

        int low = 0;
        int high = this.size - 1;
        int mid = (low + high) / 2;

        while (high >= low) {
            if (compare(key, this.array[mid]) < 0) { // problem of flipped values
                high = mid - 1;
            } else if (compare(key, this.array[mid]) == 0) {
                return mid;
            } else {
                low = mid + 1;
            }
            mid = (low + high) / 2;
        }
        return low;
    }

    /**
     * Removes all the elements from this sorted list.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        E[] empty = (E[]) new Object[array.length];
        this.size = 0;
        this.array = empty;
    }

    /**
     * Determines whether the specified element exists in this sorted list.
     *
     * @param element - the element whose existence is being checked
     * @return true if the element exists in this sorted list, false otherwise
     */
    public boolean contains(E element) {
        if (size == 0) {
            return false;
        }
        return this.array[binarySearch(element)].equals(element);
    }

    /**
     * Determines the number of elements in this sorted list that are equal to the
     * specified target.
     *
     * @param target - the target whose existence is being counted
     * @return the number of elements in this sorted list that are equal to the
     *         specified target
     */
    public int countEntries(E target) {
        if (size == 0) {
            return 0;
        }
        boolean test = true;
        int count = 0;
        int i = 1;
        int index = binarySearch(target);

        if (index < this.size && compare(this.array[index], target) == 0){
            count++;
            while (test) {
                boolean checked = false;  // check if another same value exists
                if (index + i < this.size && compare(this.array[index + i], target) == 0) {
                        count ++;
                        checked = true;
                }
                if (index - i >= 0 && compare(this.array[index - i], target) == 0) {
                        count++;
                        checked = true;
                }
                if(checked) {
                    i++;
                } else {
                    test = false;  // no other values found, break loop
                }
            }
        }
        return count;
    }

    @Override
    public Iterator<E> iterator() {
        return new SortedArrayListIterator<E>();
    }

    private static class SortedArrayListIterator<E> implements Iterator<E> {

        private int nextIndex;
        private boolean nextCalled;
        private E[] elements;

        public SortedArrayListIterator() {
            this.nextIndex = 0;
            nextCalled = false;
        }

        @Override
        public boolean hasNext() {
            return (nextIndex < elements.length);
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            nextCalled = true;
            return elements[nextIndex++];
        }

        @Override
        public void remove() {
            if(!nextCalled) {
                return;
            }
            nextCalled = false;
            nextIndex--;
        }
    }

    /**
     * Inserts the specified element into this sorted list.
     *
     * @param element - the element to insert
     */
    @SuppressWarnings("unchecked")
    public void insert(E element) {
        if (this.array.length == this.size) {
            E[] temp = (E[]) new Object[this.array.length * 2];
            int i = 0;
            while (i < this.array.length) {
                temp[i] = this.array[i];
                i++;
            }
            this.array = temp;
        }

        int index = binarySearch(element);
        for (int i = this.size - 1; i > index; i--) {
            this.array[i] = this.array[i - 1];
        }
        this.array[index] = element;
        this.size++;

    }

    /**
     * Determines whether this sorted list contains any elements.
     *
     * @return true if this sorted list contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Gets the largest element in this sorted list.
     *
     * @return the largest element in this sorted list
     * @throws NoSuchElementException if this sorted list is empty
     */
    public E max() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        return array[size - 1];
    }

    /**
     * Gets the median element in this sorted list. If this sorted list contains an
     * even number of elements, gets the larger of two middle elements.
     *
     * @return the median element in this sorted list
     * @throws NoSuchElementException if this sorted list is empty
     */
    public E median() throws NoSuchElementException {
        if (this.size == 0)
            throw new NoSuchElementException("List is empty");
        return this.array[size / 2];
    }

    /**
     * Gets the smallest element in this sorted list.
     *
     * @return the smallest element in this sorted list
     * @throws NoSuchElementException if this sorted list is empty
     */
    public E min() throws NoSuchElementException {
        if (size == 0)
            throw new NoSuchElementException("List is empty");
        return this.array[0];
    }

    /**
     * Gets the number of elements in this sorted list.
     *
     * @return the number of elements in this sorted list
     */
    public int size() {
        return this.size;
    }

    /**
     * Generates an array containing all elements in this sorted list, in order.
     *
     * @return an array containing all elements in this sorted list
     */
    public Object[] toArray() {
        Object[] temp = new Object[this.size];
        int i = 0;
        while (i < this.size) {
            temp[i] = this.array[i];
            i++;
        }
        return temp;
    }

    /**
     * Checks how to compare two objects based on if it is comparable or comparator
     *
     * @param obj1 first object
     * @param obj2 second object
     * @return integer value of the comparison
     */
    @SuppressWarnings("unchecked")
    private int compare(E obj1, E obj2) {
        if (cmp == null)
            return ((Comparable<? super E>) obj1).compareTo(obj2);
        return this.cmp.compare(obj1, obj2);
    }
}