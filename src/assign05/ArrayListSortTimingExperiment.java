package assign05;

import java.util.*;

/**
 * Abstract class to perform timing experiments for sorting arrays with varying degrees of "sortedness".
 *
 * @author Brandon Flickinger and Ethan Laynor
 * @version 2024-10-3
 */
public abstract class ArrayListSortTimingExperiment extends TimingExperiment {
    protected ArrayList<Integer> list = new ArrayList<>();
    protected Random rng = new Random();

    /**
     * Constructor to build a sort timing experiment.
     *
     * @param problemSizeDescription   - description of the problem size for the experiment
     * @param problemSizeMin           - minimum array size
     * @param problemSizeCount         - number of array sizes to use in the experiment
     * @param problemSizeStep          - Step size between consecutive array sizes
     * @param experimentIterationCount - Number of times to run computation for a given array size
     */
    public ArrayListSortTimingExperiment(
            String problemSizeDescription,
            int problemSizeMin,
            int problemSizeCount,
            int problemSizeStep,
            int experimentIterationCount
    ) {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * Helper method to populate the array with random integers in ascending order.
     *
     * @implNote integers are bounded between 0 and 20 + 10 * problemSize
     * @param problemSize - size of the array
     */
    protected void populateAscendingArrayList(int problemSize) {
        list.clear();
        int currentElement = rng.nextInt(20);
        for (int i = 0; i < problemSize; i++) {
            list.add(currentElement);
            currentElement += rng.nextInt(10);
        }
    }

    /**
     * Helper method to populate the array with random integers in random order.
     *
     * @implNote method must call populateAscendingArray and then shuffle the contents of the array
     * @param problemSize - size of the array
     */
    protected void populateRandomArrayList(int problemSize) {
        list.clear();
        for (int i = problemSize - 1; i >= 0; i--) {
            list.add(rng.nextInt(10000));
        }
    }

    /**
     * Helper method to populate the array with random integers in descending order.
     *
     * @implNote method must call populateAscendingArray and then reverse the contents of the array
     * @param problemSize - size of the array
     */
    protected void populateDescendingArrayList(int problemSize) {
        list.clear();
        int currentElement = rng.nextInt(problemSize);
        for (int i = 0; i < problemSize; i++) {
            list.add(currentElement);
            currentElement -= rng.nextInt(10);
        }
    }

    /**
     * Helper method to populate the array with random integers in nearly ascending order.
     *
     * @implNote method must call populateAscending array and then swap a small number of random
     * pairs of nearby elements
     */
    protected void populateNearlyAscendingArrayList(int problemSize) {
        list.clear();
        populateAscendingArrayList(problemSize);
        // Choose a random number of pairs to swap, between 5 and 19
        int swapCount = 5 + rng.nextInt(5);
        for (int i = 0; i < swapCount; i++) {
            // Choose a random index, excluding the final 11 indices
            int idx1 = rng.nextInt(problemSize - 11);
            // Choose an index between 1 and 10 to the right of idx1
            int idx2 = idx1 + 1 + rng.nextInt(10);
            // Swap the entries at those two indices
            swapEntries(idx1, idx2);
        }
    }

    /**
     * Helper method to swap two entries of the array.
     *
     * @param idx1 - first index to swap
     * @param idx2 - second index to swap
     * @throws IndexOutOfBoundsException if either index is out of bounds
     */
    private void swapEntries(int idx1, int idx2) throws IndexOutOfBoundsException {
        if (idx1 < 0 || idx1 >= list.size() || idx2 < 0 || idx2 >= list.size()) {
            throw new IndexOutOfBoundsException();
        }
        int temp = list.get(idx1);
        list.set(idx1, idx2);
        list.set(idx2, temp);
    }
}