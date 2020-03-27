package org.datastructure.tree.binarysorttree;

/**
 * @author LinYongJin
 * @date 2020/3/27 21:02
 */
public class BinarySortTree {

    private Node queue;

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

    /**
     * 中序遍历
     */
    public void middleOrder() {
        this.queue.middleOrder();
    }

    /**
     * 中序查找
     *
     * @param id
     * @return
     */
    public Node middleSearch(int id) {
        return this.queue.search(id);
    }

    /**
     * 删除节点
     *
     * @param id
     * @return
     */
    public Node delete(int id) {
        if (this.queue.getId() == id) {
            Node returnNode = this.queue;
            if (this.queue.getRightNode() != null && this.queue.getLeftNode() != null) {
                // 找出右子树的左小值(也可以找出左子树的最大值)
                Node node = this.queue.getLeftNode().maxNode();
                // 将root节点的子节点赋值给替换的节点替换root节点
                node.setLeftNode(this.queue.getLeftNode());
                node.setRightNode(this.queue.getRightNode());
                this.queue = node;
            } else if (this.queue.getLeftNode() == null) {
                // 左子节点为空, root节点直接指向右子树
                this.queue = this.queue.getRightNode();
            } else if (this.queue.getRightNode() == null) {
                // 右子节点为空, root节点直接指向左子树
                this.queue = this.queue.getLeftNode();
            } else {
                // 这颗树只有一个根节点
                this.queue = null;
            }
            return returnNode;
        } else {
            return this.queue.delete(id);
        }
    }
}
