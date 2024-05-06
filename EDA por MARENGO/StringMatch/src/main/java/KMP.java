import java.util.ArrayList;
import java.util.List;

public class KMP {

    //  Genera la tabla de next para la query, a partir de los sufijos y prefijos
    public List<Integer> nextComputation(char[] query) {
        int border = 0;
        int current = 1;
        List<Integer> next = new ArrayList<>();
        next.add(border);
        while(current < query.length) {
            if(query[current] == query[border]) {
                border++;
                next.add(border);
                current++;
            }
            else {
                if(border != 0) {
                    //  Ahora border es el borde de la tabla next[border-1]
                    border = next.get(border-1);
                }
                else {
                    //  Si no hay sufijos y prefijos iguales, se agrega un 0
                    next.add(border);
                    current++;
                }
            }
        }
        return next;
    }

    //  Devuelve el indice de la primera ocurrencia de query en target, usando KMP y la tabla de next
    public int indexOf(char[] query, char[] target) {
        List<Integer> next = nextComputation(query);
        int i = 0;
        int j = 0;

        while(i < target.length) {
            System.out.println(j);
            if(target[i] == query[j]) {
                i++;
                j++;
                if(j==query.length)
                    return i-j;
            }
            else {
                if(j==0)
                    i++;
                else
                    j=next.get(j-1);
            }
        }
        return -1;
    }

    //  Devuelve una lista con los indices de todas las ocurrencias de query en target, usando KMP muchas veces
    public List<Integer> findAll(char[] query, char[] target) {
        List<Integer> next = nextComputation(query);
        List<Integer> toReturn = new ArrayList<>();
        int i = 0;
        int j = 0;

        while(i < target.length) {
            if(target[i] == query[j]) {
                i++;
                j++;
                if(j==query.length) {
                    toReturn.add(i-j);
                    j= next.get(j-1);
                }

            }
            else {
                if(j == 0)
                    i++;
                else
                    j = next.get(j-1);
            }
        }
        return toReturn;
    }
}
