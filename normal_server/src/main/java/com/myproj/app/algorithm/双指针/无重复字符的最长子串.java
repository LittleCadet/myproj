package com.myproj.app.algorithm.双指针;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目：
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串
 *
 * 思路：
 * 1. 滑动窗口 + set容器：
 *      1.1 快慢指针： 最大长度的不重复的子串为： 右指针 - 左指针。 特殊api: string.charAt(index);
 *      1.2 set容器： 用于判定： 右指针的元素是否出现过。
 * 2. 其中左右指针； 都在一直往前走。
 * 3. 只不过左指针每一动一次：
 *    3.1 set就移除对应的字节。
 *    3.2 右指针会不停地往前移， 除非该字节出现过。
 *
 * @author shenxie
 * @date 2023/12/10
 */
public class 无重复字符的最长子串 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringV2("pwwkew"));
    }

    /**
     * 自己写的
     */
    public static int lengthOfLongestSubstringV2(String s) {
        // 滑动窗口。即为通过左右指针的形式， 来截取最大不重复的子串。
        // 其中左右指针； 都在一直往前走。
        // 只不过左指针每一动一次：
        // 1. set就移除对应的字节。
        // 2. 右指针会不停地往前移， 除非该字节出现过。
        // right为右指针
        int right = 0;
        int max = 0;
        // 哈希集合，记录每个字符是否出现过
        Set<Character> set = new HashSet<>();
        // i: 为左指针
        for(int i = 0; i<s.length(); i++) {
            if(i != 0 ) {
                // 左指针向右移动一格，移除一个字符
                set.remove(s.charAt(i -1) );
            }

            while(right < s.length() && ! set.contains(s.charAt(right ))) {
                set.add(s.charAt(right ));
                // 不断移动右指针
                right ++;
            }

            // 当前的最长子串为： 右指针 - 左指针。
            max = Math.max(max, right - i );
        }

        return max;
    }
}
