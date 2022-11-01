import java.util.HashSet;
import java.util.Objects;

class Pair<T, S> {
    T first;
    S second;

    // constructor for assigning values
    Pair(T first, S second) {
        this.first = first;
        this.second = second;
    }

    Pair values() {
        return new Pair(first, second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return (Objects.equals(first, pair.first) && Objects.equals(second, pair.second));
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }
}