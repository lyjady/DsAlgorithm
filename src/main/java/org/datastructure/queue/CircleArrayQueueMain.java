package org.datastructure.queue;

import java.util.Scanner;

/**
 * @author LinYongJin
 * @date 2020/1/24 19:12
 */
public class CircleArrayQueueMain {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(4);
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
                    queue.print();
                    break;
                case 'a':
                    System.out.println("输入一个数");
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

class CircleArrayQueue {

    //队列的最大长度
    private int maxSize;

    //指向队尾的最后一个数据的后一个位置
    private int rear;

    //指向队首的第一个数据
    private int font;

    private int[] array;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        font = 0;
        rear = 0;
        array = new int[maxSize];
    }

    private boolean isEmpty() {
        return rear == font;
    }

    private boolean isFull() {
        return (rear + 1) % maxSize == font;
    }

    private int size() {
        return (rear + maxSize - font) % maxSize;
    }

    public void push(int num) {
        if (isFull()) {
            System.out.println("队列已满, 无法添加数据");
            return;
        }
        array[rear] = num;
        rear = (rear + 1) % maxSize;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空, 无法出队");
        }
        int temp = array[font];
        font = (font + 1) % maxSize;
        return temp;
    }

    public void print() {
        for (int i = font; i < font + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, array[i % maxSize]);
        }
    }

    public int getHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空, 无法出队");
        }
        return array[font];
    }
}
