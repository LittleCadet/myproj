package com.myproj.app.algorithm.回溯.不重复选择元素;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 *      解法：
 *          回溯
 *
 *
 * @author shenxie
 * @date 2025/9/19
 */
public class 括号生成 {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    private static void backtrack(List<String> ans, StringBuilder builder, int open , int close, int max) {
        if(builder.toString().length() == max * 2) {
            ans.add(builder.toString());
            return;
        }
        if(open < max) {
            builder.append("(");
            backtrack(ans, builder, open + 1, close, max);
            // 回到的状态是：builder.length() - 1, 不能是open, 因为可能会出现 ()()的情况， 则open过小。
            builder.deleteCharAt(builder.length() -1);
        }

        // 注意： close < open ， 而不是 close < max: 因为： 括号必定需要现有 (, 再有 )。  如果是 close < max, 则 可以出现 ) ( 的组合。
        if(close < open) {
            builder.append(")");
            backtrack(ans, builder, open, close + 1, max) ;
            builder.deleteCharAt(builder.length() -1);
        }
    }
}
