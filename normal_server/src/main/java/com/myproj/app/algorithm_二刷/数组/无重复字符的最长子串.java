package com.myproj.app.algorithm_二刷.数组;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长
 * 子串的长度。
 *
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 思路：
 *  1. 快慢双指针 + map【用于存储元素的最新索引】
 *      - 核心点： 左指针： 只能往前走：
 *          - 即为：左指针取大： i = Math.max(i, map.get(s.charAt(j)))
 * @author shenxie
 **/
public class 无重复字符的最长子串 {

    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstringV2("dvdf"));
    }

    /**
     * 该方法不行： 因为：未考虑：入参：dvdf的情况： 即为：当元素重复时，因该更新重复元素的下标，
     */
    public static int lengthOfLongestSubstring(String s) {
        char[] ss = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        int length = 0;
        for(int i = 0; i<ss.length; i++) {
            if(set.add(ss[i])) {
                maxLength = Math.max(maxLength, ++length);
            }else{
                length = 1;
                set.clear();
                set.add(ss[i]);
            }
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstringV2(String s) {
        int maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        int i = -1;
        for(int j = 0; j<s.length(); j++) {
            if(map.containsKey(s.charAt(j))) {
                // 左指针只能一直往前走：意味着：左指针 取大 ：
                // 避免出现 入参：abba， 但由于第三个元素b的出现， 更新了左指针为1， 之后又出现第4个元素a, 由于左指针未取大的原因， 导致左指针又回去了。
                i = Math.max(i, map.get(s.charAt(j)));
            }
            map.put(s.charAt(j), j);
            maxLength = Math.max(maxLength, j -i);
        }
        return maxLength;
    }
}
