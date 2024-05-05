public class Soundex {
    private static final int MAX_LETTERS = 26;
    private static char[] soundexMapping;
    // Este codigo se corre cuando se carga la clase en memoria!!!!
    static{
        char[] soundexMapping = getSoundexValuesTable();
    }

    private static final char[][] tablaSoundex = {
        {'A','E','I','O','U','Y','H','W'},
        {'B','F','P','V'},
        {'C','G','J','K','Q','S','X','Z'},
        {'D','T'},
        {'L'},
        {'M','N'},
        {'R'}
    };

    public static String representation(String s){
        s = s.toUpperCase();
        char[] IN = s.toCharArray();
        char[] OUT = {'0','0','0','0'};
        OUT[0] = IN[0];
        int count = 1;
        char current, last = getMapping(IN[0]);
        for(int i =1; i<IN.length && count < 4; i++){
            char iter= IN[i];
            current = getMapping(iter);
            if(current != 0 && current != last){
                OUT[count++] = current;
            }
            last = current;
        }
        return new String(OUT);
    }

    // Retorna el char (EN ASCII) del numero correspondiente a la letra en soundex.
    private static char getMapping(char c) {
        return (char)(soundexMapping[c] + '0');
    }

    private static char[] getSoundexValuesTable(){
        char[] table = new char[MAX_LETTERS];
        int count = 0;
        for(int i = 0; i < tablaSoundex.length; i++){
            for(int j = 0; j < tablaSoundex[i].length; j++){
                table[tablaSoundex[i][j] - 'A'] = (char) i;
            }
        }
        return table;
    }

}

