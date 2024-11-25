package notes1;

public class CustomString {
    protected final String data;

    public CustomString(String string) {
        data = string;
    }

    /**
     * @return Java's String.hashCode()
     */
    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public String toString() {
        return data;
    }
}
