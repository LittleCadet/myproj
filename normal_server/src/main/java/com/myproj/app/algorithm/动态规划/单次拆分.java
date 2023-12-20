package com.myproj.app.algorithm.动态规划;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * 思路：
 * 动态规划：
 *      1. 最优子结构的确立：
 *      在该题中： 动态规划代表的是： 给定字符串的前i个字符， 是否能在wordDict中找到。
 *      而前i个字符的问题拆解为： 前j 【j<i】个字符是否能被找到 + 当前j到i之间的字符是否能在wordDict中找到。
 *      所以最优子结构的设计是：
 *          res[j] && wordDict.contains(s.substring(j,i))
 *          且动态规划存储的类型为： boolean数组， 数组中的第i个元素的boolean值， 代表给定字符串的前i个字符是否能从wordDict找到。
 *          且动态规划的boolean数组的长度为： s.length() + 1  , 因为第0个元素代表空字符串：默认为true；
 *      2. 滑动窗口的设计：
 *      快慢指针： 即为双重循环。
 *      核心难点在于： 如何确立 快慢指针的范围。
 *      结合最优子结构来考虑：
 *          需要的是前i个字符是否能被找到。
 *              所以慢指针的范围【即为以前的值】： 一定是 i< s.length();
 *                 快指针的范围【即为当前的值】： 一定是 j<i;
 *      考虑空字符串的场景：
 *          所以res[0]=true; i<= s.length, 且 i 从1开始。 而j从0开始。
 *      3. 返回：直接返回数据中的最后一个元素的值即可。代表： 给定的字符串是否能在wordDict中找到。
 *      4. 确定不用执行最优子结构都知道的结果：
 *          res[0] = true;
 *
 *
 *
 * @author shenxie
 * @date 2023/12/17
 */
public class 单次拆分 {

    public static void main(String[] args) {
        List<String> sts = new ArrayList<>();
        sts.add("leet");
        sts.add("code");
        System.out.println(wordBreak("leetcode", sts));;
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] res = new boolean[s.length() + 1];
        // 表示： 空字符串。 初始化为true
        res[0] = true;
        for(int i =1; i<= s.length(); i++) {
            // j<i的原因： 判定前i个元素：是否能在wordDict中找到。
            for(int j = 0 ; j < i; j++) {
                // 这里需要res[j]的原因： 判定前j个字符是否在wordDict中。
                // 而wordDict.contains(s.substring(j,i))的原因： 判定后N个字符是否在wordDict中。
                if(res[j] && wordDict.contains(s.substring(j,i))){
                    res[i] = true;
                }
            }
        }
        // 返回数据最后一个元素的原因： 代表整个s是否都可以在wordDict中找到。
        return res[s.length()] ;

    }
}
