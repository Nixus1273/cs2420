package comprehensive;

import java.io.*;
import java.util.Random;

import static comprehensive.Main.createGlossary;
import static comprehensive.Main.option10;

public class GlossaryWriteTimingExperiment extends TimingExperiment {
    private final static String problemSizeDescription = "listSize";
    private final static int problemSizeMin = 100;
    private final static int problemSizeCount = 20;
    private final static int problemSizeStep = 100;
    private final static int experimentIterationCount = 25;

    private final Glossary glossary = new Glossary();
    private final Random rng = new Random();
    private final BufferedReader bf = new BufferedReader (new FileReader("filePath.txt"));

    public static void main(String[] args) throws IOException {
        TimingExperiment timingExperiment = new GlossaryWriteTimingExperiment();
        timingExperiment.printResults();
    }

    public GlossaryWriteTimingExperiment() throws FileNotFoundException {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) throws IOException {
        File file = new File("glossaryRead.txt");
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for(int i = 0; i < problemSize; i++) {
            printWriter.println(rng.nextInt(problemSize) + "::" + "noun" + "::" + i + i + i);
        }
        printWriter.close();
        createGlossary("glossaryRead.txt", glossary);
    }

    @Override
    protected void runComputation() throws IOException {
        option10(glossary);
    }
}