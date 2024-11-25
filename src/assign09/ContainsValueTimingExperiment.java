package assign09;

import java.util.Random;
import java.util.TreeMap;

public class ContainsValueTimingExperiment extends TimingExperiment {
    private final static String problemSizeDescription = "listSize";
    private final static int problemSizeMin = 1000;
    private final static int problemSizeCount = 20;
    private final static int problemSizeStep = 500;
    private final static int experimentIterationCount = 50;

    HashTable<String, Integer> hashTable = new HashTable<>();
    TreeMap<String, Integer> treeMap = new TreeMap<>();

    private final Random rng = new Random();

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new ContainsValueTimingExperiment();
        timingExperiment.printResults();
    }

    public ContainsValueTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        for(int i = 0; i < problemSize ; i++) {

            String string = String.valueOf(rng.nextInt(problemSize));
            hashTable.put(string, i);
            treeMap.put(string, i);

        }
    }

    @Override
    protected void runComputationHash() {
        hashTable.containsValue(hashTable.size() / 2);
    }

    @Override
    protected void runComputationTree() {
        treeMap.containsValue(treeMap.size() / 2);
    }
}
