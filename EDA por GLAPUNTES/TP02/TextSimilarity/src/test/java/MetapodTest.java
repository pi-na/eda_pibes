public class MetapodTest {
    public static void main(String[] args) {
        Metapod test = new Metapod();
        System.out.println(test.distance("mnb","qsdf")); // 0
        System.out.println(test.distance("casa","tractor")); // 0.25
        System.out.println(test.distance("hola","ola")); // 0.5
        System.out.println(test.distance("ciao","ciao")); // 1
    }
}
