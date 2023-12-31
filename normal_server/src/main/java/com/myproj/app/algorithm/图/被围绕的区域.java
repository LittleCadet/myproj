package com.myproj.app.algorithm.图;

/**
 * 题目：
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 示例 1：
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * 思路：
 *      1. 递归：
 *          核心思想：
 *              1.1 农村包围城市： 即为： 从外向内递归。 将所有与边界有关联的'O'置为'A'。
 *              1.2 再将所有'A'， 还原为‘O’, 并且， 与边界的‘O’无关的'o' 置为‘X’即可。
 * @author shenxie
 * @date 2023/12/31
 */
public class 被围绕的区域 {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        solve(board);
        System.out.println();
    }

    /**
     * 核心思想：
     *  1. 农村包围城市： 即为： 从外向内递归。 将所有与边界有关联的'O'置为'A'。
     *  2. 再将所有'A'， 还原为‘O’, 并且， 与边界的‘O’无关的'o' 置为‘X’即可。
     */
    public static void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        // 将第一列 和 最后一列的'O' 全部置为'A'
        for(int i = 0; i< n ; i++){
            dfs(board, i, 0);
            dfs(board, i, m-1);
        }

        // 将第一行 和 最后一行的'0' 全部置为'A'
        for(int i = 0; i < m; i++) {
            dfs(board, 0, i);
            dfs(board, n-1, i);
        }

        // 将四边是'A'的， 全部置为'O';
        // 将'O', 全部置为'X'；
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] == 'A') {
                    board[i][j] = 'O';
                }else if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }

    private static void dfs(char[][] board, int i, int j){
        if(isOverSize(board, i, j) ) {
            return;
        }
        if(board[i][j] != 'O') {
            return;
        }

        board[i][j] = 'A';

        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i , j + 1);
        dfs(board, i,  j - 1);
    }

    private static boolean isOverSize(char[][] board, int i, int j) {
        return i<0 || j <0 || i>=board.length || j >= board[0].length;
    }
}
