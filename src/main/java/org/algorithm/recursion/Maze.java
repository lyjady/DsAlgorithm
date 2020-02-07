package org.algorithm.recursion;

/**
 * @author LinYongJin
 * @date 2020/2/7 15:56
 */
public class Maze {

    private static int[][] maze;

    static {
        maze = new int[8][8];
        for (int i = 0; i < maze.length; i++) {
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        for (int i = 0; i < maze.length; i++) {
            maze[i][0] = 1;
            maze[i][7] = 1;
        }
        maze[3][1] = 1;
        maze[3][2] = 1;
    }

    public static void main(String[] args) {
        mazeBacktracking(1, 1);
        print();
    }

    /**
     * 1: 表示当前坐标是不能走的
     * 2: 表示当前坐标是已经走过的, 可以通行的, 但不能走
     * 3: 表示当前坐标是已经走过的, 但是是不能通行的
     * 0: 表示当前坐标没有走过
     * @param x 横坐标
     * @param y 纵坐标
     */
    private static boolean mazeBacktracking(int x, int y) {
        if (maze[6][6] == 2) {
            //说明已经到达终点
            return true;
        }
        //判断当前的位置的状态
        if (maze[x][y] == 0) {
            maze[x][y] = 2;
            //说明当前位置是没有走过了, 后面根据下->左->右->上的策略进行移动
            if (mazeBacktracking(x + 1, y)) {
                return true;
            } else if (mazeBacktracking(x, y - 1)) {
                return true;
            } else if (mazeBacktracking(x, y + 1)) {
                return true;
            } else if (mazeBacktracking(x - 1, y)) {
                return true;
            } else {
                //如果这个位置上下左右都走不通则设置为死路
                maze[x][y] = 3;
                return false;
            }
        } else {
            //说明当前位置不能走
            return false;
        }
    }

    private static void print() {
        for (int[] row : maze) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
}
