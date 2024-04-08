package eda;

import java.lang.reflect.Array;

public class PCaso3<T extends Comparable> {

		private T[] arreglo;

		@SuppressWarnings("unchecked")
		public void initialize(int dim) {
			//	No puedo castear un object a un tipo generico BOUND (a comparable en este caso)
			//	ya se hizo el erasure y esto nos quita la opcion:
			// El compilador elimina (o "borra") toda la información de tipo relacionada con los genéricos durante la compilación.
			/*
			* En tu código, el tipo genérico T es acotado por Comparable, lo que significa que cualquier
			*  tipo que sustituya a T debe implementar la interfaz Comparable. Sin embargo, debido
			* al borrado de tipos, el compilador elimina esta información de tipo en tiempo de ejecución,
			*  y no es posible crear directamente un array de tipo genérico T (como new T[dim]) porque el
			* tipo T se ha borrado y se ha convertido en Object.
			*/
			arreglo=(T[]) new Object[dim];
		}

		private boolean isValidPos(int pos) {
			return ! (arreglo == null ||  pos < 0  ||  pos > arreglo.length);
		}
		
		public void setElement(int pos, T element) {
			if (! isValidPos(pos) )
				throw new RuntimeException("problema....");
			
			arreglo[pos]= element;
		}

		public T getElement(int pos)
		{
			if (! isValidPos(pos) )
				throw new RuntimeException("problema....");

			return arreglo[pos];
		}

		
		
		public static void main(String[] args) {
			PCaso3<Integer> auxi= new PCaso3<>();
			auxi.initialize(5);
			
			auxi.setElement(2, 10);
			auxi.setElement(4, 90);
			
			for(int rec= 0; rec < 5; rec++)
				System.out.println ( auxi.getElement(rec) );
			
			System.out.println();
			
			PCaso3<String> auxi2= new PCaso3<>();
			auxi2.initialize(3);
			
			auxi2.setElement(1, "hola");
			
			for(int rec= 0; rec < 3; rec++)
				System.out.println ( auxi2.getElement(rec) );
		}
}
