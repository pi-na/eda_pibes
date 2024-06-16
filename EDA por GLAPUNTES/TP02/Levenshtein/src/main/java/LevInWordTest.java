public class LevInWordTest {
    public static void main(String[] args) {
        System.out.println("---------------------------- 1P1Q2020E02 ----------------------------------");

        System.out.printf("LEV: %d\n", LevenshteinWord.distance("algoritmos y estructura de datos", "estructura de datos y algoritmos"));

        System.out.printf("LEV: %d\n", LevenshteinWord.distance("habitación   \ndoble de de lujo en hotel Hilton", "hotel Hilton habitación,   doble"));

        System.out.println("---------------------------- 1P1Q2019E03 ----------------------------------");

        System.out.printf("LEV: %d\n", Levenshtein.distance("relevant", "elephant"));

        System.out.printf("LEV: %d\n", Levenshtein.distance("big data", "bigdata"));
    }
}
