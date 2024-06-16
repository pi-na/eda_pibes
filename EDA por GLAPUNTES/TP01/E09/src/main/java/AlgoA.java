package utils;

public class AlgoA {

    public static int max(int[] array) {
        if (array == null || array.length == 0)
            throw new RuntimeException("Empty array");

        int candidate = array[0];
        for (int rec = 1; rec < array.length - 1; rec++)
            if ( candidate < array[rec] )
                candidate = array[rec];

        return candidate;
    }

}