package org.datastructure.stack.expression;

import java.util.*;

/**
 * @author LinYongJin
 * @date 2020/2/3 15:39
 */
public class ExpressionCal {

    private boolean isDouble = false;

    private Stack<String> signStack;

    private Queue<String> queue;

    private Stack<Double> calStack;

    public ExpressionCal() {
        signStack = new Stack<>();
        queue = new LinkedList<>();
        calStack = new Stack<>();
    }

    public Double calculate(String expression) {
        infix2Suffix(expression);
        return calResult();
    }

    private void infix2Suffix(String expression) {
        // 1.将中缀表达式去掉多余的空格再放置到ArrayList中
        expression = expression.replace(" ", "");
        List<String> list = new ArrayList<>();
        int index = 0;
        // 2.对字符串进行遍历
        StringBuffer sb = new StringBuffer();
        while (index < expression.length()) {
            // 3.判断遍历到的元素是数字还是符号, 如果是数字继续后移index, 直到遇到非'.'的符号
            char ch = expression.charAt(index);
            while ((ch >= 48 && ch <= 57) || ch == 46) {
                sb.append(ch);
                index++;
                if (index >= expression.length()) {
                    break;
                }
                ch = expression.charAt(index);
            }
            // 4.将拼接的数字添加到集合中
            if (!"".equals(sb.toString())) {
                list.add(sb.toString());
            }
            // 5.清空StringBuffer
            sb.delete(0, sb.length());
            // 5如果index指向超出了长度直接结束循环
            if (index >= expression.length()) {
                break;
            }
            // 6.此时index指向的是符号, 添加到集合中
            list.add(String.valueOf(ch));
            index++;
        }
        System.out.print("表达式: ");
        for (String s : list) {
            System.out.print(s + " ");
        }
        System.out.println();
        toSuffix(list);
    }

    public static void main(String[] args) {
        ExpressionCal expressionCal = new ExpressionCal();
        Double calculate = expressionCal.calculate("3 * ( 6.9 + 9 ) / 4 + 10.5");
        System.out.println();
        System.out.println(calculate);
    }

    private void toSuffix(List<String> list) {
        // 1.遍历中缀表达式的集合
        for (String ele : list) {
            // 2. 判断时候为数字是就进入数字队列
            if (this.isNumber(ele)) {
                queue.add(ele);
            } else if (isOperator(ele)){
                // 3.如果是运算符则对运算符比较优先级
                // 3.1 如果栈为空或者栈顶元素是左括号则直接入栈
                if (signStack.size() == 0 || "(".equals(signStack.peek())) {
                    signStack.push(ele);
                } else {
                    // 3.2判断当前运算符的优先级与栈顶元素的优先级
                    if (this.getPriority(ele) - this.getPriority(signStack.peek()) > 0) {
                        // 3.3如果当前运算符的优先级高于栈顶运算符的优先级那么直接入栈
                        signStack.push(ele);
                    } else {
                        // 3.4如果小于等于栈顶运算符的优先级那么, 栈顶运算符出栈进入队列, 重复3.1
                        queue.add(signStack.pop());
                        while (signStack.size() != 0) {
                            // 3.5判断栈顶与当前元素的优先级, 如果当前元素优先级大或者栈顶元素是左括号结束循环, 如果小于等于栈顶优先级则栈顶元素出栈进队列
                            if ("(".equals(signStack.peek())) {
                                break;
                            } else {
                                if (this.getPriority(ele) - this.getPriority(signStack.peek()) <= 0) {
                                    queue.add(signStack.pop());
                                } else {
                                    break;
                                }
                            }
                        }
                        signStack.push(ele);
                    }
                }
            } else {
                // 4.如果是括号
                if ("(".equals(ele)) {
                    // 4.1如果是左括号直接压入栈顶
                    signStack.push(ele);
                } else {
                    // 4.2如果是有括号依次将栈内的元素出栈进入队列
                    while (!"(".equals(signStack.peek())) {
                        queue.add(signStack.pop());
                    }
                    // 4.3将左括号出栈
                    signStack.pop();
                }
            }
        }
        // 5.栈内元素依次出栈到队列中
        int size = signStack.size();
        System.out.print("逆波兰表达式: ");
        for (int i = 0; i < size; i++) {
            queue.add(signStack.pop());
        }
        for (String s : queue) {
            System.out.print(s + " ");
        }
    }

    private Double calResult() {
        for (String ele : queue) {
            // 判断是不是数字, 数字入栈
            if (this.isNumber(ele)) {
                calStack.push(Double.parseDouble(ele));
            } else {
                // 如果不是数字就是运算符, 将栈内的元素出栈两个进行计算, 如果是减法与除法, 需要后出栈的元素减去(除去)先出栈的元素
                Double first = calStack.pop();
                Double second = calStack.pop();
                switch (ele) {
                    case "+":
                        calStack.push(first + second);
                        break;
                    case "-":
                        calStack.push(second - first);
                        break;
                    case "*":
                        calStack.push(first * second);
                        break;
                    case "/":
                        calStack.push(second / first);
                        break;
                }
            }
        }
        return calStack.pop();
    }

    private boolean isNumber(String element) {
        if (element.length() > 1) {
            return true;
        } else {
            return element.charAt(0) >= 48 && element.charAt(0) <= 57;
        }
    }

    private boolean isOperator(String element) {
        return "+".equals(element) || "-".equals(element) || "/".equals(element) || "*".equals(element);
    }

    private int getPriority(String element) {
        if ("+".equals(element) || "-".equals(element)) {
            return 1;
        } else if ("*".equals(element) || "/".equals(element)){
            return 2;
        } else {
            throw new RuntimeException("非法的运算符");
        }
    }
}

