package assign02;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains some example tests for LibraryGeneric.
 * Students are expected to add additional tests to this class to thoroughly
 * check the correctness of LibraryGeneric.
 *
 * @author Erin Parker and Ethan Laynor and Brandon Flickinger
 * @version2 September 5, 2024
 */
public class LibraryGenericTest {

	// library that uses names to identify patrons
	private LibraryGeneric<String> patronByNameLibrary;
	private LibraryGeneric<String> edgeCaseByNameLibrary;
	// library that uses phone numbers to identify patrons
	private LibraryGeneric<PhoneNumber> patronByPhoneLibrary;

	@BeforeEach
	void setUp() throws Exception {
		patronByNameLibrary = new LibraryGeneric<String>();
		patronByNameLibrary.add(9780374292799L, "Friedman", "Thomas L.", "The World is Flat");
		patronByNameLibrary.add(9780330351690L, "Krakauer", "Jon", "Into the Wild");
		patronByNameLibrary.add(9780446580342L, "Baldacci", "David", "Simple Genius");

		edgeCaseByNameLibrary = new LibraryGeneric<String>();
		edgeCaseByNameLibrary.add(1L, "Name1", "OtherName1", "Title1");
		edgeCaseByNameLibrary.add(2L, "Name2", "OtherName2", "Title2");
		edgeCaseByNameLibrary.add(3L, "Name3", "OtherName3", "Title3");
		edgeCaseByNameLibrary.add(4L, "Name4", "OtherName4", "Title4");
		edgeCaseByNameLibrary.add(5L, "Name5", "OtherName5", "Title5");
		edgeCaseByNameLibrary.add(6L, "Name6", "OtherName6", "Title6");


		patronByPhoneLibrary = new LibraryGeneric<PhoneNumber>();
		patronByPhoneLibrary.add(9780374292799L, "Friedman", "Thomas L.", "The World is Flat");
		patronByPhoneLibrary.add(9780330351690L, "Krakauer", "Jon", "Into the Wild");
		patronByPhoneLibrary.add(9780446580342L, "Baldacci", "David", "Simple Genius");
	}

	@Test
	public void testNameCheckOut() {
		String patron = "Unique Patron Name";
		assertTrue(patronByNameLibrary.checkOut(9780330351690L, patron, 10, 1, 2024));
		assertTrue(patronByNameLibrary.checkOut(9780374292799L, patron, 10, 1, 2024));
	}

	@Test
	public void testNameLookup() {
		String patron = "Unique Patron Name";
		patronByNameLibrary.checkOut(9780330351690L, patron, 10, 1, 2024);
		patronByNameLibrary.checkOut(9780374292799L, patron, 10, 1, 2024);
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = patronByNameLibrary.lookup(new String("Unique Patron Name"));

		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Krakauer", "Jon", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Friedman", "Thomas L.", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getPatron());
		assertEquals(patron, booksCheckedOut.get(1).getPatron());
	}

	@Test
	public void testNameCheckIn() {
		String patron = "Unique Patron Name";
		patronByNameLibrary.checkOut(9780330351690L, patron, 10, 1, 2024);
		patronByNameLibrary.checkOut(9780374292799L, patron, 10, 1, 2024);
		assertTrue(patronByNameLibrary.checkIn(new String("Unique Patron Name")));
	}

	@Test
	public void testPhoneCheckOut() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		assertTrue(patronByPhoneLibrary.checkOut(9780330351690L, patron, 10, 1, 2024));
		assertTrue(patronByPhoneLibrary.checkOut(9780374292799L, patron, 10, 1, 2024));
	}

	@Test
	public void testPhoneLookup() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		patronByPhoneLibrary.checkOut(9780330351690L, patron, 10, 1, 2024);
		patronByPhoneLibrary.checkOut(9780374292799L, patron, 10, 1, 2024);
		ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut = patronByPhoneLibrary.lookup(new PhoneNumber("801.555.1234"));

		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Krakauer", "Jon", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Friedman", "Thomas L.", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getPatron());
		assertEquals(patron, booksCheckedOut.get(1).getPatron());
	}

	@Test
	public void testPhoneCheckIn() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		patronByPhoneLibrary.checkOut(9780330351690L, patron, 10, 1, 2024);
		patronByPhoneLibrary.checkOut(9780374292799L, patron, 10, 1, 2024);
		assertTrue(patronByPhoneLibrary.checkIn(new PhoneNumber("801.555.1234")));
	}

	//New Test -----------------------------------------------------


	//Testing if .lookup(ISBN) returns null when book is in library but not checked out (Name)
	@Test
	public void testLookUpNullString() {
		assertEquals(null , patronByNameLibrary.lookup(9780374292799L));
	}

	//Testing if ..lookup(ISBN) returns null because of a non existent ISBN in library (Name)
	@Test
	public void testLookUpFakeString() {
		assertEquals(null , patronByNameLibrary.lookup(123L));
	}

	//Testing if .lookup(ISBN) works correctly with different Types (Testing: Name)
	@Test
	public void testLookUpString() {
		patronByNameLibrary.checkOut(9780330351690L, "name", 10, 1, 2024);
		assertEquals("name", patronByNameLibrary.lookup(9780330351690L));
	}

	//Testing if .lookup(ISBN) returns null when book is in library but not checked out (Phone)
	@Test
	public void testLookUpNullPhoneBook() {
		assertEquals(null, patronByPhoneLibrary.lookup(9780374292799L));
	}

	//Testing if .lookup(ISBN)p returns null because of a non existent ISBN in library (Phone)
	@Test
	public void testLookUpFakePhone() {
		assertEquals(null , patronByPhoneLibrary.lookup(123L));
	}

	//Testing if .lookup(ISBN) works correctly with different Types (Testing: Phone)
	@Test
	public void testLookUpPhone() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		patronByPhoneLibrary.checkOut(9780330351690L, patron, 10, 1, 2024);
		assertEquals(patron, patronByPhoneLibrary.lookup(9780330351690L));
	}



	//Testing if .lookup(Patron) works correctly with different Types (Testing: Name)
	@Test
	public void testLookUpPatronString() {
		ArrayList<LibraryBookGeneric<String>> patronList = new ArrayList<LibraryBookGeneric<String>>();
		patronList.add(new LibraryBookGeneric<String>(9780330351690L, "Krakauer", "Jon", "Into the Wild"));
		patronByNameLibrary.checkOut(9780330351690L, "name", 10, 1, 2024);
		assertEquals(patronList, patronByNameLibrary.lookup("name"));
	}

	//Testing if .lookup(Patron) works correctly when Patron hasn't checked out a book (Testing: Name)
	@Test
	public void testLookUpNullPatronString() {
		ArrayList<LibraryBookGeneric<String>> patronList = new ArrayList<LibraryBookGeneric<String>>();
		assertEquals(patronList, patronByNameLibrary.lookup("name"));
	}

	//Testing if .lookup(Patron) works correctly with different Types (Testing: Phone)
	@Test
	public void testLookUpPatronPhone() {
		ArrayList<LibraryBookGeneric<PhoneNumber>> patronList = new ArrayList<LibraryBookGeneric<PhoneNumber>>();
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		patronList.add(new LibraryBookGeneric<PhoneNumber>(9780330351690L, "Krakauer", "Jon", "Into the Wild"));
		patronByPhoneLibrary.checkOut(9780330351690L, patron, 10, 1, 2024);
		assertEquals(patronList, patronByPhoneLibrary.lookup(patron));
	}

	//Testing if .lookup(Patron) works correctly when Patron hasn't checked out a book (Testing: Phone)
	@Test
	public void testLookUpNullPatronPhone() {
		ArrayList<LibraryBookGeneric<PhoneNumber>> patronList = new ArrayList<LibraryBookGeneric<PhoneNumber>>();
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		assertEquals(patronList, patronByPhoneLibrary.lookup(patron));
	}





	//Testing if .checkOut throws an error if giving an invalid month date
	@Test
	public void testInvalidMonthString() {
		assertThrows(IllegalArgumentException.class, () -> { patronByNameLibrary.checkOut(9780330351690L, "name", -1, 1, 2024); });
	}

	//Testing if .checkOut throws an error if giving an invalid day date
	@Test
	public void testInvalidDayPhone() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		assertThrows(IllegalArgumentException.class, () -> {patronByPhoneLibrary.checkOut(9780330351690L, patron, 10, -1, 2024);});
	}

	//Testing if .checkOut returns false if given an ISBN not in the library
	@Test
	public void testInvalidISBNString() {
		assertFalse(patronByNameLibrary.checkOut(123L, "name", 10, 1, 2024));
	}





	//Testing if .checkIn works correctly and returns true (Testing: Name)
	@Test
	public void testIsbnCheckInName() {
		patronByNameLibrary.checkOut(9780330351690L, "name", 10, 1, 2024);
		assertTrue(patronByNameLibrary.checkIn(9780330351690L));
	}

	//Testing if .checkIn works correctly and returns true (Testing: Phone)
	@Test
	public void testIsbnCheckInPhone() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		patronByPhoneLibrary.checkOut(9780330351690L, patron, 10, 1, 2024);
		assertTrue(patronByPhoneLibrary.checkIn(9780330351690L));
	}

	//Testing if .checkIn returns false if checking in a book that wasn't checked out (Testing: Name)
	@Test
	public void testFalseIsbnCheckInName() {
		assertFalse(patronByNameLibrary.checkIn(321L));
	}

	//Testing if .checkIn returns false if checking in a book that wasn't checked out (Testing: Phone)
	@Test
	public void testFalseIsbnCheckInPhone() {
		assertFalse(patronByPhoneLibrary.checkIn(123L));
	}





	//Testing if OrderByISBN returns 0 if given two of the same library book of ISBN 0
	@Test
	public void testOrderByISBNSame() {
		LibraryBookGeneric<String> libraryBook1 = new LibraryBookGeneric<String>(0, "Friedman", "Thomas L.", "The World is Flat");
		LibraryBookGeneric<String> libraryBook2 = new LibraryBookGeneric<String>(0, "Friedman", "Thomas L.", "The World is Flat");
		OrderByIsbn<String> tester = new OrderByIsbn<String>();
		assertEquals(0, tester.compare(libraryBook1, libraryBook2));

	}

	//Testing if OrderBy ISBN returns -1 if LB1 is smaller then LB2
	@Test
	public void testOrderByISBNDifferntSmall() {
		LibraryBookGeneric<String> libraryBook1 = new LibraryBookGeneric<String>(-1000, "Friedman", "Thomas L.", "The World is Flat");
		LibraryBookGeneric<String> libraryBook2 = new LibraryBookGeneric<String>(1000, "Friedman", "Thomas L.", "The World is Flat");
		OrderByIsbn<String> tester = new OrderByIsbn<String>();
		assertEquals(-1, tester.compare(libraryBook1, libraryBook2));

	}

	//Testing if OrderBy ISBN returns 1 if LB1 is bigger then LB2
	@Test
	public void testOrderByISBNDifferntBig() {
		LibraryBookGeneric<String> libraryBook1 = new LibraryBookGeneric<String>(100, "Friedman", "Thomas L.", "The World is Flat");
		LibraryBookGeneric<String> libraryBook2 = new LibraryBookGeneric<String>(-100, "Friedman", "Thomas L.", "The World is Flat");
		OrderByIsbn<String> tester = new OrderByIsbn<String>();
		assertEquals(1, tester.compare(libraryBook1, libraryBook2));

	}





	//Testing if getListSortedByIsbn returns a sorted list in order of smallest to largest ISBN
	@Test
	public void testListSortByISBN() {

		List<LibraryBookGeneric<String>> copy = new ArrayList<>();
		copy.add(new LibraryBookGeneric<String>(9780330351690L, "Krakauer", "Jon", "Into the Wild"));
		copy.add(new LibraryBookGeneric<String>(9780374292799L, "Friedman", "Thomas L.", "The World is Flat"));
		copy.add(new LibraryBookGeneric<String>(9780446580342L, "Baldacci", "David", "Simple Genius"));

		assertEquals(copy, patronByNameLibrary.getListSortedByIsbn() );


	}

	//Testing if getListSortedByIsbn returns a sorted empty list
	@Test
	public void testListSortByISBNEmptyLibrary() {

		List<LibraryBookGeneric<String>> copy = new ArrayList<>();
		LibraryGeneric<String> emptyLibrary  = new LibraryGeneric<String>();
		assertEquals(copy, emptyLibrary.getListSortedByIsbn() );

	}





	//Testing if OrderByAuthor returns 0 if given two of the same library book
	@Test
	public void testOrderByAuthorSame() {
		LibraryBookGeneric<String> libraryBook1 = new LibraryBookGeneric<String>(0, "Friedman", "Thomas L.", "The World is Flat");
		LibraryBookGeneric<String> libraryBook2 = new LibraryBookGeneric<String>(0, "Friedman", "Thomas L.", "The World is Flat");
		OrderByAuthor<String> tester = new OrderByAuthor<String>();
		assertEquals(0, tester.compare(libraryBook1, libraryBook2));

	}

	//Testing if OrderByAuthor returns -1 after given two book with the same Author but different title
	@Test
	public void testOrderByAuthorSameDifferentTitle() {
		LibraryBookGeneric<String> libraryBook1 = new LibraryBookGeneric<String>(123, "Friedman", "Thomas L.", "A");
		LibraryBookGeneric<String> libraryBook2 = new LibraryBookGeneric<String>(321, "Friedman", "Thomas L.", "B");
		OrderByAuthor<String> tester = new OrderByAuthor<String>();
		assertEquals(-1, tester.compare(libraryBook1, libraryBook2));

	}

	//Testing if OrderByAuthor returns 1 after two book with different Authors
	@Test
	public void testOrderByAuthorDiffernt() {
		LibraryBookGeneric<String> libraryBook1 = new LibraryBookGeneric<String>(123, "B", "Thomas L.", "The World is Flat");
		LibraryBookGeneric<String> libraryBook2 = new LibraryBookGeneric<String>(321, "A", "Thomas L.", "The World is Flat");
		OrderByAuthor<String> tester = new OrderByAuthor<String>();
		assertEquals(1, tester.compare(libraryBook1, libraryBook2));

	}


	//Testing if OrderByAuthor returns 0 if given two of the same library book that are "empty"
	@Test
	public void testOrderByAuthorEmptyBooks() {
		LibraryBookGeneric<String> libraryBook1 = new LibraryBookGeneric<String>(0, "", "", "");
		LibraryBookGeneric<String> libraryBook2 = new LibraryBookGeneric<String>(0, "", "", "");
		OrderByAuthor<String> tester = new OrderByAuthor<String>();
		assertEquals(0, tester.compare(libraryBook1, libraryBook2));

	}



	//Testing if getListSortedByAuthor returns a sorted list
	@Test
	public void testListSortByAuthor() {

		List<LibraryBookGeneric<String>> copy = new ArrayList<>();
		copy.add(new LibraryBookGeneric<String>(9780446580342L, "Baldacci", "David", "Simple Genius"));
		copy.add(new LibraryBookGeneric<String>(9780374292799L, "Friedman", "Thomas L.", "The World is Flat"));
		copy.add(new LibraryBookGeneric<String>(9780330351690L, "Krakauer", "Jon", "Into the Wild"));

		assertEquals(copy, patronByNameLibrary.getListSortedByAuthor() );

	}

	//Testing if getListSortedByAuthor returns a sorted empty list
	@Test
	public void testListSortByAuthorEmptyLibrary() {

		List<LibraryBookGeneric<String>> copy = new ArrayList<>();
		LibraryGeneric<String> emptyLibrary  = new LibraryGeneric<String>();
		assertEquals(copy, emptyLibrary.getListSortedByAuthor() );

	}




	@Test
	public void testOrderByDueDateSame() {
		LibraryBookGeneric<String> libraryBook1 = new LibraryBookGeneric<String>(0, "Friedman", "Thomas L.", "The World is Flat");
		libraryBook1.checkingOutBook("Name", 1, 1, 1);
		LibraryBookGeneric<String> libraryBook2 = new LibraryBookGeneric<String>(0, "Friedman", "Thomas L.", "The World is Flat");
		libraryBook2.checkingOutBook("Name1", 1, 1, 1);
		OrderByDueDate<String> tester = new OrderByDueDate<String>();
		assertEquals(0, tester.compare(libraryBook1, libraryBook2));

	}


	@Test
	public void testOrderByDueDateDifferentFirstt() {
		LibraryBookGeneric<String> libraryBook1 = new LibraryBookGeneric<String>(0, "Friedman", "Thomas L.", "The World is Flat");
		libraryBook1.checkingOutBook("Name", 1, 1, 1);
		LibraryBookGeneric<String> libraryBook2 = new LibraryBookGeneric<String>(0, "Friedman", "Thomas L.", "The World is Flat");
		libraryBook2.checkingOutBook("Name1", 12, 1, 1);
		OrderByDueDate<String> tester = new OrderByDueDate<String>();
		assertEquals(-1, tester.compare(libraryBook1, libraryBook2));

	}


	@Test
	public void testOrderByDueDateDifferentLast() {
		LibraryBookGeneric<String> libraryBook1 = new LibraryBookGeneric<String>(0, "Friedman", "Thomas L.", "The World is Flat");
		libraryBook1.checkingOutBook("Name", 12, 1, 1);
		LibraryBookGeneric<String> libraryBook2 = new LibraryBookGeneric<String>(0, "Friedman", "Thomas L.", "The World is Flat");
		libraryBook2.checkingOutBook("Name1", 1, 1, 1);
		OrderByDueDate<String> tester = new OrderByDueDate<String>();
		assertEquals(1, tester.compare(libraryBook1, libraryBook2));

	}


	@Test
	public void testOrderByDueDateDifferentWithNullFirst() {
		LibraryBookGeneric<String> libraryBook1 = new LibraryBookGeneric<String>(0, "Friedman", "Thomas L.", "The World is Flat");
		libraryBook1.checkingOutBook("Name1", 1, 1, 1);
		LibraryBookGeneric<String> libraryBook2 = new LibraryBookGeneric<String>(0, "Friedman", "Thomas L.", "The World is Flat");
		OrderByDueDate<String> tester = new OrderByDueDate<String>();
		assertEquals(-1, tester.compare(libraryBook1, libraryBook2));

	}


	@Test
	public void testOrderByDueDateDifferentWithNullSecond() {
		LibraryBookGeneric<String> libraryBook1 = new LibraryBookGeneric<String>(0, "Friedman", "Thomas L.", "The World is Flat");
		LibraryBookGeneric<String> libraryBook2 = new LibraryBookGeneric<String>(0, "Friedman", "Thomas L.", "The World is Flat");
		libraryBook2.checkingOutBook("Name2", 1, 1, 1);
		OrderByDueDate<String> tester = new OrderByDueDate<String>();
		assertEquals(1, tester.compare(libraryBook1, libraryBook2));

	}


	@Test
	public void testOrderByDueDateDifferentBothNull() {
		LibraryBookGeneric<String> libraryBook1 = new LibraryBookGeneric<String>(0, "Friedman", "Thomas L.", "The World is Flat");
		LibraryBookGeneric<String> libraryBook2 = new LibraryBookGeneric<String>(0, "Friedman", "Thomas L.", "The World is Flat");
		OrderByDueDate<String> tester = new OrderByDueDate<String>();
		assertEquals(0, tester.compare(libraryBook1, libraryBook2));

	}

	//Testing if getOverdueList returns a sorted list of over due books
	@Test
	public void testOverdueList() {

		List<LibraryBookGeneric<String>> copy = new ArrayList<>();
		copy.add(new LibraryBookGeneric<String>(1L, "Name1", "OtherName1", "Title1"));
		copy.add(new LibraryBookGeneric<String>(2L, "Name2", "OtherName2", "Title2"));
		copy.add(new LibraryBookGeneric<String>(3L, "Name3", "OtherName3", "Title3"));

		edgeCaseByNameLibrary.checkOut(6L, "Name", 12, 1, 2024);
		edgeCaseByNameLibrary.checkOut(1L, "Name", 1, 1, 2024);
		edgeCaseByNameLibrary.checkOut(2L, "Name", 3, 1, 2024);
		edgeCaseByNameLibrary.checkOut(4L, "Name", 7, 1, 2024);
		edgeCaseByNameLibrary.checkOut(3L, "Name", 5, 1, 2024);
		edgeCaseByNameLibrary.checkOut(5L, "Name", 9, 1, 2024);

		assertEquals(copy, edgeCaseByNameLibrary.getOverdueList(6, 1, 2024) );


	}

	//Testing if getOverdueList returns a empty sorted list of over due books
	@Test
	public void testOverdueListEmpty() {

		List<LibraryBookGeneric<String>> copy = new ArrayList<>();

		assertEquals(copy, edgeCaseByNameLibrary.getOverdueList(6, 1, 2024) );


	}



}