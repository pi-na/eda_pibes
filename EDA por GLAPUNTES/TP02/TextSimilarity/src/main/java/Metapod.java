import info.debatty.java.stringsimilarity.Levenshtein;

public class Metapod extends org.apache.commons.codec.language.Metaphone {
    private final Levenshtein levi = new Levenshtein();
    public double distance(String s1, String s2) {
        String enc1 = encode(s1);
        String enc2 = encode(s2);
        return 1 - levi.distance(enc1,enc2) / Math.max(enc1.length(),enc2.length());
    }
}
