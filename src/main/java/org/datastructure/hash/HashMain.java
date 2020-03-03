package org.datastructure.hash;

import java.util.Scanner;

/**
 * @author LinYongJin
 * @date 2020/3/3 21:28
 */
public class HashMain {

    public static void main(String[] args) {
        HashTable table = new HashTable();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-------------------------");
            System.out.println("list  :       打印链表");
            System.out.println("put   :       添加元素");
            System.out.println("delete:       删除元素");
            System.out.println("select:       查询元素");
            System.out.println("-------------------------");
            String opt = scanner.next();
            switch (opt) {
                case "put":
                    int id = scanner.nextInt();
                    String name = scanner.next();
                    Employee employee = new Employee(id, name);
                    table.put(employee);
                    break;
                case "list":
                    table.list();
                    break;
                case "select":
                    int selectId = scanner.nextInt();
                    table.select(selectId);
                    break;
                case "delete":
                    int deleteId = scanner.nextInt();
                    table.delete(deleteId);
                    break;
            }
        }
    }
}
