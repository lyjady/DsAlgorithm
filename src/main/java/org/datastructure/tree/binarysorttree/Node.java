package org.datastructure.tree.binarysorttree;

import lombok.Getter;
import lombok.Setter;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.UnaryOperator;

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

    public Node(int id) {
        this.id = id;
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
        if (this.leftNode != null && this.rightNode != null) {
            if (Math.abs(leftTreeHeight() - rightTreeHeight()) > 1) {
                if (leftTreeHeight() > rightTreeHeight()) {
                    if (this.leftNode != null && this.leftNode.rightTreeHeight() > this.leftNode.leftTreeHeight()) {
                        this.leftNode.leftRotate();
                    }
                    this.rightRotate();
                } else {
                    if (this.rightNode != null && this.rightNode.leftTreeHeight() > this.rightNode.rightTreeHeight()) {
                        this.rightNode.rightRotate();
                    }
                    this.leftRotate();
                }
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
            if (parentNode.leftNode != null && parentNode.leftNode.id == id) {
                Node deletedNode = parentNode.leftNode;
                // 判断当前删除的节点的左右子节点是否为空
                if (deletedNode.leftNode != null && deletedNode.rightNode != null) {
                    Node maxNode = deletedNode.maxNode();
                    maxNode.leftNode = deletedNode.leftNode;
                    maxNode.rightNode = deletedNode.rightNode;
                    parentNode.leftNode = maxNode;
                } else if (deletedNode.leftNode != null) {
                    parentNode.leftNode = deletedNode.leftNode;
                } else if (deletedNode.rightNode != null) {
                    parentNode.leftNode = deletedNode.rightNode;
                } else {
                    parentNode.leftNode = null;
                }
                return deletedNode;
            } else if (parentNode.rightNode != null && parentNode.rightNode.id == id) {
                Node deletedNode = parentNode.rightNode;
                // 判断当前删除的节点的左右子节点是否为空
                if (deletedNode.leftNode != null && deletedNode.rightNode != null) {
                    Node maxNode = deletedNode.maxNode();
                    maxNode.leftNode = deletedNode.leftNode;
                    maxNode.rightNode = deletedNode.rightNode;
                    parentNode.rightNode = maxNode;
                } else if (deletedNode.leftNode != null) {
                    parentNode.rightNode = deletedNode.leftNode;
                } else if (deletedNode.rightNode != null) {
                    parentNode.rightNode = deletedNode.rightNode;
                } else {
                    parentNode.rightNode = null;
                }
                return deletedNode;
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
        // 因为当前树满足二叉排序树, 找出子树的最大值就是找出当前这个子树的最右的叶子节点
        Node node = this;
        while (node.rightNode.rightNode != null) {
            node = node.rightNode;
        }
        Node returnNode = node.rightNode;
        node.rightNode = null;
        return returnNode;
    }

    /**
     * 左子树高度
     * @return
     */
    public int leftTreeHeight() {
        return this.leftNode != null ? this.leftNode.height() : 0;
    }

    /**
     * 右子树高度
     * @return
     */
    public int rightTreeHeight() {
        return this.rightNode != null ? this.rightNode.height() : 0;
    }

    /**
     * 返回调用节点为根节点的树的高度
     *
     * @return
     */
    public int height() {
        return Math.max(leftNode == null ? 0 : leftNode.height(), rightNode == null ? 0 : rightNode.height()) + 1;
    }

    /**
     * 将以当前节点为根节点的树进行左旋转
     */
    public void leftRotate() {
        // 创建新的节点, 新节点的值为当前节点的值(当前节点不一定为根节点)
        Node newNode = new Node(this.id, this.name);
        // 把新节点的左子树设置成当前节点的左子树
        newNode.leftNode = this.leftNode;
        // 把新节点的右子树设置成当前节点右子树的左子树
        newNode.rightNode = this.rightNode.leftNode;
        // 把当前节点的值替换成右子节点的值
        this.id = this.rightNode.id;
        this.name = this.rightNode.name;
        // 把当前节点的右子树替换成当前节点右子树的右子树
        this.rightNode = this.rightNode.rightNode;
        // 把当前节点的左子树设置成新节点
        this.leftNode = newNode;
    }

    /**
     * 将以当前节点为根节点的树进行右旋转
     */
    public void rightRotate() {
        Node newNode = new Node(this.id, this.name);
        newNode.rightNode = rightNode;
        newNode.leftNode = this.leftNode.rightNode;
        this.id = this.leftNode.id;
        this.name = this.leftNode.name;
        this.leftNode = this.leftNode.leftNode;
        this.rightNode = newNode;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
