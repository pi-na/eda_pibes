import java.util.Arrays;

public class PCaso1<T extends Comparable>{
    public static void main(String[] args) {
        PCaso1<Integer> auxi = new PCaso1<Integer>();
        auxi.initialize(5);
        auxi.setElement(2, 10);
        auxi.setElement(4, 90);
        for(int rec= 0; rec < 5; rec++)
            System.out.println ( auxi.getElement(rec) );
        System.out.println();

        PCaso1<String> auxi2= new PCaso1<String>();
        auxi2.initialize(3);
        auxi2.setElement(15, "chau");
        auxi2.setElement(1, "hola");
        for(int rec= 0; rec < 3; rec++)
            System.out.println ( auxi2.getElement(rec) );

    }

    private Object[] array;
    private int dim;
    private static int CHUNK_SIZE = 10;

    public void initialize(int dim) {
        array = new Object[dim];
        this.dim = dim;
    }
    public void setElement(int pos, T element) {
        if(pos < 0) return;
        if(pos >= dim) {
            array = Arrays.copyOf(array, dim + CHUNK_SIZE + pos);
            dim = dim + CHUNK_SIZE + pos;
        }
        array[pos] = element;
    }

    @SuppressWarnings("unchecked")
    public T getElement(int pos) {
        return (T)array[pos];
    }

}
