package assign06;

import java.util.ArrayList;
import java.util.Random;

/**
 * Timing experiment to collect the running times of copying the elements of a linked list using an enhanced for-loop.
 *
 * @author CS 2420 course staff
 * @version 2024-10-04
 */
public class SinglyLinkedListForEachTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "linkedListSize";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 30;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 50;

    private SinglyLinkedList<Integer> linkedList;
    private ArrayList<Integer> arrayList;
    private final Random rng = new Random();

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new SinglyLinkedListForEachTimingExperiment();
        timingExperiment.printResults();
    }

    public SinglyLinkedListForEachTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        linkedList = new SinglyLinkedList<>();
        for (int i = 0; i < problemSize; i++) {
            linkedList.insertFirst(rng.nextInt());
        }
        arrayList = new ArrayList<>(problemSize);
    }

    @Override
    protected void runComputation() {
        copyLinkedListToArrayList();
    }

    /**
     * Uses an enhanced for-loop to copy the values of the linked list into an array list.
     *
     * @implNote An enhanced for-loop uses the iterator methods {@code hasNext()} and {@code next()}.
     * If these methods are implemented to run in O(1), then the for loop should run in O(N).
     */
    private void copyLinkedListToArrayList() {
        for (Integer value : linkedList) {
            arrayList.add(value);
        }
    }
}
