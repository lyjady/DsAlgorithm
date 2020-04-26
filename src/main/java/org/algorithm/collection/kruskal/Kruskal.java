package org.algorithm.collection.kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author LinYongJin
 * @date 2020/4/26 16:12
 */
public class Kruskal {

    private static final Integer INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Graph graph = init();
        Edge[] edges = generateEdgeArr(graph);
        System.out.println(Arrays.toString(edges));
    }

    public static Graph init() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        Graph graph = new Graph(vertex.length);
        graph.setData(vertex);
        int[][] weight = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
        graph.setWeight(weight);
        return graph;
    }

    public static Edge[] generateEdgeArr(Graph graph) {
        List<Edge> edgeList = new ArrayList<>();
        int[][] weight = graph.getWeight();
        for (int i = 0; i < weight.length; i++) {
            for (int j = i + 1; j < weight.length; j++) {
                if (weight[i][j] != INF) {
                    edgeList.add(new Edge(graph.getData()[i], graph.getData()[j], weight[i][j]));
                }
            }
        }
        edgeList.sort(Comparator.comparingInt(Edge::getWeight));
        return edgeList.toArray(new Edge[0]);
    }
}
