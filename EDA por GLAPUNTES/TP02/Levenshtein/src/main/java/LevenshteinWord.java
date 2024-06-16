import java.util.Scanner;

public class LevenshteinWord {
    private static int minimum(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static int distance(String str1, String str2) {

        String[] str1Words = str1.split("(\\.|,|;|\\s|\\t|\\n)+");
        for (int i = 0; i < str1Words.length; i++) {
            System.out.println(i + ": " + str1Words[i]);
        }
        String[] str2Words = str2.split("(\\.|,|;|\\s|\\t|\\n)+");
        System.out.println("--------------");
        for (int i = 0; i < str2Words.length; i++) {
            System.out.println(i + ": " + str2Words[i]);
        }
        System.out.println("--------------");

        return distance(str1Words, str2Words);
    }

    private static int distance(String[] str1, String[] str2) {
        int[][] distance = new int[str1.length + 1][str2.length + 1];

        for (int i = 0; i <= str1.length; i++) {
            distance[i][0] = i;
        }
        for (int j = 0; j <= str2.length; j++) {
            distance[0][j] = j;
        }
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                distance[i][j] = minimum(distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1,
                        distance[i - 1][j - 1] +
                                ((str1[i - 1].equals(str2[j - 1])) ? 0 : 1));
            }
        }
        printMatrix(distance);
        return distance[str1.length][str2.length];
    }

    public static double normalizedSimilarity(String str1, String str2) {
        return 1 - (double) distance(str1, str2) / Math.max(str1.length(), str2.length());
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%d ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
