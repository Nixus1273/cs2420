package comprehensive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlossaryTest {

    private Glossary glossary;

    @BeforeEach
    void setUp() throws IOException {
        glossary = new Glossary();
        List<String> lines = Files.readAllLines(Paths.get("2420_glossary.txt"));

        for (String line : lines) {
            String[] data = line.split("::");
            glossary.add(data[0], data[1], data[2]);
        }
    }

    @Test
    void testGetMetadata() throws IOException {
        System.out.println(glossary.getMetadata());
    }

    @Test
    void testWordInRange() {
        System.out.println(glossary.getInRange("graph", "queue"));
    }

    @Test
    void testGetDefinitions() {
        System.out.println(glossary.getAllDefs("tree"));
    }

    @Test
    void testGetFirstWord() {
        System.out.println(glossary.getFirstDefs());
    }

    @Test
    void testGetLastWord() {
        System.out.println(glossary.getLastDefs());
    }

    @Test
    void testGetPOS() {
        System.out.println(glossary.getPOS("generic"));
    }

    @Test
    void testWordDef() {
        System.out.println(glossary.getDefList("generic"));
    }
}
