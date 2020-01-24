package org.datastructure.queue;

import java.util.Scanner;

/**
 * @author LinYongJin
 * @date 2020/1/24 13:12
 */
public class ArrayQueueMain {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.printArray();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.push(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.pop();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.getHead();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~");
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

    private boolean isFull() {
        return font == rear;
    }

    private boolean isEmpty() {
        return rear == maxSize - 1;
    }

    public void push(int num) {
        if (isEmpty()) {
            System.out.println("队列已满, 无法添加数据");
            return;
        }
        array[++rear] = num;
    }

    public int pop() {
        if (isFull()) {
            throw new RuntimeException("队列空无法取出数据");
        }
        return array[++font];
    }

    public void printArray() {
        for (int i = 0; i < maxSize; i++) {
            System.out.printf("arr[%d]=%d\n", i, array[i]);
        }
    }

    public int getHead() {
        if (isFull()) {
            throw new RuntimeException("队列空无法取出数据");
        }
        return array[font + 1];
    }
}
