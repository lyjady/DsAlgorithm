package org.algorithm.collection.dijkstra;

import java.util.Arrays;

/**
 * @author LinYongJin
 * @date 2020/4/30 20:53
 */
public class VisitedVertex {

    /**
     * 保存顶点的访问情况
     * 1: 访问, 0: 未访问
     */
    private int[] alreadyArr;

    /**
     * 保存初始顶点到各个顶点的距离
     * 65535: 表示不连通
     */
    private int[] dis;

    /**
     * 各个顶点的前驱节点
     */
    private int[] pre;

    public VisitedVertex(int vertex, int starIndex) {
        alreadyArr = new int[vertex];
        alreadyArr[starIndex] = 1;
        dis = new int[vertex];
        Arrays.fill(dis, 65535);
        // 节点自身到自身距离是0
        dis[starIndex] = 0;
        pre = new int[vertex];
    }

    /**
     * 更新顶点的访问情况
     *
     * @param index
     */
    public void updateAlreadyArr(int index) {
        alreadyArr[index] = 1;
    }

    /**
     * 顶点是否被访问过
     *
     * @param index
     * @return
     */
    public boolean isVisited(int index) {
        return alreadyArr[index] == 1;
    }

    /**
     * 更新距离数据
     *
     * @param index    要更新的节点
     * @param distance 更新的距离
     */
    public void updateDis(int index, int distance) {
        dis[index] = distance;
    }

    /**
     * 更新前驱节点
     *
     * @param index    要更新的节点
     * @param preIndex 前驱节点的索引
     */
    public void updatePre(int index, int preIndex) {
        pre[index] = preIndex;
    }

    /**
     * 得到指点初始节点到指点节点的距离
     *
     * @param index
     * @return
     */
    public int getDis(int index) {
        return dis[index];
    }

    public int updateArr() {
        int min = 65535;
        int index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            if (alreadyArr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        alreadyArr[index] = 1;
        return index;
    }
}
