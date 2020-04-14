package org.algorithm.collection;

/**
 * @author LinYongJin
 * @date 2020/4/14 21:14
 */
public class DivideConquer {

    public static void main(String[] args) {
        hanota(4, 'a', 'b', 'c');
    }

    /**
     * 使用分治算法解决汉诺塔问题
     *
     * @param num 移动的盘子的数量
     * @param a 开始移动时盘子所在的柱子
     * @param b 辅助移动的柱子
     * @param c 目标柱子
     */
    public static void hanota(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第" + num + "个盘子从" + a + "移动到" + c);
        } else {
            hanota(num - 1, a, c, b);
            System.out.println("第" + num + "个盘子从" + a + "移动到" + c);
            hanota(num - 1, b, a, c);
        }
    }
}
