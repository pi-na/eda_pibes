public class KMP {
    //TODO HACER QUE FUNCIONE :(
    private static int[] nextComputation1(char[] query) {
        int[] next = new int[query.length];

        int border=0;  // Length of the current border

        int rec=1;
        while(rec < query.length){
            if(query[rec]!=query[border]){
                if(border!=0)
                    border=next[border-1];
                else
                    next[rec++]=0;
            }
            else{
                border++;
                next[rec]=border;
                rec++;
            }
        }
        return next;
    }


    private static int[] nextComputation2(char[] query) {
        int[] next = new int[query.length];
        next[0] = 0;     // Always. There's no proper border.
        int border = 0;  // Length of the current border
        for (int rec = 1; rec < query.length; rec++) {
            while ((border > 0) && (query[border] != query[rec]))
                border = next[border - 1];     // Improving previous computation
            if (query[border] == query[rec])
                border++;
            // else border = 0;  // redundant
            next[rec] = border;
        }
        return next;
    }

    public static int indexOf(char[] query, char[] target) {
        int[] next = nextComputation1(query);
        int indexQuery = 0;
        int indexTarget = 0;
        int found = -1;
        while(indexTarget < target.length && indexQuery < query.length && found == -1){
            if(indexQuery + 1 == query.length)
                found = indexTarget - indexQuery;   // Como lo encontre, indexQuery esta AL FINAL de query
            else if(query[indexQuery] != target[indexTarget] && indexQuery != 0){
                indexQuery = next[indexQuery - 1];
            } else {
                indexQuery++;
                indexTarget++;
            }
        }
        return found;
    }

    public static int indexOfJavi(char[] query, char[] target) {
        int [] next = nextComputation1(query);
        int targetCursor = 0;
        int queryCursor = 0;
        while (queryCursor < query.length && targetCursor < target.length) {
            if (query[queryCursor] == target[targetCursor]) {
                queryCursor++;
                targetCursor++;
            } else {
                if (queryCursor == 0) {
                    targetCursor++;
                }else {
                    queryCursor = next[queryCursor - 1];
                }
            }
        }
        if (queryCursor == query.length) {
            return targetCursor - queryCursor;
        }
        return -1;
    }


    public static void main(String[] args) {
        String query, text;
        int pos;

        // debe dar 3
        query= "ABXABU";
        text= "ABXABXABUF";

        pos= indexOf(query.toCharArray(), text.toCharArray());
        System.out.println(String.format("%s in %s at pos %d\n", query, text, pos));


        // debe dar 5
        query= "ABAB";
        text= "SABASABABA";
        pos= indexOf(query.toCharArray(), text.toCharArray());
        System.out.println(String.format("%s in %s at pos %d\n", query, text, pos));


        // debe dar 0
        query= "ABAB";
        text= "ABABYYYY";
        pos= indexOf(query.toCharArray(), text.toCharArray());
        System.out.println(String.format("%s in %s at pos %d\n", query, text, pos));


        // debe dar -1
        query= "ABAB";
        text= "ABAYYYA";
        pos= indexOf(query.toCharArray(), text.toCharArray());
        System.out.println(String.format("%s in %s at pos %d\n", query, text, pos));
    }
}