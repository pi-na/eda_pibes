import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QGram {
    private final int qnumber;

    public QGram(int qnumber){
        if(qnumber <= 0)
            throw new IllegalArgumentException("Q debe ser positivo");
        this.qnumber = qnumber;
    }

    public void printTokens(String str) {
        for(Map.Entry<String, Integer> entry : getTokensInMap(str).entrySet()) {
            System.out.format("%s - %d\n",entry.getKey(), entry.getValue());
        }
    }

    //  Consigue los tokens para una cadena, usando un mapa
    private Map<String, Integer> getTokensInMap(String str) {
        StringBuilder s = new StringBuilder();
        // Agrega '#' (Q - 1)veces al principio y al final de la cadena
        s.append("#".repeat(qnumber-1)).append(str).append("#".repeat(qnumber-1));
        Map<String, Integer> map = new HashMap<>();
        // Se van consiguiendo substrings de longitud Q
        // El for arranca en i=qnumber porque la substring se genera de derecha a izquierda!
        for(int i=qnumber; i<=s.length(); i++){
            // Genera la substring desde i-qnumber hasta i
            String qGram = s.subSequence(i-qnumber,i).toString();
            // Si el qGram ya esta en el mapa, se le suma 1, si no, se agrega con valor 1 (merge, franco poo)
            map.merge(qGram,1,(oldValue, newValue) -> oldValue + 1);
        }
        return map;
    }

    //  Consigue los tokens para una cadena, usando una lista
    //  A diferencia de la implementacion con mapa, las substrings se guardan repetidas veces
    private List<String> getTokensInList(String str)  {
        StringBuilder s = new StringBuilder();
        // Agrega '#' (Q - 1)veces al principio y al final de la cadena
        s.append("#".repeat(qnumber-1)).append(str).append("#".repeat(qnumber-1));
        List<String> list = new ArrayList<>();      //  Admite repes y no tiene orden!
        for(int i=qnumber; i<=s.length(); i++){
            String qGram = s.subSequence(i-qnumber,i).toString();
            list.add(qGram);
        }
        return list;
    }

    //  Calcula la similitud entre dos cadenas, usando una lista
    //  Esta version es mas ineficiente!
    public double similarityList(String str1, String str2) {
        List<String> list1 = getTokensInList(str1);
        List<String> list2 = getTokensInList(str2);
        int sumLength = list1.size() + list2.size();
        //  Removed sirve como contador de coincidencias
        int removed = 0;

        //  Se recorre la lista de qgramas de str1, y se eliminan los qgramas de str2 que coincidan
        for(String qGram : list1) {
            if(list2.remove(qGram))
                removed++;
        }
        System.out.println(removed);
        System.out.println(sumLength);
        return (double)2*removed/(sumLength);
    }

    //  Calcula la similitud basándose en el número de veces que cada q-grama de str1 aparece en str2.
    //  Para cada qgrama de s1, se decrementa las frecuencias en el mapa de str2, si hay coincidencia.
    public double similarityMap(String str1, String str2) {
        Map<String, Integer> map1 = getTokensInMap(str1);
        Map<String, Integer> map2 = getTokensInMap(str2);
        //  Removed sirve como contador de coincidencias
        int removed = 0;

        for(Map.Entry<String, Integer> entry1 : map1.entrySet()) {
            do {
                //  Si el qgrama de str1 esta en str2, se decrementa su frecuencia en el mapa de str2
                if (map2.getOrDefault(entry1.getKey(), 0) != 0) {
                    //  Notar que se usa merge con defaultValue 1, por prolijidad,
                    //  nunca se va a entrar a este if si el qgrama no esta en el mapa de str2
                    map2.merge(entry1.getKey(), 1, (oldValue, newValue) -> oldValue - 1);
                    removed++;
                    System.out.println(entry1.getKey());
                }
                //  Se decrementa el valor del qgrama de str1
                entry1.setValue(entry1.getValue()-1);
            } while(entry1.getValue()!=0);      //  Saltea cualquier qgrama que haya sido eliminado
        }

        //  Devuelve la similitud calculada como el doble del número de coincidencias
        //  dividido por la suma de las longitudes de ambas cadenas ajustadas por los caracteres de relleno.
        //  Formula dada en clase (ver NOTION)
        return (double)2*removed/(str1.length()+2*(qnumber-1)+str2.length());
    }
}