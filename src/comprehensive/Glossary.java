package comprehensive;

import java.util.*;

public class Glossary {

    private final TreeMap<String, Definitions> gloss = new TreeMap<>(Comparator.naturalOrder());
    private final ArrayList<String> validPOS = new ArrayList<>(Arrays.asList("adj", "adv", "conj", "interj", "noun", "prep", "pron", "verb"));
    private final Integer[] posCount = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0};
    private int wordCount;
    private int defCount;


    public Glossary() {
        this.wordCount = 0;
        this.defCount = 0;
    }


    //
    //-------------------------------------------------------------------------------------------------------------------------
    //


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

    //TODO |Check| - Will updating a def only update a def and not a POS?
    public void changeDef(String word, Definitions.SingleDefinition oldDef, String newDef){
        gloss.get(word).update(oldDef, newDef);
    }


    //
    //-------------------------------------------------------------------------------------------------------------------------
    //


    public boolean contains(String word) {
        return gloss.containsKey(word);
    }


    //For Option 1
    public String getMetadata() {
        int count = 0;
        for (int i = 0; i < posCount.length; i++) {
            if (posCount[i] > 0){
                count++;
            }
        }

        return  "\tword: " + wordCount + "\n" +
                "\tdefinitions: " + defCount + "\n" +
                "\tdefinitions per word: " +  String.format("%.3f", (double)defCount / wordCount) + "\n" +
                "\tparts of speech: " + count + "\n" +
                "\tfirst word: " + gloss.firstKey() + "\n" +
                "\tlast word: " + gloss.lastKey() + "\n";
    }


    //
    //-------------------------------------------------------------------------------------------------------------------------
    //


    public String getAllDefs(String word) {
        StringBuilder returnDefs = new StringBuilder(word + "\n");
        Collection<Definitions.SingleDefinition> defs = gloss.get(word).getAllDefinitions();
        for (Definitions.SingleDefinition def : defs) {
            returnDefs.append("\t").append(def.POS()).append(".\t").append(def.def()).append("\n");
        }
        return returnDefs.toString();
    }

    public String getFirstDefs(){
        return getAllDefs(gloss.firstKey());
    }

    public String getLastDefs(){
        return getAllDefs(gloss.lastKey());
    }

    public ArrayList<Definitions.SingleDefinition> getDefList(String word){
        return gloss.get(word).getAllDefinitions();
    }


    //
    //-------------------------------------------------------------------------------------------------------------------------
    //


    public String getInRange(String src, String dst) {
        NavigableSet<String> list = gloss.subMap(src, true, dst, true).navigableKeySet();
        StringBuilder returnRange = new StringBuilder();

        for (String word: list) {
            returnRange.append("\t").append(word).append("\n");
        }
        return returnRange.toString();
    }

    public String getPOS(String word) {
        return word + "\n" + gloss.get(word).getAllPOS();
    }

    public ArrayList<String> getAllWordsList(){
        return new ArrayList<>(gloss.keySet());
    }

}
