package com.myproj.app.algorithm.动态规划.多维动态规划;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串 ，使每个子串都是回文串。返回 s 所有可能的分割方案。
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 *
 * 示例 2：
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 *      思路：
 *          - 方法1： 回溯：
 *          - 方法2【推荐】： 回溯 + 动态规划
 *              - 与{@link 回文子串} 和 {@link 最长回文子串}：很类似：
 *                  - 本题：在 {@link 回文子串}的基础上，添加了回溯
 *                  - 中者：动态规划：substring(i,j);
 *                  - 后者：在{@link 回文子串}的基础上， 找到最大值。
 *         - “回文子串”问题：多需要 动态规划  /  回溯
 *
 *
 * @author shenxie
 * @date 2025/11/11
 */
public class 分割回文子串 {
    public static void main(String[] args) {
        System.out.println(partition("aab"));
        System.out.println(partitionV2("aab"));
    }

    /**
     * 方法1： 回溯：
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        partition(s, 0, new ArrayList<>(), res);
        return res;
    }

    private static void partition(String s, int idx, List<String> list, List<List<String>> res) {
        // 注意条件：而不是没有条件
        if (idx == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = idx; i < s.length(); i++) {
            String sub = s.substring(idx, i + 1);
            if (isPalindrome(sub)) {
                list.add(sub);
                partition(s, i + 1, list, res);
                list.remove(list.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }


    /**
     * 方法2： 回溯 + 动态规划
     */
    public static List<List<String>> partitionV2(String s) {
        int n = s.length();
        List<List<String>> results = new ArrayList<>();
        boolean[][] flag = new boolean[n][n];

        for(int i = 0 ; i<n ; i++) {
            // 多维数组填充
            Arrays.fill(flag[i], true);
        }

        for(int i = n-1; i>=0 ; i--) {
            for(int j = i + 1; j<n; j++) {
                // 判定当前是否是回文子串, 这里即使已经知道是回文子串了，
                flag[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 1 || flag[i + 1][j - 1]);
            }
        }
        dfs(s, results, new ArrayList<>(), flag, 0);

        return results;
    }

    private static void dfs(String s, List<List<String>> results, List<String> result, boolean[][] flag , int index){
        if(index == s.length()) {
            results.add(new ArrayList<>(result));
            return;
        }

        for(int i = index; i<s.length(); i++) {
            // 是回文子串时， 用substring做截取。
            if(flag[index][i]) {
                result.add(s.substring(index, i + 1));
                dfs(s, results, result, flag, i + 1);
                result.remove(result.size() - 1);
            }

        }
    }



}
