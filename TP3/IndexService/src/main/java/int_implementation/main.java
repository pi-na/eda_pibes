package int_implementation;

import java.util.Arrays;

public class main {
    public static void main(String[] args) {

        IndexWithDuplicates myIndex= new IndexWithDuplicates();

        System.out.println ("expected 0: " + myIndex.occurrences( 10 ) );  // se obtiene 0

        myIndex.delete( 10 );  // ignora

        System.out.println ("expected false: " + myIndex.search( 10 ) );  // se obtiene false

        myIndex.insert( 80 );  // almacena [80]

        myIndex.insert( 20 );  // almacena [20, 80]

        myIndex.insert( 80 );  // almacena [20, 80, 80]

        System.out.println("expected [20, 80, 80]" + Arrays.toString(myIndex.getElementsArray()));

        try
        {
            myIndex.initialize( new int[] {100, 50, 30, 50, 80, 100, 100, 30} );
        }
        catch(Exception e)
        {
        }
        // el índice posee [30, 30, 50, 50, 80, 100, 100, 100]
        System.out.println("expected false: " +  myIndex.search( 20 ));   // se obtiene false

        System.out.println("expected true: " +  myIndex.search( 80 ));   // se obtiene true

        System.out.println ("expected 2: " + myIndex.occurrences( 50 ) );  // se obtiene 2

        myIndex.delete( 50 );

        System.out.println ("expected 1: " + myIndex.occurrences( 50 ) );  // se obtiene 1

        myIndex.initialize( new int[] {100, 50, 30, 50, 80, 100, 100, 30} ); // guarda 30 30 50 50 80 100 100 100
        myIndex.sortedPrint();

        int[] rta= myIndex.range(50, 100, false, false); // [80]
        System.out.println("expected 80: " + Arrays.toString(rta));

        rta= myIndex.range(30, 50, true, false); // [30, 30]
        System.out.println("expected 30, 30: " + Arrays.toString(rta));

        rta= myIndex.range(45, 100, false, false); // [50, 50, 80]
        System.out.println("expected 50,50,80: " + Arrays.toString(rta));

        rta= myIndex.range(45, 100, true, false); // [50, 50, 80]
        System.out.println("expected 50,50,80: " + Arrays.toString(rta));

        rta= myIndex.range(10, 50, true, false); // [30, 30]
        System.out.println("expected 30, 30: " + Arrays.toString(rta));

        rta= myIndex.range(10, 20, false, false); // []
        System.out.println("expected []: " + Arrays.toString(rta));

        rta= myIndex.range(-10, 120, false, false); // []
        System.out.println("expected [30, 30, 50, 50, 80, 100, 100, 100]: " + Arrays.toString(rta));
    }
}
