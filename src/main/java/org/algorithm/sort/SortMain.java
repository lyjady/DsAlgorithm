package org.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * @author LinYongJin
 * @date 2020/2/11 16:31
 */
public class SortMain {

    @Test
    public void test() {
        int[] arr = {231, 32, 32, 123, 334, 1, 6, 34};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test80000() {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long startTime = System.currentTimeMillis();
        radixSort(arr);
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

    /**
     * 归并排序(分)
     *
     * @param arr   数组
     * @param left  要分的数组的左边的索引
     * @param right 要分的数组右边的索引
     */
    public void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int middle = (left + right) / 2;
            // 向左分治
            mergeSort(arr, left, middle, temp);
            // 向右分治
            mergeSort(arr, middle + 1, right, temp);
            // 合并
            merge(arr, left, middle, right, temp);
        }
    }

    /**
     * 归并排序将分的合并
     *
     * @param left   左边有序数列的初始器=索引
     * @param middle 中间的索引
     * @param right  右边有序数列的索引最大值
     * @param temp   临时数组
     */
    public void merge(int[] arr, int left, int middle, int right, int[] temp) {
        int tempLeftIndex = left;
        // 右边有序数列的索引最小值
        int tempRightIndex = middle + 1;
        // 临时数组的初始索引
        int tempIndex = 0;
        // 两个有序数列进行比较, 将大的那个放到临时数组中去。一旦左边的索引大于中间的或者右边的索引大于最右边的那就说明有一边已经比较完了
        while (tempLeftIndex <= middle && tempRightIndex <= right) {
            if (arr[tempLeftIndex] > arr[tempRightIndex]) {
                // 如果左边的数大于右边的数那么将左边的数放到临时数组中
                // 左边的临时索引+1
                temp[tempIndex++] = arr[tempRightIndex++];
            } else {
                // 反之亦然
                temp[tempIndex++] = arr[tempLeftIndex++];
            }
        }
        if (tempLeftIndex > middle) {
            // 说明右边的有序数列还剩下, 将剩下的全部放置到临时数组中
            while (tempRightIndex <= right) {
                temp[tempIndex++] = arr[tempRightIndex++];
            }
        } else {
            // 反之亦然
            while (tempLeftIndex <= middle) {
                temp[tempIndex++] = arr[tempLeftIndex++];
            }
        }
        // 将临时数组拷贝到原数组中去
        int tempStart = left;
        int t = 0;
        while (tempStart <= right) {
            arr[tempStart++] = temp[t++];
        }
    }

    /**
     * 基数排序
     *
     * @param arr 待排序的数组
     */
    public void radixSort(int[] arr) {
        // 桶二维数组
        int[][] bucket = new int[10][arr.length];
        // 创建一个数组保存桶中相应的元素个数
        int[] validCount = new int[10];
        // 取得数组中最大的那个数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        for (int i = 0; i < String.valueOf(max).length(); i++) {
            for (int j = 0; j < arr.length; j++) {
                int digit = (int) (arr[j] / Math.pow(10, i) % 10);
                // 向指定的桶中放元素, 并将存放有效长度的数组中对应的元素+1
                bucket[digit][validCount[digit]++] = arr[j];
            }
            // 按顺序取出桶中的元素放置到原数组中去, 并重置validaCount
            int index = 0;
            for (int j = 0; j < bucket.length; j++) {
                int validaCountIndex = 0;
                for (int k = 0; k < validCount[j]; k++) {
                    arr[index++] = bucket[j][validaCountIndex++];
                }
                validCount[j] = 0;
            }
        }
    }
}




















