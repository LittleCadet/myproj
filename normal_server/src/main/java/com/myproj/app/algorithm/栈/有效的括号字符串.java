package com.myproj.app.algorithm.栈;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个只包含三种字符的字符串，支持的字符类型分别是 '('、')' 和 '*'。请你检验这个字符串是否为有效字符串，如果是 有效 字符串返回 true 。
 * 有效 字符串符合如下规则：
 *
 *     任何左括号 '(' 必须有相应的右括号 ')'。
 *     任何右括号 ')' 必须有相应的左括号 '(' 。
 *     左括号 '(' 必须在对应的右括号之前 ')'。
 *     '*' 可以被视为单个右括号 ')' ，或单个左括号 '(' ，或一个空字符串 ""。
 *
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "(*)"
 * 输出：true
 *
 * 示例 3：
 * 输入：s = "(*))"
 * 输出：true
 *
 *      思路：
 *          - 栈： 遇到括号： 栈应该优先考虑
 *
 * @author shenxie
 * @date 2025/11/5
 */
public class 有效的括号字符串 {

    public static void main(String[] args) {
        System.out.println(checkValidString("(*))"));
    }

    public static boolean checkValidString(String s) {
        // 存储 ”(“的下标
        Deque<Integer> stack1 = new LinkedList<>();
        // 存储 "*" 的下标
        Deque<Integer> stack2 = new LinkedList<>();

        for(int i = 0 ; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack1.push(i);
            }else if(s.charAt(i) == '*'){
                stack2.push(i);
            }else{
                // 优先弹出左括号：因为：* 是一个万能符， 应该最后再用
                if( ! stack1.isEmpty()) {
                    stack1.pop();
                }else if( ! stack2.isEmpty()) {
                    stack2.pop();
                }else{
                    // 都不匹配时， 则不满足
                    return false;
                }
            }
        }

        // 跳出for循环后， stack1 和 stack2可能还有元素， 当 左括号 的下标 > 星号下标时： 一定不满足
        while( ! stack1.isEmpty() && ! stack2.isEmpty()) {
            int index1 = stack1.pop();
            int index2 = stack2.pop();
            if(index1 > index2) {
                return false;
            }
        }

        // 最后返回 左括号的栈 是否为空即可
        return stack1.isEmpty();
    }
}
