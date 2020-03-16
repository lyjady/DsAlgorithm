package org.datastructure.tree.threadedbinarytree;

import org.datastructure.tree.binarytree.Harem;

/**
 * @author LinYongJin
 * @date 2020/3/11 21:01
 */
public class ThreadedBinaryTree {

    private HaremExt queue;

    private HaremExt pre;

    public ThreadedBinaryTree(HaremExt queue) {
        this.queue = queue;
    }

    public HaremExt getQueue() {
        return queue;
    }

    public ThreadedBinaryTree setQueue(HaremExt queue) {
        this.queue = queue;
        return this;
    }

    /**
     * 按照中序的顺序线索化二叉树
     */
    public void threadedBinaryTree(HaremExt harem) {
        if (harem == null) {
            return;
        }
        // 线索化左子树
        threadedBinaryTree(harem.getLeftHarem());
        // 1.线索化当前节点
        // 1.1判断当前节点的左节点
        if (harem.getLeftHarem() == null) {
            // 1.2如果为空则指向前驱节点
            harem.setLeftHarem(pre);
            // 1.3改变节点类型
            harem.setLeftNodeType(1);
        }
        // 2.1当前节点是前一个节点的后继节点, 将前一个节点的右节点指向当前节点
        if (pre != null && pre.getRightHarem() == null) {
            // 2.2将前一个节点的后继节点指向自己
            pre.setRightHarem(harem);
            // 2.3改变前一个节点的右子节点的类型
            pre.setRightNodeType(1);
        }
        // 3.当前节点处理完成后需要设置下一个节点的前驱节点, 这个前驱节点就是自身
        pre = harem;
        // 线索化右子树
        threadedBinaryTree(harem.getRightHarem());
    }

    /**
     * 按照中序的顺序遍历线索化二叉树
     */
    public void initOrder() {
        HaremExt node = queue;
        while (node != null) {
            // 因为按照中序遍历的顺序第一个节点就是最左的叶子节点, 因为在线索化二叉树中叶子节点的空指针域被线索化, 所以不断得到root节点的左子节点知道leftType = 1
            while (node.getLeftNodeType() != 1) {
                node = node.getLeftHarem();
            }
            // 打印这个节点
            System.out.println(node);
            // 如果这个节点的右子节点是后继节点那么直接获取打印
            while (node.getRightNodeType() == 1) {
                node = node.getRightHarem();
                System.out.println(node);
            }
            // 此时要么到了最后一个节点 rightNodeType = 0 or rightNode = null;程序结束
            // 如果这个node的rightNode没有被线索化那么以这个node为子树的root节点重新开始一轮循环
            node = node.getRightHarem();
        }
    }

    /**
     * 按照前序的顺序遍历线索化二叉树
     */
    public void preOrder() {
        HaremExt node = this.queue;
        // 前序遍历第一个节点就是root节点, 打印
        while (node != null) {
            System.out.println(node);
            while (node.getRightNodeType() == 1) {
                node = node.getRightHarem();
                System.out.println(node);
            }
            if (node.getLeftNodeType() != 1) {
                node = node.getLeftHarem();
            } else {
                node = node.getRightHarem();
            }
        }
    }

    /**
     * 按照后序顺序遍历线索化二叉树
     */
    public void postOrder() {
        HaremExt node = this.queue;
        while (node != null) {
            while (node.getLeftNodeType() != 1) {
                node = node.getLeftHarem();
            }
            System.out.println(node);
            while (node.getRightNodeType() == 1) {
                node = node.getRightHarem();
                System.out.println(node);
            }
        }
    }

    /**
     * 按照前序的顺序线索化二叉树
     *
     * @param harem
     */
    public void preThreadBinaryTree(HaremExt harem) {
        if (harem == null || (harem.getLeftNodeType() != 0 && harem.getRightNodeType() != 0)) {
            return;
        }
        HaremExt leftHarem = harem.getLeftHarem();
        HaremExt rightHarem = harem.getRightHarem();
        // 1.线索化当前节点
        // 1.1判断当前节点的左节点
        if (harem.getLeftHarem() == null) {
            // 1.2如果为空则指向前驱节点
            harem.setLeftHarem(pre);
            // 1.3改变节点类型
            harem.setLeftNodeType(1);
        }
        // 2.1当前节点是前一个节点的后继节点, 将前一个节点的右节点指向当前节点
        if (pre != null && pre.getRightHarem() == null) {
            // 2.2将前一个节点的后继节点指向自己
            pre.setRightHarem(harem);
            // 2.3改变前一个节点的右子节点的类型
            pre.setRightNodeType(1);
        }
        // 3.当前节点处理完成后需要设置下一个节点的前驱节点, 这个前驱节点就是自身

        pre = harem;
        // 线索化左子树
        preThreadBinaryTree(leftHarem);
        // 线索化右子树
        preThreadBinaryTree(rightHarem);
    }

    public void postThreadedBinaryTree(HaremExt harem) {
        if (harem == null || (harem.getRightNodeType() != 0 && harem.getLeftNodeType() != 0)) {
            return;
        }
        // 线索化左子树
        postThreadedBinaryTree(harem.getLeftHarem());
        // 线索化右子树
        postThreadedBinaryTree(harem.getRightHarem());
        // 1.线索化当前节点
        // 1.1如果当前节点的左子节点为空则指向前驱结点
        if (harem.getLeftHarem() == null) {
            harem.setLeftHarem(pre);
            harem.setLeftNodeType(1);
        }
        // 1.2处理当前节点的前一个节点, 将前一个节点的后继节点指向当前节点
        if (pre != null && pre.getRightHarem() == null) {
            pre.setRightHarem(harem);
            pre.setRightNodeType(1);
        }
        // 1.3当前节点处理完成, 开启下一个节点的线索化, 将前一个节点设置成当前节点.
        pre = harem;
    }
}
