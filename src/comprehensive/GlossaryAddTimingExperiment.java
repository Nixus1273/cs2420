package comprehensive;

import java.io.IOException;
import java.util.Random;

public class GlossaryAddTimingExperiment extends TimingExperiment {
    private final static String problemSizeDescription = "Word * Defs";
    private final static int problemSizeMin = 100000;
    private final static int problemSizeCount = 25;
    private final static int problemSizeStep = 10000;
    private final static int experimentIterationCount = 50;

    private final Glossary glossary = new Glossary();
    private final Random rng = new Random();
    private String randomWord;
    private String randomDef;

    public static void main(String[] args) throws IOException {
        TimingExperiment timingExperiment = new GlossaryAddTimingExperiment();
        timingExperiment.printResults();
    }

    public GlossaryAddTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) throws IOException {
        for(int word = 0; word < Math.sqrt(problemSize); word++) {
            for (int def = 0; def < Math.sqrt(problemSize); def++){
                glossary.add(String.valueOf(word), "noun", String.valueOf(def));
            }
        }
        randomWord = String.valueOf(rng.nextInt((int) Math.sqrt(problemSize)));
        randomDef = String.valueOf(rng.nextInt((int) Math.sqrt(problemSize)));
    }

    @Override
    protected void runComputation() throws IOException {
        glossary.add(randomWord, "noun", randomDef);
    }
}





