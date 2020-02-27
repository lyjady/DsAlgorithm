package org.algorithm.query;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LinYongJin
 * @date 2020/2/23 14:40
 */
public class QueryMain {

    @Test
    public void test() {
        int [] arr = {1, 2, 4, 9, 10, 10, 10, 12, 13, 23, 54, 65};
        System.out.println(fibonacciQuery(arr, 65));
    }

    /**
     * 二分查找法
     *
     * @param arr 查找的数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param num 查找的元素
     */
    public List<Integer> binaryQuery(int[] arr, int left, int right, int num) {
        if (left > right) {
            return new ArrayList<>();
        }
        int middleIndex = (left + right) / 2;
        int middle = arr[middleIndex];
        if (num > middle) {
            // 向右递归
            return binaryQuery(arr, middleIndex + 1, right, num);
        } else if (num < middle) {
            // 向左递归
            return binaryQuery(arr, left, middleIndex - 1, num);
        } else {
            List<Integer> list = new ArrayList<>();
            int temp = middleIndex - 1;
            while (temp > 0 && arr[temp] == num) {
                list.add(temp);
                temp--;
            }
            list.add(middleIndex);
            temp = middleIndex + 1;
            while (temp < arr.length && arr[temp] == num) {
                list.add(temp);
                temp++;
            }
            return list;
        }
    }

    /**
     * 插值查找
     * @param arr
     * @param left
     * @param right
     * @param num
     * @return
     */
    public int insertQuery(int[] arr, int left, int right, int num) {
        if (left > right || num < arr[0] || num > arr[arr.length - 1]) {
            return -1;
        }
        int middleIndex = left + (right - left) * (num - arr[left]) / (arr[right] -arr[left]);
        int middleValue = arr[middleIndex];
        if (num < middleValue) {
            return insertQuery(arr, left, middleIndex - 1, num);
        } else if (num > middleValue) {
            return insertQuery(arr, middleIndex + 1, right, num);
        } else {
            return middleIndex;
        }
    }

    public int[] fibonacciSeq() {
        int[] seq = new int[20];
        seq[0] = 1;
        seq[1] = 1;
        for (int i = 2; i < seq.length; i++) {
            seq[i] = seq[i - 1] + seq[i - 2];
        }
        return seq;
    }

    /**
     * 斐波那契查找
     * @param arr
     */
    public int fibonacciQuery(int[] arr, int num) {
        int k = 0;
        int low = 0;
        int height = arr.length - 1;
        int[] fibonacciSeq = fibonacciSeq();
        // 斐波那契数列适配查找的数组
        while (height > fibonacciSeq[k] - 1) {
            k++;
        }
        // 被查找的数组适配斐波那契数列
        int[] temp = Arrays.copyOf(arr, fibonacciSeq[k]);
        for (int i = height + 1; i < temp.length; i++) {
            temp[i] = arr[height];
        }
        while (low <= height) {
            int mid = low + fibonacciSeq[k - 1] - 1;
            if (num < arr[mid]) {
                k--;
                height = mid - 1;
            } else if (num > arr[mid]) {
                k -= 2;
                low = mid + 1;
            } else {
                // 如果原数组被扩容, 并且查找的数正好是最大的也就是最后一个
                // 这样索引可能会越界, 此时直接返回height
                return Math.min(mid, height);
            }
        }
        return -1;
    }
}
