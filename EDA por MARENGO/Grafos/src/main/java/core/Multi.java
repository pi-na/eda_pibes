package core;

import core.AdjacencyListGraph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Multi<V,E> extends AdjacencyListGraph<V,E> {

	protected Multi(boolean isDirected, boolean acceptSelfLoops, boolean isWeighted) {
		super(false, isDirected, acceptSelfLoops, isWeighted);
		
	}

	@Override
	public void addEdge(V aVertex, V otherVertex, E theEdge) {

		// validacion y creacion de vertices si fuera necesario
		super.addEdge(aVertex, otherVertex, theEdge);

		
		Collection<InternalEdge> adjListSrc = getAdjacencyList().get(aVertex);

		// creacion de ejes sin problema
		adjListSrc.add(new InternalEdge(theEdge, otherVertex));

		if (!isDirected ) {
			Collection<InternalEdge> adjListDst = getAdjacencyList().get(otherVertex);
			adjListDst.add(new InternalEdge(theEdge, aVertex));
		}
	}

}
