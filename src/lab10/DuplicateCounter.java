package lab10;

import java.io.File;
import java.util.*;

/**
 * This class contains three methods / strategies for counting duplicates in a dataset.
 * 
 * @author CS 2420 course staff and your mom
 * @version November 8, 2024
 */
public class DuplicateCounter {
	
	/**
	 * Counts the number of duplicate items in the given list,
	 * using a brute-force, all pairs strategy.
	 * 
	 * @param dataset - list to be analyzed
	 * @return number of duplicates in the dataset
	 */
	public static <T extends Comparable<? super T>> int countDuplicatesAllPairs(List<T> dataset) {
		ArrayList<T> checked = new ArrayList<>(dataset.size());
		int count = 0;
		for (int i = 0; i < dataset.size(); i++) {
			if (!checked.contains(dataset.get(i))) {
				for (int j = i + 1; j < dataset.size(); j++) {
					if (dataset.get(i).equals(dataset.get(j))) {
						count++;
					}
				}
			}
			checked.add(dataset.get(i));
		}
		return count;
	}

	/**
	 * Counts the number of duplicate items in the given list,
	 * using a hash table strategy.
	 * 
	 * @param dataset - list to be analyzed
	 * @return number of duplicates in the dataset
	 */
	public static <T extends Comparable<? super T>> int countDuplicatesHashing(List<T> dataset) {
		HashMap<T, Integer> map = new HashMap<>();
		int count = 0;
		for (T t : dataset) {
			if (map.put(t, 0) != null) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Counts the number of duplicate items in the given list,
	 * using a sorting strategy.
	 * 
	 * @param dataset - list to be analyzed
	 * @return number of duplicates in the dataset
	 */
	public static <T extends Comparable<? super T>> int countDuplicatesSorting(List<T> dataset) {
		Collections.sort(dataset);
		int count = 0;
		for (int i = 0; i < dataset.size() - 1; i++) {
			if (dataset.get(i).equals(dataset.get(i + 1))) {
				count++;
			}
		}
		return count;
	}
				
	public static void main(String[] args) {
		
		// create a small list of phone numbers with 1 duplicate
		List<PhoneNumber> contactsSmall = new ArrayList<PhoneNumber>();
		contactsSmall.add(new PhoneNumber("801", "581", "8224"));
		contactsSmall.add(new PhoneNumber("801", "581", "4553"));
		contactsSmall.add(new PhoneNumber("801", "585", "1545"));
		contactsSmall.add(new PhoneNumber("801", "581", "8224"));
		contactsSmall.add(new PhoneNumber("801", "581", "4345"));
		
		System.out.println(countDuplicatesAllPairs(contactsSmall));
		System.out.println(countDuplicatesHashing(contactsSmall));
		System.out.println(countDuplicatesSorting(contactsSmall));

		
		// creates a large list of phone numbers with 15 duplicates
		List<PhoneNumber> contactsLarge = new ArrayList<PhoneNumber>();
		try {
			Scanner scan = new Scanner(new File("src/lab10/phone_numbers.csv"));
			while(scan.hasNextLine()) 
				contactsLarge.add(new PhoneNumber(scan.nextLine()));
			scan.close();
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}
		
		System.out.println(countDuplicatesAllPairs(contactsLarge));
		System.out.println(countDuplicatesHashing(contactsLarge));
		System.out.println(countDuplicatesSorting(contactsLarge));
	}
}