import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Splitters {
    public static final String DELIMITER = "|";
    private List<String> splitters;


    public Splitters() {
        splitters = Arrays.asList(",", ":");
    }

    public Splitters(List<String> splitters) {
        this.splitters = splitters;
    }

    public String getRegex() {
        return String.join(DELIMITER, splitters);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Splitters splitters1 = (Splitters) o;
        return Objects.equals(splitters, splitters1.splitters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(splitters);
    }
}
