package assign05;

/**
 * Timing experiment for merge sort on a nearly sorted array
 *
 * @author Ethan Laynor and Brandon Flickinger
 * @version 10/3/2024
 */
public class MergeSortNearlySortedTimingExperiment extends ArrayListSortTimingExperiment {
    private static String problemSizeDescription = "listSize";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 2000;
    private static int experimentIterationCount = 20;

    protected MergeSorter mergeSorter = new MergeSorter(10);

    public static void main(String[] args) {
        ArrayListSortTimingExperiment timingExperiment = new MergeSortNearlySortedTimingExperiment() ;
//        timingExperiment.printResults();
        timingExperiment.setupExperiment(100);
        System.out.println(timingExperiment.list);
    }

    public MergeSortNearlySortedTimingExperiment() {
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
        populateNearlyAscendingArrayList(problemSize);
    }

    @Override
    protected void runComputation() {
        mergeSorter.sort(list);
    }
}