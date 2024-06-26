package Ej2;

import java.util.Collection;
import java.util.Set;

public class SimpleOrDefault<V,E> extends AdjacencyListGraph<V,E> {

	protected SimpleOrDefault(boolean isDirected, boolean acceptSelfLoops, boolean isWeighted) {
		super(true, isDirected, acceptSelfLoops, isWeighted);
	}

	//	si el grafo es simple y no dirigido, devuelve:
	//	-1 si no es regular, o bien, devuelve N si es un N-regular graph
	@Override
	public int getNRegular() {
		if(isDirected || acceptSelfLoop) throw new RuntimeException("Operation not supported on directed or selfLoop graphs");
		//Voy a ir chequeando el grado de cada vertice. Guardo el grado del ultimo vertice chequeado
		//si en algun momento encuentro un grado distinto, retorno -1. Si sobrevivo, retorno el grado del primer vertice.

		Collection<V> vertices = super.getVertices();
		if(!vertices.iterator().hasNext()) return -1;	//Si no hay vertices lo tomo como q no es regular (la consigna no aclara)
		Integer degree = super.degree(vertices.iterator().next());
		for(V vertex : vertices){
			Integer newDegree = super.degree(vertex);
			if(!degree.equals(newDegree)){
				return -1;
			}
			degree = newDegree;
		}
		return degree;
	}

	@Override
	public void addEdge(V aVertex, V otherVertex, E theEdge) {

		// validacion y creacion de vertices si fuera necesario
		super.addEdge(aVertex, otherVertex, theEdge);

		
		Collection<InternalEdge> adjListSrc = getAdjacencyList().get(aVertex);

		// if exists edge with same target...
		for (InternalEdge internalEdge : adjListSrc) {
			if (internalEdge.target.equals(otherVertex))
				throw new IllegalArgumentException( "Simple Graph: cannot have repeated edges" );
		}
		

		// creacion de ejes
		adjListSrc.add(new InternalEdge(theEdge, otherVertex));

		if (!isDirected ) {
			Collection<AdjacencyListGraph<V, E>.InternalEdge> adjListDst = getAdjacencyList().get(otherVertex);
			adjListDst.add(new InternalEdge(theEdge, aVertex));
		}
	
	}
	
	






}