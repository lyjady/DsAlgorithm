package org.algorithm.collection;

import java.util.Arrays;

/**
 * @author LinYongJin
 * @date 2020/4/17 14:02
 */
public class DynamicProgramming {

    public static void main(String[] args) {
        int[] weight = {1, 4, 3};
        int[] value = {1500, 3000, 2000};
        int capacity = 4;
        int[][] v = new int[value.length + 1][capacity + 1];
        int[][] combination = new int[value.length + 1][capacity + 1];
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (weight[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    v[i][j] = Math.max(v[i - 1][j], value[i - 1] + v[i - 1][j - weight[i - 1]]);
                    combination[i][j] = 1;
                }
            }
        }
        for (int[] row : v) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
        int i = v.length - 1;
        int j = v[0].length - 1;
        while (i > 0 && j > 0) {
            if (combination[i][j] == 1) {
                System.out.println("第" + i + "个");
                j = j - weight[i - 1];
            }
            i--;
        }
    }
}
