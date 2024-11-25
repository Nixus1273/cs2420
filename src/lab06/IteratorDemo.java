package lab06;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;

/**
 * This program demonstrates how to use iterators for Lab 6.
 * 
 * @author CS 2420 course staff
 * @version September 27, 2024
 */
public class IteratorDemo {
	
	public static void main(String[] args) {
		SortedSet<Integer> set = new TreeSet<>();
		set.add(1400);
		set.add(1420);
		set.add(1410);
		set.add(2420);

//		 This is how we typically iterate over the collection.
//		for(int element : set)
//			System.out.println(element);

//		 But this is what the for-each loop is actually doing.
		Iterator<Integer> iter = set.iterator();
		while(iter.hasNext()) {
			int element = iter.next();
			System.out.println(element);
		}

//		 This works for ANY collection.
//		 (Add elements to these collections to observe.)
		Collection<Integer> collection = new ArrayList<>();
		collection = new LinkedList<Integer>();
		collection = new HashSet<>();
		collection = new Vector<>();
		collection = new PriorityQueue<>();

		Queue<Integer> q = new LinkedList<>();
		q = new PriorityQueue<>();

		Stack<Integer> stack = new Stack<>();

		Iterator<Integer> iterator;
		iterator = collection.iterator();
		iterator = q.iterator();
		iterator = stack.iterator();

		for(int whatever : collection)
			System.out.println(whatever);

		for(int something : q)
			System.out.println(q);

		for(int nothing : stack)
			System.out.println(stack);
	}
}