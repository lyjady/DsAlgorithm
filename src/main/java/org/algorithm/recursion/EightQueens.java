package org.algorithm.recursion;

import java.util.Map;

/**
 * @author LinYongJin
 * @date 2020/2/7 16:39
 */
public class EightQueens {

    private static int[] sequence = new int[8];

    private static int count = 0;

    public static void main(String[] args) {
        start(0);
        System.out.println("总次数: " + count);
    }

    private static void start(int row) {
        if (row >= sequence.length) {
            print();
            return;
        }
        for (int col = 0; col < sequence.length; col++) {
            sequence[row] = col;
            if (judgment(row)) {
                start(row + 1);
            }
        }
    }

    private static boolean judgment(int row) {
        for (int i = 0; i < row; i++) {
            if (sequence[row] == sequence[i] || Math.abs(row - i) == Math.abs(sequence[i] - sequence[row])) {
                return false;
            }
        }
        return true;
    }

    private static void print() {
        count++;
        for (int num : sequence) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
