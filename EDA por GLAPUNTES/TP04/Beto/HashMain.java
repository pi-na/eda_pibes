

public class HashMain {
    public static void main(String[] args) {
        HashWithList<Integer, String> hash = new HashWithList<>(i -> i);
        hash.insert(3, "Beto");
        hash.insert(4, "Salus");
        hash.insert(24, "Spit");
        hash.insert(32, "Igol");
        hash.insert(35, "Guido");
        hash.insert(37, "Pelusa");
        hash.insert(31, "Garco");
        hash.insert(36, "Beto2");
        hash.dump();
        System.out.println(hash.getValue(34));
        System.out.println(hash.getValue(2));
        HashBagWithApache<Integer> hb = new HashBagWithApache<>();
        hb.add(8);
        hb.add(9);
        hb.add(10);
        hb.add(8);
        hb.add(8);
        hb.remove(10);
        System.out.println(hb.getCount(8));

    }
}
