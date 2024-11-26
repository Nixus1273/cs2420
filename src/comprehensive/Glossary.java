package comprehensive;

import java.util.*;

public class Glossary {

    private TreeMap<String, Word> gloss = new TreeMap<>(Comparator.naturalOrder());
    private int wordCount;
    private int defCount;
    private ArrayList<String> posCount;

    public Glossary() {
        this.wordCount = 0;
        this.defCount = 0;
        this.posCount = new ArrayList<>();
    }

    public void add(String word, String POS, String def) {
        if (gloss.containsKey(word)) {
            gloss.get(word).addDefinition(POS, def);
        } else {
            gloss.put(word, new Word(word, POS, def));
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

    public String wordInRange(String src, String dst) {
        if(!gloss.containsKey(src) && !gloss.containsKey(dst)) {
            return "Not valid words"; // TODO: Throw an error or what happens if a word isn't in the gloss????
        }

        NavigableSet<String> list = gloss.subMap(src, true, dst, true).navigableKeySet();
        String returnRange = "The words between " + src + " and " + dst +  " are:" + "\n";
        for (String word: list) {
            returnRange += "\t" + word + "\n";
        }

        return returnRange;
    }

    public String getWord(String word) {
        if(!gloss.containsKey(word))
            return word + " not found\n";

        String returnDefs = word + "\n";
        Collection<Word.Definition> defs = gloss.get(word).getDefinitions();
        for (Word.Definition def : defs) {
            returnDefs +=  "\t" + def.getPOS() + ".\t" + def.getDef() + "\n";
        }

        return returnDefs;
    }

    public String getWordFirst(){
        return getWord(gloss.firstKey());
    }

    public String getWordLast(){
        return getWord(gloss.lastKey());
    }

    public String getWordPOS(String word) {
        if(!gloss.containsKey(word))
            return word + " not found\n";

        return word + "\n" + gloss.get(word).getAllPos();
    }

    public ArrayList<Word.Definition> getWordDef(String word){
        return gloss.get(word).getDefinitions();
    }

    public boolean containsWord(String word) {
        return gloss.containsKey(word);
    }

    public void changeDefOfWord(String word, Word.Definition oldDef, String newDef){
        gloss.get(word).updateDefinition(oldDef, newDef);
    }

    public void removeDefOfWord(String word, Word.Definition oldDef) {
        gloss.get(word).removeDefinition(oldDef);
        if(gloss.get(word).numberOfDefinitions() == 0)
            gloss.remove(word);
    }

    public void addDefOfWord(String word, String POS, String def) {
        gloss.get(word).addDefinition(POS, def);
    }
}
