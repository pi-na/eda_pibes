public class MergeTest {
    public static void main(String[] args) {

        int arr [] = {5,26,12,6,1,4,7};
        int n = arr.length;

        System.out.println("Array original:");
        for (int value : arr) {
            System.out.print(value + " ");
        }

        System.out.println();

        System.out.println("Array ordenado:");
        Sort.mergeSort(arr);
        Sort.printArray(arr);
    }

}
