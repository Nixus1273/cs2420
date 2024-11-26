package comprehensive;

import java.util.*;

public class Word {

    private String word;
    private TreeMap<Definition, Definition> definitions = new TreeMap<>(new DefinitionComparator());

    public Word (String word, String POS, String def) {
        this.word = word;
        Definition d = new Definition(POS, def);
        this.definitions.put(d, d);
    }

    public Collection<Definition> getDefinitions() {
        return definitions.values();
    }

    public void removeDefinition(Definition d) {
        definitions.remove(d);
    }

    public void updateDefinition(Definition oldDef, String newDef) {
        String oldPOS = definitions.get(oldDef).getPOS();
        definitions.remove(oldDef);
        Definition d = new Definition(oldPOS, newDef);
        this.definitions.put(d, d);
    }

    public void addDefinition(String POS, String def) {
        Definition d = new Definition(POS, def);
        this.definitions.put(d, d);
    }

    public String getWord() {
        return this.word;
    }


    public class Definition {

        private final ArrayList<String> validPOS = new ArrayList<>(
                Arrays.asList("noun", "verb", "adj", "adv", "pron", "prep", "conj", "interj"));
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

        public void setPOS(String POS) throws IllegalArgumentException {
            if(validPOS.contains(POS))
                this.POS = POS;

            throw new IllegalArgumentException();

            //Resort the PQ
        }


        public void setDef(){


            //Resort the PQ
        }
    }
}
