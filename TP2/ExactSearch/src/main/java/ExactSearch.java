public class ExactSearch {
    /*
     * COMPLEJIDAD ESPACIAL: O(1). LA FUNCION SOLO GUARDA LOS INDICES.
     *                       LA COMPLEJIDAD ES CONSTANTE.
     * COMPLEJIDAD TEMPORAL: PEOR CASO: NO ENCUENTRO EL MATCH, RECORRO LA STRING QUERY
     *                       TANTAS VECES COMO CARACTERES EN EL STRING TARGET
     *                       => ME QUEDA COMPLEJIDAD N*M
     * */
    //  TODO ARREGLAR EL WHILE QUE NUNCA TERMINA
    //  todo ademas deberia usar length, no null terminated
    public static int indexOf(char[] query, char[] target){
        int indexQuery = 0;
        int indexTarget = 0;
        int found = -1;  //  Si encuentro la query, le asocio el indice
        while(target[indexTarget] != 0 || found == -1){
            if(query[indexQuery] == target[indexTarget]){
                if(query[indexQuery] == 0){
                    found = indexQuery;
                }
                indexQuery++;
            }   else    {
                indexTarget -= (indexQuery + 1);
                indexQuery = 0;
            }
            indexTarget++;
        }
        System.out.println(found);
        return found;
    }
}
