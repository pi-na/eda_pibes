import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MultiSortedLinkedList<T extends Comparable<? super T>> implements MultiListService<T> {
    private SuperNode root;
    private Map<String, SuperNode> listRoots = new HashMap<>();

    
     
    @Override
    public void insert( String listName, SortedListService<T> list ) {
        if (listRoots.containsKey(listName))
            throw new RuntimeException("No se aceptan listas con el mismo nombre");


        Iterator<T> iterator = list.iterator();

        if (!iterator.hasNext()) { //no hago nada
            return;
        }

        SuperNode prevList = null;
        SuperNode currentList = new SuperNode(iterator.next(), listName, null, null);
        SuperNode prevMultiList = null;
        SuperNode currentMultiList = root;
        listRoots.put(listName, currentList);

        while (!(currentMultiList == null || currentList == null)) {
            int cmp = currentList.data.compareTo(currentMultiList.data);

            if (cmp >= 0) {
                prevMultiList = currentMultiList;
                currentMultiList = currentMultiList.nextInML;

            } else {
                if (prevMultiList == null) {
                    currentList.nextInML = root;
                    root = currentList;
                } else {
                    currentList.nextInML = prevMultiList.nextInML;
                    prevMultiList.nextInML = currentList;
                }

                if (prevList != null) {
                    prevList.nextInList = currentList;
                }

                prevList = currentList;
                currentList = iterator.hasNext() ? new SuperNode(iterator.next(), listName, null, null) : null;
                prevMultiList = prevList;
            }
        }

        while (currentList != null) {
            if (prevMultiList == null) {
                root = currentList;
            } else {
                prevMultiList.nextInML = currentList;
                prevList.nextInList = currentList;
            }

            prevList = currentList;
            prevMultiList = prevList;
            currentList = iterator.hasNext() ? new SuperNode(iterator.next(), listName, null, null) : null;
        }
    }

   
    private final class SuperNode {
        private T data;
        private String listName;
        private SuperNode nextInML;
        private SuperNode nextInList;

        private SuperNode(T data) {
            this.data= data;
        }

        private SuperNode(T data, String lName, SuperNode theNextInML, SuperNode theNextInlist) {
            this.data= data;
            this.listName = lName;
            this.nextInML = theNextInML;
            this.nextInList= theNextInlist;
        }

        public String toString() {
            return "List: [" +  listName + "] Data: " + data.toString();
        }
       
    }

    public void dump() {
        SuperNode current = root;
        while( current != null ){
            System.out.println( current );
            current = current.nextInML;
        }
    }
    
    public void dump(String listName) {
        SuperNode current = listRoots.get( listName );
        while( current != null ){
        	System.out.println( current );
           current = current.nextInList;
        }
    }

}