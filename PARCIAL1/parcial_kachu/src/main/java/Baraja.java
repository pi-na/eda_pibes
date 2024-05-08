//baraja.java
import java.util.Random;

public class Baraja {

    private Carta first;
    private Carta last;

    private int qty;

    public Baraja(String[] palos, int maxNumero ){
        qty = palos.length * maxNumero;
        for ( String p : palos ){
            for ( int i = 1; i <= maxNumero; ++i ){
                Carta c = new Carta( p, i );
                if (first == null){
                    first = c;
                    last = c;
                } else {
                    last.next = c;
                    c.prev = last;
                    last = c;
                }
            }
        }
    }

    private int randP = 0;

    private Integer[] getRandomPair(){
        Integer retValue[] = new Integer[2];

        Random r = new Random(randP);

        retValue[0] = r.nextInt( qty );
        retValue[1] = r.nextInt( qty );

        System.out.println( " {" + randP + "} [" + retValue[0].toString() + "," + retValue[1] + "]" );
        ++randP;
        return retValue;
    }

    public void Mezclar( int numMezclas ){
        // COMPLETAR
    }

    public void dump(){
        Integer i = 0;
        Carta c = first;
        while( c != null ){
            System.out.println( "[" + i.toString() + "] " + c.toString() );
            c = c.next;
            ++i;
        }
    }

    private final class Carta {
        private final String palo;
        private final Integer numero;
        private Carta next = null;
        private Carta prev = null;

        public Carta(String palo, Integer numero) {
            this.palo = palo;
            this.numero = numero;
        }

        public String toString(){
            return palo + " " + numero.toString();
        }
    }


    public static void main(String[] args) {
        Baraja b = new Baraja( new String[]{"Oro", "Copa", "Espada", "Basto"}, 5 );

        b.dump();
        System.out.println();
        b.Mezclar( 100 );
        System.out.println();
        b.dump();
    }
}