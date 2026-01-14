package com.myproj.app.algorithm.矩阵;

import java.util.Arrays;

/**
 * 给你一个 m x n 的字符矩阵 boxGrid ，它表示一个箱子的侧视图。箱子的每一个格子可能为：
 *     '#' 表示石头
 *     '*' 表示固定的障碍物
 *     '.' 表示空位置
 * 这个箱子被 顺时针旋转 90 度 ，由于重力原因，部分石头的位置会发生改变。每个石头会垂直掉落，直到它遇到障碍物，另一个石头或者箱子的底部。重力 不会 影响障碍物的位置，同时箱子旋转不会产生惯性 ，也就是说石头的水平位置不会发生改变。
 * 题目保证初始时 boxGrid 中的石头要么在一个障碍物上，要么在另一个石头上，要么在箱子的底部。
 * 请你返回一个 n x m 的矩阵，表示按照上述旋转后，箱子内的结果。
 *
 * 示例 1：
 * 输入：box = [["#",".","#"]]
 * 输出：[["."],
 *       ["#"],
 *       ["#"]]
 *
 * 示例 2：
 * 输入：box = [["#",".","*","."],
 *             ["#","#","*","."]]
 * 输出：[["#","."],
 *       ["#","#"],
 *       ["*","*"],
 *       [".","."]]
 *
 * 示例 3：
 * 输入：box = [["#","#","*",".","*","."],
 *             ["#","#","#","*",".","."],
 *             ["#","#","#",".","#","."]]
 * 输出：[[".","#","#"],
 *       [".","#","#"],
 *       ["#","#","*"],
 *       ["#","*","."],
 *       ["#",".","*"],
 *       ["#",".","."]]
 *
 *       思路：
 *          - 解法1： 先旋转，再搬运
 *          - 解法2【推荐】： 先搬运，再旋转
 *              {@link 旋转图像}: 顺时针旋转90度： matrix[row][col]，在旋转后，它的新位置为 matrixnew[col][n−row−1]
 *              与{@link 旋转盒子} 也是顺时针旋转90度： 表达式一致。
 *
 * @author shenxie
 * @date 2025/11/4
 */
public class 旋转盒子 {

    public static void main(String[] args) {
//        System.out.println(Arrays.deepToString(rotateTheBox(new char[][]{{'#', '.', '*', '.'}, {'#', '#', '*', '.'}})));
//        System.out.println(Arrays.deepToString(rotateTheBoxV2(new char[][]{{'#', '.', '*', '.'}, {'#', '#', '*', '.'}})));
        System.out.println(Arrays.deepToString(rotateTheBoxV2(new char[][]{{'#', '.', '#'}})));
    }

    /**
     * 解法1： 先旋转再搬运。
     */
    public static char[][] rotateTheBox(char[][] box) {
        char ans[][] = new char[box[0].length][box.length];
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[0].length; j++) {
                // box旋转90°
                ans[j][box.length - 1 - i] = box[i][j];
            }
        }
        for (int j = 0; j < ans[0].length; j++) {
            for (int i = ans.length - 1; i >= 0; i--) {
                // 碰到石头
                if (ans[i][j] == '#') {
                    for (int k = i + 1; ; k++) {
                        if (k == ans.length || ans[k][j] != '.') {
                            ans[k - 1][j] = '#';
                            if (k != i + 1) {
                                ans[i][j] = '.';
                            }
                            //只有在石头不用移动的时候才变空
                            break;
                        }
                    }
                }
            }
        }
        return ans;
    }


    /**
     * 解法2： 先搬运再旋转：
     *  搬运思想：
     *      当碰到石头时：就将石头放在pos位置，之后pos的位置左移 ， 且 将原位置空
     *      当碰到障碍物时， 将pos的位置左移。
     */
    public static char[][] rotateTheBoxV2(char[][] box) {
        int m = box.length, n = box[0].length;
        char[][] ans = new char[n][m];  // 用来构建返回值的二维数组
        // 首先逐行处理，把石头挪到该放的地方去
        for (int i = 0; i < m; ++i) {
            // 首先假设当前 i 行可放的位置是 pos
            int pos = n - 1;
            // 然后从右往左遍历，逐个更新石头的位置
            for (int j = n - 1; j >= 0; --j) {
                if (box[i][j] == '#') {
                    // 遇到了石头，先把它放到该放的位置去: 即为 碰到障碍物的前一个位置。
                    box[i][pos] = '#';
                    // 更新pos的位置
                    pos--;
                    // 如果最后一列已经有石头了， 那么在box[i][pos] = ‘#’ 的时候， 相当于石头 虽然已经被覆盖了， 但是 从空间上来讲：石头位置没有发生改变。 即为：挪动前的位置：不需要置空： '.'
                    // 如果最后一列没有石头：那么在bos[i][pos] = '#'的时候， 相当于改变石头的位置，放到了最后一列。那么需要将挪动前的位置置为空： 即为：‘。’
                    if (pos != j - 1){
                        box[i][j] = '.';
                    }
                }
                // 如果遇到了障碍物，那么就更新可放的位置为障碍物的下一个位置（左边）
                else if (box[i][j] == '*'){
                    pos = j - 1;
                }

            }
        }
        // 然后把更新后的位置映射到返回值中
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[j][m - 1 - i] = box[i][j];
            }
        }
        return ans;
    }
}
