package assign02;

/**
 * This class represents a book, in which the ISBN (unique), author, 
 * and title information cannot change once the book is created.
 * 
 * @author CS 2420 Prof. Aaron Wood and Ethan Laynor and Brandon Flickinger
 * @version September 5, 2024
 */
public class Book {
	
	private long isbn;

	private String authorSurname;
	
	private String authorOtherName;

	private String title;

	
	
	
	
	/**
	 * Creates a book from the given ISBN, author, and title.
	 * 
	 * @param isbn - unique id for this book
	 * @param authorSurname - last name for this book's author
	 * @param authorOtherName - rest of name for this book's author
	 * @param title - title for this book
	 */
	public Book(long isbn, String authorSurname, String authorOtherName, String title) {
		this.isbn = isbn;
		this.authorSurname = authorSurname;
		this.authorOtherName = authorOtherName;
		this.title = title;
	}

	
	
	
	
	/**
	 * Gets the surname of this book's author.
	 * (NOTE: Surname is the same as last name or family name.)
	 * 
	 * @return surname of this book's author
	 */
	public String getAuthorSurname() {
		return this.authorSurname;
	}
	
	
	
	
	
	/**
	 * Gets the other name of this book's author.
	 * (NOTE: Other name is a first or given name and optional middle name or initial.)
	 * 
	 * @return other name of this book's author
	 */
	public String getAuthorOtherName() {
		return this.authorOtherName;
	}

	
	
	
	
	/**
	 * Gets the ISBN for this book.
	 * 
	 * @return ISBN for this book
	 */
	public long getIsbn() {
		return this.isbn;
	}

	
	
	
	
	/**
	 * Gets the title for this book.
	 * 
	 * @return title for this book
	 */
	public String getTitle() {
		return this.title;
	}
	
	
	
	
	
	/**
	 * Generates a textual representation of this book.
	 * 
	 * @return user-friendly string containing information for this book
	 */
	public String toString() {
		return this.isbn + ", " + this.authorOtherName + " " + this.authorSurname + ", \"" + this.title + "\"";
	}

	
	
	
	
	/**
	 * Determines whether this book is the same as a given object.
	 * Two books are considered equal if they have the same ISBN, author, and
	 * title information.
	 * 
	 * @param other - object begin compared with this book
	 * @return true if other is a Book type and is equal to this book,
	 *         false otherwise
	 */
	public boolean equals(Object other) {
		if(!(other instanceof Book))
			return false;

		Book otherBook = (Book) other;
		
		
		if(this.isbn == otherBook.getIsbn() && this.authorSurname.equals(otherBook.getAuthorSurname()) 
				&& this.authorOtherName.equals(otherBook.getAuthorOtherName()) && this.title.equals(otherBook.getTitle()))
			return true;
		
		return false;
	}
	
	
	
	
}