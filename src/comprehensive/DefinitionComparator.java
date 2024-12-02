package comprehensive;

import java.util.Comparator;

public class DefinitionComparator implements Comparator<Definitions.Definition> {
    @Override
    public int compare(Definitions.Definition o1, Definitions.Definition o2) {
        if (o1.POS().compareTo(o2.POS()) == 0) {
            return o1.def().compareTo(o2.def());
        } else {
            return o1.POS().compareTo(o2.POS());
        }
    }
}
