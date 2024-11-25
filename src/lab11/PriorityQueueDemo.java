package lab11;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Demonstration of Java's PriorityQueue class.
 * 
 * @author CS 2420 course staff
 * @version November 15, 2024
 */
public class PriorityQueueDemo {

	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);
		pq.add(36);
		pq.add(17);
		pq.add(3);
		pq.add(100);
		pq.add(19);
		pq.add(2);
		pq.add(70);

		PriorityQueue<String> pq2 = new PriorityQueue<>(new ReverseStringComparator());
		pq2.add("A");
		pq2.add("B");
		pq2.add("C");

		System.out.println("Array: " + Arrays.toString(pq.toArray()));

		System.out.println("First item removed: " + pq.remove());
		System.out.println("Second item removed: " + pq.remove());
		System.out.println("Third item removed: " + pq.remove());
		System.out.println("Fourth item removed: " + pq.remove());
		System.out.println("Fifth item removed: " + pq.remove());
		System.out.println("Sixth item removed: " + pq.remove());
		System.out.println("Seventh item removed: " + pq.remove());

		System.out.println("Array: " + Arrays.toString(pq2.toArray()));

		System.out.println("First item removed: " + pq2.remove());
		System.out.println("Second item removed: " + pq2.remove());
		System.out.println("Third item removed: " + pq2.remove());

	}
}
