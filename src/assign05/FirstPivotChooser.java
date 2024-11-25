package assign05;

import java.util.ArrayList;

/**
 * Chooses first index for a pivot
 *
 * @author Ethan Laynor and Brandon Flickinger
 * @version 10/3/2024
 */
public class FirstPivotChooser<E extends Comparable<? super E>> implements PivotChooser<E>{
	@Override
	public int getPivotIndex(ArrayList<E> list, int leftIndex, int rightIndex) {
		return leftIndex;
	}
}