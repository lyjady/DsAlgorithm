package org.algorithm.collection.greed;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author LinYongJin
 * @date 2020/4/22 13:54
 */
public class Greed {

    private static Map<String, HashSet<String>> broadcast = null;

    private static Set<String> allAreas = null;

    public static void main(String[] args) {
        init();
        // 创建一个集合, 内容是选中的广播
        Set<String> set = new HashSet<>();
        // 循环遍历, 当allAreas为空时说明全部地区都被覆盖, 此时结束循环
        while (allAreas.size() > 0) {
            String selectedKey = null;
            for (Map.Entry<String, HashSet<String>> entry : broadcast.entrySet()) {
                HashSet<String> areas = broadcast.get(entry.getKey());
                // 创建一个临时变量存当前电台的区域并与全部未覆盖的的区域取交集
                Set<String> intersection = new HashSet<>(areas);
                intersection.retainAll(allAreas);
                // 创建一个集合保存selectedKey电台的区域并与全部的未覆盖的区域取交集
                Set<String> selectedIntersection = StringUtils.isNotBlank(selectedKey) ? new HashSet<>(broadcast.get(selectedKey)) : new HashSet<>();
                selectedIntersection.retainAll(allAreas);
                // 如果当前电台的覆盖的区域(即与未覆盖的全部区域的交集)大于selectedKey电台覆盖的区域的话将selectedKey替换成当前电台
                if (intersection.size() > 0 && intersection.size() > selectedIntersection.size()) {
                    selectedKey = entry.getKey();
                }
            }
            // 一轮完成后将selectedKey添加到集合中
            set.add(selectedKey);
            // 将selectedKey覆盖的区域从未覆盖的区域中去除
            allAreas.removeAll(broadcast.get(selectedKey));
        }
        System.out.println(set);
    }

    private static void init() {
        broadcast = new HashMap<>();
        HashSet<String> k1 = new HashSet<>();
        k1.add("北京");
        k1.add("上海");
        k1.add("天津");
        broadcast.put("k1", k1);
        HashSet<String> k2 = new HashSet<>();
        k2.add("北京");
        k2.add("广州");
        k2.add("深圳");
        broadcast.put("k2", k2);
        HashSet<String> k3 = new HashSet<>();
        k3.add("成都");
        k3.add("上海");
        k3.add("杭州");
        broadcast.put("k3", k3);
        HashSet<String> k4 = new HashSet<>();
        k4.add("天津");
        k4.add("上海");
        broadcast.put("k4", k4);
        HashSet<String> k5 = new HashSet<>();
        k5.add("杭州");
        k5.add("大连");
        broadcast.put("k5", k5);
        allAreas = new HashSet<>();
        broadcast.keySet().forEach(key -> allAreas.addAll(broadcast.get(key)));
    }
}
