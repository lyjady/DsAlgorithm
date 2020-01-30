package org.datastructure.linkedList.singlelinkedList;

/**
 * @author LinYongJin
 * @date 2020/1/27 15:10
 */
public class SingleLinkedListMain {

    public static void main(String[] args) {
        SingleLinkedList linked = new SingleLinkedList();
        Country russia = new Country(1, "俄罗斯", "俄");
        Country canada = new Country(2, "加拿大", "加");
        Country china = new Country(3, "中国", "中");
        Country american = new Country(4, "美国", "美");

        SingleLinkedList linked2 = new SingleLinkedList();
        Country brazil = new Country(5, "巴西", "巴");
        Country australia = new Country(6, "澳大利亚", "澳");
        Country india = new Country(7, "印度", "印");

        linked.orderAdd(american);
        linked.orderAdd(china);
        linked.orderAdd(canada);
        linked.orderAdd(russia);

        linked2.add(india);
        linked2.add(brazil);
        linked2.add(australia);

        System.out.println("-------------------------------------------------");
        System.out.println(linked.size());
        System.out.println(linked);
        System.out.println("-------------------------------------------------");
        linked.update(new Country(4, "美利坚共和国", "美利坚"));
        System.out.println(linked);
        System.out.println("-------------------------------------------------");
        linked.delete(1);
        System.out.println(linked);
        System.out.println("-------------------------------------------------");
        System.out.println(linked.get(4));
        System.out.println("-------------------------------------------------");
        System.out.println(linked.getReciprocal(3));
        System.out.println("-------------------------------------------------");
        System.out.println(linked);
        System.out.println("-------------------------------------------------");
        linked.reverse();
        System.out.println(linked);
        System.out.println("-------------------------------------------------");
        System.out.println("反向打印");
        linked.reversePrint();
        System.out.println("-------------------------------------------------");
        linked.orderAddAll(linked2);
        System.out.println(linked);
        System.out.println("-------------------------------------------------");
    }
}
