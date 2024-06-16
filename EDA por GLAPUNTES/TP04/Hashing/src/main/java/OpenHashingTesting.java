public class OpenHashingTesting {
    public static void main(String[] args) {
        OpenHashing<Integer,String> openHash = new OpenHashing<>(x->x);
        openHash.insert(1, "Paula");
        openHash.insert(22, "Matias");
        openHash.dump();
        System.out.println("-----------------");
        openHash.insert(11, "Sirio");
        openHash.dump();
        System.out.println("-----------------");
        openHash.insert(22,"Katia");
        openHash.dump();
        System.out.println("-----------------");
        openHash.insert(111,"Matias");
        openHash.dump();
        System.out.println("-----------------");
        openHash.delete(22);
        openHash.delete(11);
        openHash.delete(111);
        openHash.dump();
        System.out.println("-----------------");
    }
}
