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
        System.out.println(simplifyPath("/home//foo/"));
    }

    public static String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        List<String> excludes = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        boolean flag = false;
        excludes.add(".");
        excludes.add("..");
        String[] sts = path.split("/");
        // 将非“.” 和 “..”的路径塞入栈中， 当遇到“..”时， 从栈顶取出。
        for (int i = 0; i < sts.length; i++) {
            if (!excludes.contains(sts[i]) && !sts[i].isEmpty()) {
                // 塞到最后一个
                stack.offerLast(sts[i]);
            } else if (sts[i].equals("..")) {
                // 从栈顶poll出来
                stack.pollLast();
            }
        }
        buffer.append("/");
        // 将剩余stack中的路径， 从栈底 poll出来。
        while (!stack.isEmpty()) {
            // 从栈底poll出来。
            String string = stack.pollFirst();
            if (string.isEmpty()) {
                continue;
            }
            if (flag) {
                buffer.append("/");
            }
            buffer.append(string);
            flag = true;
        }
        return buffer.toString();
    }
}
