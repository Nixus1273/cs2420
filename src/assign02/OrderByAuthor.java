package assign02;

import java.util.Comparator;

/**
 * This class defines a custom comparison for library books, using Author Name.
 * 
 * @author CS 2420 Prof. Aaron Wood and Ethan Laynor and Brandon Flickinger
 * @version September 5, 2024
 */
public class OrderByAuthor<Type> implements Comparator<LibraryBookGeneric<Type>> {

	/**
	 * Compares two library books, using Author Name.
	 * 
	 * @return positive integer if the first library book's Author's Name is after the second
	 *         negative integer if the first library book's Author's Name is before the second
	 *         0 if the Author's name are the same
	 */
	public int compare(LibraryBookGeneric<Type> firstBook, LibraryBookGeneric<Type> secondBook) {
		if(firstBook.getAuthorSurname().compareTo(secondBook.getAuthorSurname()) != 0)
			return firstBook.getAuthorSurname().compareTo(secondBook.getAuthorSurname());
			
		if(firstBook.getTitle().compareTo(secondBook.getTitle()) != 0)
			return firstBook.getTitle().compareTo(secondBook.getTitle());

		return 0;
	}
	
	
	
}
