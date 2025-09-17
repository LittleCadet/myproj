package com.myproj.app.algorithm.栈;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目：
 * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 * 请注意，返回的 规范路径 必须遵循下述格式：
 *     始终以斜杠 '/' 开头。
 *     两个目录名之间必须只有一个斜杠 '/' 。
 *     最后一个目录名（如果存在）不能 以 '/' 结尾。
 *     此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * 返回简化后得到的 规范路径 。
 * 示例 1：
 * 输入：path = "/a/./b/../../c/"
 * 输出："/c"
 *
 * 思路：
 *      1. 栈：
 *          核心思想： 1.1 遇到..时， 需要将栈顶元素取出。
 *                   1.2 遇到非“.” 和“..”的元素时， 推入栈中。
 *                   1.3 最后将栈中所有元素从栈底依次吐出即可。
 *
 * @author shenxie
 * @date 2023/12/29
 */
public class 简化路径 {
    public static void main(String[] args) {
        System.out.println(simplifyPathCopy("/home/user/Documents/../Pictures"));
    }

    public static String simplifyPathCopy(String path) {
        String[] names = path.split("/");
        Deque<String> stack = new LinkedList<>();
        for (String name : names) {
            if ("..".equals(name)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (name.length() > 0 && !".".equals(name)) {
                // 不要想 offerLast 是栈 还是 offerFirst是栈，
                // 而是 当前用last 放入， 又用last取出， 就是栈，
                // 当前first放入， 又first取出， 就是栈
                stack.offerLast(name);
            }
        }
        StringBuffer ans = new StringBuffer();
        if (stack.isEmpty()) {
            ans.append('/');
        } else {
            while (!stack.isEmpty()) {
                ans.append('/');
                ans.append(stack.pollFirst());
            }
        }
        return ans.toString();
    }
}
