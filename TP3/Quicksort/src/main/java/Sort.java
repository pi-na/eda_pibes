public class Sort {
    public static void quickSortHelper(int[] unsorted, int leftPos, int rightPos){
        if(rightPos <= leftPos)
            return;

        //  Tomo como pivot el valor del medio (es mas eficiente que tomar el primer valor)
        int pivotIndex = (leftPos + rightPos) / 2;
        int pivotValue = unsorted[pivotIndex];

        //  Muevo el pivot al final
        swap(unsorted, pivotIndex, rightPos);

        //  Particiono el arreglo (sin el pivot)
        int pivotFinalPos = partition(unsorted, leftPos, rightPos - 1, pivotValue);

        //  Muevo el pivot a su lugar final
        swap(unsorted, pivotFinalPos, rightPos);

        //  Llamo recursivamente a quickSort para las dos mitades
        quickSortHelper(unsorted, leftPos, pivotFinalPos - 1);
        quickSortHelper(unsorted, pivotFinalPos + 1, rightPos);
    }

    private static void swap(int[] unsorted, int i, int j){
        int temp = unsorted[i];
        unsorted[i] = unsorted[j];
        unsorted[j] = temp;
    }

    private static int partition(int[] unsorted, int leftPos, int rightPos, int pivotValue) {
        while (leftPos <= rightPos) {
            // Avanzo leftPos hasta encontrar un valor mayor al pivot
            // Avanzo rightPos hasta encontrar un valor menor al pivot
            // Si leftPos < rightPos, intercambio los valores
            // Si leftPos >= rightPos, termino

            // para que funcione con valores repetidos, es necesario cambiar
            // unsorted[leftPos] < pivotValue por unsorted[leftPos] <= pivotValue (y lo mismo para rightPos)
            while (leftPos <= rightPos && unsorted[leftPos] < pivotValue)
                leftPos++;
            while (leftPos <= rightPos && unsorted[rightPos] > pivotValue)
                rightPos--;
            if (leftPos < rightPos)
                swap(unsorted, leftPos, rightPos);
        }
        return leftPos;
    }
}
