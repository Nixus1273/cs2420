package assign10;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class BinaryMinHeapAddTimingExperiment extends TimingExperiment {
    private final static String problemSizeDescription = "listSize";
    private final static int problemSizeMin = 100000;
    private final static int problemSizeCount = 25;
    private final static int problemSizeStep = 10000;
    private final static int experimentIterationCount = 250;

    protected BinaryMinHeap<Integer> pq = new BinaryMinHeap<>();
    private final Random rng = new Random();
    private int randomNumber;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new BinaryMinHeapAddTimingExperiment();
        timingExperiment.printResults();
    }

    public BinaryMinHeapAddTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        pq.clear();
        for (int i = 0; i < problemSize; i++) {
            pq.add(rng.nextInt(problemSize));
        }
        randomNumber = rng.nextInt(problemSize);
    }

    @Override
    protected void runComputation() {
//        pq.add(randomNumber);
//        pq.peek();
        pq.extract();
    }
}