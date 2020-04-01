package org.datastructure.tree.avltree;

import lombok.extern.slf4j.Slf4j;
import org.datastructure.tree.binarysorttree.Node;

import java.util.Arrays;
import java.util.List;

/**
 * @author LinYongJin
 * @date 2020/4/1 20:46
 */
public class AVLTreeMain {

    /**
     * 树的结构
     *          4
     *        /  \
     *       3    6
     *           / \
     *          5   7
     *               \
     *                8
     *    中序遍历: 3 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
     */
    public static void main(String[] args) {
        Node[] heroes = {new Node(4, "Jaina"), new Node(3, "Tyrande"),
                new Node(6, "Sylvanas"), new Node(5, "Whitemane"),
                new Node(7, "Onyxia"), new Node(8, "Isera")};
        AVLTree tree = new AVLTree();
        tree.create(heroes);
        System.out.println("树的高度: " + tree.height());
        System.out.println("左子树高度: " + tree.getQueue().leftTreeHeight());
        System.out.println("右子树高度: " + tree.getQueue().rightTreeHeight());
    }
}
