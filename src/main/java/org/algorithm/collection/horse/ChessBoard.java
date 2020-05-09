package org.algorithm.collection.horse;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author LinYongJin
 * @date 2020/5/9 21:11
 */
public class ChessBoard {

    /**
     * 行
     */
    private static int X = 8;

    /**
     * 列
     */
    private static int Y = 8;

    private static boolean isFinish = false;

    private static int[] visited;

    public static void main(String[] args) {
        int[][] chess = new int[X][Y];
        visited = new int[X * Y];
        long startTime = System.currentTimeMillis();
        chessboard(0, 2, 1, chess);
        System.out.println("花费时间: " + (System.currentTimeMillis() - startTime) + "ms");
        for (int[] ints : chess) {
            System.out.println(Arrays.toString(ints));
        }
    }

    private static void chessboard(int x, int y, int step, int[][] chess) {
        chess[y][x] = step;
        visited[y * X + x] = 1;
        List<Point> next = next(x, y);
        next.sort(Comparator.comparingInt(p -> next(p.x, p.y).size()));
        while (!next.isEmpty()) {
            Point point = next.remove(0);
            if (visited[point.y * X + point.x] == 0) {
                chessboard(point.x, point.y, step + 1, chess);
            }
        }
        if (step < X * Y && !isFinish) {
            chess[y][x] = 0;
            visited[y * X + x] = 0;
        } else {
            isFinish = true;
        }
    }

    private static List<Point> next(int curX, int curY) {
        Point point = new Point(curX, curY);
        List<Point> points = new ArrayList<>();
        if (point.x - 2 >= 0 && point.y - 1 >= 0) {
            points.add(new Point(point.x - 2, point.y - 1));
        }
        if (point.x - 1 >= 0 && point.y - 2 >= 0) {
            points.add(new Point(point.x - 1, point.y - 2));
        }
        if (point.x + 1 < X && point.y - 2 >= 0) {
            points.add(new Point(point.x + 1, point.y - 2));
        }
        if (point.x + 2 < X && point.y - 1 >= 0) {
            points.add(new Point(point.x + 2, point.y - 1));
        }
        if (point.x + 2 < X && point.y + 1 < Y) {
            points.add(new Point(point.x + 2, point.y + 1));
        }
        if (point.x + 1 < X && point.y + 2 < Y) {
            points.add(new Point(point.x + 1, point.y + 2));
        }
        if (point.x - 1 >= 0 && point.y + 2 < Y) {
            points.add(new Point(point.x - 1, point.y + 2));
        }
        if (point.x - 2 >= 0 && point.y + 1 < Y) {
            points.add(new Point(point.x - 2, point.y + 1));
        }
        return points;
    }
}
