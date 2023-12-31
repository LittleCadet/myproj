package com.myproj.app.algorithm.图;

/**
 * 题目：
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * 示例 1：
 * 输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * 输出：16
 * 解释：它的周长是上面图片中的 16 个黄色的边
 *
 * 思路：
 *      1.递归：
 *          核心思想：
 *              周长的思想：
     *              1. 抵达边界： 周长 +1；
     *              2. 碰到水： 周长 + 1;
     *              3. 已经被检索过的岛屿： 周长 + 0；
     *              4. 将该岛屿的四周周长相加， 即可
 *              3种状态：
 *                  0： 水
 *                  1： 岛屿： 未被检索过。
 *                  2： 岛屿： 被检索过。
 *
 * @author shenxie
 * @date 2023/12/31
 */
public class 岛屿的周长 {

    public static void main(String[] args) {
        System.out.println(islandPerimeter(new int[][]{{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}}));
    }

    public static int islandPerimeter(int[][] grid) {
        for(int i = 0 ; i< grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    return dfs(grid, i,j);
                }
            }
        }
        return 0;
    }

    private static int dfs(int[][] grid, int i, int j){
        // 边界 + 1
        if(isOverSize(grid, i, j )) {
            return 1;
        }
        // 海洋 + 1
        if(grid[i][j] == 0) {
            return 1;
        }
        // 检索过的岛屿： +0
        if(grid[i][j] != 1) {
            return 0;
        }

        grid[i][j] = 2;

        return  dfs(grid, i + 1, j) +
                dfs(grid, i - 1, j) +
                dfs(grid, i, j + 1) +
                dfs(grid, i, j - 1);
    }

    private static boolean isOverSize(int[][] grid, int i , int j){
        return i<0 || j<0 || i>=grid.length || j>=grid[0].length;
    }
}
