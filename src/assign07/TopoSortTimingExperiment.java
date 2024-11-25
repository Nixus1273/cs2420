package assign07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TopoSortTimingExperiment extends TimingExperiment {
    private final static String problemSizeDescription = "numberOfVertices";
    private final static int problemSizeMin = 10000;
    private final static int problemSizeCount = 50;
    private final static int problemSizeStep = 1000;
    private final static int experimentIterationCount = 50;

    private Graph<Integer> graph;
    private final Random rng = new Random();

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new BFSTimingExperiment();
        timingExperiment.printResults();
    }

    public TopoSortTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        List<Integer> src = new ArrayList<>();
        List<Integer> dst = new ArrayList<>();
        for (int i = 0; i < problemSize - 1; i++) {
            src.add(i);
            src.add(i);
            dst.add(rng.nextInt(i + 1 + rng.nextInt(problemSizeMin - (i - 1))));
            dst.add(rng.nextInt(i + 1 + rng.nextInt(problemSizeMin - (i - 1))));
        }
        graph = new Graph<>(src, dst);
    }

    @Override
    protected void runComputation() {
        try {
            graph.topoSort();
        } catch (IllegalArgumentException e) {
            return;
        }
    }
}
