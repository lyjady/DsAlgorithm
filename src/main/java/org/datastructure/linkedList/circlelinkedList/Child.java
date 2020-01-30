package org.datastructure.linkedList.circlelinkedList;

/**
 * @author LinYongJin
 * @date 2020/1/30 16:24
 */
public class Child {

    private int no;

    public Child next;

    public Child(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Child{" +
                "no=" + no +
                '}';
    }
}
