import java.util.Comparator;
import java.util.List;

public class Sort {
    /**
     * Complejijad espacial: O(log2n)
     * Complejidad temporal: Para el mejor caso: O(n logn). Para el peor caso: O(n^2).
     *
     * @param values, vector a ordenar por insercion.
     */
    public static void insertionSort(int[] values) {
        for (int rec = 1; rec < values.length; rec++) {
            int temp = values[rec];
            int pos = rec;
            while (pos > 0 && values[pos - 1] > temp) {
                values[pos] = values[pos - 1];
                pos--;
            }
            values[pos] = temp;
        }
    }

    /**
     * Complejidad temporal:
     * Peor caso → O(N2).
     * Promedio → O(NlogN).
     *
     * @param values, vector de valores a ordenar.
     * @param from,   desde deonde ordenar.
     * @param to,     hasta donde ordenar.
     * @param <E>,    tipo variable.
     */
    private static <E> void quickSort(int[] values, int from, int to) {
        int size = to - from + 1;
        if (size <= 1) return;
        int k = from;
        int pivot = (int) (Math.random() * size) + from; // se elige un pívot de manera aleatoria
        swap(values, pivot, from);
        for (int i = from + 1; i <= to; i++) {
            if (values[i] < values[from]) { // si values[i] es menor que el pívot hago el swap
                swap(values, ++k, i);
            }
        }
        swap(values, k, from);
        quickSort(values, from, k - 1);
        quickSort(values, k + 1, to);
    }

    private static void swap(@org.jetbrains.annotations.NotNull int[] values, int pivot, int from) {
        int aux = values[pivot];
        values[pivot] = values[from];
        values[from] = aux;
    }

    /**
     * Complejijad espacial:
     * O(n).
     * Complejidad temporal:
     * O(N log N)
     * @param elems, lista (preferentemente encadenada) a ordenar.
     * @param cmp,   comparador por el cual se desea ordenar.
     * @param left,  fin sector izquierdo a unir y ordenar.
     * @param right, fin sector derecho a unir y ordenar.
     * @param aux
     * @param <E>
     */
    private static <E> void mergeSort(List<E> elems, Comparator<? super E> cmp, int left, int right, List<E> aux) {
        int size = right - left + 1;
        if (size <= 1) return;
        int n = left + size / 2;
        mergeSort(elems, cmp, left, n - 1, aux);
        mergeSort(elems, cmp, n, right, aux);
        int i1 = left, i2 = n, i = left;
        while (i1 <= n - 1 || i2 <= right) {
            if (i2 > right || (i1 <= n - 1 && cmp.compare(elems.get(i1), elems.get(i2)) <= 0)) {
                aux.set(i++, elems.get(i1++));
            } else {
                aux.set(i++, elems.get(i2++));
            }
        }
        for (int k = left; k <= right; k++) {
            elems.set(k, aux.get(k));
        }
    }

    /**
     * Complejijad espacial:
     * O(n).
     * Complejidad temporal:
     * O(n log n).
     * @param values, array to merge sort.
     */
    public static void mergeSort(int [] values) {
        mergeSort(values, 0, values.length - 1);
    }

    private static void mergeSort(int arr[], int left, int right){
        if(left < right){
            //Encuentra el punto medio del vector.
            int middle = (left + right) / 2;

            //Divide la primera y segunda mitad (llamada recursiva).
            mergeSort(arr, left, middle);
            mergeSort(arr, middle+1, right);

            //Une las mitades.
            merge(arr, left, middle, right);
        }
    }

    public void sort(int arr[], int left, int right) {
        if (left < right) {
            //Encuentra el punto medio del vector.
            int middle = (left + right) / 2;

            //Divide la primera y segunda mitad (llamada recursiva).
            sort(arr, left, middle);
            sort(arr, middle + 1, right);

            //Une las mitades.
            merge(arr, left, middle, right);
        }
    }

    private static void merge(int arr[], int left, int middle, int right) {
        //Encuentra el tamaño de los sub-vectores para unirlos.
        int n1 = middle - left + 1;
        int n2 = right - middle;

        //Vectores temporales.
        int leftArray[] = new int[n1];
        int rightArray[] = new int[n2];

        //Copia los datos a los arrays temporales.
        for (int i = 0; i < n1; i++) {
            leftArray[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = arr[middle + j + 1];
        }
        /* Une los vectorestemporales. */

        //Índices inicial del primer y segundo sub-vector.
        int i = 0, j = 0;

        //Índice inicial del sub-vector arr[].
        int k = left;

        //Ordenamiento.
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }//Fin del while.

        /* Si quedan elementos por ordenar */
        //Copiar los elementos restantes de leftArray[].
        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }
        //Copiar los elementos restantes de rightArray[].
        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.println(arr[i] + "");
        }
        System.out.println();
    }

} // End class