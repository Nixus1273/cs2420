package assign02;

/**
 * This class represents a phone number.
 * 
 * @author CS 2420 course staff
 * @version August 29, 2024
 */
public class PhoneNumber {

	private String areaCode;

	private String trunk;

	private String rest;

	/**
	 * Creates a phone number by parsing the given string.
	 * 
	 * @param phoneNumber - given string
	 */
	public PhoneNumber(String phoneNumber) {
		phoneNumber = phoneNumber.replaceAll("-|\\s|\\.|\\(|\\)", "");

		boolean isValid = true;
		if(phoneNumber.length() != 10)
			isValid = false;
		for(int i = 0; isValid && i < 10; i++)
			if(!Character.isDigit(phoneNumber.charAt(i)))
				isValid = false;

		if(isValid) {
			this.areaCode = phoneNumber.substring(0, 3);
			this.trunk = phoneNumber.substring(3, 6);
			this.rest = phoneNumber.substring(6, 10);
		}
		else {
			this.areaCode = "000";
			this.trunk = "000";
			this.rest = "000";
			System.err.println("Phone number \"" + phoneNumber + "\" is not formatted correctly, initializing as " + this.toString()
					+ ".");
		}
	}

	/**
	 * Determines whether this phone number is the same as a given object.
	 * Two phone numbers are considered equal if they have the same area code, trunk, and remaining numbers.
	 * 
	 * @param other - object begin compared with this phone number
	 * @return true if other is a PhoneNumber type and is equal to this phone number,
	 *         false otherwise
	 */
	public boolean equals(Object other) {
		if(!(other instanceof PhoneNumber))
			return false;

		PhoneNumber otherPhoneNumber = (PhoneNumber) other;

		return this.areaCode.equals(otherPhoneNumber.areaCode) && this.trunk.equals(otherPhoneNumber.trunk) && 
				this.rest.equals(otherPhoneNumber.rest);
	}

	/**
	 * Generates a textual representation of this phone number.
	 * 
	 * @return formatted string for this phone number
	 */
	public String toString() {
		return "(" + this.areaCode + ") " + this.trunk + "-" + this.rest;
	}
}