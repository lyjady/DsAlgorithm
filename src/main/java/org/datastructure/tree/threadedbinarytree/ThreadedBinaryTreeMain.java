package org.datastructure.tree.threadedbinarytree;

import org.datastructure.tree.binarytree.BinaryTree;
import org.datastructure.tree.binarytree.Harem;
import org.junit.Before;
import org.junit.Test;

/**
 * @author LinYongJin
 * @date 2020/3/11 21:27
 */
public class ThreadedBinaryTreeMain {

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
    private ThreadedBinaryTree threadedBinaryTree;

    @Test
    public void init() {
        HaremExt h1 = new HaremExt(1, "吉安娜");
        HaremExt h2 = new HaremExt(2, "泰兰德");
        HaremExt h3 = new HaremExt(3, "斯尔瓦娜斯");
        HaremExt h4 = new HaremExt(4, "怀特迈恩");
        HaremExt h5 = new HaremExt(5, "奥妮克希亚");
        HaremExt h6 = new HaremExt(6, "阿莱克斯塔萨");
        h1.setLeftHarem(h2).setRightHarem(h3);
        h2.setLeftHarem(h4).setRightHarem(h5);
        h3.setRightHarem(h6);
        threadedBinaryTree = new ThreadedBinaryTree(h1);
        threadedBinaryTree.postThreadedBinaryTree(threadedBinaryTree.getQueue());
        System.out.println("-----------");
    }

    @Test
    public void threadedBinaryTreeTest() {
    }
}
