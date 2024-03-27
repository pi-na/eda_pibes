package space;

public class StackOverflow {
    public int method1() {
        int[] array = new int[1000000];
        array[999] = method1();
        return array[999];
    }
}
