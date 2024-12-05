package comprehensive;

import java.util.*;

/**
 * A class that represents a glossary, managing words and their definitions.
 * It allows adding, removing, updating definitions, and retrieving metadata and other details about the glossary.
 *
 * @author - Brandon Flickinger and Ethan Laynor
 * @version - December 5, 2024
 */
public class Glossary {

    private final TreeMap<String, Definitions> gloss = new TreeMap<>(Comparator.naturalOrder());
    private final ArrayList<String> validPOS = new ArrayList<>(Arrays.asList("adj", "adv", "conj", "interj", "noun", "prep", "pron", "verb"));
    private final Integer[] posCount = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0};
    private int wordCount;
    private int defCount;

    /**
     * Constructs an empty glossary with no words or definitions.
     */
    public Glossary() {
        this.wordCount = 0;
        this.defCount = 0;
    }

    /**
     * Adds a word and its definition to the glossary.
     *
     * @param word the word to add.
     * @param POS  the part of speech of the word (e.g., noun, verb).
     * @param def  the definition of the word.
     */
    public void add(String word, String POS, String def) {
        if (contains(word)) {
            gloss.get(word).add(POS, def);
        } else {
            gloss.put(word, new Definitions(POS, def));
            wordCount++;
        }
        defCount++;
        posCount[validPOS.indexOf(POS)] += 1;
    }

    /**
     * Removes a specific definition of a word.
     *
     * @param word   the word whose definition is to be removed.
     * @param oldDef the definition to remove.
     * @return true if the word has no more definitions and was removed; false otherwise.
     */
    public boolean removeDef(String word, Definitions.SingleDefinition oldDef) {
        posCount[validPOS.indexOf(oldDef.POS())] -= 1;
        defCount--;
        gloss.get(word).remove(oldDef);

        if(gloss.get(word).numberOfDefinitions() == 0) {
            gloss.remove(word);
            wordCount--;
            return true;
        }
        return false;
    }

    /**
     * Updates an existing definition of a word.
     *
     * @param word   the word whose definition is to be updated.
     * @param oldDef the old definition to replace.
     * @param newDef the new definition to set.
     */
    public void changeDef(String word, Definitions.SingleDefinition oldDef, String newDef){
        gloss.get(word).update(oldDef, newDef);
    }

    /**
     * Checks if the glossary contains a specific word.
     *
     * @param word the word to check.
     * @return true if the word exists in the glossary, false otherwise.
     */
    public boolean contains(String word) {
        return gloss.containsKey(word);
    }

    /**
     * Retrieves metadata about the glossary, such as word count, definition count, and parts of speech used.
     *
     * @return a string containing the glossary metadata.
     */
    public String getMetadata() {
        int count = 0;
        for (Integer number : posCount) {
            if (number > 0) {
                count++;
            }
        }

        String metaData = "words - " + wordCount + "\n" +
                          "definitions - " + defCount + "\n";

        if(wordCount == 0){
            metaData += "definitions per word - 0.00\n";
        }else{
            metaData += "definitions per word - " +  String.format("%.3f", (double)defCount / wordCount) + "\n";
        }

        metaData += "parts of speech - " + count + "\n";

        if(gloss.isEmpty()){
            metaData += "first word - \n" +
                    "last word - \n";
        }else{
            metaData += "first word - " + gloss.firstKey() + "\n" +
                "last word - " + gloss.lastKey() + "\n";
        }
        return metaData;
    }

    /**
     * Retrieves all definitions of a specific word.
     *
     * @param word the word to retrieve definitions for.
     * @return a string containing all definitions of the word.
     */
    public String getAllDefs(String word) {
        StringBuilder returnDefs = new StringBuilder(word + "\n");
        Collection<Definitions.SingleDefinition> defs = gloss.get(word).getAllDefinitions(); // log(Words)
        for (Definitions.SingleDefinition def : defs) {
            returnDefs.append("\t").append(def.POS()).append(".\t").append(def.def()).append("\n");
        }
        return returnDefs.toString();
    }

    /**
     * Retrieves the definitions of the first word in the glossary.
     *
     * @return a string containing the definitions of the first word.
     */
    public String getFirstDefs(){
        if(gloss.isEmpty())
            return " - List is empty.\n";
        return getAllDefs(gloss.firstKey());
    }

    /**
     * Retrieves the definitions of the last word in the glossary.
     *
     * @return a string containing the definitions of the last word.
     */
    public String getLastDefs(){
        if(gloss.isEmpty())
            return " - List is empty.\n";
        return getAllDefs(gloss.lastKey());
    }

    /**
     * Retrieves the list of all definitions for a word.
     *
     * @param word the word to retrieve definitions for.
     * @return a list of definitions.
     */
    public ArrayList<Definitions.SingleDefinition> getDefList(String word){
        return gloss.get(word).getAllDefinitions();
    }

    /**
     * Retrieves a range of words in the glossary.
     *
     * @param src the starting word.
     * @param dst the ending word.
     * @return a string containing words within the specified range.
     */
    public String getInRange(String src, String dst) {
        String adjSrc = gloss.ceilingKey(src);
        String adjDst = gloss.floorKey(dst);

        if(adjSrc == null || adjDst == null || adjSrc.compareTo(adjDst) > 0)
            return "";

        NavigableSet<String> list = gloss.subMap(adjSrc, true, adjDst, true).navigableKeySet();

        StringBuilder returnRange = new StringBuilder();
        for (String word: list) {
            returnRange.append("\t").append(word).append("\n");
        }

        return returnRange.toString();
    }

    /**
     * Retrieves all parts of speech for a word.
     *
     * @param word the word to retrieve parts of speech for.
     * @return a string containing the parts of speech for the word.
     */
    public String getPOS(String word) {
        return word + "\n" + gloss.get(word).getAllPOS();
    }

    /**
     * Retrieves a list of all words in the glossary.
     *
     * @return a list of all words in the glossary.
     */
    public ArrayList<String> getAllWordsList(){
        return new ArrayList<>(gloss.keySet());
    }
}
