package assign06;

import java.util.Iterator;
import java.util.Random;

/**
 * Timing experiment to collect the running times of removing items from a linked list via its iterator.
 *
 * @author CS 2420 course staff
 * @version 2024-10-04
 */
public class SinglyLinkedListIteratorRemoveTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "linkedListSize";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 30;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 50;

    private SinglyLinkedList<Integer> linkedList;
    private final Random rng = new Random();

    public static void main(String[] args) {
        SinglyLinkedListIteratorRemoveTimingExperiment timingExperiment = new SinglyLinkedListIteratorRemoveTimingExperiment();
        timingExperiment.printResults();
    }

    public SinglyLinkedListIteratorRemoveTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        linkedList = new SinglyLinkedList<>();
        for (int i = 0; i < problemSize; i++) {
            linkedList.insertFirst(rng.nextInt());
        }
    }

    @Override
    protected void runComputation() {
        filterOutEvenNumbersFromLinkedList();
    }

    /**
     * Uses the iterator to remove all even integers from the linked list.
     *
     * @implNote Uses the {@code hasNext()}, {@code next()}, and {@code remove()} methods
     * from the linked list iterator. If these have been implemented to run in O(1), then
     * filtering out the even numbers should run in O(n).
     */
    private void filterOutEvenNumbersFromLinkedList() {
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            if (value % 2 == 0) {
                iterator.remove();
            }
        }
    }
}
