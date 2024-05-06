import java.util.ArrayList;
import java.util.Arrays;

public class Levenshtein {

    private int[][] matrix;
    private char[] horizontalWord;
    private char[] verticalWord;

    //  Esta funcion inicializa la matriz con la fila 0 y la columna 0 en 0123456...
    public Levenshtein(char[] horizontalWord, char[] verticalWord) {

        this.horizontalWord= horizontalWord;
        this.verticalWord= verticalWord;

        matrix = new int[ verticalWord.length+1][ horizontalWord.length + 1];

        for(int col = 1; col <= horizontalWord.length; col++) {
            matrix[0][col] = col;
        }

        for(int row = 1; row <= verticalWord.length; row++) {
            matrix[row][0] = row;
        }
    }

    //  Llena la matriz y devuelve la distancia (vertice inferior derecho de la matriz)
    public int distance() {
        // no se ha calculado antes
        if (verticalWord != null) {
            // Arranca desde la fila 1 pues la fila 0 la lleno Levenshtein
            for(int row=1; row<= verticalWord.length; row++) {
                for(int col=1; col<= horizontalWord.length; col++) {
                    //  Usa un llamado a min dentro del otro pq min no funciona con 3 argumentos!
                    matrix[row][col] = Math.min(
                            //  Si las letras son iguales, no se suma nada, si no, se suma 1
                            matrix[row-1][col-1] + (( verticalWord[row-1]== horizontalWord[col-1])?0:1),
                            //  Se suma 1 a la distancia de la celda de arriba y a la de la izquierda, y se toma el minimo
                            Math.min( matrix[row-1][col]+1, matrix[row][col-1]+1 )
                    );
                }
            }
            //Puedo liberar estas variables desp de calcular la matriz
            verticalWord= null;
            horizontalWord= null;
        }

        return matrix [ matrix.length-1][ matrix[0].length-1] ;
    }
}