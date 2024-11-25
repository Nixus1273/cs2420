package lab11;

import java.util.*;

public class PriorityQueueAddTimingExperiment extends TimingExperiment {
    private final static String problemSizeDescription = "listSize";
    private final static int problemSizeMin = 100000;
    private final static int problemSizeCount = 25;
    private final static int problemSizeStep = 10000;
    private final static int experimentIterationCount = 500;

    protected PriorityQueue<Integer> pq;
    protected List<Integer> elementsToAdd = new ArrayList<>();
    private final Random rng = new Random();

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new PriorityQueueAddTimingExperiment();
        timingExperiment.printResults();
    }

    public PriorityQueueAddTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        pq = new PriorityQueue<>();
        elementsToAdd.clear();
        for (int i = 0; i < problemSize; i++) {
            pq.add(rng.nextInt(problemSize));
        }
    }

    @Override
    protected void runComputation() {
        pq.add(rng.nextInt(problemSizeMin));
    }
}