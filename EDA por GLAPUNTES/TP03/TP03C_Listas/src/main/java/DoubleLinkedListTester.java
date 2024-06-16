import java.util.Iterator;

public class DoubleLinkedListTester {
    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.add(5);
        list.add(1);
        list.add(3);
        list.add(8);
        list.add(7);
        list.add(7);

        System.out.println("Tamaño: " + list.size());
        System.out.println("Maximo: " + list.getMax());
        System.out.println("Minimo: " + list.getMin());
        list.dump();
        list.reverseDump();
        list.delete(8);
        list.delete(1);
        list.delete(5);
        System.out.println("Maximo: " + list.getMax());
        list.dump();
        list.reverseDump();
        System.out.println("-------------");
        list.add(5);
        list.add(1);
        list.add(3);
        list.add(8);
        list.add(7);
        list.add(7);
        for (Iterator<Integer> iter = list.iterator(); iter.hasNext();) {
            Integer nro =  iter.next();
            if (nro.equals(8) || nro.equals(1) || nro.equals(5)) {
                System.out.println(String.format("deleting %s", nro));
                iter.remove();
            }
            else
                System.out.println(String.format("intacto %s", nro));
        }

        System.out.println("-------------");
        list.dump();
        list.reverseDump();

    }

}
