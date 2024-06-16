import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class LevenshteinTest {
    @Test
    void distanciaCorrecta() {
        Assertions.assertEquals(1, Levenshtein.distance("pepe", "pope"));
        Assertions.assertEquals(2, Levenshtein.distance("smart", "art"));
        Assertions.assertEquals(0, Levenshtein.distance("machine", "machine"));
        Assertions.assertEquals(0.6, Levenshtein.normalizedSimilarity("smart", "art"));
    }

    @Test
    void simetria() {
        Assertions.assertTrue(Levenshtein.distance("estructura", "caricatura") == Levenshtein.distance("caricatura", "estructura"));
        Assertions.assertTrue(Levenshtein.distance("java", "ruby") == Levenshtein.distance("ruby", "java"));
    }

    @Test
    void desigualdad() {
        String str1 = "algoritmos", str2 = "arquitectura", str3 = "itba";
        Assertions.assertTrue((Levenshtein.distance(str1, str2) + Levenshtein.distance(str2, str3)) >= Levenshtein.distance(str1, str3));
    }

}