package org.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author LinYongJin
 * @date 2020/2/11 16:31
 */
public class SortMain {

    @Test
    public void test() {
        int[] arr = {231, 32, 32, 123, 334, 1, 6, 34};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test80000() {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long startTime = System.currentTimeMillis();
        selectSort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println("费时: " + (endTime - startTime));
    }

    /**
     * 冒泡排序
     *
     * @param arr 待排序的数组
     */
    public void bubbleSort(int[] arr) {
        int temp;
        int count = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                count++;
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }

    /**
     * 选择排序
     *
     * @param arr 待排序的数组
     */
    public void selectSort(int[] arr) {
        int minTemp;
        int index;
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            // 将第一个数设置为临时的最小值并纪律下索引
            minTemp = arr[i];
            index = i;
            for (int j = i; j < arr.length - 1; j++) {
                // 如果有数小于零时的最小值将值与索引进行替换
                if (minTemp > arr[j + 1]) {
                    minTemp = arr[j + 1];
                    index = j + 1;
                }
            }
            // 此时maxTemp和index记录着剩下元素中的最小值与最小值的索引, 与当前的i进行替换
            temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }
}




















