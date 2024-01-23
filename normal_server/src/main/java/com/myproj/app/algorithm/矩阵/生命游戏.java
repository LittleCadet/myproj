package com.myproj.app.algorithm.矩阵;

/**
 * 题目：
 * 根据 百度百科 ， 生命游戏 ，简称为 生命 ，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态： 1 即为 活细胞 （live），或 0 即为 死细胞 （dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 *     如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 *     如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 *     如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 *     如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态。
 *
 * 示例 1：
 * 输入：board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * 输出：[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 *
 * 思路：
 *      1. 先影响， 再计算：
 *          1.1 影响： 指的是： 以当前的活细胞为核心， 看看周边有多少活细胞。
 *          1.2 计算：
 *                   a. 原来是活的，周围有2-3个活的，成为活的
 *                   b. 原来是死的，周围有3个活的，成为活的
 *                   c. 其他都是死了
 *
 * @author shenxie
 * @date 2024/1/16
 */
public class 生命游戏 {

    public static void main(String[] args) {
        int[][] board = new int[][]{{0,1,0}, {0,0,1}, {1,1,1},{0,0,0}};
        gameOfLife(board);
    }

    public static void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int[][] directions = new int[][]{{-1,-1},{-1, 0},{-1, 1},{0,-1},{0,1}, {1,-1},{1,0},{1,1}};
        // 先影响。
        for(int i = 0; i<rows; i++) {
            for(int j = 0; j<cols;j++) {
                // 以(i,j)为核心， 向外扩散， 用于计算：周边有多少个活细胞。
                // 原理： 周边每出现1个活细胞， 那么核心+10, 所以 liveNums = 核心数 / 10
                // 用于后面的计算。
                if(board[i][j] % 10 == 1) {
                    affect(directions, i,j, board, rows, cols);
                }
            }
        }

        // 再计算
        for(int i = 0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                cal(board[i][j], board, i, j);
            }
        }
    }

    private static void affect(int[][] directions, int i, int j, int[][] board, int rows, int cols){
        int newI = 0;
        int newJ = 0;
        for(int[] d : directions){
            newI = d[0] + i;
            newJ = d[1] + j;
            boolean validRow = newI >=0 && newI < rows;
            boolean validCol = newJ >= 0 && newJ < cols;
            if( validRow && validCol){
                board[newI][newJ] += 10;
            }
        }
    }

    private static void cal(int num, int[][] board, int i , int j){
        // 规则：
        // 1. 原来是活的，周围有2-3个活的，成为活的
        // 2. 原来是死的，周围有3个活的，成为活的
        // 3. 其他都是死了
        boolean liveFlag = num % 10 == 1 && num / 10 == 2 || num / 10 == 3;
        boolean deadFlag = num % 10 == 0 && num / 10 == 3;
        if(liveFlag || deadFlag){
            board[i][j] = 1;
        }else{
            board[i][j] = 0;
        }
    }
}
