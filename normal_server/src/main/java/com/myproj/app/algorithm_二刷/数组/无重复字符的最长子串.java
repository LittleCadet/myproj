package com.myproj.app.algorithm_二刷.数组;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。
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
 *          - map 不需要删除left之前的元素， 因为left 一直在往前走，所以如果 right的元素 与 left之前的元素匹配上，那么left的下标会因为取大 而 不会变
 * @author shenxie
 **/
public class 无重复字符的最长子串 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringV2Copy("abcbadcbb"));
//        System.out.println(lengthOfLongestSubstringV2("dvdf"));
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

    public static int lengthOfLongestSubstringV2Copy(String s) {
        int result = 0 ;
        int left = -1 ;
        Map<Character, Integer> map = new HashMap<>();
        for(int right = 0 ; right < s.length(); right++) {
            if(map.containsKey(s.charAt(right))) {
                // 1. map 不需要删除left之前的元素， 因为left 一直在往前走，所以如果 right的元素 与 left之前的元素匹配上，那么left的下标会因为取大 而 不会变


                // 2.1 左指针只能一直往前走：意味着：左指针 取大 ：左指针的步长不固定， 通过大小比较来的， 原因见 3
                // 2.2 因为： 避免出现 入参：abba， 但由于第三个元素b的出现， 更新了左指针为1， 之后又出现第4个元素a, 由于左指针未取大的原因， 导致左指针又回去了。
                // 2.3 这里只能用Math.max(left, map.get(s.charAt(right))) ， 而不能用 Math.max(left, right);
                // 2.4 因为right代表当前下标， map.get(s.charAt(right))代表以前的该元素的下标
                left = Math.max(left, map.get(s.charAt(right)));
                System.out.println(right +":"+ map.get(s.charAt(right)));
            }
            // 3. 需要保存所有不重复元素的下标， 这样才能获取到每个元素【即使重复】的下标
            map.put(s.charAt(right), right);
            // 4. 更新result, 取result最大值
            result = Math.max(result, right - left);
        }
       return result;
    }
}
