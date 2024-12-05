package comprehensive;

import java.io.*;
        import java.util.Random;

public class GlossaryUpdateTimingExperiment extends TimingExperiment {
    private final static String problemSizeDescription = "listSize";
    private final static int problemSizeMin = 100000;
    private final static int problemSizeCount = 25;
    private final static int problemSizeStep = 10000;
    private final static int experimentIterationCount = 50;

    private final Random rng = new Random();
    private Glossary glossary;
    private String randomWord;
    private Definitions.SingleDefinition oldDef;
    private String newDef;

    public static void main(String[] args) throws IOException {
        TimingExperiment timingExperiment = new GlossaryUpdateTimingExperiment();
        timingExperiment.printResults();
    }

    public GlossaryUpdateTimingExperiment() throws FileNotFoundException {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) throws IOException {
        glossary = new Glossary();
        for(int word = 0; word < Math.sqrt(problemSize); word++) {
            for (int def = 0; def < Math.sqrt(problemSize); def++){
                glossary.add(String.valueOf(word), "noun", String.valueOf(def));
            }
        }
        randomWord = String.valueOf(rng.nextInt((int) Math.sqrt(problemSize)));
        oldDef = new Definitions.SingleDefinition("noun", String.valueOf((rng.nextInt((int) Math.sqrt(problemSize)))));
        newDef = String.valueOf((int) Math.sqrt(problemSize));
    }

    @Override
    protected void runComputation() throws IOException {
        glossary.changeDef(randomWord, oldDef, newDef);
    }
}
