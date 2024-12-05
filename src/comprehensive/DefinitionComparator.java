package comprehensive;

import java.util.Comparator;

/**
 * A comparator for comparing Definitions.SingleDefinition objects.
 * Compares first by the part of speech (POS), and if they are equal,
 * compares by the textual definition.
 *
 * @author - Brandon Flickinger and Ethan Laynor
 * @version - December 5, 2024
 */
public class DefinitionComparator implements Comparator<Definitions.SingleDefinition> {

    /**
     * Compares two Definitions.SingleDefinition objects.
     *
     * @param o1 the first definition to compare.
     * @param o2 the second definition to compare.
     * @return a negative integer, zero, or a positive integer as the first definition
     *         is less than, equal to, or greater than the second.
     *         The comparison is first by POS and then by the textual definition if POS is equal.
     */
    @Override
    public int compare(Definitions.SingleDefinition o1, Definitions.SingleDefinition o2) {
        if (o1.POS().compareTo(o2.POS()) == 0) {
            return o1.def().compareTo(o2.def());
        } else {
            return o1.POS().compareTo(o2.POS());
        }
    }
}
