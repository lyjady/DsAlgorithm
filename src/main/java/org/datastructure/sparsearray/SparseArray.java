package org.datastructure.sparsearray;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LinYongJin
 * @date 2020/1/24 20:04
 */
public class SparseArray {
    public static void main(String[] args) {
        int[][] checkerboard = new int[11][11];
        checkerboard[1][2] = 1;
        checkerboard[2][3] = 2;
        checkerboard[4][1] = 1;
        checkerboard[2][5] = 1;
        checkerboard[6][6] = 2;
        System.out.println("原未压缩的二维数组");
        printArray(checkerboard);

        List<Sparse> sparses = new ArrayList<>();
        for (int i = 0; i < checkerboard.length; i++) {
            for (int j = 0; j < checkerboard[i].length; j++) {
                if (checkerboard[i][j] != 0) {
                    Sparse sparse = new Sparse();
                    sparse.setRow(i);
                    sparse.setColumn(j);
                    sparse.setValue(checkerboard[i][j]);
                    sparses.add(sparse);
                }
            }
        }
        int[][] sparseArr = new int[sparses.size() + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sparses.size();
        int sparseRow = 1;
        for (Sparse sparse : sparses) {
            sparseArr[sparseRow][0] = sparse.getRow();
            sparseArr[sparseRow][1] = sparse.getColumn();
            sparseArr[sparseRow][2] = sparse.getValue();
            sparseRow++;
        }
        System.out.println("疏散数组");
        printArray(sparseArr);
        StringBuffer sb = new StringBuffer();
        for (int[] row : sparseArr) {
            for (int col : row) {
                sb.append(col).append("\t");
            }
            sb.append("\n");
        }
        //将疏散数组写入磁盘
        write(sb.toString());
        int[][] read = read(System.getProperty("user.dir") + "/src/main/resources/" + "file/save.data");
        System.out.println("硬盘读取的数据");
        printArray(read);
        int[][] reCheckerboard = new int[read[0][0]][read[0][1]];
        for (int i = 1; i < read.length; i++) {
            reCheckerboard[read[i][0]][read[i][1]] = read[i][2];
        }
        System.out.println("复原之后的二维数组");
        printArray(reCheckerboard);
    }

    public static void printArray(int[][] arr) {
        for (int[] row : arr) {
            for (int col : row) {
                System.out.printf("%d\t", col);
            }
            System.out.println();
        }
        System.out.println("==========================================");
    }

    public static void write(String content) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(System.getProperty("user.dir") + "/src/main/resources/" + "file/save.data");
            fos.write(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int[][] read(String path) {
        FileReader fileReader = null;
        int[][] loadArray = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
            String content = null;
            StringBuffer sb = new StringBuffer();
            int loadRow = 0;
            while ((content = bufferedReader.readLine()) != null) {
                sb.append(content).append("\n");
                loadRow++;
            }
            sb.delete(sb.length() - 2, sb.length());
            String[] split = sb.toString().split("\n");
            loadArray = new int[split.length][3];
            int loadArrayRow = 0;
            for (String s : split) {
                String[] strings = s.split("\t");
                loadArray[loadArrayRow][0] = Integer.parseInt(strings[0]);
                loadArray[loadArrayRow][1] = Integer.parseInt(strings[1]);
                loadArray[loadArrayRow][2] = Integer.parseInt(strings[2]);
                loadArrayRow++;
            }
            return loadArray;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Sparse {

    private int value;

    private int row;

    private int column;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Sparse{" +
                "value=" + value +
                ", row=" + row +
                ", column=" + column +
                '}';
    }
}
