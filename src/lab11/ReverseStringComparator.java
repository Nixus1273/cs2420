package lab11;

import java.util.Comparator;

public class ReverseStringComparator implements Comparator<String> {
    @Override
    public int compare(String arg0, String arg1) {
        return arg1.compareTo(arg0);
    }
}
