package org.algorithm.query;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LinYongJin
 * @date 2020/2/23 14:40
 */
public class QueryMain {

    @Test
    public void test() {
        int [] arr = {1, 2, 4, 9, 10, 10, 10, 12, 13, 23, 54, 65};
        System.out.println(binaryQuery(arr, 0, arr.length - 1, 10));
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
            return binaryQuery(arr, left, middle - 1, num);
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
}
