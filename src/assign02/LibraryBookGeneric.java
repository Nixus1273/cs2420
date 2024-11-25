package assign02;

import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book{

	private Type patron;
	private GregorianCalendar dueDate;
	
	public LibraryBookGeneric(long isbn, String authorSurname, String authorOtherName, String title) {
		super(isbn, authorSurname, authorOtherName, title);
		this.patron = null;
	}

	
	
	public Type getPatron() {
		return this.patron;
	}

	
	
	public GregorianCalendar getDueDate() {
		return this.dueDate;
	}

	
	
	public void checkingOutBook(Type patron, int month, int day, int year) {
		this.patron = patron;
		this.dueDate = new GregorianCalendar(year, month, day);
	}
	
	
	
	public void checkingInBook() {
		this.patron = null;
		this.dueDate = null;
	}
	
}
