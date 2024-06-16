public class HashBagMain {
    public static void main(String[] args) {
        HashBagFromScratch<Integer> h = new HashBagFromScratch<>(i->i);
        h.add(45);
        h.add(46);
        h.add(45);
        h.add(45);
        h.remove(46);
        System.out.println(h.getCount(54));
        System.out.println(h.getCount(45));
        System.out.println(h.getCount(46));
    }
}
