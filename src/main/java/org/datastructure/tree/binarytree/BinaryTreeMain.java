package org.datastructure.tree.binarytree;

/**
 * @author LinYongJin
 * @date 2020/3/6 20:25
 */
public class BinaryTreeMain {

    /**
     * 树的结构
     *          1
     *        /  \
     *      2     3
     *     /  \    \
     *    4   5     6
     *    前序遍历: 1 -> 2 -> 4 -> 5 -> 3 -> 6
     *    中序遍历: 4 -> 2 -> 5 -> 1 -> 3 -> 6
     *    后序遍历: 4 -> 5 -> 2 -> 6 -> 3 -> 1
     */
    public static void main(String[] args) {
        Harem h1 = new Harem(1, "吉安娜");
        Harem h2 = new Harem(2, "泰兰德");
        Harem h3 = new Harem(3, "斯尔瓦娜斯");
        Harem h4 = new Harem(4, "怀特迈恩");
        Harem h5 = new Harem(5, "奥妮克希亚");
        Harem h6 = new Harem(6, "阿莱克斯塔萨");
        h1.setLeftHarem(h2).setRightHarem(h3);
        h2.setLeftHarem(h4).setRightHarem(h5);
        h3.setRightHarem(h6);
        BinaryTree binaryTree = new BinaryTree(h1);
        System.out.println("-------------遍历-------------");
        System.out.println("前序遍历:");
        binaryTree.preOrder();
        System.out.println("中序遍历: ");
        binaryTree.middleOrder();
        System.out.println("后序遍历: ");
        binaryTree.postOrder();
        System.out.println("------------------------------");
        System.out.println("-------------查找-------------");
        System.out.println("前序查找: " + binaryTree.preOrderQuery(1));
        System.out.println("中序查找: " + binaryTree.middleOrderQuery(1));
        System.out.println("后序查找: " + binaryTree.postOrderQuery(1));
        System.out.println("------------------------------");
        System.out.println("-------------删除-------------");
        binaryTree.delete(5);
    }
}
