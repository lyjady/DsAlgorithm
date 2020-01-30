package org.datastructure.stack.arraystack;

/**
 * @author LinYongJin
 * @date 2020/1/30 18:00
 */
public class ArrayStack {

    private int[] array;

    private int maxSize;

    private int offset = -1;

    public ArrayStack() {
        this.maxSize = 10;
        array = new int[maxSize];
    }

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    public boolean isEmpty() {
        return offset == -1;
    }

    public boolean isFull() {
        return offset == maxSize - 1;
    }

    public void push(int num) {
        if (isFull()) {
            System.out.println("栈已满");
            return;
        }
        array[++offset] = num;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈是空");
        }
        return array[offset--];
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = offset; i >= 0 ; i--) {
            sb.append("array[" + i + "] = " + array[i]).append("\n");
        }
        return sb.toString();
    }
}
