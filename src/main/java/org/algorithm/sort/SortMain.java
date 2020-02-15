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
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test80000() {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long startTime = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long endTime = System.currentTimeMillis();
        System.out.println("费时: " + (endTime - startTime) + "ms");
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

    /**
     * 插入排序
     *
     * @param arr 待排序的数组
     */
    public void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int sortedVal = arr[i];
            for (int j = i; j > 0; j--) {
                if (sortedVal > arr[j - 1]) {
                    arr[j] = sortedVal;
                    break;
                } else if (j == 1) {
                    arr[j] = arr[j - 1];
                    arr[0] = sortedVal;
                } else {
                    arr[j] = arr[j - 1];
                }
            }
        }
    }

    /**
     * 希尔排序(交换法)
     *
     * @param arr 待排序的数组
     */
    public void shellSortExchange(int[] arr) {
        int temp = 0;
        for (int step = arr.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arr.length; i++) {
                for (int j = i - step; j >= 0; j -= step) {
                    if (arr[j] > arr[j + step]) {
                        temp = arr[j + step];
                        arr[j + step] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
    }

    /**
     * 希尔排序(位移法)
     *
     * @param arr 待排序的数组
     */
    public void shellSortDisplacement(int[] arr) {
        for (int step = arr.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arr.length; i++) {
                // sortedVal: 待比较的数
                int sortedVal = arr[i];
                for (int j = i - step; j >= 0; j -= step) {
                    // arr[j]: 是待比较的数前一个步长的数
                    if (sortedVal > arr[j]) {
                        arr[j + step] = sortedVal;
                        break;
                    } else if (j - 2 * step <= 0) {
                        arr[j + step] = arr[j];
                        arr[j] = sortedVal;
                    } else {
                        arr[j + step] = arr[j];
                    }
                }
            }
        }
    }

    /**
     * 快速排序
     *
     * @param arr        待排序的数组
     * @param leftIndex  左边的索引
     * @param rightIndex 右边的索引
     */
    public void quickSort(int[] arr, int leftIndex, int rightIndex) {
        int tempLeft = leftIndex;
        int tempRight = rightIndex;
        int temp;
        int center = arr[(tempLeft + tempRight) / 2];
        while (true) {
            while (arr[tempLeft] < center) {
                tempLeft++;
            }
            while (arr[tempRight] > center) {
                tempRight--;
            }
            if (tempLeft >= tempRight) {
                break;
            }
            temp = arr[tempLeft];
            arr[tempLeft] = arr[tempRight];
            arr[tempRight] = temp;
            // 数组中有相同的值没有如下判断将会造成死循环, 因为此时这两个变量都指向了相同的值并且参考值就是该值
            // 此时上面的while判断都将不会执行, 两个变量不会移动, if (tempLeft >= tempRight) 这个判断永远不会成立
            // 所以需要如下判断来迫使变量发生移动
            if (arr[tempLeft] == center) {
                tempRight--;
            }
            if (arr[tempRight] == center) {
                tempLeft++;
            }
        }
        // 左递归
        if (leftIndex < tempRight) {
            quickSort(arr, leftIndex, tempRight - 1);
        }
        // 右递归
        if (rightIndex > tempLeft) {
            quickSort(arr, tempLeft + 1, rightIndex);
        }
    }
}




















