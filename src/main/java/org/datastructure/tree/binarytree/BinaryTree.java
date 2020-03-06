package org.datastructure.tree.binarytree;

import java.util.HashMap;

/**
 * @author LinYongJin
 * @date 2020/3/6 20:10
 */
public class BinaryTree {

    private Harem queue;

    public BinaryTree(Harem queue) {
        this.queue = queue;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        queue.preOrder();
    }

    /**
     * 中序遍历
     */
    public void middleOrder() {
        queue.middleOrder();
    }

    /**
     * 后续遍历
     */
    public void postOrder() {
        queue.postOrder();
    }

    /**
     * 前序查找
     * @return
     */
    public Harem preOrderQuery(int id) {
        return queue.preOrderQuery(id);
    }

    /**
     * 中序查找
     * @return
     */
    public Harem middleOrderQuery(int id) {
        return queue.middleOrderQuery(id);
    }

    /**
     * 后序查找
     * @return
     */
    public Harem postOrderQuery(int id) {
        return queue.postOrderQuery(id);
    }

    /**
     * 删除节点
     * @param id
     */
    public void delete(int id) {
        if (this.queue.getId() == id) {
            queue = null;
            return;
        }
        queue.delete(id);
    }
}
