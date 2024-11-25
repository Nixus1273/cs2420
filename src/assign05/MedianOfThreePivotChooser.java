package assign05;

import java.util.ArrayList;

/**
 * Chooses a pivot index with median of three method
 *
 * @author Ethan Laynor and Brandon Flickinger
 * @version 10/3/2024
 */
public class MedianOfThreePivotChooser<E extends Comparable<? super E>> implements PivotChooser<E>{
    @Override
    public int getPivotIndex(ArrayList<E> list, int leftIndex, int rightIndex) {
        int middle = (leftIndex + rightIndex) / 2;
        int[] pivot = {leftIndex, middle, rightIndex};

        if (list.get(leftIndex).compareTo(list.get(middle)) > 0) {
            pivot[0] = middle;
            pivot[1] = leftIndex;
        }
        if (list.get(pivot[1]).compareTo(list.get(pivot[2])) > 0) {
            int temp = pivot[1];
            pivot[1] = pivot[2];
            pivot[2] = temp;

            if (list.get(pivot[0]).compareTo(list.get(pivot[1])) > 0) {
                int temp2 = pivot[0];
                pivot[0] = pivot[1];
                pivot[1] = temp2;
            }
        }
        return pivot[1];
    }
}