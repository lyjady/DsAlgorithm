package org.algorithm.collection;

import java.util.Arrays;

/**
 * @author LinYongJin
 * @date 2020/4/17 21:14
 */
public class KMP {

    public static void main(String[] args) {
        String motherStr = "BBC ABCDAB ABCDABCDABDE";
        String sonStr =              "ABCDABD";
        int[] next = generateNext(sonStr);
        System.out.println(KMPSearch(motherStr, sonStr, next));
    }

    private static int KMPSearch(String motherStr, String sonStr, int[] next) {
        int j = 0;
        int step = 0;
        int originI = 0;
        for (int i = 0; i < motherStr.length(); i++) {
            if (motherStr.charAt(i) == sonStr.charAt(j)) {
                if (j == 0) {
                    originI = i;
                }
                j++;
                step++;
            } else {
                if (j > 0) {
                    int move = step - next[j - 1];
                    i = move == 0 ? i : originI + move - 1;
                    step = 0;
                    j = 0;
                }
            }
            if (j == sonStr.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    private static int[] generateNext(String sonStr) {
        int[] next = new int[sonStr.length()];
        next[0] = 0;
        int j = 0;
        for (int i = 1; i < sonStr.length(); i++) {
            if (j > 0 && sonStr.charAt(i) != sonStr.charAt(j)) {
                j = next[j  -1];
            }
            if (sonStr.charAt(i) == sonStr.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
