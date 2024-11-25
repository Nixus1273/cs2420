package notes1;

public class CustomStringBadHash extends CustomString {
    public CustomStringBadHash(String string) {
        super(string);
    }

    public int hashCode() {
        return data.length();
    }
}
