package org.datastructure.tree.binarysorttree;

/**
 * @author LinYongJin
 * @date 2020/3/27 21:12
 */
public class BinarySortTreeMain {

    /**
     * 树的结构
     *          7
     *        /  \
     *      5     9
     *    /  \   / \
     *   3    6 8   10
     *    中序遍历: 3 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
     */
    public static void main(String[] args) {
        Node[] heroes = {new Node(7, "Jaina"), new Node(9, "Tyrande"),
                new Node(5, "Sylvanas"), new Node(3, "Whitemane"),
                new Node(8, "Onyxia"), new Node(10, "Isera"), new Node(6, "Aleksassa")};
        BinarySortTree binarySortTree = new BinarySortTree();
        binarySortTree.create(heroes);
        binarySortTree.middleOrder();
        System.out.println("-------------------------");
        System.out.println(binarySortTree.middleSearch(1));
        System.out.println("-------------------------");
        binarySortTree.delete(9);
        binarySortTree.delete(7);
        binarySortTree.delete(10);
        binarySortTree.delete(8);
        binarySortTree.delete(6);
        binarySortTree.delete(3);
        binarySortTree.delete(5);
    }
}
