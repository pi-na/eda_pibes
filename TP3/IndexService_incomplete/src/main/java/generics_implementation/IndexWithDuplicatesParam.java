package generics_implementation;/*              !!USAR UN ARREGLO ORDENADO!!
>declarar una constante con el tamaño de chunksize por el que irá creciendo el arreglo.
    para crecer el arreglo usar Arrays.copyOf
>implementar un método privado getClosestPosition, retorna:
    si no existe la key: la posición donde insertarla
    si existe la key: la posición de alguna de las repeticiones
*/

import java.lang.reflect.Array;
import java.util.Arrays;

public class IndexWithDuplicatesParam<T extends Comparable<? super T>> implements IndexServiceParam<T> {
    private T[] elements;
    private int dim = 0;
    private static final int CHUNK_SIZE = 10;
    Class<T> elements_class;

    @SuppressWarnings("unchecked")
    IndexWithDuplicatesParam(Class<T> elements_class){
        this.elements_class = elements_class;
        elements = (T[]) Array.newInstance(elements_class, CHUNK_SIZE);
        dim = 0;
    }

    public T[] getElementsArray(){
        return this.elements;
    }

    @SuppressWarnings("unchecked")
    private void resize(){
       T[] new_elements = (T[]) Array.newInstance(elements_class, dim + CHUNK_SIZE);
       for(int i = 0; i < dim; i++){
           new_elements[i] = elements[i];
       }
       this.elements = new_elements;
    }

    @Override
    public void initialize(T[] elements) {
        if(elements == null){
            throw new IllegalArgumentException("elements array must not be null. Elements have not been updated.");
        }
        for(Comparable elem:elements){
            if(elem == null)
                throw new IllegalArgumentException("No elements in the provided array can be null. Elements have not been updated.");
        }
        Arrays.sort(elements);
        for(T elem:elements){
            insert(elem);
        }
    }

    //si no existe la key: la posición donde insertarla; si existe la key: la posición de alguna de las repeticiones
    // (!!!!!o DIM si es mas grande que todas!!!!!)
    //  TODO !!!!!!!!!! PARA USAR ESTA FUNCION TENES QUE CHEQUEAR QUE INDEX != DIM && INDEX != -1 SIEMPRE !!!!!!
    public int getClosestPosition(T key) {
        return getClosestPosition(this.elements, key, 0, dim == 0? dim:(dim - 1));
    }

    private int getClosestPosition(T[] array, T key, int leftIndex, int rightIndex){
        if(leftIndex > rightIndex) {
            return rightIndex + 1;  // Siempre retorna una posición válida para insertar.
        }
        int mid = (leftIndex + rightIndex) / 2;
        int cmp = array[mid].compareTo(key);
        if(cmp == 0) {
            return mid;  // Retorna la posición encontrada.
        } else if(cmp > 0) {
            return getClosestPosition(array, key, leftIndex, mid - 1);
        } else {
            return getClosestPosition(array, key, mid + 1, rightIndex);
        }
    }

    /*
    private int getClosestPosition(T[] array, T key, int leftIndex, int rightIndex){
        if(leftIndex >= rightIndex) {
            if((array[leftIndex]).compareTo(key)>0)
                return leftIndex-1;
            if((array[leftIndex]).compareTo(key)<0)
                return leftIndex+1;
            return leftIndex;
        }

        int mid = (leftIndex + rightIndex) / 2;
                if(array[mid].compareTo(key) > 0) {
            return getClosestPosition(array, key, leftIndex, mid - 1);
        }
        if(array[mid].compareTo(key) < 0) {
            return getClosestPosition(array, key, mid + 1, rightIndex);
        }
        return mid;
    }

     */

    @Override
    public void insert(T key) {
        if(dim == elements.length) {
            resize();
        }

        int insertIndex = getClosestPosition(key);

        //System.out.println("insertIndex: " + insertIndex + " dim: " + dim);
        if(insertIndex < dim){
            for(int i = dim; i > insertIndex; i--){
                //System.out.println(String.format("insert rn: elements[%d] = %d = elements [%d] = %d", i, elements[i], i-1, elements[i-1]));
                elements[i] = elements[i-1];
            }
        }

        elements[insertIndex] = key;
        dim++;
        return;
    }

    @Override
    public boolean search(T key) {
        int closestIndex = getClosestPosition(key);
        return closestIndex != -1 && closestIndex < dim && elements[closestIndex].equals(key);
    }

    @Override
    public void delete(T key) {
        int index = getClosestPosition(key);
        if(index == -1 || index >= dim) return;

        if(elements[index] == key){
            for(int i = index; i < dim - 1; i++){
                elements[i] = elements[i+1];
            }
            dim--;
        }
    }

    @Override
    public int occurrences(T key) {
        int index = getClosestPosition(key);
        if(index == -1 || dim == index || elements[index] !=  key) return 0;

        return lastOccurrence(key, index) - firstOccurrence(key, index) + 1;
    }

    //  Retorna la primer aparicion de la key, a partir de una posicion que contenga la key
    private int firstOccurrence(T key, int closestPosition){
        int index = closestPosition;
        while(index >= 0 && elements[index] == key) index--;
        return ++index;
    }

    //  Retorna la ultima aparicion de la key, a partir de una posicion que contenga la key
    private int lastOccurrence(T key, int closestPosition){
        int index = closestPosition;
        while(index < dim && elements[index] == key) index++;
        return --index;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] range(T leftKey, T rightKey, boolean leftIncluded, boolean rightIncluded) {
        int leftIndex = getClosestPosition(leftKey);
        if(leftIndex == -1 || dim == leftIndex) return (T[])Array.newInstance(elements_class, 0);
        leftIncluded = leftKey.compareTo(elements[leftIndex]) < 0 || leftIncluded;
        leftKey = elements[leftIndex];

        int rightIndex = getClosestPosition(rightKey);
        if(rightIndex == -1 || dim == rightIndex) return (T[])Array.newInstance(elements_class, 0);
        rightIncluded = rightKey.compareTo(elements[rightIndex]) > 0 || rightIncluded;
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
    public T getMax() {
        return dim > 0? elements[dim - 1]:null;
    }

    @Override
    public T getMin() {
        return dim > 0? elements[0]:null;
    }
}
