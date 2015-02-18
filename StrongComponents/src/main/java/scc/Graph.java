package scc;

import java.util.Stack;

/**
 * Graph object class.
 * 
 * @version 1.0
 * @author Julia.Denisova
 * @since 02-18-2015
 * 
 *
 */
public class Graph {
    /**
     * 2-dimensional array. Stores information about adjacent vertices. edges[i]
     * is a graph vertex i+1. edges[i][j] is a graph vertex adjacent to the
     * vertex i+1. edges.length is a total number of graph vertices.
     */
    private int[][] edges;
    /**
     * 
     * An int array with information about leader vertices. contains 0 elements
     * if the graph wasn't explored.
     */
    private int[] leaders;
    /**
     * Stack of the vertex indices in sorted order. first element is the first
     * sorted vertex. last element is the last sorted vertex.
     */
    private Stack<Integer> stack;

    /**
     * Graph constructor. Creates an unsorted graph.
     * 
     * @param edges
     *            @see {@link #edges}
     */
    public Graph(int[][] edges) {
        this.edges = edges;

        leaders = new int[edges.length];
        stack = new Stack<Integer>();
    }

    /**
     * Returns a size of the graph.
     * 
     * @return number of graph vertices.
     */
    public int size() {
        return edges.length;
    }

    /**
     * Produces a 2-dimensional array of all adjacent vertices
     * 
     * @return edges field @see {@link #edges}
     */
    public int[][] getAllEdges() {
        return edges;
    }

    /**
     * Method returns all vertices adjacent to a vertex i.
     * 
     * @param i
     *            vertex index.
     * @return all vertices adjacent to a vertex i.
     */
    public int[] getVertexEdges(int i) {
        return edges[i - 1];
    }

    /**
     * Returns @see {@link #leaders}.
     * 
     * @return an array of leader vertices.
     */
    public int[] getLeaders() {
        return leaders;
    }

    /**
     * Sets a leader n for a vertex i.
     * 
     * @param i
     *            an index of a vertex in the graph.
     * @param leader
     *            an index of the leader vertex for the vertex[index].
     */
    public void setLeaders(int i, int leader) {
        leaders[i - 1] = leader;
    }

    /**
     * Returns a stack of the graph sorted order. see {@link #stack}
     * 
     * @return {@link #stack}
     */
    public Stack<Integer> getStack() {
        return stack;
    }

    /**
     * Pushes a vertex index i to the stack. see {@link #stack}
     * 
     * @param i
     *            index of the graph vertex.
     */
    public void setToStack(int i) {
        stack.push(i);
    }

}
