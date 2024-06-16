public class Levenshtein {
    private static int minimum(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static int distance(String str1, String str2) {
        return distance(str1.toCharArray(),
                str2.toCharArray());
    }

    private static int distance(char[] str1, char[] str2) {
        /**
         * Version 1 con matriz de complejidad n*m.
         */

        int [][]distance = new int[str1.length+1][str2.length+1];

        for(int i = 0; i <= str1.length; i++) {
            distance[i][0]=i;
        }
        for(int j = 0; j <= str2.length; j++) {
            distance[0][j]=j;
        }
        for(int i = 1; i <= str1.length; i++){
            for(int j = 1;j <= str2.length; j++){
                distance[i][j]= minimum(distance[i-1][j]+1,
                        distance[i][j-1]+1,
                        distance[i-1][j-1]+
                                ((str1[i-1]==str2[j-1])?0:1));
            }
        }
        printMatrix(distance);
        return distance[str1.length][str2.length];

        /**
         * Version 2 con vectores de complejidad espacial min(s1.length, s2.length)
         */
//        int len0 = str1.length + 1;
//        int len1 = str2.length + 1;
//
//        // the array of distances
//        int[] cost = new int[len0];
//        int[] newcost = new int[len0];
//
//        // initial cost of skipping prefix in String s0
//        for (int i = 0; i < len0; i++) cost[i] = i;
//
//        // dynamically computing the array of distances
//
//        // transformation cost for each letter in s1
//        for (int j = 1; j < len1; j++) {
//            // initial cost of skipping prefix in String s1
//            newcost[0] = j;
//
//            // transformation cost for each letter in s0
//            for (int i = 1; i < len0; i++) {
//                // matching current letters in both strings
//                int match = (str1[i - 1] == str2[j - 1]) ? 0 : 1;
//
//                // computing cost for each transformation
//                int cost_replace = cost[i - 1] + match; // diagonal adjacent value.
//                int cost_insert = cost[i] + 1; // upper adjacent value.
//                int cost_delete = newcost[i - 1] + 1; // left adjacent value.
//
//                // keep minimum cost
//                newcost[i] = minimum(cost_insert, cost_delete, cost_replace);
//            }
//
//            // swap cost/newcost arrays
//            int[] swap = cost;
//            cost = newcost;
//            newcost = swap;
//        }
//        // the distance is the cost for transforming all letters in both strings
//        return cost[len0 - 1];
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