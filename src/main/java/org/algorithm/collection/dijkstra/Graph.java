package org.algorithm.collection.dijkstra;

import lombok.Data;

import java.util.Arrays;

/**
 * @author LinYongJin
 * @date 2020/4/26 15:42
 */
@Data
public class Graph {

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
