package com.myproj.app.algorithm.栈;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 题目：
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 *     左括号必须用相同类型的右括号闭合。
 *     左括号必须以正确的顺序闭合。
 *     每个右括号都有一个对应的相同类型的左括号。
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 *
 * 思路：
 *      1. hashMap + 栈：
 *          1.1 使用栈的原因： 栈是FILO的。 刚好符合这题的需求。
 *                  当入参遍历完成后， stack.isEmpty为true时， 代表：带有括号都是成双成对的。
 *                  如果未遍历完， stack.isEmpty已经为true时， 代表： 不是成双成对的
 *
 * @author shenxie
 * @date 2023/12/29
 */
public class 有效的括号 {
    public static void main(String[] args) {
        // 不符合要求
//        System.out.println(isValid("{[]}"));
        // 符合要求
        System.out.println(isValidV2("{[]}"));
    }

    public static boolean isValidV2(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Deque<Character> stack = new LinkedList<>();
        // 只能初始化这3个元素。
        // 原因： 一定先出现 左括号， 再出现右括号。
        // 所以 左括号： 一定要先入栈， 不然会返回false.
        // 所以只能初始化这3个元素。
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                // 当前元素 与 栈顶元素不同时， 返回false.
                // stack.peek(): 只查看栈顶元素， 但不会取出！！！！！
                if (stack.isEmpty() || map.get(s.charAt(i)).equals( stack.peek())) {
                    return false;
                }
                // 出栈。
                stack.pop();
            } else {
                // 将([{ 推入栈中。
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }

    /**
     * 以下方法不行：
     * 原因： 对于{[]}， 应该是true , 但会返回false.
     */
    public static boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put(')', '(');
        map.put('{', '}');
        map.put('}', '{');
        map.put('[', ']');
        map.put(']', '[');
        for (int i = 1; i < s.length(); i++) {
            if ((i + 1) % 2 != 0) {
                continue;
            }
            // 只要出现半个括号， 另外半个 必须立刻出现。
            // 不符合题意。
            if (!map.get(s.charAt(i - 1)).equals(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
