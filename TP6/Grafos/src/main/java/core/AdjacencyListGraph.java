package core;

import java.lang.reflect.Method;
import java.util.*;

import core_service.GraphService;

abstract public class AdjacencyListGraph<V, E> implements GraphService<V, E> {
	enum Color{
		WHITE, RED, GREEN
	}
	private boolean isSimple;
	protected boolean isDirected;
	private boolean acceptSelfLoop;
	private boolean isWeighted;
	protected String type;
	
	// HashMap no respeta el orden de insercion. En el testing considerar eso
	private Map<V,Collection<InternalEdge>> adjacencyList= new HashMap<>();
	
	// respeta el orden de llegada y facilita el testing
	//	private Map<V,Collection<InternalEdge>> adjacencyList= new LinkedHashMap<>();
	
	protected   Map<V,  Collection<InternalEdge>> getAdjacencyList() {
		return adjacencyList;
	}

	/*
	El constructor de la clase se ha hecho protected para restringir su acceso.
	En Java, un constructor protected significa que solo puede ser accedido dentro de
	su propia clase, ***por cualquier clase en el mismo paquete***, y por las subclases de
	su clase en cualquier paquete. En este caso, parece que el diseño del código tiene
	la intención de que los objetos solo se creen a través de la fábrica de gráficos,
	es decir, la clase GraphFactory. (la cual esta en el mismo paquete)	*/
	protected AdjacencyListGraph(boolean isSimple, boolean isDirected, boolean acceptSelfLoop, boolean isWeighted) {
		this.isSimple = isSimple;
		this.isDirected = isDirected;
		this.acceptSelfLoop= acceptSelfLoop;
		this.isWeighted = isWeighted;

		this.type = String.format("%s %sWeighted %sGraph with %sSelfLoop", 
				isSimple ? "Simple" : "core.Multi", isWeighted ? "" : "Non-",
				isDirected ? "Di" : "", acceptSelfLoop? "":"No ");
	}

	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public void addVertex(V aVertex) {
	
		if (aVertex == null )
			throw new IllegalArgumentException(Messages.getString("addVertexParamCannotBeNull"));
	
		// Si no estaba presente, se agrega una nueva entrada en la tabla de adyacencia - aun no tiene edges
		getAdjacencyList().putIfAbsent(aVertex, 
				new ArrayList<InternalEdge>());
	}

	
	@Override
	public int numberOfVertices() {
		return getVertices().size();
	}

	@Override
	public Collection<V> getVertices() {
		return getAdjacencyList().keySet() ;
	}

	// Retorna el numero de edges total del grafo
	@Override
	public int numberOfEdges() {
		int numberOfEdges = 0;

		// Si es dirigido, cada edge se cuenta una sola
		int multiplier = isDirected ? 2 : 1;

		for(Map.Entry<V, Collection<InternalEdge>> entry : adjacencyList.entrySet()) {
			numberOfEdges += multiplier * entry.getValue().size();
		}

		/*
		* Se divide el contador total de aristas por 2. Esto es necesario porque, al recorrer las aristas en un
		* grafo no dirigido, cada arista se contará dos veces (una vez desde cada vértice). Al dividir por 2,
		* se corrige este doble conteo. En el caso de un grafo dirigido, la división por 2 compensa el multiplicador
		* aplicado anteriormente, resultando en el conteo correcto de aristas. */
		return numberOfEdges/2;
	}

	// Este metodo TAN SOLO hace validaciones y agrega los vertices si no estaban presentes!
	@Override
	public void addEdge(V aVertex, V otherVertex, E theEdge) {

		// validation!!!!
		if (aVertex == null || otherVertex == null || theEdge == null)
			throw new IllegalArgumentException(Messages.getString("addEdgeParamCannotBeNull"));

		// es con peso? debe tener implementado el metodo double getWeight()
		if (isWeighted) {
			// reflection
			Class<? extends Object> c = theEdge.getClass();
			try {
				c.getDeclaredMethod("getWeight");
			} catch (NoSuchMethodException | SecurityException e) {
				throw new RuntimeException(
						type + " is weighted but the method double getWeighed() is not declared in theEdge");
			}
		}

		// es un lazo? Deben permitirse los lazos
		if (!acceptSelfLoop && aVertex.equals(otherVertex)) {
			throw new RuntimeException(String.format("%s does not accept self loops between %s and %s" , 
					type, aVertex, otherVertex) );
		}

		// Se agregan los vertices si no estaban presentes
		addVertex(aVertex);
		addVertex(otherVertex);
	}
	
	@Override
	public boolean removeVertex(V aVertex) {
		if(aVertex==null)
			throw new RuntimeException("Vertex is null");
		if(adjacencyList.get(aVertex)==null)
			return false;

		if(isDirected){
			adjacencyList.remove(aVertex);
			for(Map.Entry<V, Collection<InternalEdge>> entry : adjacencyList.entrySet()) {
				entry.getValue().removeIf(edge -> edge.target.equals(aVertex));
			}
		}
		else {
			// Si no es dirigido, elimino todas las edge que tengan a aVertex como target
			for(InternalEdge edge : adjacencyList.get(aVertex)) {
				// Para cada edge que no sea un lazo
				if(!edge.target.equals(aVertex)) {
					// Elimino todas las edge que tengan a aVertex como target
					adjacencyList.get(edge.target).removeIf(otherEdge -> otherEdge.target.equals(aVertex));
				}
			}
			adjacencyList.remove(aVertex);
		}
		return true;
	}

	@Override
	public boolean removeEdge(V aVertex, V otherVertex) {
		if(!isSimple)
			throw new RuntimeException("this method isn't available for multigraphs");
		if(!adjacencyList.containsKey(aVertex) || !adjacencyList.containsKey(otherVertex))
			return false;

		boolean toReturn = adjacencyList.get(aVertex).removeIf(edge -> edge.target.equals(otherVertex));
		if(!isDirected) {
			adjacencyList.get(otherVertex).removeIf(edge -> edge.target.equals(aVertex));
		}

		return toReturn;
	}

	
	@Override
	public boolean removeEdge(V aVertex, V otherVertex, E theEdge) {
		if(!adjacencyList.containsKey(aVertex) || !adjacencyList.containsKey(otherVertex))
			return false;

		if(isSimple)
			return removeEdge(aVertex,otherVertex);

		boolean toReturn = adjacencyList.get(aVertex).removeIf(edge -> edge.edge.equals(theEdge) && edge.target.equals(otherVertex));
		if(!isDirected)
			adjacencyList.get(otherVertex).removeIf(edge -> edge.edge.equals(theEdge) && edge.target.equals(aVertex));

		return toReturn;
	}
	
	
	@Override
	public void dump() {
		// COMPLETAR
		throw new RuntimeException("not implemented yet");
	}
	
	
	@Override
	public int degree(V aVertex) {
		if(isDirected)
			throw new RuntimeException("directed graph cannot call this method");
		if(aVertex == null)
			throw new RuntimeException("vertex cannot be null");
		if(adjacencyList.get(aVertex) == null)
			throw new RuntimeException("vertex not found");

		return adjacencyList.get(aVertex).size();
	}

	

	@Override
	public int inDegree(V aVertex) {
		if(!isDirected)
			throw new RuntimeException("inDegree method is only available for directed graphs");
		if(aVertex == null)
			throw new RuntimeException("vertex cannot be null");

		int degree = 0;

		// Recorro las listas de adyacencia de todos los vertices y cuento cuantos tienen a aVertex como target
		// (cuantos edges INCIDEN sobre aVertex)
		for(Map.Entry<V, Collection<InternalEdge>> entry : adjacencyList.entrySet()) {
				for(InternalEdge edge : entry.getValue()) {
					if(edge.target.equals(aVertex))
						degree++;
			}
		}
		return degree;
	}



	@Override
	public int outDegree(V aVertex) {
		if(!isDirected)
			throw new RuntimeException("graph that isn't directed cannot call this method");
		if(aVertex == null)
			throw new RuntimeException("vertex cannot be null");
		if(adjacencyList.get(aVertex) == null)
			throw new RuntimeException("vertex not found");

		// La cantidad de edges que salen de aVertex es dada por el tamaño de su lista de adyacencia
		return adjacencyList.get(aVertex).size();
	}


	@Override

	public void printBFS(V startNode) {
		if (startNode == null || !adjacencyList.containsKey(startNode)) {
			throw new IllegalArgumentException(Messages.getString("vertexParamError")); //$NON-NLS-1$
		}

		Set<V> visited= new HashSet<>();
		Queue<V> theQueue= new LinkedList<>();

		theQueue.add(startNode);

		while( ! theQueue.isEmpty()) {
			V current = theQueue.poll();

			if (!visited.contains(current)) {
				visited.add(current);
				System.out.println(current);

				Collection<AdjacencyListGraph<V, E>.InternalEdge> vecinos = getAdjacencyList().get(current);
				for (InternalEdge unvecino : vecinos) {
					if (!visited.contains(unvecino.target))
						theQueue.add(unvecino.target);
				}
			}
		}
	}

	@Override
	public void printDFS(V startNode) {
		if (startNode == null || ! adjacencyList.containsKey( startNode)) {
			throw new IllegalArgumentException(Messages.getString("vertexParamError")); //$NON-NLS-1$
		}

		Set<V> visited= new HashSet<>();
		printDFS(startNode, visited);
	}

	private void printDFS(V current, Set<V> visited) {
		if (visited.contains (current))
			return;

		visited.add(current);
		System.out.println(current);

		Collection<AdjacencyListGraph<V, E>.InternalEdge> vecinos = getAdjacencyList().get(current);
		for(InternalEdge unvecino: vecinos) {
			if (! visited.contains (unvecino.target))
				printDFS(unvecino.target, visited);
		}
	}

	@Override
	public Iterable<V> getBFS(V vertex) {
		if(vertex == null || !adjacencyList.containsKey(vertex))
			throw new IllegalArgumentException(Messages.getString("vertexParamError"));

		return () -> new GraphBFSIterator<V,E>(this, vertex);
	}

	@Override
	public Iterable<V> getDFS(V vertex) {
		if(vertex == null || !adjacencyList.containsKey(vertex))
			throw new IllegalArgumentException(Messages.getString("vertexParamError"));

		return () -> new GraphDFSIterator<V,E>(this, vertex);
	}

	@Override
	public void printAllPaths(V start, V end) {
		printAllPathsRec(start,end, new Stack<>(), new StringBuilder());
	}

	private void printAllPathsRec(V start, V end, Stack<V> visited, StringBuilder s) {
		if(start.equals(end)) {
			System.out.println(s.toString() + end);
			return;
		}

		visited.add(start);
		s.append(start).append(" ");

		for(InternalEdge edge : adjacencyList.get(start)) {
			if(!visited.contains(edge.target)) {
				printAllPathsRec(edge.target, end, visited, s);
			}
		}

		s.deleteCharAt(s.length()-1).deleteCharAt(s.length()-1);
		visited.pop();
	}

	public boolean isBipartite(){

		//check if graph is empty
		if(adjacencyList.isEmpty())
			return true;

		//initialize colors for all vertices
		Map<V, Color> colors = new HashMap<>();

		//color all the vertices with white color

		for (V v : adjacencyList.keySet()) {
			colors.put(v, Color.WHITE);
		}

		//start coloring vertices , this code will handle the disconnected graph as well
		//color the first vertex with RED
		for(V vertex : adjacencyList.keySet()) {
			if(colors.get(vertex)==Color.WHITE) {
				colors.put(vertex, Color.RED);

				boolean result = isBipartiteUtil(vertex, colors);
				if(!result)
					return false;
			}
		}
		return true;
	}

	private boolean isBipartiteUtil(V vertex, Map<V, Color> colors){

		//travel all adjacent vertices
		for(InternalEdge edge : adjacencyList.get(vertex)) {
			if(colors.get(edge.target)==Color.WHITE) {
				if(colors.get(vertex)==Color.RED) {
					colors.put(edge.target,Color.GREEN);
				}
				else if(colors.get(vertex)==Color.GREEN) {
					colors.put(edge.target, Color.RED);
				}
				isBipartiteUtil(edge.target, colors);
			}
			else if(colors.get(vertex)==colors.get(edge.target)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean hasCycle() {
		Set<V> visitedNodes = new HashSet<>();
		Set<InternalEdge> visitedEdges = new HashSet<>();

		for(V vertex : getAdjacencyList().keySet()) {
			if(hasCycleRec(visitedNodes,visitedEdges, null, vertex))
				return true;
		}
		return false;
	}

	private boolean hasCycleRec(Set<V> visitedNodes, Set<InternalEdge> visitedEdges, V lastNode, V vertex) {
		visitedNodes.add(vertex);
		boolean addedSameEdge = false;
		for(InternalEdge edge : getAdjacencyList().get(vertex)) {
			if(!addedSameEdge && !isDirected && edge.target.equals(lastNode)) { //No cuento la misma arista
				visitedEdges.add(edge);
				addedSameEdge = true;
			}
			else if(visitedNodes.contains(edge.target) && !visitedEdges.contains(edge)) {
				return true;
			}
			else {
				visitedEdges.add(edge);
				if(hasCycleRec(visitedNodes, visitedEdges, vertex, edge.target)){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public DijkstraPath<V, E> dijsktra(V source) {
		if(!isWeighted)
			throw new RuntimeException("Edges needs to implements getWeight and this graph doesn't accept weight " +
					"edges");

		if(source == null)
			throw new RuntimeException("Source cannot be null");
		if(!adjacencyList.containsKey(source))
			throw new RuntimeException("Source doesn't exist");

		PriorityQueue<NodePQ> pq= new PriorityQueue<>();

		//stores shortest distance from source to every vertex
		Map<V,Integer> costo = new HashMap<>();
		Map<V,V> prev= new HashMap<>();

		// empieza vacio
		Set<V> nodesVisited= new HashSet<>();

		// inicializacion
		for(V aV: getAdjacencyList().keySet()) {
			costo.put(aV, Integer.MAX_VALUE);
			prev.put(aV, null);
		}
		pq.add(new NodePQ(source,0));
		costo.put(source,0);


		while( ! pq.isEmpty()) {
			NodePQ current = pq.poll(); // el menor

			if (nodesVisited.contains(current.vertex)) // ya lo procese
				continue;

			nodesVisited.add(current.vertex); //sino lo agrego

			// ahora recorrer todos los ejes incidentes a current
			for(InternalEdge neighbor: getAdjacencyList().get(current.vertex)) {
				// si fue visitado seguir. Esto tambien excluye los self loops...
				if (nodesVisited.contains(neighbor.target)) {
					continue;
				}

				// invocando a getWeight (se ha validado en insercion)

				int weight;
				// peso de ese eje?
				try {
					Method fn = neighbor.edge.getClass().getMethod("getWeight");
					weight = (int) fn.invoke(neighbor.edge);
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}

				// verificacion
				if (weight < 0 )
					throw new IllegalArgumentException( String.format(Messages.getString("disjkstraWithNegativeWeight"),
									current.vertex, neighbor.target, weight));

				// cual seria el costo de neighbor viniendo desde current?
				int newCosto = costo.get(current.vertex) + weight;
				// es una mejora?
				if (newCosto < costo.get(neighbor.target) ) {
					// insertar neighbor con ese valor mejorado
					costo.put(neighbor.target, newCosto);
					pq.add(new NodePQ(neighbor.target, newCosto));
					// armar camino
					prev.put(neighbor.target, current.vertex);
				}
			}
		}
		return 	new DijkstraPath<>(costo, prev);
	}


	@Override
	public void kruskal() {
		if(!isWeighted || isDirected)
			throw new RuntimeException();

		Map<V, Collection<InternalEdge>> kruskalTree = new HashMap<>();

		for(V vertex : adjacencyList.keySet()) {
			if(!kruskalTree.containsKey(vertex)) {
				int weight = Integer.MAX_VALUE;
				InternalEdge minEdge = null;

				for(InternalEdge neighbor : adjacencyList.get(vertex)) {
					if(vertex.equals(neighbor.target)) //EVITO LOOPS
						continue;

					try {
						Method fn = neighbor.edge.getClass().getMethod("getWeight");
						int aux;
						if((aux = (int) fn.invoke(neighbor.edge)) < weight) {
							weight = aux;
							minEdge = neighbor;
						}
					}
					catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
				if(kruskalTree.get(vertex) == null) { //SI LA COLECCION DEL VERTICE SOURCE ESTA VACIA LA CREO
					kruskalTree.put(vertex, new LinkedList<>());
				}
				if(minEdge != null && kruskalTree.get(minEdge.target) == null) { // IDEM PERO DEL VERTICE TARGET
					kruskalTree.put(minEdge.target, new LinkedList<>());
				}

				if(minEdge != null) { // SI NO ES UN VERTICE AISLADO
					kruskalTree.get(vertex).add(minEdge); //AGREGO A LA LISTA DEL SOURCE
					kruskalTree.get(minEdge.target).add(new InternalEdge(minEdge.edge, vertex)); //AGREGO A LA LISTA DEL TARGET
				}

			}

		}

		for(V vertex : kruskalTree.keySet()) {
			if(kruskalTree.get(vertex).isEmpty()) {
				System.out.println("[ " + vertex + " ]");
			}
			for(InternalEdge edge : kruskalTree.get(vertex)) {
				StringBuilder s = new StringBuilder();
				s.append("[ ").append(vertex);
				int weight;
				try {
					Method fn = edge.edge.getClass().getMethod("getWeight");
					weight = (int) fn.invoke(edge.edge);
				}catch (Exception e) {
					throw new RuntimeException(e);
				}
				s.append(" ").append(weight).append("-> ").append(edge.target).append(" ]");
				System.out.println(s);
			}
		}
	}

	class Node {
		V vertex;
		Integer color;

		public Node(V vertex, Integer color) {
			this.vertex = vertex;
			this.color = color;
		}
	}


	class NodePQ implements Comparable<NodePQ> {
		V vertex;
		Double distance;

		public NodePQ(V vertex, double distance) {
			this.vertex= vertex;
			this.distance= distance;
		}

		@Override
		public int compareTo(NodePQ o2) {
			return Double.compare( distance, o2.distance);
		}
	}

	class InternalEdge{
		E edge;
		V target;

		InternalEdge(E propEdge, V target) {
			this.target = target;
			this.edge = propEdge;
		}

		@Override
		public boolean equals(Object obj) {
			@SuppressWarnings("unchecked")
			InternalEdge aux = (InternalEdge) obj;

			return ((edge == null && aux.edge == null) || (edge != null && edge.equals(aux.edge)))
					&& target.equals(aux.target);
		}

		@Override
		public int hashCode() {
			return target.hashCode();
		}

		@Override
		public String toString() {
			return String.format("-[%s]-(%s)", edge, target);
		}

	}

}
