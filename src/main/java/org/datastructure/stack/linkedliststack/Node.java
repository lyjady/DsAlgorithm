package org.datastructure.stack.linkedliststack;

/**
 * @author LinYongJin
 * @date 2020/1/30 18:46
 */
public class Node {

    private int value;

    public Node next;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
