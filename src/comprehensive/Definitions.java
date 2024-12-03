package comprehensive;

import java.util.*;

public class Definitions {
    private final TreeMap<SingleDefinition, SingleDefinition> definitions = new TreeMap<>(new DefinitionComparator());
    private final ArrayList<String> validPOS = new ArrayList<>(Arrays.asList( "adj", "adv", "conj", "interj", "noun", "prep", "pron", "verb"));
    private final Integer[] posCount = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0};

    public Definitions(String POS, String def) {
        add(POS, def);
    }


    //
    //-------------------------------------------------------------------------------------------------------------------------
    //


    public void add(String POS, String def) {
        SingleDefinition sd = new SingleDefinition(POS, def);
        this.definitions.put(sd, sd);
        posCount[validPOS.indexOf(POS)] += 1;
    }

    public void remove(SingleDefinition d) {
        posCount[validPOS.indexOf(d.POS())] -= 1;
        definitions.remove(d);
    }

    //TODO |Check| - Will updating a def only update a def and not a POS?
    public void update(SingleDefinition oldDef, String newDef) {
        String oldPOS = definitions.get(oldDef).POS();
        remove(oldDef);
        add(oldPOS, newDef);
    }


    //
    //-------------------------------------------------------------------------------------------------------------------------
    //


    public String getAllPOS() {
        StringBuilder allPOS = new StringBuilder();
        for (int i = 0; i < posCount.length; i++) {
            if (posCount[i] > 0){
                allPOS.append("\t").append(validPOS.get(i)).append(".\n");
            }
        }
        return allPOS.toString();
    }

    public ArrayList<SingleDefinition> getAllDefinitions() {
        return new ArrayList<>(definitions.values());
    }

    public int numberOfDefinitions() {
        return definitions.size();
    }



    //
    //-------------------------------------------------------------------------------------------------------------------------
    //


    public record SingleDefinition(String POS, String def){}

}
