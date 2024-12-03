package comprehensive;

import java.util.*;

public class Glossary {

    private final TreeMap<String, Definitions> gloss = new TreeMap<>(Comparator.naturalOrder());
    private int wordCount;
    private int defCount;

    ArrayList<String> validPOS = new ArrayList<>(Arrays.asList(
            "adj", "adv", "conj", "interj", "noun", "prep", "pron", "verb"));
    Integer[] posCount = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0};


    public Glossary() {
        this.wordCount = 0;
        this.defCount = 0;
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


    public String[] getMetadata() {
        return new String[]{String.valueOf(wordCount),
                String.valueOf(defCount),
                String.valueOf(getPOSCount()),
                gloss.firstKey(),
                gloss.lastKey()};
    }


    public String get(String word) {
        StringBuilder returnDefs = new StringBuilder(word + "\n");
        Collection<Definitions.Definition> defs = gloss.get(word).getAll();
        for (Definitions.Definition def : defs) {
            returnDefs.append("\t").append(def.POS()).append(".\t").append(def.def()).append("\n");
        }
        return returnDefs.toString();
    }

    // TODO: can this merge with get(word)
    public ArrayList<Definitions.Definition> getDef(String word){
        return gloss.get(word).getAll();
    }

    public String getFirst(){
        return get(gloss.firstKey());
    }

    public String getLast(){
        return get(gloss.lastKey());
    }

    public NavigableSet<String> getInRange(String src, String dst) {
        return gloss.subMap(src, true, dst, true).navigableKeySet();
    }

    public List<String> getPOS(String word) {
        return gloss.get(word).getAllPOS();
    }

    public int getPOSCount() {
        int count = 0;
        for (Integer integer : posCount) {
            if (integer > 0) {
                count++;
            }
        }
        return count;
    }

    public void addDef(String word, String POS, String def) {
        gloss.get(word).add(POS, def);
    }

    public void changeDef(String word, Definitions.Definition oldDef, String newDef){
        posCount[validPOS.indexOf(oldDef.POS())] -= 1;
        gloss.get(word).update(oldDef, newDef);
    }

    public void removeDef(String word, Definitions.Definition oldDef) {
        posCount[validPOS.indexOf(oldDef.POS())] -= 1;
        defCount--;

        gloss.get(word).remove(oldDef);
        if(gloss.get(word).numberOfDefs() == 0) {
            gloss.remove(word);
            wordCount--;
        }
    }

    public boolean contains(String word) {
        return gloss.containsKey(word);
    }

    public ArrayList<String> getAllWords(){
        return new ArrayList<>(gloss.keySet());
    }
}
