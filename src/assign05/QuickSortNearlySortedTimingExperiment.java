package assign05;

/**
 * Timing experiment for quick sort on a nearly sorted array
 *
 * @author Ethan Laynor and Brandon Flickinger
 * @version 10/3/2024
 */
public class QuickSortNearlySortedTimingExperiment extends ArrayListSortTimingExperiment {
    private static String problemSizeDescription = "listSize";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 2000;
    private static int experimentIterationCount = 20;

    protected static MedianOfThreePivotChooser pivotChooser = new MedianOfThreePivotChooser();
    protected QuickSorter quickSorter = new QuickSorter(pivotChooser);

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new QuickSortNearlySortedTimingExperiment() ;
        timingExperiment.printResults();
    }

    public QuickSortNearlySortedTimingExperiment() {
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
        quickSorter.sort(list);
    }
}