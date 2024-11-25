package assign03;

import java.util.NoSuchElementException;

/**
 * Classes that implement this interface represent a list in sorted order,
 * from smallest to largest.
 * 
 * @author CS 2420 course staff
 * @version September 5, 2024
 */
public interface SortedList<E> {

	/**
	 * Removes all of the elements from this sorted list.
	 */
	void clear();

	/**
	 * Determines whether the specified element exists in this sorted list.
	 * 
	 * @param element - the element whose existence is being checked
	 * @return true if the element exists in this sorted list, false otherwise
	 */
	boolean contains(E element);

	/**
	 * Determines the number of elements in this sorted list that are equal to the
	 * specified target.
	 * 
	 * @param target - the target whose existence is being counted
	 * @return the number of elements in this sorted list that are equal to the
	 *         specified target
	 */
	int countEntries(E target);

	/**
	 * Inserts the specified element into this sorted list.
	 * 
	 * @param element - the element to insert
	 */
	void insert(E element);

	/**
	 * Determines whether this sorted list contains any elements.
	 * 
	 * @return true if this sorted list contains no elements, false otherwise
	 */
	boolean isEmpty();

	/**
	 * Gets the largest element in this sorted list.
	 * 
	 * @return the largest element in this sorted list
	 * @throws NoSuchElementException if this sorted list is empty
	 */
	E max() throws NoSuchElementException;

	/**
	 * Gets the median element in this sorted list. If this sorted list contains an
	 * even number of elements, gets the larger of two middle elements.
	 * 
	 * @return the median element in this sorted list
	 * @throws NoSuchElementException if this sorted list is empty
	 */
	E median() throws NoSuchElementException;

	/**
	 * Gets the smallest element in this sorted list.
	 * 
	 * @return the smallest element in this sorted list
	 * @throws NoSuchElementException if this sorted list is empty
	 */
	E min() throws NoSuchElementException;

	/**
	 * Gets the number of elements in this sorted list.
	 * 
	 * @return the number of elements in this sorted list
	 */
	int size();

	/**
	 * Generates an array containing all of elements in this sorted list, in order.
	 * 
	 * @return an array containing all of elements in this sorted list
	 */
	Object[] toArray();
}