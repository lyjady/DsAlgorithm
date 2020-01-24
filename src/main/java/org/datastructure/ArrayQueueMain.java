package org.datastructure;

/**
 * @author LinYongJin
 * @date 2020/1/24 13:12
 */
public class ArrayQueueMain {
    public static void main(String[] args) {

    }
}

class ArrayQueue {

    //队列的最大长度
    private int maxSize;

    //指向队尾的最后一个数据
    private int rear;

    //指向队首的前一个位置
    private int font;

    private int[] array;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        font = -1;
        rear = -1;
        array = new int[maxSize];
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return font == rear;
    }
}
