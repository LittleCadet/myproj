package com.myproj.app.algorithm.动态规划;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目：
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
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
public class 单词拆分 {

    public static void main(String[] args) {
        List<String> sts = new ArrayList<>();
        sts.add("leet");
        sts.add("code");
        System.out.println(wordBreakCopy("leetcode", sts));;
    }

    /**
     * 以leet code为例，理解官解状态转移：
     * 当i=0时，""为空字符串，dp[0] = true
     * 当i=1时，"l"不在字典，dp[1]=false
     * i=2时，"le"不在字典，dp[2]=false
     * i=3时，"lee"不在字典，dp[3]=false
     * i=4时，"leet"在字典，dp[4]=true
     * 由于有两个位置，0和4都为true，接下来要检查两个子串
     * i=5时，"leetc"和"c"都不在字典，dp[5]=false
     * i=6时，"leetco"和"co"都不在字典，dp[6]=false
     * i=7时，"leetcod"和"cod"都不在字典，dp[7]=false
     * i=8时，"leetcode"不在字典，但"code"在字典，dp[8]=true
     */
    public static boolean wordBreakCopy(String s, List<String> wordDict) {
        boolean[] res = new boolean[s.length() + 1];
        // 表示： 空字符串。 初始化为true
        res[0] = true;
        // i 从1开始， s.length()结束的原因： 因为 substring()是左开右闭区间， 所以必须要从1开始，而 到 s.length()的原因是 为了substring 整个字符串。
        for(int i =1; i<= s.length(); i++) {
            // j<i的原因： 判定前i个元素：是否能在wordDict中找到。
            for(int j = 0 ; j < i; j++) {
                // 这里需要res[j]的原因： 判定前j个字符是否在wordDict中。
                // 而wordDict.contains(s.substring(j,i))的原因： 判定后N个字符是否在wordDict中。
                if(res[j] && wordDict.contains(s.substring(j,i))){
                    // res[j]相当于存量数据， 用动态规划。
                    // res[i]相当于增量数据， 为后续的动态规划做准备。
                    res[i] = true;
                }
            }
        }
        // 返回数据最后一个元素的原因： 代表整个s是否都可以在wordDict中找到。
        return res[s.length()] ;

    }


    /**
     * 错误方法： 不能通过 replaceAll的方式来解决， 因为wordDict的替换顺序是不一定的： eg:
     *  当 s = "cars" , wordDict = ["car","ca","rs"]时， 会判定为false, 但实际为true
     */
    public static boolean wordBreakV2(String s, List<String> wordDict) {
        for(String word : wordDict) {
            s = s.replaceAll(word, "");
        }

        return s.length() == 0;
    }
}
