package graph;


import java.lang.reflect.Method;
import java.util.*;

abstract public class AdjacencyListGraph<V, E> implements GraphService<V, E> {

    private boolean isSimple;
    protected boolean isDirected;
    private boolean acceptSelfLoop;
    private boolean isWeighted;
    protected String type;

    // HashMap no respeta el orden de insercion. En el testing considerar eso
    private Map<V,Collection<InternalEdge>> adjacencyList= new HashMap<>();

    // respeta el orden de llegada y facilita el testing
    //	private Map<V,Collection<InternalEdge>> adjacencyList= new LinkedHashMap<>();

    protected   Map<V,  Collection<AdjacencyListGraph<V, E>.InternalEdge>> getAdjacencyList() {
        return adjacencyList;
    }

    protected AdjacencyListGraph(boolean isSimple, boolean isDirected, boolean acceptSelfLoop, boolean isWeighted) {
        this.isSimple = isSimple;
        this.isDirected = isDirected;
        this.acceptSelfLoop= acceptSelfLoop;
        this.isWeighted = isWeighted;

        this.type = String.format("%s %sWeighted %sGraph with %sSelfLoop",
                isSimple ? "Simple" : "Multi", isWeighted ? "" : "Non-",
                isDirected ? "Di" : "", acceptSelfLoop? "":"No ");
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void addVertex(V aVertex) {

        if (aVertex == null )
            throw new IllegalArgumentException("addVertex parameters cannot be null");

        // no edges yet
        getAdjacencyList().putIfAbsent(aVertex, new ArrayList<InternalEdge>());
    }

    @Override
    public Collection<V> getVertices() {
        return getAdjacencyList().keySet() ;
    }

    @Override
    public void addEdge(V aVertex, V otherVertex, E theEdge) {

        // validation!!!!
        if (aVertex == null || otherVertex == null || theEdge == null)
            throw new IllegalArgumentException("addEdge parameters cannot be null");

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

        if (! acceptSelfLoop && aVertex.equals(otherVertex)) {
            throw new RuntimeException(String.format("%s does not accept self loops between %s and %s" ,
                    type, aVertex, otherVertex) );
        }

        // if any of the vertex is not presented, the node is created automatically
        addVertex(aVertex);
        addVertex(otherVertex);
    }


    @Override
    public boolean hasEdge(V fromVertex, V toVertex, E edge) {
        if (fromVertex == null || toVertex == null || edge == null) {
            throw new RuntimeException("hasEdges called with at least one null parameter");
        }

        Collection<InternalEdge> edges = adjacencyList.get(fromVertex);
        if (edges == null) {
            return false;
        }

        for (InternalEdge e : edges) {
            if (e.target.equals(toVertex) && e.edge.equals(edge)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public GraphService<V, E> popularSubgraph(Integer popularThreshold) {
        if(!isDirected || !isWeighted)
            throw new RuntimeException("Este metodo solo aplica a grafos dirigidos y con peso");

        GraphService<V, E> graphToReturn = new GraphBuilder<V,E>()
                .withMultiplicity(isSimple?Multiplicity.SIMPLE:Multiplicity.MULTIPLE)
                .withAcceptSelfLoop(acceptSelfLoop?SelfLoop.YES:SelfLoop.NO)
                .withAcceptWeight(Weight.YES)
                .withDirected(EdgeMode.DIRECTED)
                .withStorage(Storage.SPARSE)
                .build();

        Map<V, Integer> inDegree = new HashMap<>();
        Map<V, Collection<InternalEdge>> graph = new HashMap<>();

        for(V vertex : adjacencyList.keySet()) {
            inDegree.put(vertex, 0);
            graph.put(vertex, new LinkedList<>());
        }

        for(V vertex : adjacencyList.keySet()) { //me fijo los inDegree
            for(InternalEdge edge : adjacencyList.get(vertex)) {
                int weight;
                // peso de ese eje?
                try {
                    Method fn = edge.edge.getClass().getMethod("getWeight");
                    weight = (int) fn.invoke(edge.edge);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }

                int previousWeight = inDegree.get(edge.target);
                inDegree.put(edge.target, previousWeight+weight);
                graph.get(edge.target).add(new InternalEdge(edge.edge, vertex));
            }
        }

        for(V vertex : inDegree.keySet()) {
            if(inDegree.get(vertex)>=popularThreshold) {
                for(InternalEdge edge : graph.get(vertex)) {
                    graphToReturn.addEdge(edge.target, vertex, edge.edge);
                }
            }
        }

        return graphToReturn;
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
            for(InternalEdge edge : adjacencyList.get(aVertex)) {
                if(!edge.target.equals(aVertex)) {
                    adjacencyList.get(edge.target).removeIf(otherEdge -> otherEdge.target.equals(aVertex));
                }
            }
            adjacencyList.remove(aVertex);
        }
        return true;
    }

    class InternalEdge {
        E edge;
        V target;

        InternalEdge(E propEdge, V target) {
            this.target = target;
            this.edge = propEdge;
        }

        @Override
        public int hashCode() {
            return target.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            @SuppressWarnings("unchecked")
            InternalEdge aux = (InternalEdge) obj;

            return ((edge == null && aux.edge == null) || (edge != null && edge.equals(aux.edge)))
                    && target.equals(aux.target);
        }

        @Override
        public String toString() {
            return String.format("-[%s]-(%s)", edge, target);
        }
    }
}