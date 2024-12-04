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

    public void changeDef(String word, Definitions.SingleDefinition oldDef, String newDef){
        gloss.get(word).update(oldDef, newDef);
    }


    //
    //-------------------------------------------------------------------------------------------------------------------------
    //


    public boolean contains(String word) {
        return gloss.containsKey(word);
    }


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
        if(gloss.isEmpty())
            return " - List is empty.\n";
        return getAllDefs(gloss.firstKey());
    }

    public String getLastDefs(){
        if(gloss.isEmpty())
            return " - List is empty.\n";

        return getAllDefs(gloss.lastKey());
    }

    public ArrayList<Definitions.SingleDefinition> getDefList(String word){
        return gloss.get(word).getAllDefinitions();
    }


    //
    //-------------------------------------------------------------------------------------------------------------------------
    //


    public String getInRange(String src, String dst) {
        if(!gloss.containsKey(src) || !gloss.containsKey(dst))
            return "";


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
