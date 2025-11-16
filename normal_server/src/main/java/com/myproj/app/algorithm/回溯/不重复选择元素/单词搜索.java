package com.myproj.app.algorithm.回溯.不重复选择元素;

import com.myproj.app.algorithm.回溯.重复选择元素.组合总和;
import com.myproj.app.algorithm.图.岛屿数量;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例 1：
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCCED"
 * 输出：true
 *
 * 示例 2：
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "SEE"
 * 输出：true
 *
 * 示例 3：
 * 输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCB"
 * 输出：false
 *
 *      思路： 回溯
 *          - 重复选择元素：
 *              - {@link 组合总和}：是不主动改变i的值， 则 数值 可以做到重复选择。
 *              - {@link 全排列} 也没有主动改变 下标， 而是通过for循环的方式完成， 所以 数值可以重复选择
 *          - 不重复选择元素：
 *              - 与 {@link 组合} / {@link 单词搜索} / {@link 子集} / {@link 括号生成} / {@link 电话号码的字母组合}类似：
 *              - 是主动变更index的方式【index + 1】，做到不重复选择元素。
 *
 *          - 类似于岛屿问题：
 *              - {@link 岛屿数量} 等： 遵循先污染，后治理的原理
 *
 * @author shenxie
 * @date 2025/9/19
 */
public class 单词搜索 {

    public static void main(String[] args) {
//        System.out.println(exist(new char[][]{{'A','B','C','E'}, {'S','F','C','S'},{'A','D','E','E'}}, "ABCB"));
        System.out.println(existV2(new char[][]{{'A','B','C','E'}, {'S','F','C','S'},{'A','D','E','E'}}, "SEE"));
    }

    /**
     * 错误方法： 不能通过 HashMap 对元素计数的方式来解答， 因为：题意要求：字母需由相邻单元格的字母组成。 而 hashMap无法表达相邻的概念 ！！！
     */
    public static boolean exist(char[][] board, String word) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0 ; i<board.length; i++) {
            for(int j = 0 ; j<board[0].length; j++) {
                int times = map.getOrDefault(board[i][j], 0);
                map.put(board[i][j], times +1);
            }
        }

        for(int i =0 ;i<word.length(); i++) {
            char ch = word.charAt(i);
            if(map.containsKey(ch) && map.get(ch) >= 1) {
                int times = map.get(ch) - 1;
                map.put(ch, times);

            }else{
                return false;
            }
        }

        return true;
    }


    public static boolean existV2(char[][] board, String word) {

        for(int i = 0 ; i<board.length; i++) {
            for(int j = 0 ; j<board[0].length; j++) {
                // 这里必须要通过遍历所有的board的元素的方法来执行， 因为需要找到起点元素的位置， 才能以此为原点，找其他的相邻元素。
                if( dfs(i,j, board, word, 0)) {
                    // 后治理
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs (int i , int j, char[][] board, String word, int index) {
        // 一旦起点元素 不匹配时， 直接 返回。
        if(i>= board.length || i<0 ||
                j>= board[0].length || j <0 ||
                board[i][j] != word.charAt(index)) {
            return false;
        }
        // 已经找到了 word的最后一个字符， 且在相邻的单元格中能找到， 则返回 true
        if(index == word.length() - 1) {
            return true;
        }

        System.out.println("i:" + i + ",j:" + j + ",index:" + index);

        // 先污染
        // 随便填，目的是为了不再重复运算， 最后会回溯的
        board[i][j] = '0';

        boolean res = dfs(i+1, j, board, word, index + 1) || dfs(i-1, j , board, word, index + 1) ||
                dfs(i, j+1, board, word, index + 1) || dfs(i, j-1, board, word, index + 1);

        board[i][j] = word.charAt(index);

        return res;
    }
}
