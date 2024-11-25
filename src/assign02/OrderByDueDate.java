package assign02;

import java.util.Comparator;


/**
 * This class defines a custom comparison for library books, using Due dDate.
 * 
 * @author CS 2420 Prof. Aaron Wood and Ethan Laynor and Brandon Flickinger
 * @version September 5, 2024
 */
public class OrderByDueDate<Type> implements Comparator<LibraryBookGeneric<Type>> {

	/**
	 * Compares two library books, using Due Date.
	 * 
	 * @return positive integer if the first library book's 
	 *         negative integer if the first library book's 
	 *         0 if the Author's name are the same
	 */
	public int compare(LibraryBookGeneric<Type> firstBook, LibraryBookGeneric<Type> secondBook) {

		if(firstBook.getDueDate() == null && secondBook.getDueDate() == null)
			return 0;
		
		if(secondBook.getDueDate() == null)
			return -1;
		
		if(firstBook.getDueDate() == null)
			return 1;
			
		return firstBook.getDueDate().compareTo(secondBook.getDueDate());
	}
	

	
	
	
}
