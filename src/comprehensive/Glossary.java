package comprehensive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class Glossary {

    TreeMap<Word, Word> gloss = new TreeMap<>(Comparator.comparing(Word::getWord));
    int defCount = 0;
    int posCount = 0;

    public Glossary(String fileName) throws IOException {
        // with open: for line in file name; split(::)
        // each element
        List<String> lines = Files.readAllLines(Paths.get(fileName));

        for (String line : lines) {
            // add each element
        }

    }
}
