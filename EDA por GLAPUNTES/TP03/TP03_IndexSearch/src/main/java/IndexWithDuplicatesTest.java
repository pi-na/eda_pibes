public class IndexWithDuplicatesTest {
    public static void main(String[] args) {
        IndexService myIndex= new IndexWithDuplicates();
        System.out.println (myIndex.occurrences( 10 ) );  // se obtiene 0
        myIndex.delete( 10 );  // ignora
        System.out.println (myIndex.search( 10 ) );  // se obtiene false
        myIndex.insert( 80 );  // almacena [80]
        myIndex.insert( 20 );  // almacena [20, 80]
        myIndex.insert( 80 );  // almacena [20, 80, 80]
        try
        {
            myIndex.initialize( null );
        }
        catch(Exception e)
        {
        }
        // sigue con lo anterior
        System.out.println (myIndex.occurrences( 80 ) );  // se obtiene 2
        try
        {
            myIndex.initialize( new int[] {100, 50, 30, 50, 80, 100, 100, 30} );
        }
        catch(Exception e)
        {
        }
        // el índice posee [30, 30, 50, 50, 80, 100, 100, 100]
        System.out.println( myIndex.search( 20 ));   // se obtiene false
        System.out.println( myIndex.search( 80 ));   // se obtiene true
        System.out.println (myIndex.occurrences( 50 ) );  // se obtiene 2
        myIndex.delete( 50 );
        System.out.println (myIndex.occurrences( 50 ) );  // se obtiene 1
    }
}
