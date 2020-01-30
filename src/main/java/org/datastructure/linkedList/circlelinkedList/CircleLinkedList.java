package org.datastructure.linkedList.circlelinkedList;

import java.util.concurrent.TimeUnit;

/**
 * @author LinYongJin
 * @date 2020/1/30 16:21
 */
public class CircleLinkedList {

    private Child first;

    private int length;

    public CircleLinkedList() {}

    public CircleLinkedList(Child first) {
        this.first = first;
        first.next = first;
    }

    public void add(Child child) {
        if (first == null) {
            first = child;
            child.next = first;
            return;
        }
        Child temp = first;
        while (temp.next != first) {
            temp = temp.next;
        }
        temp.next = child;
        child.next = first;
        length++;
    }

    public void addByMuns(int nums) {
        Child end = null;
        for (int i = 1; i <= nums; i++) {
            if (i == 1) {
                first = new Child(i);
                end = first;
                continue;
            }
            Child child = new Child(i);
            end.next = child;
            end = child;
            end.next = first;
        }
        length = nums;
    }

    public void joseph(int startNo, int count, int nums) {
        if (startNo > nums || count == 1) {
            System.out.println("请输入正确的参数");
            return;
        }
        addByMuns(nums);
        //移动first致startNo
        for (int i = 1; i < startNo ; i++) {
            first = first.next;
        }
        int offset = 1;
        while (length > 1) {
            while (offset != count - 1) {
                first = first.next;
                offset++;
            }
            System.out.println("编号是: " + first.next.getNo() + "的学生出队");
            first.next = first.next.next;
            first = first.next;
            length--;
            offset = 1;
        }
        System.out.println("留在队中的学生是: " + first.getNo());
    }

    public void loopPrint() throws InterruptedException {
        Child temp = first;
        while (true) {
            System.out.println("学生编号: " + temp.getNo());
            temp = temp.next;
            TimeUnit.SECONDS.sleep(1);
        }
    }

    @Override
    public String toString() {
        Child temp = first;
        StringBuffer sb = new StringBuffer();
        while (temp.next != first) {
            sb.append(temp).append("\n");
            temp = temp.next;
        }
        sb.append(temp);
        return sb.toString();
    }
}
