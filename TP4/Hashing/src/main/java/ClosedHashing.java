import java.util.function.Function;

public class ClosedHashing<K, V> implements IndexParametricService<K, V> {
	// Tamaño inicial de la tabla de hash
	final private int initialLookupSize= 10;
	// Umbral de carga para rehashing
	final private double threshold = 0.75;
	// Tamaño actual de la tabla
	private int size = 0;
	// Tabla de hash
	@SuppressWarnings({"unchecked"})
	private Slot<K,V>[] lookup= (Slot<K,V>[]) new Slot[initialLookupSize];
	// Función de prehashing
	private Function<? super K, Integer> prehash;

	// Constructor que recibe la función de mapeo
	public ClosedHashing( Function<? super K, Integer> mappingFn) {
		if (mappingFn == null)
			throw new RuntimeException("fn not provided");

		prehash= mappingFn;
	}

	// Función de hash
	private int hash(K key) {
		if (key == null)
			throw new IllegalArgumentException("key cannot be null");

		return prehash.apply(key) % lookup.length;
	}

	// Inserta o actualiza un elemento en la tabla
	public void insertOrUpdate(K key, V data) {
		// Validación de argumentos
		if (key == null || data == null) {
			// Excepción si la clave o los datos son nulos
			throw new IllegalArgumentException("Key and data cannot be null");
		}

		// Rehashing si se supera el umbral de carga
		// notar que se usa un "umbral" con valor entre 0 y 1; Es proporcional al tamanio actual de la lookup table.
		if((double)(size+1)/lookup.length >= threshold)
			rehash();
		size++;

		// Cálculo de índices:
		// indexLogico se utiliza para mantener un registro del primer índice que se encuentra durante la búsqueda
		// que ha sido marcado para eliminación lógica (es decir, bajaLogica es verdadero). Ver notion para mas info.
		int index = hash(key);
		int indexLogico = -1;
		int indexFinal = (index + lookup.length - 1) % lookup.length;

		// Búsqueda de posición para insertar o actualizar
		while(lookup[index]!=null && !lookup[index].key.equals(key) && index!=indexFinal) {
			if(indexLogico==-1 && lookup[index].bajaLogica)
				indexLogico = index;
			index++;
			index = index % lookup.length;
		}

		// Inserta o actualiza el elemento
		if(indexLogico!=-1 && (lookup[index]==null || !lookup[index].key.equals(key)))
			lookup[indexLogico] = new Slot<>(key,data);
		else{
			if(lookup[index]!=null)
				size--;
			lookup[index] = new Slot<>(key, data);
		}
	}

	// Rehashing de la tabla
	@SuppressWarnings({"unchecked"})
	private void rehash() {
		Slot<K,V>[] aux = lookup;
		lookup = (Slot<K,V>[]) new Slot[lookup.length*2];
		size = 0;
		for (Slot<K, V> kvSlot : aux) {
			if (kvSlot != null && !kvSlot.bajaLogica)
				insertOrUpdate(kvSlot.key, kvSlot.value);
		}
	}

	// Busca un elemento en la tabla
	public V find(K key) {
		if (key == null)
			return null;

		int index = hash(key);
		int indexFinal = (index + lookup.length - 1) % lookup.length;
		while(index != indexFinal) {
			Slot<K,V> entry = lookup[index];
			if(entry == null)
				return null;
			if(entry.key.equals(key) && !entry.bajaLogica)
				return entry.value;
			index++;
			index = index % lookup.length;
			//	Esta ultima linea se utiliza para asegurar que el índice no se salga de los límites de la tabla de hash.
			//	Esto es útil cuando el índice alcanza el final de la tabla de hash, ya que esta operación
			//  hará que el índice vuelva al principio de la tabla: DEBE SER CIRCULAR! (Ver notion)
		}

		return null;
	}

	// Elimina un elemento de la tabla
	public boolean remove(K key) {
		if (key == null)
			return false;

		int index = hash(key);
		int indexFinal = (index + lookup.length - 1) % lookup.length;
		//  Ver notion para entender mejor la logica de este while
		while(index != indexFinal) {
			Slot<K,V> entry = lookup[index];
			if(entry != null && entry.key.equals(key)) {
				size--;
				//	Si el elemento que sigue esta en "baja fisica" puedo eliminar el actual (dejarlo en baja fisica)
				if(lookup[(index+1) % lookup.length] == null)
					lookup[index] = null;
				//  Si en cambio esta marcado como baja logica, debo marcar el actual como baja logica
				else
					lookup[index].bajaLogica = true;
				return true;
			}
			index++;
			index = index % lookup.length;
		}
		return false;
	}

	// Muestra el contenido de la tabla
	public void dump()  {
		for(int rec= 0; rec < lookup.length; rec++) {
			if (lookup[rec] == null)
				System.out.println(String.format("slot %d is empty", rec));
			else
				System.out.println(String.format("slot %d contains %s bajaLogica= %s",rec, lookup[rec], lookup[rec].bajaLogica));
		}
	}

	// Devuelve el tamaño actual de la tabla
	public int size() {
		return size;
	}

	// Clase interna para representar las ranuras de la tabla
	static private final class Slot<K, V>	{
		private final K key;
		private V value;
		private boolean bajaLogica = false;

		private Slot(K theKey, V theValue){
			key= theKey;
			value= theValue;
		}

		public String toString() {
			return String.format("(key=%s, value=%s)", key, value );
		}
	}
}
