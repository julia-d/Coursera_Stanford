package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class provides IO operations for arrays.
 * 
 * @author Julia.Denisova
 * @since 2-18-2015
 * @version 1.0
 *
 */
public class IoUtil {
    /**
     * The methods parces information on graph adjacent vertices.
     * 
     * @param fileName
     *            full name of the file for parsing.
     * @param vertexN
     *            number of graph vertices.
     * @param order
     *            @see util.GraphOrder.
     * @return a 2-dimensional array with all adjacent vertices of the graph.
     * @throws NumberFormatException
     * @throws IOException
     */
    public static int[][] parseGraphEdges(String fileName, int vertexN,
            GraphOrder order) throws NumberFormatException, IOException {

        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

        BufferedReader br = makeBufReader(fileName);

        String line = null;

        while ((line = br.readLine()) != null) {
            String[] edge = line.split(" ");
            mapVertices(map, edge, order);
        }
        br.close();

        int[][] edges = toArray(vertexN, map);

        return edges;
    }

    /**
     * Converts map representation of vertex connections to a 2-dimensional
     * array.
     * 
     * @param vertexN
     *            - number of vertices.
     * @param map
     *            map representation of vertex connections in the graph.
     * @return a 2-dimensional array of graph adjacent vertices.
     */
    private static int[][] toArray(int vertexN, Map<Integer, List<Integer>> map) {
        int[][] edges = new int[vertexN][];
        for (int i : map.keySet()) {
            List<Integer> list = map.get(i);
            Integer[] arr = list.toArray(new Integer[list.size()]);
            int[] arr2 = new int[arr.length];
            for (int j = 0; j < arr.length; j++) {
                arr2[j] = arr[j];
            }
            edges[i - 1] = arr2;
        }
        return edges;
    }

    /**
     * Parses string with information about 2 vertices and adds it to map.
     * 
     * @param map
     *            map representation of graph vertices.
     * @param edge
     *            vertices adjacent to a specific vertex.
     * @param order
     *            @see util.GraphOrder
     */
    private static void mapVertices(Map<Integer, List<Integer>> map,
            String[] edge, GraphOrder order) {
        int i, j;
        if (order == GraphOrder.REVERSED) {
            i = 1;
            j = 0;
        } else {
            i = 0;
            j = 1;
        }
        int key;
        List<Integer> l;
        key = Integer.parseInt(edge[i]);
        l = map.get(key);
        if (l == null) {
            l = new ArrayList<Integer>();
        }
        l.add(Integer.parseInt(edge[j]));
        map.put(key, l);
    }

    /**
     * Creates a BufferedReader.
     * 
     * @param fileName
     * @return a BufferedReader object.
     */
    private static BufferedReader makeBufReader(String fileName) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = null;
        if (fis != null) {
            br = new BufferedReader(new InputStreamReader(fis));
        }
        return br;
    }

    /**
     * Writes array to a file.
     * 
     * @param fileName
     *            Name of the file
     * @param source
     *            array with information.
     * @throws IOException
     */
    public static void write(String fileName, int[] source) throws IOException {
        BufferedWriter wr = new BufferedWriter(new FileWriter(fileName));
        String s = Arrays.toString(source);
        wr.write(s);
        wr.flush();
        wr.close();
    }

    public static void write(String fileName, Long[] source) throws IOException {
        BufferedWriter wr = new BufferedWriter(new FileWriter(fileName));
        String s = Arrays.toString(source);
        wr.write(s);
        wr.flush();
        wr.close();
    }

}
