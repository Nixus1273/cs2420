package assign08;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Timing experiment for add all method in array sorted set on nearly sorted list
 *
 * @author Ethan Laynor and Brandon Flickinger
 * @version 10/31/2024
 */
public class ArraySortedSetAddAllNearlySortedTimingExperiment extends TimingExperiment {
    private final static String problemSizeDescription = "listSize";
    private final static int problemSizeMin = 1000;
    private final static int problemSizeCount = 25;
    private final static int problemSizeStep = 500;
    private final static int experimentIterationCount = 50;

    protected ArraySortedSet<Integer> sortedSet;
    protected List<Integer> elementsToAdd = new ArrayList<>();
    private final Random rng = new Random();

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new ArraySortedSetAddAllNearlySortedTimingExperiment();
        timingExperiment.printResults();
    }

    public ArraySortedSetAddAllNearlySortedTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        sortedSet = new ArraySortedSet<>();
        elementsToAdd.clear();
        for (int i = 0; i < problemSize; i++) {
            elementsToAdd.add(i);
        }
        // Choose a random number of pairs to swap, between 5 and 19
        int swapCount = 5 + rng.nextInt(15);
        for (int i = 0; i < swapCount; i++) {
            int idx1 = rng.nextInt(problemSize - 11);
            int idx2 = idx1 + 1 + rng.nextInt(10);
            swapEntries(idx1, idx2);
        }
    }

    private void swapEntries(int idx1, int idx2) throws IndexOutOfBoundsException {
        if (idx1 < 0 || idx1 >= elementsToAdd.size() || idx2 < 0 || idx2 >= elementsToAdd.size()){
            throw new IndexOutOfBoundsException();
        }
        int temp = elementsToAdd.get(idx1);
        elementsToAdd.set(idx1, idx2);
        elementsToAdd.set(idx2, temp);
    }

    @Override
    protected void runComputation() {
        sortedSet.addAll(elementsToAdd);
    }
}
