package org.datastructure.hash;

/**
 * @author LinYongJin
 * @date 2020/3/3 21:17
 */
public class HashTable {

    private EmpLinkedList[] empLinkedLists;

    private int length;

    public HashTable() {
        this(10);
        init();
    }

    public HashTable(int length) {
        empLinkedLists = new EmpLinkedList[length];
        this.length = length;
        init();
    }

    private void init() {
        for (int i = 0; i < length; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public void put(Employee employee) {
        int index = employee.getId() % length;
        EmpLinkedList empLinkedList = empLinkedLists[index];
        empLinkedList.add(employee);
    }

    public void list() {
        for (int i = 0; i < length; i++) {
            EmpLinkedList empLinkedList = empLinkedLists[i];
            if (empLinkedList.size() == 0) {
                System.out.printf("第%d条链表为空", i + 1);
                System.out.println();
                continue;
            }
            String emps = empLinkedList.toString();
            System.out.println("第" + (i + 1) +"条链表: " + emps);
        }
    }

    public void select(Integer id) {
        int index = id % length;
        EmpLinkedList empLinkedList = empLinkedLists[index];
        empLinkedList.select(id);
    }

    public void delete(Integer id) {
        int index = id % length;
        EmpLinkedList empLinkedList = empLinkedLists[index];
        empLinkedList.delete(id);
    }
}
