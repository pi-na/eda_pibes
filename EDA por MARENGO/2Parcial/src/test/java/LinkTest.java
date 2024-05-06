import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LinkTest {
    //Para los test se deberían crear nuevos metodos como un contains, size, etc. Por falta de tiempo no los
    // puedo implementar. Me las arreglo con dump y algunos casos

    @Test
    void test1() {
        SortedLinkedList<Integer> l1 = new SortedLinkedList<>();
        l1.insert(4);
        l1.insert(8);
        l1.insert(4);

        SortedLinkedList<Integer> l2 = new SortedLinkedList<>();
        l2.insert(5);
        l2.insert(11);

        MultiListService<Integer> ml = new MultiSortedLinkedList<>();

        ml.insert( "l1", l1);
        ml.insert( "l2", l2);

        System.out.println("global");
        ml.dump();

        System.out.println("\nsolo l1");
        ml.dump("l1");

        System.out.println("\nsolo l2");
        ml.dump("l2");
    }

    @Test
    void test2() {
        SortedLinkedList<Integer> l1 = new SortedLinkedList<>();
        SortedLinkedList<Integer> l2 = new SortedLinkedList<>();
        MultiListService<Integer> ml = new MultiSortedLinkedList<>();
        ml.insert( "l1", l1);
        ml.insert( "l2", l2);

        ml.dump(); //vacio
    }

    @Test
    void test3() {
        SortedLinkedList<Integer> l1 = new SortedLinkedList<>();
        SortedLinkedList<Integer> l2 = new SortedLinkedList<>();
        MultiListService<Integer> ml = new MultiSortedLinkedList<>();
        ml.insert( "l1", l1);
        ml.insert("l1",l2); //no lanza exception porque no agrego la vacia segun mi codigo

    }

    @Test
    void test4() {
        SortedLinkedList<Integer> l1 = new SortedLinkedList<>();
        l1.insert(4);
        l1.insert(8);
        l1.insert(4);

        SortedLinkedList<Integer> l2 = new SortedLinkedList<>();
        l2.insert(5);
        l2.insert(11);

        MultiListService<Integer> ml = new MultiSortedLinkedList<>();

        ml.insert( "l1", l1);
        Assertions.assertThrows(RuntimeException.class, () -> ml.insert( "l1", l2)); //ahora si

    }
}
