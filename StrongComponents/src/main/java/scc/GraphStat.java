package scc;

/**
 * 
 * Class contains a statistics on a @see {@link #graph} .
 * 
 * @author Julia.Denisova
 * @since 2-18-2015
 * @version 1.0
 */
public class GraphStat {
    /**
     * @see scc.Graph
     */
    Graph graph;
    /**
     * index of the vertex. Used in the graph calculations.
     */
    private int curVertex;
    /**
     * Number of processed vertices. Used in graph calculations.
     */
    private int procsdVertices;
    /**
     * Boolean array. All vertices are unexplored by default (explored[i] =
     * false).
     */
    private boolean explored[];

    /**
     * Constructor.
     * 
     * @param graph
     * @see scc.Graph
     */
    GraphStat(Graph graph) {
        this.graph = graph;
        explored = new boolean[graph.size()];
    }

    /**
     * Returns a boolean array with statistics on the explored vertices.
     * 
     * @return @see {@link #explored}
     */
    public boolean[] getExplored() {
        return explored;
    }

    /**
     * Returns information if the vertex i is explored during calculations.
     * 
     * @param i
     *            a vertex index
     * @return true if the vertex is explored. Returns false if the vertex is
     *         unexplored.
     */
    public boolean isExplored(int i) {
        return explored[i - 1];
    }

    /**
     * Sets the vertex i as explored
     * 
     * @param i
     *            vertex index
     * 
     */
    public void setExplored(int i) {
        explored[i - 1] = true;
    }

    /**
     * Returns @see {@link #curVertex} field.
     * 
     * @return @see {@link #curVertex} field .
     */
    public int getCurVertex() {
        return curVertex;
    }

    /**
     * Sets the index of the current vertex under calculations.
     * 
     * @param curVertex
     *            index of the vertex.
     */
    public void setCurVertex(int curVertex) {
        this.curVertex = curVertex;
    }

    /**
     * Returns @see {@link #procsdVertices} field
     * 
     * @return number of processed vertices during calculations.
     */
    public int getProcsdVertices() {
        return procsdVertices;
    }

    /**
     * Sets number of the processed vertices during calculations.
     * 
     * @param procsdVertices
     *            @see {@link #procsdVertices}
     */
    public void setProcsdVertices(int procsdVertices) {
        this.procsdVertices = procsdVertices;
    }

}
