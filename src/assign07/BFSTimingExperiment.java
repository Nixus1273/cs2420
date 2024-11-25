package assign07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BFSTimingExperiment extends TimingExperiment {
    private final static String problemSizeDescription = "numberOfVertices";
    private final static int problemSizeMin = 10000;
    private final static int problemSizeCount = 50;
    private final static int problemSizeStep = 1000;
    private final static int experimentIterationCount = 50;

    private Graph<Integer> graph;
    private final Random rng = new Random();
    private int destination;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new BFSTimingExperiment();
        timingExperiment.printResults();
    }

    public BFSTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        List<Integer> src = new ArrayList<>();
        List<Integer> dst = new ArrayList<>();
        for (int i = 0; i < problemSize - 1; i++) {
            src.add(i);
            src.add(i);
            dst.add(rng.nextInt(problemSize));
            dst.add(rng.nextInt(problemSize));
        }
//      dst.add(v.get(i + 1 + rng.nextInt(problemSizeMin - (i - 1))));
        graph = new Graph<>(src, dst);
        destination = dst.get(rng.nextInt(dst.size()));
    }

    @Override
    protected void runComputation() {
        try {
            graph.BFSWeighted(0, destination);
        } catch (IllegalArgumentException e) {
            return;
        }
    }
}
