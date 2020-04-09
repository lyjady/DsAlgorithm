package org.datastructure.graph;

import java.util.*;

/**
 * @author LinYongJin
 * @date 2020/4/9 10:59
 */
public class Graph {

    /**
     * 顶点列表
     */
    private List<String> vertexes;

    /**
     * 图的边的数量
     */
    private int edgeNumber = 0;

    /**
     * 图的路径
     */
    private int[][] path;

    /**
     * 节点是否访问的数组
     */
    private boolean[] isVisited;

    public Graph(int vertexNumber) {
        path = new int[vertexNumber][vertexNumber];
        vertexes = new ArrayList<>();
        isVisited = new boolean[vertexNumber];
    }

    /**
     * 添加顶点
     *
     * @param vertex
     */
    public void addVertex(String vertex) {
        vertexes.add(vertex);
    }


    /**
     * 添加路径
     *
     * @param originVertex
     * @param destinationVertex
     * @param weight
     */
    public void addPath(int originVertex, int destinationVertex, int weight) {
        path[originVertex][destinationVertex] = weight;
        path[destinationVertex][originVertex] = weight;
        edgeNumber++;
    }

    public int getEdgeNumber() {
        return edgeNumber;
    }

    public int getVertexNumber() {
        return vertexes.size();
    }

    public String getVertex(int index) {
        return vertexes.get(index);
    }

    public void printPath() {
        for (int[] row : path) {
            for (int column : row) {
                System.out.print(column + "  ");
            }
            System.out.println();
        }
    }

    private void dfs(int root) {
        System.out.print(getVertex(root) + " --> ");
        isVisited[root] = true;
        // 得到当前顶点的第一个邻接顶点
        int next = getFirstNeighbor(root);
        while (next != -1) {
            if (!isVisited[next]) {
                // 该顶点没有被访问过
                isVisited[next] = true;
                dfs(next);
            } else {
                next = getNextNeighbor(root, next);
            }
        }
    }

    public void dfs() {
        for (int i = 0; i < vertexes.size(); i++) {
            if (!isVisited[i]) {
                dfs(i);
            }
        }
        isVisited = new boolean[vertexes.size()];
    }

    private void bfs(int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Integer head = queue.poll();
            System.out.print(getVertex(head) + " --> ");
            isVisited[head] = true;
            int next = getFirstNeighbor(head);
            while (next != -1) {
                if (!isVisited[next]) {
                    queue.add(next);
                    isVisited[next] = true;
                }
                next = getNextNeighbor(head, next);
            }
        }
    }

    public void bfs() {
        for (int i = 0; i < vertexes.size(); i++) {
            if (!isVisited[i]) {
                bfs(i);
            }
        }
        isVisited = new boolean[vertexes.size()];
    }

    /**
     * 得到第一个指定顶点的第一个邻接顶点
     *
     * @param vertex
     * @return
     */
    private int getFirstNeighbor(int vertex) {
        for (int i = 0; i < path[vertex].length; i++) {
            if (path[vertex][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 得到vertex这个顶点的邻接顶点sonVertex的下一个邻接顶点
     *
     * @param vertex
     * @param sonVertex
     * @return
     */
    private int getNextNeighbor(int vertex, int sonVertex) {
        for (int i = sonVertex + 1; i < path[vertex].length; i++) {
            if (path[vertex][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        Arrays.stream(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"}).forEach(graph::addVertex);
        graph.addPath(0, 1, 1);
        graph.addPath(0, 2, 1);
        graph.addPath(1, 3, 1);
        graph.addPath(1, 4, 1);
        graph.addPath(3, 7, 1);
        graph.addPath(4, 7, 1);
        graph.addPath(2, 5, 1);
        graph.addPath(2, 6, 1);
        graph.addPath(5, 6, 1);
        graph.dfs();
        System.out.println();
        graph.bfs();
    }
}
