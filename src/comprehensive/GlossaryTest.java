package comprehensive;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlossaryTest {

    private Glossary glossary;
    private Glossary repeatedGlossary;
    private Glossary bigGlossary;

    Random rng = new Random();
    private final ArrayList<String> validPOS = new ArrayList<>(Arrays.asList("adj", "adv", "conj", "interj", "noun", "prep", "pron", "verb"));

    @BeforeEach
    void setUp() throws IOException {
        glossary = new Glossary();
        List<String> lines = Files.readAllLines(Paths.get("2420_glossary.txt"));

        repeatedGlossary = new Glossary();
        bigGlossary = new Glossary();

        for (String line : lines) {
            String[] data = line.split("::");
            glossary.add(data[0], data[1], data[2]);
        }

        repeatedGlossary.add("remark", "noun", "");
        repeatedGlossary.add("remember", "noun", "");
        repeatedGlossary.add("remedy", "noun", "");
        repeatedGlossary.add("renounce", "noun", "");
        repeatedGlossary.add("queue", "noun", "");

        for (int i = 0; i < 10000; i++) {
            bigGlossary.add( String.valueOf(i), validPOS.get(rng.nextInt(validPOS.size())), "a definition :D");
        }
    }

    @Test
    void testGetMetadata() {
        String expected = """
                words - 10
                definitions - 15
                definitions per word - 1.500
                parts of speech - 3
                first word - algorithm
                last word - tree
                """;
        assertEquals(expected, glossary.getMetadata());
        glossary.removeDef("data structure", new Definitions.SingleDefinition("noun",
                                "A way of organizing data, specifically in terms of its storage and management."));
        expected = """
                words - 9
                definitions - 14
                definitions per word - 1.556
                parts of speech - 3
                first word - algorithm
                last word - tree
                """;
        assertEquals(expected, glossary.getMetadata());
    }

    @Test
    void testBigMetadata() {
        String expected = """
                words - 10000
                definitions - 10000
                definitions per word - 1.000
                parts of speech - 8
                first word - 0
                last word - 9999
                """;
        System.out.println(bigGlossary.getMetadata());
        assertEquals(expected, bigGlossary.getMetadata());
    }

    @Test
    void testWordInRange() {
        String expected = """
                	graph
                	hashing
                	logic proposition
                	queue
                """;
        assertEquals(expected, glossary.getInRange("graph", "queue"));
    }

    @Test
    void testGetDefinitions() {
        String expected = """
                tree
                	noun.	A directed graph in which any two vertices are connected by exactly one path.
                	noun.	A tree with exactly one node designated as the root and every node as at most two children.
                	noun.	An acyclic connected digraph.
                	verb.	To drive an animal or person into or up a tree.
                """;
        assertEquals(expected, glossary.getAllDefs("tree"));
    }

    @Test
    void testGetFirstWord() {
        String expected = """
                algorithm
                	noun.	A detailed, unambiguous process for solving a problem.
                """;
        assertEquals(expected, glossary.getFirstDefs());
    }

    @Test
    void testGetLastWord() {
        String expected = """
                tree
                	noun.	A directed graph in which any two vertices are connected by exactly one path.
                	noun.	A tree with exactly one node designated as the root and every node as at most two children.
                	noun.	An acyclic connected digraph.
                	verb.	To drive an animal or person into or up a tree.
                """;
        assertEquals(expected, glossary.getLastDefs());
    }

    @Test
    void testGetPOS() {
        String expected = """
                generic
                	adj.
                	noun.
                """;
        assertEquals(expected, glossary.getPOS("generic"));
    }

    @Test
    void testWordDef() {
        ArrayList<Definitions.SingleDefinition> expected = new ArrayList<>(List.of(new Definitions.SingleDefinition(
                "adj", "A programming paradigm based on classes or " +
                "static methods that are defined independent of data type."),
                new Definitions.SingleDefinition("noun", "A parameterized type in Java, denoted with <>.")));
        assertEquals(expected, glossary.getDefList("generic"));
    }

    @Test
    void testWordInRange2() {
        String expected = """
                	queue
                	remark
                	remedy
                	remember
                """;
        assertEquals(expected, repeatedGlossary.getInRange("queue", "ren"));
    }

    @Test
    void testWordInRange3() {
        String expected = "";
        assertEquals(expected, glossary.getInRange("queue", "graph"));
    }
}
