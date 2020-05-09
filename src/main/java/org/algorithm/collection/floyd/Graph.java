package org.algorithm.collection.floyd;

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

    private int[][] pre;

    public Graph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
        pre = new int[vertex][vertex];
        for (int i = 0; i < pre.length; i++) {
            Arrays.fill(pre[i], i);
        }
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

    public void show() {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.print(vertex[pre[i][j]] + "\t");
            }
            System.out.println();
            for (int j = 0; j < vertex.length; j++) {
                System.out.print("(" + vertex[i] + " -> " + vertex[j] + " : " + weight[i][j] + ")" + "\t");
            }
            System.out.println();
        }
    }
}
