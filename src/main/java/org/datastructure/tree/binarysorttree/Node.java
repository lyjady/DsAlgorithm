package org.datastructure.tree.binarysorttree;

import lombok.Getter;
import lombok.Setter;

import java.awt.print.Pageable;
import java.util.StringJoiner;

/**
 * @author LinYongJin
 * @date 2020/3/27 20:57
 */
@Setter
@Getter
public class Node {

    private int id;

    private String name;

    private Node leftNode;

    private Node rightNode;

    public Node() {
    }

    public Node(int id, String name, Node leftNode, Node rightNode) {
        this.id = id;
        this.name = name;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 插入节点
     *
     * @param node
     */
    public void insertNode(Node node) {
        // 如果小于当前树或者子树的root节点
        if (node.id < this.id) {
            // 查看左子树
            if (this.leftNode != null) {
                // 如果左子树不为空继续递归插入左子节点为root的那个子树
                this.leftNode.insertNode(node);
            } else {
                // 为空插入
                this.leftNode = node;
            }
        } else if (node.id > this.id) {
            if (this.rightNode != null) {
                this.rightNode.insertNode(node);
            } else {
                this.rightNode = node;
            }
        }
    }

    /**
     * 中序遍历
     */
    public void middleOrder() {
        if (this.leftNode != null) {
            this.leftNode.middleOrder();
        }
        System.out.println(this);
        if (this.rightNode != null) {
            this.rightNode.middleOrder();
        }
    }

    /**
     * 中序查找
     *
     * @param id
     * @return
     */
    public Node search(int id) {
        if (this.id == id) {
            return this;
        } else if (id < this.id) {
            // 左子树找
            if (this.leftNode != null) {
                return this.leftNode.search(id);
            } else {
                return null;
            }
        } else {
            // 右子树找
            if (this.rightNode != null) {
                return this.rightNode.search(id);
            } else {
                return null;
            }
        }
    }

    /**
     * 删除节点
     *
     * @param id
     * @return
     */
    public Node delete(int id) {
        Node parentNode = getParent(id);
        if (parentNode != null) {
            if (parentNode.leftNode.id == id) {

                return null;
            } else if (parentNode.rightNode.id == id) {

                return null;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 取得指定节点的父节点
     *
     * @param id
     * @return
     */
    public Node getParent(int id) {
        if ((this.leftNode != null && this.leftNode.id == id) || (this.rightNode != null && this.rightNode.id == id)) {
            // 当前的this就是查找目标的父元素
            return this;
        } else if (id < this.id && this.leftNode != null) {
            return this.leftNode.getParent(id);
        } else if (id > this.id && this.rightNode != null) {
            return this.rightNode.getParent(id);
        } else {
            return null;
        }
    }

    /**
     * 找出子树的最小节点, 并将这个节点的父节点的左子节点置空
     *
     * @return
     */
    public Node minNode() {
        // 因为当前树满足二叉排序树, 找出子树的最小值就是找出当前这个子树的最左的叶子节点
        Node node = this;
        while (node.leftNode.leftNode != null) {
            node = node.leftNode;
        }
        Node returnNode = node.leftNode;
        node.leftNode = null;
        return returnNode;
    }

    /**
     * 找出子树的最大节点, 并将这个节点的父节点的右子节点置空
     *
     * @return
     */
    public Node maxNode() {
        // 因为当前树满足二叉排序树, 找出子树的最小值就是找出当前这个子树的最左的叶子节点
        Node node = this;
        while (node.rightNode.rightNode != null) {
            node = node.rightNode;
        }
        Node returnNode = node.rightNode;
        node.rightNode = null;
        return returnNode;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
