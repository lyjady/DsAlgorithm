package org.datastructure.tree.huffman.tree;

import java.util.StringJoiner;

/**
 * @author LinYongJin
 * @date 2020/3/19 20:32
 */
public class Node {

    private int weight;

    private Byte data;

    private Node leftNode;

    private Node rightNode;

    public Node(int weight) {
        this(weight, null);
    }

    public Node(int weight, Byte data) {
        this.weight = weight;
        this.data = data;
    }

    public Node(int weight, Byte data, Node leftNode, Node rightNode) {
        this.weight = weight;
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public Node(int weight, Node leftNode, Node rightNode) {
        this(weight, null, leftNode, rightNode);
    }

    public void preOrder() {
        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    public int getWeight() {
        return weight;
    }

    public Node setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
        return this;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public Node setRightNode(Node rightNode) {
        this.rightNode = rightNode;
        return this;
    }

    public Byte getData() {
        return data;
    }

    public Node setData(Byte data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                .add("weight=" + weight)
                .add("data=" + data)
                .toString();
    }
}
