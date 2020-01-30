package org.datastructure.stack.linkedliststack;

import org.datastructure.stack.arraystack.ArrayStack;

import java.util.Scanner;

/**
 * @author LinYongJin
 * @date 2020/1/30 19:10
 */
public class Main {
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示栈");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到栈");
            System.out.println("g(get): 从队栈出数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    System.out.println(stack);
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = stack.pop();
                        System.out.printf("取出的数据是%d\n", res);
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
