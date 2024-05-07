package int_implementation;
/*              !!USAR UN ARREGLO ORDENADO!!
>declarar una constante con el tamaño de chunksize por el que irá creciendo el arreglo.
    para crecer el arreglo usar Arrays.copyOf
>implementar un método privado getClosestPosition, retorna:
    si no existe la key: la posición donde insertarla
    si existe la key: la posición de alguna de las repeticiones
*/

import java.util.Arrays;

public class IndexWithDuplicates implements IndexService{
    private int[] elements;
    private int dim = 0;

    private static int CHUNK_SIZE = 10;

    IndexWithDuplicates(){
        elements = new int[CHUNK_SIZE];
        dim = 0;
    }

    public int[] getElementsArray(){
        return this.elements;
    }

    @Override
    public void initialize(int[] elements) {
        if(elements == null){
            throw new IllegalArgumentException("elements array must not be null. Elements have not been updated.");
        }
        Arrays.sort(elements);
        this.elements = elements;
        this.dim = elements.length;
    }

    @Override
    public boolean search(int key) {
        int closestIndex = getClosestPosition(key);
        return closestIndex != -1 && closestIndex < dim && elements[closestIndex] == key;
    }

    @Override
    public void insert(int key) {
        if(dim == elements.length) {
            this.elements = Arrays.copyOf(this.elements, elements.length + CHUNK_SIZE);
        }

        int insertIndex = getClosestPosition(key);

        System.out.println("insertIndex: " + insertIndex + " dim: " + dim);
        if(insertIndex < dim){
            for(int i = dim; i > insertIndex; i--){
                System.out.println(String.format("insert rn: elements[%d] = %d = elements [%d] = %d", i, elements[i], i-1, elements[i-1]));
                elements[i] = elements[i-1];
            }
        }

        elements[insertIndex] = key;
        dim++;
        return;
    }

    //si no existe la key: la posición donde insertarla
    // (!!!!!o DIM si es mas grande que todas!!!!!)
    //  TODO !!!!!!!!!! PARA USAR ESTA FUNCION TENES QUE CHEQUEAR QUE INDEX != DIM && INDEX != -1 SIEMPRE !!!!!!
    //si existe la key: la posición de alguna de las repeticiones
    public int getClosestPosition(int key) {
        return getClosestPosition(this.elements, key, 0, dim == 0? dim:(dim - 1));
    }

    public int getClosestPosition(int[] array, int key, int left, int right){
        if(left >= right) {
            if(array[left] > key)
                return left - 1;
            if(array[left] < key)
                return left + 1;
            return left;
        }

        int mid = (left + right) / 2;

        if(array[mid] > key) {
            return getClosestPosition(array, key, left, mid - 1);
        }
        if(array[mid] < key) {
            return getClosestPosition(array, key, mid + 1, right);
        }

        return mid;
    }

    //  Al final la profe dijo q no importa hacerlo decrecer
    @Override
    public void delete(int key) {
        int index = getClosestPosition(key);
        if(index == -1 || index >= dim) return;

        if(elements[index] == key){
            for(int i = index; i < dim - 1; i++){
                elements[i] = elements[i+1];
            }
            dim--;
        }
        if(dim + CHUNK_SIZE == elements.length) {
            this.elements = Arrays.copyOf(this.elements, elements.length - CHUNK_SIZE);
        }
    }

    @Override
    public int occurrences(int key) {
        int index = getClosestPosition(key);
        if(index == -1 || dim == index || elements[index] !=  key) return 0;

        while(elements[index] == key) index--;
        int startingIndex = ++index;
        while(elements[index] == key) index++;
        return index - startingIndex;
    }

    //  Retorna la primer aparicion de la key, a partir de una posicion que contenga la key
    private int firstOccurrence(int key, int closestPosition){
        int index = closestPosition;
        while(index >= 0 && elements[index] == key) index--;
        return ++index;
    }

    //  Retorna la ultima aparicion de la key, a partir de una posicion que contenga la key
    private int lastOccurrence(int key, int closestPosition){
        int index = closestPosition;
        while(index < dim && elements[index] == key) index++;
        return --index;
    }

    @Override
    public int[] range(int leftKey, int rightKey, boolean leftIncluded, boolean rightIncluded) {
        int leftIndex = getClosestPosition(leftKey);
        if(leftIndex == -1 || dim == leftIndex) return new int[]{};
        leftIncluded = leftKey<elements[leftIndex]? true:leftIncluded;
        leftKey = elements[leftIndex];

        int rightIndex = getClosestPosition(rightKey);
        if(rightIndex == -1 || dim == rightIndex) return new int[]{};
        rightIncluded = rightKey>elements[rightIndex]? true:rightIncluded;
        rightKey = elements[rightIndex];

        leftIndex = leftIncluded? firstOccurrence(leftKey, leftIndex):(lastOccurrence(leftKey, leftIndex) + 1);
        rightIndex = rightIncluded? (lastOccurrence(rightKey, rightIndex) + 1):firstOccurrence(rightKey, rightIndex);


        //  Incluye el borde izquierdo. Excluye el borde derecho.
        return Arrays.copyOfRange(elements, leftIndex, rightIndex);
    }

    @Override
    public void sortedPrint() {
        System.out.println(Arrays.toString(elements));
    }

    @Override
    public int getMax() {
        return dim > 0? elements[dim - 1]:-1;
    }

    @Override
    public int getMin() {
        return dim > 0? elements[0]:-1;
    }
}
