package comprehensive;

import java.util.*;

public class Word {

    private final String word;
    private TreeMap<Definition, Definition> definitions = new TreeMap<>(new DefinitionComparator());
    ArrayList<String> validPOS = new ArrayList<>(Arrays.asList(
            "adj", "adv", "conj", "interj", "noun", "prep", "pron", "verb"));
    Integer[] POSCount = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0};

    public Word (String word, String POS, String def) {
        this.word = word;
        addDefinition(POS, def);
    }


    public ArrayList<Definition> getDefinitions() {
        return new ArrayList<>(definitions.values());
    }

    public void removeDefinition(Definition d) {
        //Get the pos of the def and -1 from the hash
        POSCount[validPOS.indexOf(d.getPOS())] -= 1;
        definitions.remove(d);
    }

    public void updateDefinition(Definition oldDef, String newDef) {
        String oldPOS = definitions.get(oldDef).getPOS();
        removeDefinition(oldDef);
        Definition d = new Definition(oldPOS, newDef);
        this.definitions.put(d, d);
    }

    public void addDefinition(String POS, String def) {
        Definition d = new Definition(POS, def);
        this.definitions.put(d, d);
        POSCount[validPOS.indexOf(POS)] += 1;
    }

    public String getWord() {
        return this.word;
    }

    public String getAllPos() {
        String allPOS = "";
        for (int i = 0; i < POSCount.length; i++) {
            if (POSCount[i] > 0){
                allPOS +=  "\t" + validPOS.get(i) + ".\n";
            }
        }
        return allPOS;
    }

    public int numberOfDefinitions() {
        return definitions.size();
    }

    public static class Definition {

        private String POS;
        private String def;

        public Definition(String POS, String def) {
            this.POS = POS;
            this.def = def;
        }

        public String getPOS() {
            return this.POS;
        }

        public String getDef() {
            return this.def;
        }
    }
}
