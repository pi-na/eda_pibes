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
		int[] mergedData = new int[cantElems + other.cantElems];
		int i = 0, j = 0, k = 0;

		while (i < cantElems && j < other.cantElems) {
			if (indexedData[i] <= other.indexedData[j]) {
				mergedData[k++] = indexedData[i++];
			} else {
				mergedData[k++] = other.indexedData[j++];
			}
		}

		while (i < cantElems) {
			mergedData[k++] = indexedData[i++];
		}

		while (j < other.cantElems) {
			mergedData[k++] = other.indexedData[j++];
		}

		indexedData = mergedData;
		cantElems = k;
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

