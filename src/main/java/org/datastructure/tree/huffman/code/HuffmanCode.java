package org.datastructure.tree.huffman.code;

import org.datastructure.tree.huffman.tree.HuffmanTree;
import org.datastructure.tree.huffman.tree.Node;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author LinYongJin
 * @date 2020/3/19 20:58
 */
public class HuffmanCode {

    private static final String LEFT_CHILD_PATH = "0";

    private static final String RIGHT_CHILD_PATH = "1";

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        // 将content转换成List
        List<Character> strList = new ArrayList<>();
        for (char c : content.toCharArray()) {
            strList.add(c);
        }
        // 将content转成成Map, key是字符对应的acs码, value是这个字符出现的次数
        Map<Character, Long> groupMap = strList.stream().collect(Collectors.groupingBy(item -> item, Collectors.counting()));
        // 将Map转成List泛型的Node
        ArrayList<Node> collect = groupMap.keySet().stream().collect(ArrayList::new, (list, data) -> {
            Node node = new Node(Math.toIntExact(groupMap.get(data)), (byte) data.charValue());
            list.add(node);
        }, ArrayList::addAll);
        // 将collect构造成哈夫曼树
        HuffmanTree huffmanTree = new HuffmanTree(collect);
        huffmanTree.preOrder();
        Map<Byte, String> huffmanCodeMap = huffmanCode(huffmanTree.getRoot());
        for (Map.Entry<Byte, String> entry : huffmanCodeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static Map<Byte, String> huffmanCode(Node node) {
        if (node == null) {
            return new HashMap<>();
        }
        Map<Byte, String> huffmanCodeMap = new HashMap<>();
        StringBuffer stringBuffer = new StringBuffer();
        huffmanCode(node, "", stringBuffer, huffmanCodeMap);
        return huffmanCodeMap;
    }

    /**
     * 生成字符与发哈夫曼编码的映射表Map
     *
     * @param node 当前需要遍历子树的root节点, 将这个子树的全部叶子结点放入huffmanCodeMap
     * @param path 路径 左: 0, 右:1
     * @param stringBuffer 存储历史路径的StringBuffer
     * @param huffmanCodeMap 哈夫曼表码表
     */
    private static void huffmanCode(Node node, String path, StringBuffer stringBuffer, Map<Byte, String> huffmanCodeMap) {
        // 用新的StringBuffer包装旧的StringBuffer
        StringBuffer subStringBuffer = new StringBuffer(stringBuffer);
        // 将新的路径拼接上历史StringBuffer的路径
        subStringBuffer.append(path);
        if (node != null && node.getData() == null) {
            // 非叶子节点
            // 继续遍历左子树
            huffmanCode(node.getLeftNode(), LEFT_CHILD_PATH, subStringBuffer, huffmanCodeMap);
            // 继续遍历右子树
            huffmanCode(node.getRightNode(), RIGHT_CHILD_PATH, subStringBuffer, huffmanCodeMap);
        } else {
            huffmanCodeMap.put(node.getData(), subStringBuffer.toString());
        }
    }


}
