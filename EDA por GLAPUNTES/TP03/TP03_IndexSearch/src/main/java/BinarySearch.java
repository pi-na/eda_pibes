public class BinarySearch {
    public static int recursiveBinarySearch (int[] array, int left, int right, int x) {
        if (right>=1) {
            int mid = left + (right-left) / 2;
            // Fist we check if the middle value is x.
            if (array[mid] == x) {
                return mid;
            }
            // mid is not x. We need to know if x is on the left or right side of the array.
            if (array[mid]>x) {
                return recursiveBinarySearch(array,left,mid-1, x);
            }
            // Now we reach the conclusion that x is on the right side of the array.
            return recursiveBinarySearch(array,mid+1, right,x);
        }
        // x is not on the array.
        return -1;
    }
    public static int cyclicBinarySearch (int[] array, int x) {
        int left = 0, right = array.length - 1, mid;
        while (array[mid = left + (right-left) / 2]!=x) {
            // If we are looking for a value between 0 and 0 x is not in the array.
            if (right<1) {
                return -1;
            }
            // Now we need to know if we'll be working with a new left or right side,
            if (array[mid] > x) {
                right=mid-1;
            }
            // because our array is already ordered we update our borders.
            else {
                left=mid+1;
            }
        }
        return mid;
    }
    // Preliminary testing.
    public static void main(String[] args) {
        int[] array = new int[] {10, 30, 50, 80, 100, 130};
        int value = 130;
        System.out.println("The index of 130 in {10, 30, 50, 80, 100, 130} is: "+recursiveBinarySearch(array,0,array.length-1,value));
        System.out.println("The index of 0 in {10, 30, 50, 80, 100, 130} is: "+cyclicBinarySearch(array,0));
    }
}
