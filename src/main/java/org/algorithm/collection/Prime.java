package org.algorithm.collection;

import java.util.Arrays;

/**
 * @author LinYongJin
 * @date 2020/4/22 21:24
 */
public class Prime {

    public static void main(String[] args) {
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
        graph.showWeight();
    }
}

class Graph {

    private int vertex;

    private char[] data;

    private int[][] weight;

    public Graph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }

    public Graph setData(char[] data) {
        this.data = data;
        return this;
    }

    public Graph setWeight(int[][] weight) {
        this.weight = weight;
        return this;
    }

    public void showWeight() {
        for (int[] ints : this.weight) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
