package ej2;

import java.util.Arrays;

/**
 IMPLEMENTAR: merge(IndexWithDuplicates other). Este método debe fusionar dos índices ordenados en uno solo,
 manteniendo la integridad de la ordenación y respetando la múltiple aparición de elementos repetidos, si corresponde.

 Debe buscar espacio para alace
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
		if (other == null || other.indexedData == null) {
			throw new IllegalArgumentException("Other IndexWithDuplicates cannot be null");
		}

		int[] otherData = other.indexedData;
		int[] mergedData = new int[this.cantElems + other.cantElems];

		int i = 0, j = 0, k = 0;
		while (i < this.cantElems && j < other.cantElems) {
			if (this.indexedData[i] <= otherData[j]) {
				mergedData[k++] = this.indexedData[i++];
			} else {
				mergedData[k++] = otherData[j++];
			}
		}

		while (i < this.cantElems) {
			mergedData[k++] = this.indexedData[i++];
		}

		while (j < other.cantElems) {
			mergedData[k++] = otherData[j++];
		}

		this.indexedData = mergedData;
		this.cantElems = k;
		}
}

