package com.myproj.app.algorithm_二刷.矩阵;

/**
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 *
 * 示例 2：
 *
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 *
 *
 * 提示：
 *
 *     n == matrix.length == matrix[i].length
 *     1 <= n <= 20
 *     -1000 <= matrix[i][j] <= 1000
 *
 *
 * 解法：
 *      - 解法1： 通过辅助数组的方式解决：
 *          观察图像可得规律：
 *              - 第i行的数据, 最终会在第n-1-i列
 *              - 第j列的数据， 最终会在第j行。
 *           所以通过辅助数组， 完成对原数组的克隆。 之后借助双重for循环， 即可完成旋转图像。
 *
 *     - 解法2： 通过临时变量的方式解决：
 *          观察图像可得规律：
 *              - 矩阵大小 n 为偶数时，取前 n/2 行、前 n/2 列的元素为起始点；当矩阵大小 n 为奇数时，取前 n/2 行、前 (n+1) /2 列的元素为起始点
 *              - 暂存tmp=matrix[i][j]时：
 *                  - matrix[i][j]←matrix[n−1−j][i]←matrix[n−1−i][n−1−j]←matrix[j][n−1−i]←tmp
 *                  - 本质依旧是：
 *                      - 第i行的数据, 最终会在第n-1-i列
 *                      - 第j列的数据， 最终会在第j行
 *              - 函数表达上述语句：
 *                      int tmp = matrix[i][j];
 *                      matrix[i][j] = matrix[n-1-j][i];
 *                      matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
 *                      matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
 *                      matrix[j][n-1-i] = tmp;
 *
 * @author shenxie
 **/
public class 旋转图像 {

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
        rotateCopy(matrix);
    }

    /**
     * 借助辅助数组 + 规律 ：
     *   tmp[j][n-i-1] = matrix[i][j];
     */
    public static void rotateCopy(int[][] matrix) {
        int n = matrix.length;
        int[][] tmp = new int[n][n];

        // 旋转
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                tmp[j][n-i-1] = matrix[i][j];
            }
        }

        // 赋值原数组
        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n; j++) {
                matrix[i][j] = tmp[i][j];
            }
        }
    }

    public void rotateV2(int[][] matrix) {
        int n = matrix.length;
        // 注意：取值范围：
        // 矩阵大小 n 为偶数时，取前 n/2 行、前 n/2 列的元素为起始点；当矩阵大小 n 为奇数时，取前 n/2 行、前 (n+1) /2 列的元素为起始点
        for(int i = 0 ; i<matrix.length / 2; i++) {
            for(int j = 0 ; j < (matrix.length +1 )/2; j++) {
                // 借助tmp变量， 完成复制操作。
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = tmp;
            }
        }
    }
}
