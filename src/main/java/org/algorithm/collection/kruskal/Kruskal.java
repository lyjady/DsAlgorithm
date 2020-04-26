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
        Edge[] minTree = generateMinTree(edges, graph.getVertex() - 1, graph);
        for (Edge edge : minTree) {
            System.out.printf("%c -> %c = %d", edge.getStart(), edge.getEnd(), edge.getWeight());
            System.out.println();
        }
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

    private static Edge[] generateMinTree(Edge[] edges, int vertex, Graph graph) {
        Edge[] minTree = new Edge[vertex];
        // 保存顶点所对应的的终点的索引
        int[] ends = new int[vertex + 1];
        List<Edge> minTreeList = new ArrayList<>();
        for (Edge edge : edges) {
            // 判断当前边的两个顶点的终点是否一样
            int start = getVertexIndex(edge.getStart(), graph);
            int end = getVertexIndex(edge.getEnd(), graph);
            // 计算出start和end的终点
            int f1 = getEnds(ends, start);
            int f2 = getEnds(ends, end);
            if (f1 != f2) {
                // 不等则说明将当前节点加入到最小生成树的集合中不会形成回路
                minTreeList.add(edge);
                // 设置当前边的两个节点的终点
                ends[f1] = f2;
            }
        }
        return minTreeList.toArray(minTree);
    }

    private static int getVertexIndex(char vertex, Graph graph) {
        for (int i = 0; i < graph.getData().length; i++) {
            if (graph.getData()[i] == vertex) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 等到当前节点的所对应的终点的索引
     *
     * @param ends  保存着顶点的对应终点的索引
     * @param index  当前节点的索引
     * @return
     */
    private static int getEnds(int[] ends, int index) {
        while (ends[index] != 0) {
            index = ends[index];
        }
        return index;
    }
}
