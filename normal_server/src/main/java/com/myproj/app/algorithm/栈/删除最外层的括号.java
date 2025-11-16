package com.myproj.app.algorithm.栈;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 有效括号字符串为空 ""、"(" + A + ")" 或 A + B ，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。
 *     例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
 * 如果有效字符串 s 非空，且不存在将其拆分为 s = A + B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
 * 给出一个非空有效字符串 s，考虑将其进行原语化分解，使得：s = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
 * 对 s 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 s 。
 *
 * 示例 1：
 * 输入：s = "(()())(())"
 * 输出："()()()"
 * 解释：
 * 输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
 * 删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
 *
 * 示例 2：
 * 输入：s = "(()())(())(()(()))"
 * 输出："()()()()(())"
 * 解释：
 * 输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
 * 删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
 *
 * 示例 3：
 * 输入：s = "()()"
 * 输出：""
 * 解释：
 * 输入字符串为 "()()"，原语化分解得到 "()" + "()"，
 * 删除每个部分中的最外层括号后得到 "" + "" = ""。
 *
 *      思路：
 *          - 栈： 【题意很重要： 删除最外层的括号！！！】
 *              - 先去除最外层的"("
 *              - 再stringBuilder进场
 *              - 最终压栈 "("
 *          - 括号的删除： 这个概念： 与 {@link 移除无效的括号}很类似： 都是通过stringBuilder完成。
 *
 * @author shenxie
 * @date 2025/11/5
 */
public class 删除最外层的括号 {

    public static void main(String[] args) {
        System.out.println(removeOuterParentheses("()()"));
    }

    public static String removeOuterParentheses(String s) {
        StringBuilder builder = new StringBuilder();
        Deque<Character> stack = new LinkedList<>();
        for(int i = 0 ; i<s.length(); i++) {

            // 题意：删除每个原语字符串的最外层括号，所以对于栈深为 1 的情况【即为 "()"】不能保留。
            // 即为 应该先判定 ")"， 之后直接pop：  即为去除最外层括号
            if(s.charAt(i) == ')') {
                stack.pop();
            }

            // 去除最外层括号后， 且stack不为空时， 不管是"(" 还是 ")" 【因为题目保证是有效括号】， 都应该重新build，
            if( ! stack.isEmpty()) {
                builder.append(s.charAt(i));
            }

            // 最后压栈
            if(s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            }
        }

        return builder.toString();
    }
}
