package comprehensive;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import static comprehensive.Main.createGlossary;

public class GlossaryTimingExperiment extends TimingExperiment {
    private final static String problemSizeDescription = "listSize";
    private final static int problemSizeMin = 1000;
    private final static int problemSizeCount = 25;
    private final static int problemSizeStep = 1000;
    private final static int experimentIterationCount = 50;

    private final Glossary glossary = new Glossary();
    private final Random rng = new Random();

    public static void main(String[] args) throws IOException {
        TimingExperiment timingExperiment = new GlossaryTimingExperiment();
        timingExperiment.printResults();
    }

    public GlossaryTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) throws IOException {
        File file = new File("glossaryRead.txt");
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for(int word = 0; word < Math.sqrt(problemSize); word++) {
            for(int def = 0; def < Math.sqrt(problemSize); def++)
                printWriter.println(word + "::" + "noun" + "::" + def);
        }

        printWriter.close();
    }

    @Override
    protected void runComputation() throws IOException {
        createGlossary("glossaryRead.txt", glossary);
    }
}


//O(word * def) a
    //add or find word to gloss treeMap O(logWord) b
    //add or find word to def treeMap O(logDef) c


//(ab + ac)
//O( [ WD (logW) + WD (logD) ]
// O( N log N)

