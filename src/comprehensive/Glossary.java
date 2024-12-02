package comprehensive;

import java.util.*;

public class Glossary {

    private final TreeMap<String, Word> gloss = new TreeMap<>(Comparator.naturalOrder());
    private int wordCount;
    private int defCount;
    private final ArrayList<String> posCount;

    public Glossary() {
        this.wordCount = 0;
        this.defCount = 0;
        this.posCount = new ArrayList<>();
    }

    public void add(String word, String POS, String def) {
        if (contains(word)) {
            gloss.get(word).addDefinition(POS, def);
        } else {
            gloss.put(word, new Word(POS, def));
            wordCount++;
        }
        defCount++;

        if (!posCount.contains(POS)) {
            posCount.add(POS);
        }
    }

    public String getMetadata() {
        return  "word: " + wordCount + "\n" +
                "definitions: " + defCount + "\n" +
                "definitions per word: " +  String.format("%.3f", (double)defCount / wordCount) + "\n" +
                "parts of speech: " + posCount.size() + "\n" +
                "first word: " + gloss.firstKey() + "\n" +
                "last word: " + gloss.lastKey() + "\n";
    }

    public String getWordsInRange(String src, String dst) {
        NavigableSet<String> list = gloss.subMap(src, true, dst, true).navigableKeySet();
        StringBuilder returnRange = new StringBuilder();

        for (String word: list) {
            returnRange.append("\t").append(word).append("\n");
        }

        return returnRange.toString();
    }

    public String getWord(String word) {
        StringBuilder returnDefs = new StringBuilder(word + "\n");
        Collection<Word.Definition> defs = gloss.get(word).getDefinitions();
        for (Word.Definition def : defs) {
            returnDefs.append("\t").append(def.POS()).append(".\t").append(def.def()).append("\n");
        }

        return returnDefs.toString();
    }

    public String getFirst(){
        return getWord(gloss.firstKey());
    }

    public String getLast(){
        return getWord(gloss.lastKey());
    }

    public String getPOS(String word) {
        return word + "\n" + gloss.get(word).getAllPOS();
    }

    public ArrayList<Word.Definition> getDef(String word){
        return gloss.get(word).getDefinitions();
    }

    public boolean contains(String word) {
        return gloss.containsKey(word);
    }

    public void changeDef(String word, Word.Definition oldDef, String newDef){
        gloss.get(word).updateDefinition(oldDef, newDef);
    }

    public void removeDef(String word, Word.Definition oldDef) {
        gloss.get(word).removeDefinition(oldDef);
        if(gloss.get(word).numberOfDefinitions() == 0)
            gloss.remove(word);
    }

    public void addDef(String word, String POS, String def) {
        gloss.get(word).addDefinition(POS, def);
    }
}
