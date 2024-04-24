
public class Couple<T extends Comparable<? super T>> implements Comparable<Couple<T>> {
    T elem1;
    T elem2;

    public Couple(T elem1, T elem2) {
        this.elem1 = elem1;
        this.elem2 = elem2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Couple<?> couple = (Couple<?>) o;
        return elem1.equals(couple.elem1) && elem2.equals(couple.elem2);
    }

    @Override
    public int hashCode() {
        int result = elem1.hashCode();
        result = 31 * result + elem2.hashCode();
        return result;
    }

    @Override
    public int compareTo(Couple<T> o) {
        int cmp = this.elem1.compareTo(o.elem1);
        if (cmp == 0) cmp = this.elem2.compareTo(o.elem2);
        return cmp;
    }
}
