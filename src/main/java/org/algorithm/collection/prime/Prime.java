package org.algorithm.collection.prime;

import java.util.Arrays;

/**
 * @author LinYongJin
 * @date 2020/4/22 21:24
 */
public class Prime {

    public static void main(String[] args) {
        Graph graph = init();
        createMinTree(graph, 0);
    }

    private static Graph init() {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        Graph graph = new Graph(data.length);
        graph.setData(data);
        int[][] weight = {
                {0, 5, 7, 0, 0, 0, 2},
                {5, 0, 0, 9, 0, 0, 3},
                {7, 0, 0, 0, 8, 0, 0},
                {0, 9, 0, 0, 0, 4, 0},
                {0, 0, 8, 0, 0, 5, 4},
                {0, 0, 0, 4, 5, 0, 6},
                {2, 3, 0, 0, 4, 6, 0}
        };
        graph.setWeight(weight);
        return graph;
    }

    public static void createMinTree(Graph graph, int v) {
        // 定义一个数组, 保存当前节点是否被访问过
        boolean[] visited = new boolean[graph.getVertex()];
        visited[v] = true;
        int minWeight = Integer.MAX_VALUE;
        int[][] weight = graph.getWeight();
        // 出发节点的索引
        int outSetIndex = -1;
        // 到达节点的索引
        int getToIndex = -1;
        for (int i = 1; i < graph.getVertex(); i++) {
            // 出发的节点
            for (int j = 0; j < graph.getVertex(); j++) {
                // 到达的节点
                for (int k = 0; k < graph.getVertex(); k++) {
                    // 判断出发的节点是不是已经访问过的, 目标节点是不是为访问过, 并且他么之间的权重最小
                    if (visited[j] && !visited[k] && weight[j][k] != 0 && weight[j][k] < minWeight) {
                        minWeight = weight[j][k];
                        outSetIndex = j;
                        getToIndex = k;
                    }
                }
            }
            // 将getToIndex标记为一被访问
            visited[getToIndex] = true;
            // 重置最小权重
            minWeight = Integer.MAX_VALUE;
            System.out.printf("%c -> %c, weight: %d", graph.getData()[outSetIndex], graph.getData()[getToIndex], graph.getWeight()[outSetIndex][getToIndex]);
            System.out.println();
        }
    }
}
