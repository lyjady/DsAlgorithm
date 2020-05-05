package org.algorithm.collection.floyd;

/**
 * @author LinYongJin
 * @date 2020/5/5 15:02
 */
public class Floyd {

    public static void main(String[] args) {
        Graph graph = init();
        floyd(graph);
        graph.show();
    }

    private static void floyd(Graph graph) {
        int[][] dis = graph.getWeight();
        int[][] pre = graph.getPre();
        // 中间节点
        for (int i = 0; i < graph.getVertex(); i++) {
            // 出发节点
            for (int j = 0; j < graph.getVertex(); j++) {
                // 终点
                for (int k = 0; k < graph.getVertex(); k++) {
                    // 求出当前节点到中间节点及中间节点到终点的距离的和
                    int distance = dis[j][i] + dis[i][k];
                    // 与当前存储的距离相比较设置较小的值
                    if (distance < dis[j][k]) {
                        dis[j][k] = distance;
                        pre[j][k] = pre[i][k];
                    }
                }
            }
        }
    }

    private static Graph init() {
        char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[7][];
        final int n = 65535;
        matrix[0] = new int[] { 0, 5, 7, n, n, n, 2 };
        matrix[1] = new int[] { 5, 0, n, 9, n, n, 3 };
        matrix[2] = new int[] { 7, n, 0, n, 8, n, n };
        matrix[3] = new int[] { n, 9, n, 0, n, 4, n };
        matrix[4] = new int[] { n, n, 8, n, 0, 5, 4 };
        matrix[5] = new int[] { n, n, n, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, n, n, 4, 6, 0 };
        Graph graph = new Graph(vertex.length);
        graph.setData(vertex);
        graph.setWeight(matrix);
        return graph;
    }
}
