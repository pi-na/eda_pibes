public class Levenshtein {
    public int getDistance(String s1, String s2){
        int tableLength = Integer.max(s1.length(), s2.length()) + 1;
        int[][] table = new int[tableLength][];
        //inicializar tabla!!


        return 1;
    }

    public int getValue(int[][] table, int row, int column, String rowString, String columnString){
        int left = table[row][column - 1] + 1;
        int up = table[row - 1][column] + 1;
        int matches =
        int diag = table[row - 1][column] + ;
        return 1;
    };

}
