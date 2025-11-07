package com.myproj.app.algorithm.栈;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 *     () 得 1 分。
 *     AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 *     (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 *
 * 示例 1：
 * 输入： "()"
 * 输出： 1
 *
 * 示例 2：
 * 输入： "(())"
 * 输出： 2
 *
 * 示例 3：
 * 输入： "()()"
 * 输出： 2
 *
 * 示例 4：
 * 输入： "(()(()))"
 * 输出： 6
 *
 *      思路：
 *          - 解法1：递归 + 分治
 *          - 解法2：栈
 *
 *
 * @author shenxie
 * @date 2025/11/5
 */
public class 括号分数 {

    public static void main(String[] args) {
//        System.out.println(scoreOfParenthesesV2("()"));
        System.out.println(scoreOfParenthesesV2("(()(()))"));
    }

    /**
     * 方法一： 递归 + 分治
     *
     * 分治算法：本质是 将一个大问题，拆分为多个子问题， 递归求解。
     */
    public static int scoreOfParentheses(String s) {
        // 不满足分治算法， 当做特例处理
        if (s.length() == 2) {
            return 1;
        }
        // sum: 用于平衡括号字符串的判定
        // len: 用于分治判定
        int sum = 0, n = s.length(), len = 0;
        for (int i = 0; i < n; i++) {
            // 平衡括号字符串：即为：N对括号的求和 = 0；则记录 sum = 0 时， len的位置，用于分治
            sum += (s.charAt(i) == '(' ? 1 : -1);
            if (sum == 0) {
                len = i + 1;
                break;
            }
        }
        // 当 len = n时 ，则一定符合 (A) 的结构：
        // 但有种情况除外： 即为 输入刚好是 ()时， 不符合 ，所以当做特例来处理
        if (len == n) {
            return 2 * scoreOfParentheses(s.substring(1, n - 1));
        } else {
            // 当 len != n时， 则一定符合 AB 的结构
            return scoreOfParentheses(s.substring(0, len)) + scoreOfParentheses(s.substring(len));
        }
    }

    /**
     * 方法2： 栈：两次出栈 + Math.max(2*v, 1)
     */
    public static int scoreOfParenthesesV2(String s) {
        Deque<Integer> st = new LinkedList<Integer>();
        // 为了兼容两次出栈，而必须在开始的时候：压入0
        st.push(0);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(0);
            } else {
                // 第一次出栈：是为了取出之前 "("的值。
                int v = st.pop();
                // 第二次出栈：是为了将当前的值【Math.max(2 *v, 1)】 与 前一个值累加【即为 A+B模型】,A+B模型的原因：
                // - 如果再之前一个是")"，则 A+B可以。
                // - 如果再之前一个是"("，则 B = 0 ， 所以A+B = A+0， 即为依旧成立。

                // Math.max(2*v , 1): 是为了：
                // - 如果v = 0 时：则前一个是 "("， 所以得分1
                // - 如果v != 0 时： 则当前的")" 与 匹配的"(" 之间隔了一些字符， 则是 2*v
                int top = st.pop() + Math.max(2 * v, 1);
                st.push(top);
            }
        }
        // 此时栈顶：就是答案。
        return st.peek();
    }
}
