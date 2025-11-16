package com.myproj.app.algorithm.图;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你两个 m x n 的二进制矩阵 grid1 和 grid2 ，它们只包含 0 （表示水域）和 1 （表示陆地）。一个 岛屿 是由 四个方向 （水平或者竖直）上相邻的 1 组成的区域。任何矩阵以外的区域都视为水域。
 * 如果 grid2 的一个岛屿，被 grid1 的一个岛屿 完全 包含，也就是说 grid2 中该岛屿的每一个格子都被 grid1 中同一个岛屿完全包含，那么我们称 grid2 中的这个岛屿为 子岛屿 。
 * 请你返回 grid2 中 子岛屿 的 数目 。
 *
 * 示例 1：
 * 输入：grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
 * 输出：3
 * 解释：如上图所示，左边为 grid1 ，右边为 grid2 。
 * grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。
 *
 * 示例 2：
 * 输入：grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
 * 输出：2
 * 解释：如上图所示，左边为 grid1 ，右边为 grid2 。
 * grid2 中标红的 1 区域是子岛屿，总共有 2 个子岛屿。
 *
 *      思路：
 *          - 先污染后治理的原则：
 *          - 先统计grid2的岛屿情况， 再将grid2的岛屿放入grid1中校验：
 *              - 检测是否是陆地，
 *              - 检测在grid1中匹配的陆地是否与grid2的岛屿的陆地相同
 *
 *
 * @author shenxie
 * @date 2025/11/16
 */
public class 统计子岛屿 {

    public static void main(String[] args) {
        System.out.println(countSubIslands(null, null));
    }

    public static int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid2.length , n = grid2[0].length;
        List<List<int[]>> ans = new ArrayList<>();
        // 先统计grid2的岛屿情况
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid2[i][j] == 1){
                    List<int[]> path = new ArrayList<>();
                    dfs(i, j, grid2, path);
                    ans.add(path);
                }
            }
        }

        int result = 0;
        for(int i = 0 ; i < ans.size() ; i++){
            int tmp = 0;
            // 将grid2的岛屿 放入 grid1中比较，看在grid1中是否依旧是陆地，是则 + 1
            for(int[] x : ans.get(i)){
                if(grid1[x[0]][x[1]] == 1){
                    tmp++;
                }
            }
            // 校验岛屿的定义： 检测在grid1中匹配的陆地是否与grid2的单个岛屿的陆地数量相同
            if(tmp == ans.get(i).size()){
                result++;
            }
        }
        return result;
    }

    public static void dfs(int i , int j , int[][] grid , List<int[]> path){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1){
            return;
        }
        // 0:水域 1:陆地 2:被检索过的陆地
        grid[i][j] = 2;
        path.add(new int[]{i,j});

        dfs(i-1, j, grid, path);
        dfs(i+1, j, grid, path);
        dfs(i, j-1, grid, path);
        dfs(i, j+1, grid, path);
    }
}
