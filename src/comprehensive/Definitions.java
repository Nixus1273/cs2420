package comprehensive;

import java.util.*;

/**
 * A class that manages multiple definitions for a single word.
 * Each definition includes a part of speech (POS) and a string definition.
 *
 * @author - Brandon Flickinger and Ethan Laynor
 * @version - December 5, 2024
 */
public class Definitions {
    private final TreeMap<SingleDefinition, SingleDefinition> definitions = new TreeMap<>(new DefinitionComparator());
    private final ArrayList<String> validPOS = new ArrayList<>(Arrays.asList( "adj", "adv", "conj", "interj", "noun", "prep", "pron", "verb"));
    private final Integer[] posCount = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0};

    /**
     * Constructs a Definitions object with an initial definition.
     *
     * @param POS the part of speech for the initial definition (e.g., noun, verb).
     * @param def the string definition.
     */
    public Definitions(String POS, String def) {
        add(POS, def);
    }

    /**
     * Adds a new definition to the list of definitions.
     *
     * @param POS the part of speech for the definition.
     * @param def the string definition.
     */
    public void add(String POS, String def) {
        SingleDefinition sd = new SingleDefinition(POS, def);
        this.definitions.put(sd, sd);
        posCount[validPOS.indexOf(POS)] += 1;
    }

    /**
     * Removes a specific definition from the list.
     *
     * @param d the definition to remove.
     */
    public void remove(SingleDefinition d) {
        posCount[validPOS.indexOf(d.POS())] -= 1;
        definitions.remove(d);
    }

    /**
     * Updates an existing definition with a new string definition.
     *
     * @param oldDef the definition to update.
     * @param newDef the new string definition.
     */
    public void update(SingleDefinition oldDef, String newDef) {
        String oldPOS = definitions.get(oldDef).POS();
        remove(oldDef);
        add(oldPOS, newDef);
    }

    /**
     * Retrieves all parts of speech present in the definitions.
     *
     * @return a string containing all parts of speech used in the definitions.
     */
    public String getAllPOS() {
        StringBuilder allPOS = new StringBuilder();
        for (int i = 0; i < posCount.length; i++) {
            if (posCount[i] > 0){
                allPOS.append("\t").append(validPOS.get(i)).append(".\n");
            }
        }
        return allPOS.toString();
    }

    /**
     * Retrieves all definitions as a list.
     *
     * @return a list of all definitions.
     */
    public ArrayList<SingleDefinition> getAllDefinitions() {
        return new ArrayList<>(definitions.values());
    }

    /**
     * Retrieves the number of definitions currently stored.
     *
     * @return the number of definitions.
     */
    public int numberOfDefinitions() {
        return definitions.size();
    }

    /**
     * A record that represents a single definition.
     * Each definition includes a part of speech (POS) and a string definition.
     *
     * @param POS the part of speech (e.g., noun, verb).
     * @param def the string definition.
     */
    public record SingleDefinition(String POS, String def){}
}
