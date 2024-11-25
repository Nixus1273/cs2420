package assign05;

/**
 * Timing experiment for quick sort on a reverse sorted array
 *
 * @author Ethan Laynor and Brandon Flickinger
 * @version 10/3/2024
 */
public class QuickSortDescendingTimingExperiment extends ArrayListSortTimingExperiment {
    private static String problemSizeDescription = "listSize";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 20;
    private static int problemSizeStep = 2000;
    private static int experimentIterationCount = 20;

    protected static MedianOfThreePivotChooser pivotChooser = new MedianOfThreePivotChooser();
    protected QuickSorter quickSorter = new QuickSorter(pivotChooser);

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new QuickSortDescendingTimingExperiment() ;
        timingExperiment.printResults();
    }

    public QuickSortDescendingTimingExperiment() {
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
        quickSorter.sort(list);
    }
}