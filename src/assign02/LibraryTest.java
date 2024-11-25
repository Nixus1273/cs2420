package assign02;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import assign01.MathVector;

import java.util.ArrayList;

/**
 * This class contains some example tests for Library.
 * Students are expected to add additional tests to this class to thoroughly 
 * check the correctness of Library.
 * 
 * @author Erin Parker and Ethan Laynor and Brandon Flickinger
 * @version September 5, 2024
 */
public class LibraryTest {

	private Library emptyLibrary, tinyLibrary, smallLibrary, addAllTest;
	
	@BeforeEach
	void setUp() throws Exception {
		emptyLibrary = new Library();
		
		tinyLibrary = new Library();
		tinyLibrary.add(9780374292799L, "Friedman", "Thomas L.", "The World is Flat");
		tinyLibrary.add(9780330351690L, "Krakauer", "Jon", "Into the Wild");
		tinyLibrary.add(9780446580342L, "Baldacci", "David", "Simple Genius");

		
		
		smallLibrary = new Library();
		smallLibrary.addAll("src/assign02/Mushroom_Publishing.txt");
		
		addAllTest = new Library();

	}

	@Test
	public void testEmptyLookupISBN() {
		assertEquals(-1, emptyLibrary.lookup(978037429279L));
	}
	
	@Test
	public void testEmptyLookupPatron() {
		ArrayList<LibraryBook> booksCheckedOut = emptyLibrary.lookup(123);
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
	}
	
	@Test
	public void testEmptyCheckOut() {
		assertFalse(emptyLibrary.checkOut(978037429279L, 123, 10, 1, 2024));
	}

	@Test
	public void testEmptyCheckInISBN() {
		assertFalse(emptyLibrary.checkIn(978037429279L));
	}
	
	@Test
	public void testEmptyCheckInPatron() {
		assertFalse(emptyLibrary.checkIn(123));
	}

	@Test
	public void testTinyLibraryLookupISBN() {
		assertEquals(-1, tinyLibrary.lookup(9780330351690L));
	}
	
	@Test
	public void testTinyLibraryLookupPatron() {
		tinyLibrary.checkOut(9780330351690L, 123, 10, 1, 2024);
		ArrayList<LibraryBook> booksCheckedOut = tinyLibrary.lookup(123);
		
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals(new Book(9780330351690L, "Krakauer", "Jon", "Into the Wild"), booksCheckedOut.get(0));
		assertEquals(123, booksCheckedOut.get(0).getPatron());
	}

	@Test
	public void testTinyLibraryCheckOut() {
		assertTrue(tinyLibrary.checkOut(9780330351690L, 123, 10, 1, 2024));
	}

	@Test
	public void testTinyLibraryCheckInISBN() {
		tinyLibrary.checkOut(9780330351690L, 123, 10, 1, 2024);
		assertTrue(tinyLibrary.checkIn(9780330351690L));
	}

	@Test
	public void testTinyLibraryCheckInPatron() {
		assertFalse(tinyLibrary.checkIn(123));
	}
	
	

	
	//New Test--------------------------------------------------------
	
	
	
	//Testing if .addAll throws an error if given a empty ArrayList
	@Test
	public void testaddAllNull() {
		ArrayList<LibraryBook> list = new ArrayList<LibraryBook>();
		assertThrows(NullPointerException.class, () -> { addAllTest.addAll(list); });
	}
	
	
	
	
	//Testing if .checkOut throws an error if given an invalid patron ID
	@Test
	public void testInvalidPatronNumber() {
		assertThrows(IllegalArgumentException.class, () -> { tinyLibrary.checkOut(9780330351690L, -1, 10, 1, 2024); });
	}
	
	//Testing if .checkOut throws an error if given an invalid month
	@Test
	public void testInvalidMonthNumber() {
		assertThrows(IllegalArgumentException.class, () -> { tinyLibrary.checkOut(9780330351690L, 123, -1, 1, 2024); });
	}

	//Testing if .checkOut throws an error if given an invalid day
	@Test
	public void testInvalidDayNumber() {
		assertThrows(IllegalArgumentException.class, () -> { tinyLibrary.checkOut(9780330351690L, 12, 1, -1, 2024); });
	}
	
	
	
	
	
	//Testing if .checkIn returns false if given an ISBN that isn't in the library system
	@Test
	public void testNotFoundISBN() {
		assertFalse(tinyLibrary.checkIn(123456789));
	}
	
	
	//Testing if .checkIn works correctly
	@Test
	public void testCheckInPatron() {
		tinyLibrary.checkOut(9780330351690L, 123, 10, 1, 2024);
		assertTrue(tinyLibrary.checkIn(123));
	}
	
	
	
	
	
	
	//Testing if .toString works correctly
	@Test
	public void testToString() {
		Book testBook = new Book(123456789, "AuthorName", "OtherName", "BookTitle");
		assertEquals("123456789, OtherName AuthorName, \"BookTitle\"",testBook.toString());
	}
	
	
	
	
	
	//Testing if that .equals returns false when two Book objects aren't the same
	@Test
	public void testEqualsBookFalse() {
		Book testBook1 = new Book(123456789, "AuthorName", "OtherName", "BookTitle");
		Book testBook2 = new Book(1, "Author", "Other", "Book");
		assertFalse(testBook1.equals(testBook2));
	}
	
	//Testing if that .equals returns false when an Object isn't of type Book
	@Test
	public void testEqualsNotBook() {
		Book testBook1 = new Book(123456789, "AuthorName", "OtherName", "BookTitle");
		Object testBook2 = new Object();
		assertFalse(testBook1.equals(testBook2));
	}
	
	//Testing if that .equals returns true when two Book objects are the same
	@Test
	public void testEqualsTrue() {
		Book testBook1 = new Book(123456789, "AuthorName", "OtherName", "BookTitle");
		Book testBook2 = new Book(123456789, "AuthorName", "OtherName", "BookTitle");
		assertTrue(testBook1.equals(testBook2));
	}
	
	
		
		
		
	//Testing that .getDueDate will return null when it is currently checked in
	@Test
	public void testGetDueDateNull() {
		LibraryBook testLibraryBook = new LibraryBook(123456789, "AuthorName", "OtherName", "BookTitle");
		assertEquals(null, testLibraryBook.getDueDate());
	}
	
}