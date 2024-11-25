package assign06;

import java.util.Random;

public class LinkedListQueueOfferTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "linkedListSize";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 30;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 50;

    protected Queue<Integer> queue;
    private final Random rng = new Random();

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new LinkedListQueueOfferTimingExperiment();
        timingExperiment.printResults();
    }

    public LinkedListQueueOfferTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        queue = new LinkedListQueue<>();
        for (int i = 0; i < problemSize; i++) {
            queue.offer(rng.nextInt());
        }
    }

    @Override
    protected void runComputation() {
        queue.offer(rng.nextInt());
    }
}