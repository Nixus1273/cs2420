
package comprehensive;

import java.io.*;
        import java.util.Random;

public class GlossaryMetaDataTimingExperiment extends TimingExperiment {
    private final static String problemSizeDescription = "listSize";
    private final static int problemSizeMin = 100000;
    private final static int problemSizeCount = 25;
    private final static int problemSizeStep = 10000;
    private final static int experimentIterationCount = 50;

    private final Glossary glossary = new Glossary();
    private final Random rng = new Random();
    private int randomWord;
    private Definitions.SingleDefinition oldDef;

    public static void main(String[] args) throws IOException {
        TimingExperiment timingExperiment = new GlossaryMetaDataTimingExperiment();
        timingExperiment.printResults();
    }

    public GlossaryMetaDataTimingExperiment() throws FileNotFoundException {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) throws IOException {
        for(int word = 0; word < Math.sqrt(problemSize); word++) {
            for (int def = 0; def < Math.sqrt(problemSize); def++){
                glossary.add(String.valueOf(word), "noun", String.valueOf(def));
            }
        }
    }

    @Override
    protected void runComputation() throws IOException {
        glossary.getMetadata();
    }
}