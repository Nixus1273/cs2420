package comprehensive;

import java.util.Comparator;

public class DefinitionComparator implements Comparator<Word.Definition> {
    @Override
    public int compare(Word.Definition o1, Word.Definition o2) {
        if (o1.getPOS().compareTo(o2.getPOS()) == 0) {
            return o1.getDef().compareTo(o2.getDef());
        } else {
            return o1.getPOS().compareTo(o2.getPOS());
        }
    }
}



