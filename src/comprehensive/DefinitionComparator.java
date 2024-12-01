package comprehensive;

import java.util.Comparator;

public class DefinitionComparator implements Comparator<Word.Definition> {
    @Override
    public int compare(Word.Definition o1, Word.Definition o2) {
        if (o1.POS().compareTo(o2.POS()) == 0) {
            return o1.def().compareTo(o2.def());
        } else {
            return o1.POS().compareTo(o2.POS());
        }
    }
}



