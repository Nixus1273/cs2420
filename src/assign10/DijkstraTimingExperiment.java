package assign10;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DijkstraTimingExperiment extends TimingExperiment {
    private final static String problemSizeDescription = "numberOfVertices";
    private final static int problemSizeMin = 1000;
    private final static int problemSizeCount = 25;
    private final static int problemSizeStep = 1000;
    private final static int experimentIterationCount = 250;

    private List<Integer> src = new ArrayList<>();
    private List<Integer> dst = new ArrayList<>();
    private List<Double> wght = new ArrayList<>();

    private final Random rng = new Random();
    private int destination;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new DijkstraTimingExperiment();
        timingExperiment.printResults();
    }

    public DijkstraTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        src = new ArrayList<>();
        dst = new ArrayList<>();
        wght = new ArrayList<>();
        for (int i = 0; i < problemSize - 1; i++) {
            src.add(i);
            src.add(i);
            dst.add(rng.nextInt(problemSize));
            dst.add(rng.nextInt(problemSize));
        }
        for (int i = 0; i < problemSize; i++) {
            wght.add(rng.nextDouble(100.0));
        }
        destination = dst.get(rng.nextInt(dst.size()));
    }

    @Override
    protected void runComputation() {
        try {
            GraphUtility.shortestWeightedPathWithPriorityQueue(src, dst, wght, 0, destination);
        } catch (IllegalArgumentException e) {
            return;
        }
    }
}