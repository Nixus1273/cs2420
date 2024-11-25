package assign02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * This class represents a library, which is a collection of library books.
 * 
 * @author CS 2420 course staff and ?? 
 * @version September 5, 2024
 */
public class Library {

	private ArrayList<LibraryBook> library;

	
	
	
	
	/**
	 * Creates an empty library.
	 */
	public Library() {
		this.library = new ArrayList<LibraryBook>();
	}

	
	
	
	
	/**
	 * Adds the book, with the given ISBN, author, and title information, to this
	 * library. Assumes there is no possibility of duplicate library books.
	 * 
	 * @param isbn - ISBN of the book to be added
	 * @param authorSurname - surname of the author of the book to be added
	 * @param authorOtherName - other name of the author of the book to be added
	 * @param title - title of the book to be added
	 */
	public void add(long isbn, String authorSurname, String authorOtherName, String title) {
		this.library.add(new LibraryBook(isbn, authorSurname, authorOtherName, title));
	}

	
	
	
	
	/**
	 * Adds a list of library books to the library. Assumes there is no possibility
	 * of duplicate library books.
	 * 
	 * @param list - list of library books to be added
	 */
	public void addAll(ArrayList<LibraryBook> list) {
		if(list.size() == 0)
			throw new NullPointerException("List canot be empty");
		
		this.library.addAll(list);
	}

	
	
	
	
	/**
	 * Adds the books specified by an input file to the library. Assumes the input
	 * files specifies one book per line with ISBN, author, and title separated by
	 * tabs, and the author surname before a comma.
	 * 
	 * If file does not exist or a formatting rule is violated, prints an error
	 * message and does not change the library.
	 * 
	 * @param filename - name of the file containing information for the books to be
	 *                 added
	 */
	public void addAll(String filename) {
		ArrayList<LibraryBook> toBeAdded = new ArrayList<LibraryBook>();

		try (Scanner fileIn = new Scanner(new File(filename))) {
			int lineNum = 1;

			while(fileIn.hasNextLine()) {
				String line = fileIn.nextLine();

				try (Scanner lineIn = new Scanner(line)) {
					lineIn.useDelimiter("\\t");
					
					if(!lineIn.hasNextLong())
						throw new ParseException("ISBN", lineNum);
					long isbn = lineIn.nextLong();

					if(!lineIn.hasNext())
						throw new ParseException("Author", lineNum);
					String author = lineIn.next();
					String[] authorNames = author.split(", ");
					if(authorNames.length != 2)
						throw new ParseException("Author", lineNum);

					if(!lineIn.hasNext())
						throw new ParseException("Title", lineNum);
					String title = lineIn.next();

					toBeAdded.add(new LibraryBook(isbn, authorNames[0], authorNames[1], title));

					lineNum++;
				}
				catch (ParseException e) {
					System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
							+ ". Nothing added to the library.");
					return;
				}
			}
		}
		catch (FileNotFoundException e) {
			System.err.println(e.getMessage() + " Nothing added to the library.");
			return;
		}

		this.library.addAll(toBeAdded);
	}

	
	
	
	
	/**
	 * Gets the id of the patron who has the library book with the specified ISBN
	 * checked out of the library. If there is no such patron, because no book with
	 * the specified ISBN is in the library or the library book is not checked out,
	 * returns -1.
	 * 
	 * @param isbn - ISBN of the book to be looked up
	 */
	public int lookup(long isbn) {
		
	
		for(LibraryBook element: library) {
			if(element.getIsbn() == isbn)
				return element.getPatron();
		}
		
		return -1;
	}

	
	
	
	
	/**
	 * Gets the list of library books checked out to the specified patron. If the
	 * patron does not exist or has no books checked out of the library, returns an
	 * empty list.
	 * 
	 * @param patron - id of patron whose list of checked out books is being
	 *               accessed
	 */
	public ArrayList<LibraryBook> lookup(int patron) {
		
		ArrayList<LibraryBook> patronList = new ArrayList<LibraryBook>();
		
		for(LibraryBook element: library) {
			if(element.getPatron() == patron) {
				patronList.add(element);

			}	
		}
	

		return patronList;
	}

	
	
	
	
	/**
	 * Checks out of the library the book with the specified ISBN, by setting the
	 * patron and due date. If no book with the specified ISBN is in the library,
	 * returns false. If the book with the specified ISBN is already checked out,
	 * returns false. Otherwise, returns true.
	 * 
	 * @param isbn - ISBN of the library book to be checked out
	 * @param patron - id of the patron who checking this book out of the library
	 * @param dueDate - date when this book is due to be returned to the library
	 * @param month - month (as number) when this book is due to be returned to
	 *                the library
	 * @param day - day when this book is due to be returned to the library
	 * @param year - year when this book is due to be returned to the library
	 */
	public boolean checkOut(long isbn, int patron, int month, int day, int year) {
		
		if(patron < 1)
			throw new IllegalArgumentException("Patron ID canont equal -1");

		if(month < 1 || month > 12)
			throw new IllegalArgumentException("Invaild month number");
		
		if(day < 1 || day > 31)
			throw new IllegalArgumentException("Invaild day number");
		
		
		for(LibraryBook element: library) {
			if(element.getIsbn() == isbn) {
				if(element.getPatron() == -1) {
					element.checkingOutBook(patron,  month, day, year);
					return true;
				}
			}
		}
		
		return false;
	}

	
	
	
	
	/**
	 * Gives the book with the specified ISBN back to the library by setting the
	 * patron and due date to their default values. (I.e., returns the books, checks
	 * the books back into the library)
	 * 
	 * If no book with the specified ISBN is in the library, returns false. If the
	 * book with the specified ISBN is not checked out, returns false. Otherwise,
	 * returns true.
	 * 
	 * @param isbn - ISBN of the book to be given back to the library
	 */
	public boolean checkIn(long isbn) {

		for(LibraryBook element: library) {
			if(element.getIsbn() == isbn) {
				if(element.getPatron() != -1) {
					element.checkingInBook();
					return true;
				}
			}
		}
		
		return false;
	}

	
	
	
	
	/**
	 * Gives all book checked out by the specified patron back to the library by
	 * setting the patron and due date to their default values. (I.e., returns all
	 * books, checks all books back into the library)
	 *
	 * If no library books are checked out by the patron, returns false; Otherwise,
	 * returns true.
	 * 
	 * @param patron - id of the patron returning all books to the library
	 */
	public boolean checkIn(int patron) {
		
		boolean check = false;
		
		for(LibraryBook element: library) {
			if(element.getPatron() == patron) {
				element.checkingInBook();
				check = true;
			}
		}
		
		return check;
		
	}
}