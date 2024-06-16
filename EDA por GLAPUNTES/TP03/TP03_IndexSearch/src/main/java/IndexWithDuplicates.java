import java.util.Arrays;
import java.util.NoSuchElementException;

public class IndexWithDuplicates implements IndexService{
    private int[] array;
    private int dim;
    static private final int CHUNK = 5;

    public IndexWithDuplicates() {
        this.array = new int[CHUNK];
        this.dim=0;
    }

    @Override
    public void initialize(int[] elements) {
        if (elements==null){
            throw new RuntimeException();
        }
        Arrays.sort(elements);
        array = elements;
        dim = elements.length;
    }

    @Override
    public boolean search(int key) {
        if (dim == 0) return false;
        return array[getClosestPosition(key)]==key;
    }

    private int getClosestPosition(int key) {
        int left= 0;
        int right= dim-1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if ( array[ mid ] == key )
                return mid;
            if ( key < array[mid]  )
                right = mid  - 1;
            else
                left = mid + 1;

        }
        return left;
    }

    private void resize(int delta) {
        array = Arrays.copyOf(array, array.length + delta);
    }

    @Override
    public void insert(int key) {
        int idx = getClosestPosition(key);
        // We resize the vector to add a new key.
        if (dim==array.length)
            resize(CHUNK);
        if (dim - idx >= 0) System.arraycopy(array, idx, array, idx + 1, dim - idx);
        array[idx]=key;
        dim++;
    }

    @Override
    public void delete(int key) {
        if (dim == 0)
            return;
        // podría decrecer, pero no lo hacemos ahora

        // buscar el lugar más cercano donde debe ir en O(log2 n)
        int pos= getClosestPosition(key);

        if (array[pos] != key) // no lo encontre
            return;

        dim--;

        // eliminar el lugar y mantener contiguedad
        if (dim - pos >= 0) System.arraycopy(array, pos + 1, array, pos, dim - pos);
    }

    @Override
    public int occurrences(int key) {
        int rta= 0;

        if (dim == 0)
            return 0;

        int pos= getClosestPosition(key);

        // a derecha
        for(int rec= pos; rec < dim && array[rec]== key ; rec++)
            rta++;

        // a izquierda
        for(int rec= pos-1; rec >= 0 && array[rec]== key ; rec--)
            rta++;

        return rta;
    }

    public void sortedPrint() {
        for(int rec= 0; rec < dim; rec++)
            System.out.println(array[rec]);
    }

    public int getMax() {
        if (dim == 0)
            throw new RuntimeException("no hay elementos");

        return array[dim-1];
    }

    public int getMin() {
        if (dim == 0)
            throw new RuntimeException("no hay elementos");

        return array[0];
    }

    public int[] range(int leftKey, int rightKey, boolean leftIncluded, boolean rightIncluded) {
        int leftPos = getClosestPosition(leftKey);
        int rightPos = getClosestPosition(rightKey);

        if(rightPos >= dim){
            rightPos = dim - 1;
            rightIncluded = true;
        }

        if(leftPos > rightPos)
            return new int[0];

        if(!leftIncluded){
            while(leftPos < dim && array[leftPos] == 0)
                leftPos++;
        }else {
            while (leftPos > 0 && array[leftPos - 1] == 0) {
                leftPos--;
            }
        }

        if(!rightIncluded){
            while(rightPos >= 0 && array[rightPos] == 0)
                rightPos--;
        } else{
            while(rightPos < dim - 1 && array[rightPos+1] == 0)
                rightPos++;
        }
        return Arrays.copyOfRange(array,leftPos,rightPos+1);
    }

}
