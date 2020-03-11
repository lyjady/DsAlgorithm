package org.datastructure.tree.threadedbinarytree;

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
     * 按照前序的顺序线索化二叉树
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
}
