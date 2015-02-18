package scc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 * Class with static methods for calculation of graph strongly connected
 * components.
 * 
 * @author Julia.Denisova
 * @since 2-18-2015
 * @version 1.0
 *
 */
public class GraphCompStack {
    /**
     * Makes a dfs (@see {@link #dfs(Graph, int, GraphStat)}) loop on the graph
     * vertices in the default order.
     * 
     * @param graph
     * @see scc.Graph.
     * @return @see scc.Graph with filled in leaders and stack.
     */
    public static Graph dfsLoop(Graph graph) {
        GraphStat gs = new GraphStat(graph);
        Graph g = graph;

        for (int i = g.size(); i > 0; i--) {

            if (!gs.isExplored(i)) {
                gs.setCurVertex(i);
                dfs(g, i, gs);
            }
        }

        return g;
    }

    /**
     * Makes a dfs (@see {@link #dfs(Graph, int, GraphStat)}) loop on the graph
     * vertices in the specified in stack order.
     * 
     * @param graph
     * @see scc.Graph.
     * @param stack
     * @see scc.Graph.stack.
     * @return @see scc.Graph with filled in leaders and stack.
     */
    public static Graph dfsLoop(Graph graph, Stack<Integer> stack) {
        GraphStat gs = new GraphStat(graph);
        Graph g = graph;
        Integer i;
        while (!stack.isEmpty()) {
            i = stack.pop();
            if (!gs.isExplored(i)) {
                gs.setCurVertex(i);
                dfs(g, i, gs);
            }
        }

        return g;
    }

    /**
     * Deep First Sort on the graph vertices.
     * 
     * @param graph
     * @see scc.Graph
     * @param i
     *            vertex index
     * @param gs
     * @see scc.GraphStat
     */
    public static void dfs(Graph graph, int i, GraphStat gs) {

        gs.setExplored(i);
        graph.setLeaders(i, gs.getCurVertex());
        int[] arcs = graph.getVertexEdges(i);
        if (arcs != null) {
            for (int j = 0; j < arcs.length; j++) {
                int ver = arcs[j];
                if (!gs.isExplored(ver)) {
                    dfs(graph, ver, gs);
                }
            }
        }
        gs.setProcsdVertices(gs.getProcsdVertices() + 1);
        graph.setToStack(i);

    }

    /**
     * Returns an array of graph cycles. Each element in the array represents
     * number of vertices in a cycle.
     * 
     * @param graph
     * @see scc.Graph
     * @return an array of graph cycles. Each element in the array represents
     *         number of vertices in a cycle.
     */
    public static Long[] countScc(Graph graph) {

        int[] leaders = graph.getLeaders();
        if (leaders == null) {
            throw new NullPointerException("Leaders array is null!");
        }

        if (leaders != null) {
            long[] scc = countCycled(leaders);
            int j1 = countZeros(scc);

            Long[] cycles = removeZeros(scc, j1);
            Arrays.sort(cycles, Collections.reverseOrder());
            return cycles;
        } else
            return new Long[] { 0l };

    }

    /**
     * Removes 0 elements in the array.
     * 
     * @param scc
     *            - array with 0 elements.
     * @param j1
     *            - number of 0 elements.
     * @return an array without 0 elements.
     */
    private static Long[] removeZeros(long[] scc, int j1) {
        Long[] temp = new Long[scc.length - j1];
        int l = 0;
        for (int k = 0; k < scc.length; k++) {
            if (scc[k] > 0) {
                temp[l] = scc[k];
                l++;
            }
        }
        return temp;
    }

    /**
     * Counts number of 0 elements in the array.
     * 
     * @param scc
     *            an array with 0 elements
     * @return number of 0 elements in the array.
     */
    private static int countZeros(long[] scc) {
        int j = 0;
        for (int z = 0; z < scc.length; z++) {
            if (scc[z] < 1) {
                j++;
            }
        }
        return j;
    }

    /**
     * Counts cycled vertexes for every leader vertex.
     * 
     * @param leaders
     * @see scc.Graph.leaders returns an array of cycled vertexes for every
     *      leader vertex.
     */
    private static long[] countCycled(int[] leaders) {
        long[] scc = new long[leaders.length];
        int j;
        for (int i = 0; i < leaders.length; i++) {
            j = leaders[i] - 1;
            scc[j]++;
        }
        return scc;
    }
}
