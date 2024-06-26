package ej2;

import java.util.Arrays;

/**
 IMPLEMENTAR: merge(IndexWithDuplicates other). Este método debe fusionar dos índices ordenados en uno solo,
 manteniendo la integridad de la ordenación y respetando la múltiple aparición de elementos repetidos, si corresponde.

 Debe buscar espacio para almacenar los nuevos elementos A LO SUMO UNA VEZ
 */

public class IndexWithDuplicates  {

	final static private int chunksize= 5;

	private int[] indexedData;
	private int cantElems;
	
	
	public IndexWithDuplicates() {
		indexedData= new int[chunksize];
		cantElems= 0;
	}

	public void initialize(int[] unsortedElements) {

		if (unsortedElements == null)
			throw new RuntimeException("Problem: null data collection");

		indexedData= unsortedElements;
		Arrays.sort(indexedData);
		cantElems= indexedData.length;
	}


	public int[] getIndexedData() {
		return indexedData;
	}

	public void print() {
		System.out.print("[");
		for (int i : indexedData)
			System.out.print(i + " ") ;
		System.out.println("]");
		
	}

	public void merge(IndexWithDuplicates other) {
		int[] newData = new int[cantElems + other.cantElems];
		int indexThis = 0, indexOther = 0, indexMerged = 0;

		while (indexThis < cantElems && indexOther < other.cantElems) {
			if (indexedData[indexThis] <= other.indexedData[indexOther]) {
				newData[indexMerged++] = indexedData[indexThis++];
			} else {
				newData[indexMerged++] = other.indexedData[indexOther++];
			}
		}

		while (indexThis < cantElems) {
			newData[indexMerged++] = indexedData[indexThis++];
		}

		while (indexOther < other.cantElems) {
			newData[indexMerged++] = other.indexedData[indexOther++];
		}

		this.cantElems = indexMerged;
		this.indexedData = newData;
	}

	public static void main(String[] args) {
		IndexWithDuplicates index1 = new IndexWithDuplicates();
		index1.initialize(new int[]{1, 3, 5, 7});
		IndexWithDuplicates index2 = new IndexWithDuplicates();
		index2.initialize(new int[]{2, 4, 6, 8});
		index1.merge(index2);
		index1.print();
		System.out.println("Resultado esperado: [1, 2, 3, 4, 5, 6, 7, 8]");
		System.out.println("===============");

		index1 = new IndexWithDuplicates();
		index1.initialize(new int[] {1, 1, 3, 5, 7});
		index2 = new IndexWithDuplicates(); index2.initialize(new int[] {2, 4, 4, 6, 8});
		index1.merge(index2);
		index1.print();
		System.out.println("Resultado esperado: [1, 1, 2, 3, 4, 4, 5, 6, 7, 8]");
		System.out.println("===============");

		index1 = new IndexWithDuplicates();
		index1.initialize(new int[] {1, 3, 5});
		index2 = new IndexWithDuplicates(); index2.initialize(new int[] {2, 4, 6, 8, 10});
		index1.merge(index2);
		index1.print();
		System.out.println("Resultado esperado: [1, 2, 3, 4, 5, 6, 8, 10]");

	}
}

