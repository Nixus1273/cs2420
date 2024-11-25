package assign02;

import java.util.GregorianCalendar;

public class LibraryBook extends Book{

	private int patron;
	private GregorianCalendar dueDate;
	
	public LibraryBook(long isbn, String authorSurname, String authorOtherName, String title) {
		super(isbn, authorSurname, authorOtherName, title);
		patron = -1;
	}

	
	
	public int getPatron() {
		return this.patron;
	}

	
	
	public GregorianCalendar getDueDate() {
		return this.dueDate;
	}

	
	
	public void checkingOutBook(int patron, int month, int day, int year) {
		this.patron = patron;
		this.dueDate = new GregorianCalendar(year, month, day);
	}
	
	
	
	public void checkingInBook() {
		this.patron = -1;
		this.dueDate = null;
	}
	
}
