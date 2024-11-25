package notes1;

public class CustomStringGoodHash extends CustomString {
    public CustomStringGoodHash(String string) {
        super(string);
    }

    public int hashCode() {
        int hashValue = 0;
        for (int i = 0; i < data.length(); i++) {
            hashValue = 128 * hashValue + data.charAt(i);
        }
        return hashValue;
    }
}
