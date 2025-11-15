package com.myproj.app.algorithm.动态规划.多维动态规划;

/**
 * 题目：
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 示例 1：
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * 思路：
 * 1. 动态规划：
 *      核心思想： 到当前节点的总路径数 ： 左边节点的总路径数 + 上边节点的总路径数：
 *                  即为：ans[i][j] = ans[i - 1][j] + ans[i][j - 1];
 *
 * @author shenxie
 * @date 2023/12/31
 */
public class 不同路径II {

    /**
     * 动态规划正确： 但此题不能仿效{@link 不同路径} 方法2的解法来： 因为：如果只有一行 且 该行有石头时， 会因为初始化错误的原因，导致答案错误。
     * eg: [[0,1,0,0]]: 如果按照{@link 不同路径} 方法2来， 答案会是1 ， 应该为0.
     */
    public int uniquePathsWithObstaclesCopy(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int[][] ans = new int[obstacleGrid.length][obstacleGrid[0].length];
        // 第一行：动态规划：数据准备
        // 假设：只有一行路径， 则只能往前走， 所以ans[0][i] = 1;
        for (int i = 0; i < obstacleGrid[0].length; i++) {
            // 碰到障碍物时， 当前节点和剩余节点： 都没路走了。
            if (obstacleGrid[0][i] == 1) {
                break;
            } else {
                ans[0][i] = 1;
            }
        }

        // 第一列：动态规划； 数据准备
        // 假设: 只有一列路径， 则只能往下走， 所以ans[i][0] = 1;
        for (int i = 0; i < obstacleGrid.length; i++) {
            // 碰到障碍物时， 当前节点和剩余节点： 都没路走了。
            if (obstacleGrid[i][0] == 1) {
                break;
            } else {
                ans[i][0] = 1;
            }
        }

        // 动态规划
        for (int i = 1; i < obstacleGrid.length; i++) {
            for (int j = 1; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 0) {
                    ans[i][j] = ans[i - 1][j] + ans[i][j - 1];
                }
            }
        }
        return ans[ans.length - 1][ans[0].length - 1];
    }

}
