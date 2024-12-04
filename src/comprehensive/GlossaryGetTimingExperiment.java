package comprehensive;

import java.io.IOException;
import java.util.Random;

public class GlossaryGetTimingExperiment extends TimingExperiment {
    private final static String problemSizeDescription = "Words * Defs";
    private final static int problemSizeMin = 100;
    private final static int problemSizeCount = 15;
    private final static int problemSizeStep = 100;
    private final static int experimentIterationCount = 25;

    private Glossary glossary;
    private final Random rng = new Random();
    private int randomInt;

    public static void main(String[] args) throws IOException {
        TimingExperiment timingExperiment = new GlossaryGetTimingExperiment();
        timingExperiment.printResults();
    }

    public GlossaryGetTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) throws IOException {
        glossary = new Glossary();
        randomInt = rng.nextInt(problemSize);
        for(int word = 0; word < Math.sqrt(problemSize); word++) {
            for(int def = 0; def < Math.sqrt(problemSize); def++)
                glossary.add(String.valueOf(word), "noun", String.valueOf(def));
        }
    }

    @Override
    protected void runComputation() throws IOException {
        glossary.getAllDefs(String.valueOf(randomInt));
    }
}
