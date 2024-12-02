package comprehensive;

import java.util.*;

public class Word {
    private final TreeMap<Definition, Definition> definitions = new TreeMap<>(new DefinitionComparator());
    ArrayList<String> validPOS = new ArrayList<>(Arrays.asList(
            "adj", "adv", "conj", "interj", "noun", "prep", "pron", "verb"));
    Integer[] posCount = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0};

    public Word (String POS, String def) {
        addDefinition(POS, def);
    }

    public ArrayList<Definition> getDefinitions() {
        return new ArrayList<>(definitions.values());
    }

    public void addDefinition(String POS, String def) {
        Definition d = new Definition(POS, def);
        this.definitions.put(d, d);
        posCount[validPOS.indexOf(POS)] += 1;
    }

    public void updateDefinition(Definition oldDef, String newDef) {
        String oldPOS = definitions.get(oldDef).POS();
        removeDefinition(oldDef);
        Definition d = new Definition(oldPOS, newDef);
        this.definitions.put(d, d);
    }

    public void removeDefinition(Definition d) {
        posCount[validPOS.indexOf(d.POS())] -= 1;
        definitions.remove(d);
    }

    public String getAllPOS() {
        StringBuilder allPOS = new StringBuilder();
        for (int i = 0; i < posCount.length; i++) {
            if (posCount[i] > 0){
                allPOS.append("\t").append(validPOS.get(i)).append(".\n");
            }
        }
        return allPOS.toString();
    }

    public int numberOfDefinitions() {
        return definitions.size();
    }

    public record Definition(String POS, String def) {}
}

//1

