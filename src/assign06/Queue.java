package assign06;

import java.util.NoSuchElementException;

/**
 * Classes that implement this interface represent a first-in-first-out (FIFO)
 * queue of elements. The running-time behavior of each method should be O(1).
 * 
 * @author CS 2420 course staff
 * @version October 3, 2024
 */
public interface Queue<E> {

	/**
	 * Removes all the elements from the queue.
	 */
	public void clear();

	/**
     * Indicates whether this queue is empty.
	 * 
	 * @return true if this queue contains no elements; false, otherwise
	 */
	public boolean isEmpty();

	/**
	 * Adds the given element to this queue, putting it at the rear/back of the queue.
	 * 
	 * @param element - the element to add
	 */
	public void offer(E element); 
	
	/**
	 * Gets the element at the front of this queue.
	 * 
	 * @return the element at the front of this queue
	 * @throws NoSuchElementException if this queue is empty
	 */
	public E peek() throws NoSuchElementException;

	/**
	 * Deletes and returns the element at the front of this queue.
	 * 
	 * @return the element at the front of this queue
	 * @throws NoSuchElementException if this queue is empty
	 */
	public E poll() throws NoSuchElementException; 
	
	/**
	 * Indicates the number of elements in this queue.
	 * 
	 * @return the number of elements in this queue
	 */
	public int size();
}