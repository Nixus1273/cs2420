package assign08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Timing experiment for add all method in array sorted set on random list
 *
 * @author Ethan Laynor and Brandon Flickinger
 * @version 10/31/2024
 */
public class ArraySortedSetAddAllRandomOrderTimingExperiment extends TimingExperiment {
    private final static String problemSizeDescription = "listSize";
    private final static int problemSizeMin = 1000;
    private final static int problemSizeCount = 25;
    private final static int problemSizeStep = 500;
    private final static int experimentIterationCount = 50;

    protected ArraySortedSet<Integer> sortedSet;
    protected List<Integer> elementsToAdd = new ArrayList<>();
    private final Random rng = new Random();

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new ArraySortedSetAddAllRandomOrderTimingExperiment();
        timingExperiment.printResults();
    }

    public ArraySortedSetAddAllRandomOrderTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        sortedSet = new ArraySortedSet<>();
        elementsToAdd.clear();
        for (int i = 0; i < problemSize; i++) {
            elementsToAdd.add(i);
        }
        Collections.shuffle(elementsToAdd);
    }

    @Override
    protected void runComputation() {
        sortedSet.addAll(elementsToAdd);
        System.out.println("SIZE: " + sortedSet.size());
    }
}