package assign06;

import java.util.NoSuchElementException;

/** 
 * This class represents a first-in-first-out (FIFO) queue of elements
 * (backed by a basic array).
 * 
 * @author CS 2420 course staff
 * @version October 3, 2024
 */
public class ArrayQueue<E> implements Queue<E> {

	private E[] arr;
	private int front;
	private int rear;
	private int size;

	/**
	 * Creates a new queue.
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue() {
	    arr = (E[])new Object[100];
	    front = 0;
	    rear = -1;
	    size = 0;
	}
	
	/**
	 * Removes all of the elements from this queue.
	 */
	public void clear() {
		size = 0;
		front = 0;
		rear = -1;
	}
	
	/**
     * Determines whether this queue is empty.
	 * 
	 * @return true if this queue contains no elements; false, otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Adds the given element to this queue, putting it at the rear/back.
	 * 
	 * @param element - the element to add
	 */
	@SuppressWarnings("unchecked")
	public void offer(E element) {
		if(size == arr.length) {
			Object[] temp = new Object[2 * arr.length];
			for(int i = front, j = 0; j < size; j++, i = (i + 1) % size)
				temp[j] = arr[i];
			arr = (E[])temp;
			front = 0;
			rear = size - 1;
		}

		rear = (rear + 1) % arr.length;
		arr[rear] = element;
		size++;
	}
	
	/**
	 * Gets the element at the front of this queue.
	 * 
	 * @return the element at the front of this queue
	 * @throws NoSuchElementException if this queue is empty
	 */
	public E peek() throws NoSuchElementException {
		if(isEmpty())
			throw new NoSuchElementException();
		return arr[front];
	}

	/**
	 * Deletes the element at the front of this queue.
	 * 
	 * @return the element at the front of this queue
	 * @throws NoSuchElementException if this queue is empty
	 */
	public E poll() throws NoSuchElementException {
		if(isEmpty())
			throw new NoSuchElementException();
		E result = arr[front];
		size--;
		front = (front + 1) % arr.length;
		return result;
	}

	/**
	 * Indicates the number of elements in this queue.
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}
}