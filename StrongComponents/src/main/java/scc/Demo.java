package scc;

import java.io.IOException;

import util.GraphOrder;
import util.IoUtil;

/**
 * Demo class showing work of the algorithm for scc calculation.
 * 
 * @version 1.0
 * @author Julia.Denisova
 * @since 02-18-2015
 *
 */
public class Demo {
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        int[][] edges = null;
        int[][] revEdges = null;
        int verticesN = 875715;
        try {
            edges = IoUtil.parseGraphEdges("./inputs/SCC2.txt", verticesN,
                    GraphOrder.ORIGINAL);
            revEdges = IoUtil.parseGraphEdges("./inputs/SCC2.txt", verticesN,
                    GraphOrder.REVERSED);

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (edges == null) {
            throw new NullPointerException(
                    "Null Array with original graph info!");
        }
        if (revEdges == null) {
            throw new NullPointerException(
                    "Null Array with reversed graph info!");
        }
        Graph original = new Graph(edges);
        original = GraphCompStack.dfsLoop(original);
        System.out.println("Original loop done");

        Graph reversed = new Graph(revEdges);

        reversed = GraphCompStack.dfsLoop(reversed, original.getStack());
        System.out.println("Second loop done");
        Long[] cycles = GraphCompStack.countScc(reversed);
        try {
            IoUtil.write("D://Algorythms/Stanford/hw4/out.txt", cycles);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Data in file");
    }

}
