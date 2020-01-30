package org.datastructure.stack.linkedliststack;

/**
 * @author LinYongJin
 * @date 2020/1/30 18:53
 */
public class Stack {

    private int maxSize;

    private SingleLinkedList list;

    public Stack() {
        this.maxSize = 5;
        list = new SingleLinkedList();
    }

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        list = new SingleLinkedList();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public boolean isFull() {
        return list.size() == maxSize;
    }

    public void push(int num) {
        if (isFull()) {
            System.out.println('满');
            return;
        }
        list.add(new Node(num));
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("空");
        }
        Node delete = list.delete();
        return delete.getValue();
    }

    @Override
    public String toString() {
        if (list.size() == 0) {
            return "栈空";
        }
        return list.toString();
    }
}
