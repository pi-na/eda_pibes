package core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class SimpleOrDefault<V,E> extends AdjacencyListGraph<V,E> {

	protected SimpleOrDefault(boolean isDirected, boolean acceptSelfLoops, boolean isWeighted) {
		super(true, isDirected, acceptSelfLoops, isWeighted);
	
	}

	@Override
	public void addEdge(V aVertex, V otherVertex, E theEdge) {

		// validacion y creacion de vertices si fuera necesario
		super.addEdge(aVertex, otherVertex, theEdge);

		// Consigo la lista de adyacencia del vertice origen aVertex
		Collection<InternalEdge> adjListSrc = getAdjacencyList().get(aVertex);

		// No puede haber dos edges iguales entre los mismos vertices, es un grafo simple
		// si el vertice destino ya esta en la lista de adyacencia del vertice origen, lanzo excepcion
		for (InternalEdge internalEdge : adjListSrc) {
			if (internalEdge.target.equals(otherVertex))
				throw new IllegalArgumentException(
						String.format(Messages.getString("addEdgeSimpleOrDefaultError"), aVertex, otherVertex) ); //$NON-NLS-1$
		}

x		// Se agrega el nuevo edge a la lista de adyacencia de aVertex (edge desde aVertex hacia otherVertex)
		adjListSrc.add(new InternalEdge(theEdge, otherVertex));

		// Si el grafo no es dirigido, se agrega el edge a la lista de adyacencia de otherVertex (edge desde otherVertex hacia aVertex)
		if (!isDirected ) {
			Collection<AdjacencyListGraph<V, E>.InternalEdge> adjListDst = getAdjacencyList().get(otherVertex);
			adjListDst.add(new InternalEdge(theEdge, aVertex));
		}
	
	}
}