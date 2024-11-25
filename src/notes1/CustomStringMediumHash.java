package notes1;

public class CustomStringMediumHash extends CustomString {
    public CustomStringMediumHash(String string) {
        super(string);
    }

    public int hashCode() {
        return data.charAt(0) + data.charAt(data.length() - 1);
    }
}
