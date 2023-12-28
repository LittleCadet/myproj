package com.myproj.app.algorithm.哈希表;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：
 * 给定两个字符串 s 和 t ，判断它们是否是同构的。
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 * 示例 1:
 * 输入：s = "egg", t = "add"
 * 输出：true
 * 示例 2：
 * 输入：s = "foo", t = "bar"
 * 输出：false
 *
 * 思路：
 *      1. 双map判定： 因为： 根据题意： s与t是双射关系。
 *
 * @author shenxie
 * @date 2023/12/28
 */
public class 同构字符串 {

    public static void main(String[] args) {
        System.out.println(isIsomorphic("egg", "add"));
    }

    public static boolean isIsomorphic(String s, String t) {
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();
        for(int i = 0; i< s.length(); i++) {
            // 需要双map判定的原因：因为s 与 t是双射关系：
            // sss 和 ttt 每个位置上的字符是否都一一对应，:
            // 即 sss 的任意一个字符被 ttt 中唯一的字符对应，同时 ttt 的任意一个字符被 sss 中唯一的字符对应
            boolean s2sFlag = s2t.containsKey(s.charAt(i)) && s2t.get(s.charAt(i)) != t.charAt(i);
            boolean t2sFlag = t2s.containsKey(t.charAt(i)) && t2s.get(t.charAt(i)) != s.charAt(i);
            if( s2sFlag || t2sFlag ){
                return false;
            }
            s2t.put(s.charAt(i), t.charAt(i));
            t2s.put(t.charAt(i), s.charAt(i));
        }
        return true;
    }
}
