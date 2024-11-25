package assign06;

import java.util.NoSuchElementException;

/**
 * Classes that implement this interface represent a first-in-first-out (FIFO)
 * queue of elements. The running-time behavior of each method should be O(1).
 *
 * @author Brandon Flickinger
 * @version October 17, 2024
 */
public class LinkedListQueue<E> implements Queue<E>{

    private final SinglyLinkedList<E> list;

    public LinkedListQueue(){
        list = new SinglyLinkedList<>();
    }

    /**
     * Removes all the elements from the queue.
     */
    @Override
    public void clear() {
        list.clear();
    }

    /**
     * Indicates whether this queue is empty.
     *
     * @return true if this queue contains no elements; false, otherwise
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Adds the given element to this queue, putting it at the rear/back of the queue.
     *
     * @param element - the element to add
     */
    @Override
    public void offer(E element) {
        if (size() == 0) {
            list.insertFirst(element);
        } else {
            list.insertLast(element);
        }
    }


    /**
     * Gets the element at the front of this queue.
     *
     * @return the element at the front of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        if(isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return list.getFirst();
    }

    /**
     * Deletes and returns the element at the front of this queue.
     *
     * @return the element at the front of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
    public E poll() throws NoSuchElementException {
        E deleted = list.getFirst();
        list.deleteFirst();
        return deleted;
    }

    /**
     * Indicates the number of elements in this queue.
     *
     * @return the number of elements in this queue
     */
    @Override
    public int size() {
        return list.size;
    }
}