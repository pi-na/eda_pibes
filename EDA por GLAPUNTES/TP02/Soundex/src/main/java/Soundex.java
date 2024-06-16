import java.util.Arrays;

public class Soundex {
                                //a b c d e f g h i j k l m n o p q r s t u v w x y z
    public static char[] codes = {'0','1','2','3','0','1','2','0','0','2','2','4',
                                        '5','5','0','1','2','6','2','3','0','2','0','2','0','2'};
    public static String encode(String toEncode) {
        char[] in = toEncode.toUpperCase().toCharArray();
        char[] out = new char[4];
        Arrays.fill(out, '0');
        out[0] = in[0];
        char last = getMapping(in[0]);
        for (int i = 1, count = 1; i < in.length && count < 4; i++) {
            char aux = getMapping(in[i]);
            if (aux != '0' && aux != last) {
                out[count++] = aux;
            }
            last = aux;
        }
        return new String(out);
    }

    private static char getMapping(char toMap) {
        return codes[toMap - 'A'];
    }

    public static double similarity(String s1, String s2) {
        String encoded1 = encode(s1);
        String encoded2 = encode(s2);
        double toReturn = 0;
        for (int i = 0; i < encoded1.length(); i++) {
            if (encoded1.charAt(i) == encoded2.charAt(i)) {
                toReturn++;
            }
        }
        return toReturn/4;
    }
}
