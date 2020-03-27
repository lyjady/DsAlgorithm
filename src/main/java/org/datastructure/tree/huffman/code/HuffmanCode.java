package org.datastructure.tree.huffman.code;

import org.apache.commons.lang3.StringUtils;
import org.datastructure.tree.huffman.tree.HuffmanTree;
import org.datastructure.tree.huffman.tree.Node;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
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
        zipText("i like like like java do you like a java");
        zipFile(System.getProperty("user.dir") + "/src.bmp", System.getProperty("user.dir") + "/src.zip");
        unzipFile(System.getProperty("user.dir") + "/src.zip", System.getProperty("user.dir") + "/src2.bmp");
    }

    /**
     * 压缩文本
     *
     * @param content
     */
    public static void zipText(String content) {
        if (StringUtils.isEmpty(content)) {
            return;
        }
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
        Map<Byte, String> huffmanCodeMap = huffmanCode(huffmanTree.getRoot());
        byte[] encode = encode(content.getBytes(), huffmanCodeMap);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < encode.length; i++) {
            boolean isLast = i == encode.length - 1;
            sb.append(byte2BitStr(!isLast, encode[i]));
        }
        byte[] decode = decode(sb, huffmanCodeMap);
        System.out.println("解码之后的数据: " + new String(decode, StandardCharsets.UTF_8));
    }

    /**
     * 压缩文件
     *
     * @param srcFile
     * @param destFile
     */
    public static void zipFile(String srcFile, String destFile) {
        FileInputStream fis = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            File file = new File(srcFile);
            fis = new FileInputStream(file);
            byte[] data = new byte[fis.available()];
            // 读取文件的字节数据
            fis.read(data);
            List<Byte> byteList = new ArrayList<>(data.length);
            for (int i = 0; i < data.length; i++) {
                byteList.add(data[i]);
            }
            Map<Byte, Long> groupMap = byteList.stream().collect(Collectors.groupingBy(item -> item, Collectors.counting()));
            ArrayList<Node> collect = groupMap.keySet().stream().collect(ArrayList::new, (list, item) -> {
                Node node = new Node(Math.toIntExact(groupMap.get(item)), item);
                list.add(node);
            }, ArrayList::addAll);
            HuffmanTree huffmanTree = new HuffmanTree(collect);
            // 生成哈夫曼编码表
            Map<Byte, String> huffmanCodeMap = huffmanCode(huffmanTree.getRoot());
            // 将原内容生成压缩后的字节数组
            byte[] encode = encode(data, huffmanCodeMap);
            // 使用输出流输出数据
            os = new FileOutputStream(destFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(encode);
            oos.writeObject(huffmanCodeMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (os != null) {
                    os.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 解压文件
     *
     * @param zipPath
     * @param destPath
     */
    private static void unzipFile(String zipPath, String destPath) {
        ObjectInputStream ois = null;
        FileOutputStream fos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(zipPath));
            // 获得压缩文件的字节数组
            byte[] binary = (byte[]) ois.readObject();
            // 获得发h哈夫曼编码表
            Map<Byte, String> huffmanCodeMap = (Map<Byte, String>) ois.readObject();
            // 解码
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < binary.length; i++) {
                boolean isLast = i == binary.length - 1;
                sb.append(byte2BitStr(!isLast, binary[i]));
            }
            byte[] decode = decode(sb, huffmanCodeMap);
            fos = new FileOutputStream(destPath);
            fos.write(decode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据指定的内容生成哈夫曼编码表
     *
     * @param node
     * @return
     */
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
     * @param node           当前需要遍历子树的root节点, 将这个子树的全部叶子结点放入huffmanCodeMap
     * @param path           路径 左: 0, 右:1
     * @param stringBuffer   存储历史路径的StringBuffer
     * @param huffmanCodeMap 哈夫曼表码表
     */
    private static void huffmanCode(Node node, String path, StringBuffer stringBuffer, Map<Byte, String> huffmanCodeMap) {
        // 用新的StringBuffer包装旧的StringBuffer
        StringBuffer subStringBuffer = new StringBuffer(stringBuffer);
        // 将新的路径拼接上历史StringBuffer的路径
        subStringBuffer.append(path);
        if (node != null) {
            if (node.getData() == null) {
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

    /**
     * 将表示内容的字节数组根据huffmanCodeMap编码成传输的字节数组
     *
     * @param content
     * @param huffmanCodeMap
     */
    private static byte[] encode(byte[] content, Map<Byte, String> huffmanCodeMap) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < content.length; i++) {
            String code = huffmanCodeMap.get(content[i]);
            sb.append(code);
        }
        int size = sb.length() % 8 == 0 ? sb.length() / 8 : sb.length() / 8 + 1;
        int index = 0;
        byte[] encode = new byte[size];
        for (int i = 0; i < sb.length(); i += 8) {
            String substring;
            if (i + 8 > sb.length()) {
                substring = sb.substring(i);
            } else {
                substring = sb.substring(i, i + 8);
            }
            encode[index++] = (byte) Integer.parseInt(substring, 2);
        }
        System.out.println("编码之后的字节数组: " + Arrays.toString(encode));
        return encode;
    }

    /**
     * 将编码过后的字节数组转换成字符串形式的二进制数
     *
     * @param flag 是否需要补高位
     * @param data 传入的字节数据
     */
    private static String byte2BitStr(boolean flag, byte data) {
        // 将data转成int
        int temp = data;
        // 如果是正数补高位
        if (flag) {
            temp |= 256;
        }
        // 获得temp对应的二进制的补码
        String str = Integer.toBinaryString(temp);
        return flag ? str.substring(str.length() - 8) : str;
    }

    /**
     * 将二进制字符串根据哈夫曼编码表解码成原数据
     *
     * @param bitContent     二进制字符串化的原数据
     * @param huffmanCodeMap 哈西曼编码表
     * @return
     */
    private static byte[] decode(StringBuffer bitContent, Map<Byte, String> huffmanCodeMap) {
        Map<String, Byte> reverseHuffmanCodeMap = new HashMap<>();
        // 将哈夫曼编码表中的key value对调
        for (Map.Entry<Byte, String> huffmanCodeMapEntry : huffmanCodeMap.entrySet()) {
            reverseHuffmanCodeMap.put(huffmanCodeMapEntry.getValue(), huffmanCodeMapEntry.getKey());
        }
        List<Byte> datas = new ArrayList<>();
        for (int i = 0; i < bitContent.length(); ) {
            int offset = 1;
            while (true) {
                // 截取bitContent的内容与反转过后的哈夫曼编码表的数据进行比较
                String subBitContent = bitContent.substring(i, i + offset);
                Byte data = reverseHuffmanCodeMap.get(subBitContent);
                if (data != null) {
                    // 如果截取到的字符串(原哈夫曼树中的路径)不为空则说明找到当前二进制字符串对应的原数据
                    datas.add(data);
                    break;
                }
                offset++;
            }
            i += offset;
        }
        byte[] res = new byte[datas.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = datas.get(i);
        }
        return res;
    }
}
