package com.myproj.app.algorithm.哈希表;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：
 * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 s 中的每个非空单词之间存在着双向连接的对应规律。
 * 示例1:
 * 输入: pattern = "abba", s = "dog cat cat dog"
 * 输出: true
 *
 * 思路：
 *      1. 双map：
 *          核心思想： 与同构字符串相同： pattern 与 s的关系：是双射关系。
 *                  即为：pattern的每个字符都要与 s中的每个词 一一对应。
 *
 * @author shenxie
 * @date 2023/12/28
 */
public class 单词规律 {
    public static void main(String[] args) {
        System.out.println(wordPattern("abba", "dog cat cat dog"));
    }

    public static boolean wordPattern(String pattern, String s) {

        Map<Character, String> p2s = new HashMap<>();
        Map<String, Character> s2t = new HashMap<>();
        String[] strs = s.split("\\s+");
        if(pattern.length() != strs.length) {
            return false;
        }
        for(int i = 0; i<pattern.length(); i++) {
            boolean p2sFlag = p2s.containsKey(pattern.charAt(i)) && ! p2s.get(pattern.charAt(i)).equals(strs[i]);
            boolean s2tFlag = s2t.containsKey(strs[i]) && ! s2t.get(strs[i]).equals(pattern.charAt(i));
            if(p2sFlag || s2tFlag){
                return false;
            }
            p2s.put(pattern.charAt(i), strs[i]);
            s2t.put(strs[i], pattern.charAt(i));
        }
        return true;
    }
}
