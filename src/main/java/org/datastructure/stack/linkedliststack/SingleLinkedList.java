package org.datastructure.stack.linkedliststack;

import org.datastructure.linkedList.singlelinkedList.Country;

import java.util.Stack;

/**
 * @author LinYongJin
 * @date 2020/1/27 15:01
 */
public class SingleLinkedList {

    private Node head;

    private int length;

    public SingleLinkedList() {
        this.length = 0;
        head = new Node();
    }

    /**
     * 添加节点
     * @param node 将要添加的节点
     */
    public void add(Node node) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        length++;
    }

    /**
     * 删除链表
     */
    public Node delete() {
        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        Node node = temp.next;
        temp.next = null;
        length--;
        return node;
    }

    /**
     * 得到第一个节点
     * @return
     */
    public Node linkedFirst() {
        return head.next;
    }

    /**
     * 获取链表长度
     * @return
     */
    public int size() {
        return length;
    }

    @Override
    public String toString() {
        Node temp = head.next;
        StringBuffer sb = new StringBuffer();
        while (temp != null) {
            sb.append(temp).append("\n");
            temp = temp.next;
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }
}
