package com.myproj.app.algorithm.哈希表;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 如果可以，返回 true ；否则返回 false 。
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * 示例 1：
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 *
 * 思路：
 *      1. hashMap:
 *          核心思想： 用hashMap统计magazine的每个字符出现的次数。当ransomNote每使用一个字符时， hashMap对应的元素个数减一；
 * @author shenxie
 * @date 2023/12/28
 */
public class 赎金信 {

    public static void main(String[] args) {
        System.out.println(canConstruct("aab", "baa"));
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        // k-v: magazine的字符 - 每个字符出现的次数
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i<magazine.length(); i++) {
            int times = map.getOrDefault(magazine.charAt(i), 0);
            map.put(magazine.charAt(i), ++times);
        }
        for(int i = 0; i<ransomNote.length(); i++) {
            if(null == map.get(ransomNote.charAt(i)) || 0 == map.get(ransomNote.charAt(i))) {
                return false;
            }
            int times = map.get(ransomNote.charAt(i));
            map.put(ransomNote.charAt(i), --times);
        }
        return true;
    }
}
