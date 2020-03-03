package org.datastructure.hash;

/**
 * @author LinYongJin
 * @date 2020/3/3 20:58
 */
public class EmpLinkedList {

    private Employee head;

    private int length;

    public EmpLinkedList() {
    }

    public EmpLinkedList(Employee head) {
        this.head = head;
        this.length++;
    }

    public int size() {
        return length;
    }

    public void add(Employee employee) {
        if (length == 0) {
            head = employee;
            this.length++;
            return;
        }
        Employee temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = employee;
        this.length++;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Employee temp = head;
        while (temp.next != null) {
            sb.append(temp).append(", ");
            temp = temp.next;
        }
        sb.append(temp);
        return sb.toString();
    }

    public void select(Integer id) {
        if (length == 0) {
            System.out.println("链表为空");
            return;
        }
        Employee temp = head;
        while (temp.next != null) {
            if (temp.getId().equals(id)) {
                System.out.println("Employee: " + temp);
                return;
            }
            temp = temp.next;
        }
        if (temp.getId().equals(id)) {
            System.out.println("Employee: " + temp);
            return;
        }
        System.out.println("没有该员工");
    }

    public void delete(Integer id) {
        if (length == 0) {
            System.out.println("链表为空");
            return;
        }
        if (head.getId().equals(id)) {
            head = head.next;
            this.length--;
            return;
        }
        Employee temp = head;
        while (temp.next != null) {
            if (temp.next.getId().equals(id)) {
                temp.next = temp.next.next;
                this.length--;
                System.out.println("删除Employee: " + temp.next);
                return;
            }
            temp = temp.next;
        }
    }
}
