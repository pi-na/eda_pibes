package SortedLinkedListWithHeaderAllowsRemove;

import SortedLinkedListWithHeaderAllowsRemove.SortedLinkedList;

import java.util.Iterator;

public class Tester {
    public static void main(String[] args) {
        System.out.println("Initial testing: add, dump, getMin and getMax.");
        SortedListService<Integer> d = new SortedLinkedList<>();
        d.add(16);;
        d.add(14);
        d.add(12);
        System.out.println("List size: "+d.size());
        d.dump();
        System.out.println(String.format("Min Element: %d\nMax Element: %d",d.getMin(), d.getMax()));

        System.out.println("-----------------------------");

        System.out.println("Further testing: delete and iterator.");
        d.delete(16);
        d.delete(10);
        System.out.println("List size: "+d.size());
        for (Integer elem : d) {
            System.out.println(elem);
        }
        System.out.println(String.format("Min Element: %d\nMax Element: %d",d.getMin(), d.getMax()));

        System.out.println("-----------------------------");

        System.out.println("Refined testing: remove from iterator.");
        SortedListService<Integer> a= new SortedLinkedList<>();
        System.out.println("tamaño = " + a.size()); System.out.println("min = " +
                a.getMin()); System.out.println("max = " + a.getMax()); a.delete(100);


        a.add(50); a.add(30); a.add(40); a.add(10);
        a.add(20); a.add(60); a.add(70); a.add(80);
        a.dump();

        System.out.println("tamaño = " + a.size());
        System.out.println("min = " + a.getMin());
        System.out.println("max = " + a.getMax());

        System.out.println("con iterador ...");

        for (Iterator<Integer> iter = a.iterator(); iter.hasNext();) {
            Integer nro =  iter.next();
            if (nro.equals(80) || nro.equals(10) || nro.equals(40)) {
                System.out.println(String.format("deleting %s", nro));
                iter.remove();
            }
            else
                System.out.println(String.format("intacto %s", nro));
        }
        a.dump();
        System.out.println("tamaño = " + a.size());
        System.out.println("min = " + a.getMin());
        System.out.println("max = " + a.getMax());

        /**
         * -----------------------------
         * Expected output:
         * tamaño = 0
         * min = null
         * max = null
         * Element 100 not found
         * 10 -> 20 -> 30 -> 40 -> 50 -> 60 -> 70 -> 80
         * tamaño = 8
         * min = 10
         * max = 80
         * con iterador ...
         * deleting 10
         * intacto 20
         * intacto 30
         * deleting 40
         * intacto 50
         * intacto 60
         * intacto 70
         * deleting 80
         * 20 -> 30 -> 50 -> 60 -> 70
         * tamaño = 5
         * min = 20
         * max = 70
         * -----------------------------
         */
    }


}
