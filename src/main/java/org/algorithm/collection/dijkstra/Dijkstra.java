package org.algorithm.collection.dijkstra;

/**
 * @author LinYongJin
 * @date 2020/4/30 20:51
 */
public class Dijkstra {

    public static void main(String[] args) {
        Graph graph = init();
        graph.showWeight();
        VisitedVertex visitedVertex = new VisitedVertex(graph.getVertex(), 6);
        dijkstra(graph, visitedVertex, 6);
    }

    private static Graph init() {
        char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[7][];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        Graph graph = new Graph(vertex.length);
        graph.setData(vertex);
        graph.setWeight(matrix);
        return graph;
    }

    /**
     * 迪杰斯特拉算法
     *
     * @param graph         图
     * @param visitedVertex 辅助对象
     * @param currentIndex    当前顶点的索引
     */
    private static void dijkstra(Graph graph, VisitedVertex visitedVertex, int currentIndex) {
        int[][] matrix = graph.getWeight();
        // 遍历邻接矩阵中当前顶点所在的行, 那行的数据就是当前顶点到其他顶点的距离
        for (int i = 0; i < matrix[currentIndex].length; i++) {
            // i此时就是当前节点的即将遍历的邻接节点
            // distance是初始节点到i这个几点的距离, distance = currentIndex到i的距离 + 初始节点到currentIndex的距离
            int distance = matrix[currentIndex][i] + visitedVertex.getDis(currentIndex);
            // 将distance与dis数组中初始节点到i的距离(这个距离是初始节点到上一个节点再到i的距离)比较
            if (!visitedVertex.isVisited(i) && distance < visitedVertex.getDis(i)) {
                visitedVertex.updateDis(i, distance);
                visitedVertex.updatePre(i, currentIndex);
            }
        }
    }
}
