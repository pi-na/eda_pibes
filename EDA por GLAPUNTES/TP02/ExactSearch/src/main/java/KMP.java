import java.util.ArrayList;
import java.util.List;

public class KMP {
    private static int[] nextComputation(char[] query) {
        int[] next = new int[query.length];
        next[0] = 0;
        int border = 0;

        for(int i = 1; i < query.length; i++) {
            while((border > 0) && (query[border] != query[i]))
                border = next[border - 1];
            if(query[border] == query[i])
                border++;
            //else border=0;//Redundant
            next[i] = border;
        }
        return next;
    }

    public static List<Integer> findAll(char[] target, char [] query) {
        // query: ABAB. Palabra a consultar si se encuentra en target.
        // target: SABASABABA. Palabra que es consultada.
        int[] next = nextComputation(query);
        List<Integer> ret = new ArrayList<>();
        int pTarget=0,pQuery=0;
        while (pTarget < target.length) // Se mantiene en el ciclo hasta que llegue al final de target
        {
            if(query[pQuery] == target[pTarget]) // Coinciden tanto el query como el target.
            {
                pQuery++; pTarget++; // Avanzan ambos.
            }
            if (pQuery == query.length) // Match!
            {
                ret.add(pQuery-pTarget);
                pQuery = next[pQuery-1];
            }
            else if (pQuery==0) // Estamos en el comienzo de query, avanza solo el indice que recorre el target.
            {
                pTarget++;
            }
            else // Como estamos despues del primero del query y no coincide con el target, se cambia el indice.
            {
                pQuery=next[pQuery-1];
            }
        }
        return ret;
    }

    //en el primer match frena
    public static int indexOf(char[] target, char [] query) {
        // query: ABAB. Palabra a consultar si se encuentra en target.
        // target: SABASABABA. Palabra que es consultada.
        int[] next = nextComputation(query), ret;
        int i=0,j=0;
        while (i < target.length && j < query.length) // Se mantiene en el ciclo hasta que encuentre
        {
            if(query[j] == target[i]) // Coinciden tanto el query como el target.
            {
                i++; j++; // Avanzan ambos.
            }
            else if (j==0) // Estamos en el comienzo de query, avanza solo el indice que recorre el target.
            {
                i++;
            }
            else // Como estamos despues del primero del query y no coincide con el target, se cambia el indice.
            {
                j=next[j-1];
            }
        }
        if (j == query.length) {
            return i - j;
        }
        return -1;
    }
}
