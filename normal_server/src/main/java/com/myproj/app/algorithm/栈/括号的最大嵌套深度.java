package com.myproj.app.algorithm.栈;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定 有效括号字符串 s，返回 s 的 嵌套深度。嵌套深度是嵌套括号的 最大 数量。
 *
 * 示例 1：
 * 输入：s = "(1+(2*3)+((8)/4))+1"
 *  输出：3
 * 解释：数字 8 在嵌套的 3 层括号中。
 *
 * 示例 2：
 * 输入：s = "(1)+((2))+(((3)))"
 * 输出：3
 * 解释：数字 3 在嵌套的 3 层括号中。
 *
 * 示例 3：
 * 输入：s = "()(())((()()))"
 * 输出：3

        思路：
            - 方法1： 纯for循环
            - 方法2： 栈 【推荐】

 * @author shenxie
 * @date 2025/11/5
 */
public class 括号的最大嵌套深度 {

    public static void main(String[] args) {
        System.out.println(maxDepth("(1)+((2))+(((3)))"));
    }

    /**
     * 方法1： 纯for循环
     */
    public static int maxDepth(String s) {
        int result = 0 , tmp = 0 ;
        for(int i = 0 ; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                tmp ++;
                result = Math.max(result, tmp);
            }else if(s.charAt(i) == ')') {
                tmp --;
            }
        }
        return result;
    }

    /**
     * 方法2： 栈：
     */
    public static int maxDepthV2(String s) {
        int result = 0 ;
        Deque<Character> stack = new LinkedList<>();

        for(int i = 0 ; i<s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(s.charAt(i));
                result = Math.max(result, stack.size());
            }else if(s.charAt(i) == ')') {
                stack.pop();
            }
        }
        return result;
    }
}
