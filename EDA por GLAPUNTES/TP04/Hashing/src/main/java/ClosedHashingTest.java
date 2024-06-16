public class ClosedHashingTest {
    public static void main(String[] args) {
        ClosedHashing<Integer,String> closedHashing = new ClosedHashing<>(x->x);
        closedHashing.insert(1, "Paula");
        closedHashing.insert(22, "Matias");
        closedHashing.dump();
        System.out.println("-----------------");
        closedHashing.insert(11, "Sirio");
        closedHashing.dump();
        System.out.println("-----------------");
        closedHashing.insert(22,"Katia");
        closedHashing.dump();
        System.out.println("-----------------");
        closedHashing.insert(111,"Matias");
        closedHashing.dump();
        System.out.println("-----------------");
        closedHashing.delete(22);
        closedHashing.delete(11);
        closedHashing.delete(111);
        closedHashing.dump();
        System.out.println("-----------------");

    }
}
