package org.datastructure.tree.huffman.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author LinYongJin
 * @date 2020/3/19 20:33
 */
public class HuffmanTree {

    private Node root;

    /**
     * 构建哈夫曼树
     * @param arr
     */
    public HuffmanTree(int[] arr) {
        // 将arr装换成list集合
        List<Node> nodeList = Arrays.stream(arr).mapToObj(Node::new).collect(Collectors.toList());
        while (nodeList.size() > 1) {
            // 对集合尽心排序
            nodeList.sort(Comparator.comparingInt(Node::getWeight));
            // 取得最小的两个节点
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);
            // 构建root节点并将左右子树指向最小的那两个节点
            Node root = new Node(leftNode.getWeight() + rightNode.getWeight(), leftNode, rightNode);
            // 删除最小的那两个节点, 将新的root节点放置到集合中
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            nodeList.add(root);
        }
        this.root = nodeList.get(0);
    }

    public HuffmanTree(List<Node> nodeList) {
        while (nodeList.size() > 1) {
            // 对集合尽心排序
            nodeList.sort(Comparator.comparingInt(Node::getWeight));
            // 取得最小的两个节点
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);
            // 构建root节点并将左右子树指向最小的那两个节点
            Node root = new Node(leftNode.getWeight() + rightNode.getWeight(), leftNode, rightNode);
            // 删除最小的那两个节点, 将新的root节点放置到集合中
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            nodeList.add(root);
        }
        this.root = nodeList.get(0);
    }

    public void preOrder() {
        if (root != null) {
            root.preOrder();
        }
    }

    public Node getRoot() {
        return root;
    }

    public HuffmanTree setRoot(Node root) {
        this.root = root;
        return this;
    }

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        HuffmanTree huffmanTree = new HuffmanTree(arr);
        huffmanTree.preOrder();
    }
}
