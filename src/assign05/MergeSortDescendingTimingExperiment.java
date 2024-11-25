package assign05;

/**
 * Timing experiment for merge sort on a reverse sorted array
 *
 * @author Ethan Laynor and Brandon Flickinger
 * @version 10/3/2024
 */
public class MergeSortDescendingTimingExperiment extends ArrayListSortTimingExperiment {
    private static String problemSizeDescription = "listSize";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 2000;
    private static int experimentIterationCount = 20;

    protected MergeSorter mergeSorter = new MergeSorter(10);

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new MergeSortDescendingTimingExperiment() ;
        timingExperiment.printResults();
    }

    public MergeSortDescendingTimingExperiment() {
        super(
                problemSizeDescription,
                problemSizeMin,
                problemSizeCount,
                problemSizeStep,
                experimentIterationCount
        );
    }

    @Override
    protected void setupExperiment(int problemSize) {
        populateDescendingArrayList(problemSize);
    }


    @Override
    protected void runComputation() {
        mergeSorter.sort(list);
    }
}