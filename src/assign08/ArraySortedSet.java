package assign08;

import java.util.*;

/**
 * Array sorted set class that implements the sorted set interface.
 * This set is backed by an array and maintains sorted order for its elements.
 *
 * @param <Type> the type of elements maintained by this set
 * @version 10/31/2024
 */
public class ArraySortedSet<Type extends Comparable<? super Type>> implements SortedSet<Type> {

    private Object[] array;
    private int size;

    /**
     * Constructs an array sorted set.
     */
    public ArraySortedSet() {
        this.array = new Object[32];
        this.size = 0;
    }

    /**
     * Binary search helper method.
     *
     * @param key the data to look for
     * @return the index if the key should be inserted; -1 if it already exists
     */
    private int binarySearch(Type key) {
        if (key == null)
            throw new IllegalArgumentException("Cannot contain null value");

        int low = 0;
        int high = this.size - 1;
        int mid = (low + high) / 2;

        while (high >= low) {
            if (key.compareTo(getElement(mid)) < 0) {
                high = mid - 1;
            } else if (key.compareTo(getElement(mid)) == 0) {
                return -1;
            } else {
                low = mid + 1;
            }
            mid = (low + high) / 2;
        }
        return low;
    }

    @Override
    public boolean add(Type item) {
        if (this.array.length == this.size) {
            Object[] temp = new Object[this.array.length * 2];
            System.arraycopy(this.array, 0, temp, 0, this.array.length);
            this.array = temp;
        }

        int index = binarySearch(item);
        if (index != -1) {
            for (int i = this.size; i > index; i--) {
                this.array[i] = this.array[i - 1];
            }
            this.array[index] = item;
            this.size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Type> items) {
        boolean changed = false;
        for (Type element : items) {
            if (add(element))
                changed = true;
        }
        return changed;
    }

    @Override
    public void clear() {
        this.array = new Object[16];
        this.size = 0;
    }

    @Override
    public boolean contains(Type item) {
        return (binarySearch(item) == -1);
    }

    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        for (Type element : items) {
            if (!contains(element))
                return false;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public Type min() {
        if (this.size == 0)
            throw new NoSuchElementException("Set is empty");
        return getElement(0);
    }

    @Override
    public Type max() {
        if (this.size == 0)
            throw new NoSuchElementException("Set is empty");
        return getElement(this.size - 1);
    }

    @Override
    public boolean remove(Type item) {
        return SortedSet.super.remove(item);
    }

    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        return SortedSet.super.removeAll(items);
    }

    @Override
    public int size() {
        return this.size;
    }

    @SuppressWarnings("unchecked")
    public Type getElement(int index) {
        return (Type) array[index];
    }

    @Override
    public ArrayList<Type> toArrayList() {
        ArrayList<Type> temp = new ArrayList<>();
        for (int i = 0; i < this.size; i++)
            temp.add(getElement(i));
        return temp;
    }
}
