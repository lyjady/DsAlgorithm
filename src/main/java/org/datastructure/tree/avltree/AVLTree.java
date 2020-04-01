package org.datastructure.tree.avltree;

import lombok.Data;
import org.datastructure.tree.binarysorttree.Node;

/**
 * @author LinYongJin
 * @date 2020/4/1 20:42
 */
@Data
public class AVLTree {

    private Node queue;

    /**
     * 返回调用节点为根节点的树的高度
     * @return
     */
    public int height() {
        return this.queue.height();
    }

    /**
     * 创建二叉排序树
     *
     * @param nodes
     */
    public void create(Node[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            if (i == 0) {
                queue = nodes[i];
                continue;
            }
            this.queue.insertNode(nodes[i]);
        }
    }
}
