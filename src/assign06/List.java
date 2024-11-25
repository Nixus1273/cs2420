package assign06;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Classes that implement this interface represent an ordered collection,
 * in which there is a first element, a second element, and so on.
 * 
 * @author CS 2420 course staff
 * @version October 3, 2024
 */
public interface List<E> extends Iterable<E> {
	
	/**
	 * Removes all the elements from this list.
	 * O(1) running-time behavior for a singly-linked list.
	 */
	void clear();
	
	/**
	 * Deletes and returns the element at a specific position in this list.
	 * O(N) running-time behavior for a singly-linked list, in the average case.
	 * 
	 * @param index - the specified position
	 * @return the element at the position
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 */
	E delete(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Deletes and returns the first element from this list.
	 * O(1) running-time behavior for a singly-linked list.
	 * 
	 * @return the first element
	 * @throws NoSuchElementException if this list is empty
	 */
	E deleteFirst() throws NoSuchElementException;
	
	/**
	 * Gets the element at a specific position in this list.
	 * O(N) running-time behavior for a singly-linked list, in the average case.
	 * 
	 * @param index - the specified position
	 * @return the element at the position
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 */
	E get(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Gets the first element in this list.
	 * O(1) running-time behavior for a singly-linked list.
	 * 
	 * @return the first element in this list
	 * @throws NoSuchElementException if this list is empty
	 */
	E getFirst() throws NoSuchElementException;
	
	/**
	 * Gets the last element in this list.
	 * O(1) running-time behavior for a singly-linked list with an end/tail reference.
	 * 
	 * @return the last element in this list
	 * @throws NoSuchElementException if this list is empty
	 */
	E getLast() throws NoSuchElementException;
	
	/**
	 * Determines the index of the first occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * O(N) running-time behavior for a singly-linked list, in the average case.
	 * 
	 * @param element - the element to search for
	 * @return the index of the first occurrence; -1 if the element is not found
	 */
	int indexOf(E element);

	/**
	 * Inserts an element at a specific position in this list.
	 * O(N) running-time behavior for a singly-linked list, in the average case.
	 * 
	 * @param index - the specified position
	 * @param element - the element to add
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 */
	void insert(int index, E element) throws IndexOutOfBoundsException;
	
	/**
	 * Inserts an element at the beginning of this list.
	 * O(1) running-time behavior for a singly-linked list.
	 * 
	 * @param element - the element to add
	 */
	void insertFirst(E element);
	
	/**
	 * Inserts an element at the end of this list.
	 * O(1) running-time behavior for a singly-linked list with an end/tail reference.
	 * 
	 * @param element - the element to add
	 */
	void insertLast(E element);
	
	/**
	 * Indicates whether this list is empty.
	 * O(1) running-time behavior for a singly-linked list.
	 * 
	 * @return true if this list contains no elements; false, otherwise
	 */
	boolean isEmpty();
	
	/**
	 * Creates and returns an iterator for this list.
	 * 
	 * @return an iterator over the elements in this list in proper sequence (from first 
	 * element to last element)
	 */
	@NotNull
	public Iterator<E> iterator();
	
	/**
	 * Indicates the number of elements in this list.
	 * O(1) running-time behavior for a singly-linked list.
	 * 
	 * @return the number of elements in this list
	 */
	int size();
	
	/**
	 * Generates an array containing all the elements in this list in proper sequence
	 * (from first element to last element).
	 * O(N) running-time behavior for a singly-linked list.
	 * 
	 * @return an array containing all the elements in this list, in order
	 */
	Object[] toArray();
}