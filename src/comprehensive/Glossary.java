package comprehensive;

import java.util.*;

public class Glossary {

    private final TreeMap<String, Definitions> gloss = new TreeMap<>(Comparator.naturalOrder());
    private int wordCount;
    private int defCount;
//    private final ArrayList<String> posCount;

    ArrayList<String> validPOS = new ArrayList<>(Arrays.asList(
            "adj", "adv", "conj", "interj", "noun", "prep", "pron", "verb"));
    Integer[] posCount = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0};


    public Glossary() {
        this.wordCount = 0;
        this.defCount = 0;
//        this.posCount = new ArrayList<>();
    }



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


    public String getMetadata() {
        return  "word: " + wordCount + "\n" +
                "definitions: " + defCount + "\n" +
                "definitions per word: " +  String.format("%.3f", (double)defCount / wordCount) + "\n" +
                "parts of speech: "  + "\n" +
                "first word: " + gloss.firstKey() + "\n" +
                "last word: " + gloss.lastKey() + "\n";
    }


    public String getInRange(String src, String dst) {
        NavigableSet<String> list = gloss.subMap(src, true, dst, true).navigableKeySet();
        StringBuilder returnRange = new StringBuilder();

        for (String word: list) {
            returnRange.append("\t").append(word).append("\n");
        }

        return returnRange.toString();
    }


    public String get(String word) {
        StringBuilder returnDefs = new StringBuilder(word + "\n");
        Collection<Definitions.Definition> defs = gloss.get(word).getAll();
        for (Definitions.Definition def : defs) {
            returnDefs.append("\t").append(def.POS()).append(".\t").append(def.def()).append("\n");
        }
        return returnDefs.toString();
    }

    public String getFirst(){
        return get(gloss.firstKey());
    }

    public String getLast(){
        return get(gloss.lastKey());
    }

    public String getPOS(String word) {
        return word + "\n" + gloss.get(word).getAllPOS();
    }

    // TODO: can this merge with get(word)
    public ArrayList<Definitions.Definition> getDef(String word){
        return gloss.get(word).getAll();
    }

    public boolean contains(String word) {
        return gloss.containsKey(word);
    }

    public void changeDef(String word, Definitions.Definition oldDef, String newDef){
        posCount[validPOS.indexOf(oldDef.POS())] -= 1;
        gloss.get(word).update(oldDef, newDef);
    }

    public void removeDef(String word, Definitions.Definition oldDef) {
        posCount[validPOS.indexOf(oldDef.POS())] -= 1;
        defCount--;

        gloss.get(word).remove(oldDef);
        if(gloss.get(word).numberOfDefinitions() == 0) {
            gloss.remove(word);
            wordCount--;
        }

    }

    public void addDef(String word, String POS, String def) {
        gloss.get(word).add(POS, def);
    }


    public ArrayList<String> getAllWords(){
        return new ArrayList<>(gloss.keySet());
    }


    public record Definition(String POS, String def) {}


}
