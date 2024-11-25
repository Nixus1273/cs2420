package assign09;

import java.util.Arrays;

public abstract class TimingExperiment {
    protected String problemSizeDescription;
    protected int problemSizeMin;
    protected int problemSizeCount;
    protected int problemSizeStep;
    protected int experimentIterationCount;

    public TimingExperiment(
        String problemSizeDescription,
        int problemSizeMin,
        int problemSizeCount,
        int problemSizeStep,
        int experimentIterationCount
    ) {
        this.problemSizeDescription = problemSizeDescription;
        this.problemSizeMin = problemSizeMin;
        this.problemSizeCount = problemSizeCount;
        this.problemSizeStep = problemSizeStep;
        this.experimentIterationCount = experimentIterationCount;
    }

    public void printResults() {
        System.out.println(problemSizeDescription + "\ttime (ns)");
        int size = problemSizeMin;
        for (int i = 0; i < problemSizeCount; i++) {
            long medianElapsedTimeHash = computeMedianElapsedTimeHash(size);
            long medianElapsedTimeTree = computeMedianElapsedTimeTree(size);

            System.out.println(size + "\t\t" + medianElapsedTimeHash + "\t\t" + medianElapsedTimeTree);
            size += problemSizeStep;
        }
    }


    protected long computeMedianElapsedTimeHash(int problemSize) {
        long[] elapsedTimes = new long[experimentIterationCount];
        for (int i = 0; i < experimentIterationCount; i++) {
            elapsedTimes[i] = computeElapsedTimeHash(problemSize);
        }
        Arrays.sort(elapsedTimes);
        return elapsedTimes[experimentIterationCount / 2];
    }

    protected long computeElapsedTimeHash(int problemSize) {
        setupExperiment(problemSize);
        long startTime = System.nanoTime();
        runComputationHash();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    protected long computeMedianElapsedTimeTree(int problemSize) {
        long[] elapsedTimes = new long[experimentIterationCount];
        for (int i = 0; i < experimentIterationCount; i++) {
            elapsedTimes[i] = computeElapsedTimeTree(problemSize);
        }
        Arrays.sort(elapsedTimes);
        return elapsedTimes[experimentIterationCount / 2];
    }

    protected long computeElapsedTimeTree(int problemSize) {
        setupExperiment(problemSize);
        long startTime = System.nanoTime();
        runComputationTree();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }





    protected abstract void setupExperiment(int problemSize);

    protected abstract void runComputationHash();

    protected abstract void runComputationTree();
}
