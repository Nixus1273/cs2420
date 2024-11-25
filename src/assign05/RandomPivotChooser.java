package assign05;

import java.util.ArrayList;

/**
 * Randomly chooses a pivot index in an array
 *
 * @author Ethan Laynor and Brandon Flickinger
 * @version 10/3/2024
 */
public class RandomPivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {
    @Override
    public int getPivotIndex(ArrayList<E> list, int leftIndex, int rightIndex) {
          return ((int)  ( Math.random() * (rightIndex - leftIndex + 1) )+ leftIndex );
    }
}