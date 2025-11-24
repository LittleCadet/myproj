package com.myproj.app.algorithm.字符串;

/**
 * 返回 s 字典序最小的
 * ，该子序列包含 s 的所有不同字符，且只包含一次。
 *
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 *
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *
 *      思路：
 *          - 题意：返回字典序最小的，且每个字符只能包含一次。
 *              所以：会有将ans的答案不断修正的过程：
 *          - 注意： 这题很特殊： 最小子序列： 并没有 Math.min(x1,x2)的过程。
 *
 * @author shenxie
 * @date 2025/11/20
 */
public class 不同字符的最小最序列 {

    public static void main(String[] args) {
        System.out.println(smallestSubsequence("bcabc"));
    }

    /**
     *对于 s=cbacdcbc，从左到右遍历其中的字母。
     *
     * 1.    s[0]=c。由于只遍历了一个字母，目前已知字典序最小的字符串是 c。
     * 2.    s[1]=b。如果右边没有字母 c，那么 s[0]=c 必须保留；实际上右边还有字母 c，我们可以去掉 c，改用 b 当作目前字典序最小的字符串。
     * 3.    s[2]=a。同样的，由于右边还有字母 b，我们可以去掉 b，改用 a 当作目前字典序最小的字符串（下面记作 ans）。
     * 4.    s[3]=c。由于 c 比 a 大，可以接在 a 后面，目前 ans=ac。
     * 5.    s[4]=d。由于 d 比 c 大，可以接在 c 后面，目前 ans=acd。
     * 6.    s[5]=c。由于 acd 里面已经有 c 了，直接跳过。目前 ans=acd。
     * 7.    s[6]=b。我们发现 b 比 d 小，能不能像上面 s[1] 和 s[2] 那样，去掉 d 替换成 b 呢？这是不行的，因为后面没有 d 了，我们只能老老实实地接在 d 后面，目前 ans=acdb。
     * 8.    s[7]=c。由于 acdb 里面已经有 c 了，直接跳过。
     */
    public static String smallestSubsequence(String S) {
        char[] s = S.toCharArray();

        // 最终输出的答案
        StringBuilder ans = new StringBuilder(26);
        // 统计每个字母的出现次数： 所以26的size足够
        int[] left = new int[26];
        // 该字母是否在ans中，所以26的size足够
        boolean[] inAns = new boolean[26];

        for (char c : s){
            left[c - 'a']++;
        }


        for (char c : s) {
            // 该字符的出现次数 -1
            left[c - 'a']--;
            // ans 中不能有重复字母
            if (inAns[c - 'a']) {
                continue;
            }
            // 设 x = ans.charAt(ans.length() - 1)，
            // 如果 c < ans中最后一个字符x，且右边还有 x，那么可以把 x 去掉，因为后面可以重新把 x 加到 ans 中
            // 这里必须是while, 不能if, 因为ans中可能会包含多个字符 > c， 所以要while不断去除：
            // - eg: 字符串为bcabc, 而ans中含有bc, 当前是a, 则应该去除bc， 保留a.
            while (ans.length() >0 && c < ans.charAt(ans.length() - 1) && left[ans.charAt(ans.length() - 1) - 'a'] > 0) {
                // 标记 x 不在 ans 中
                inAns[ans.charAt(ans.length() - 1) - 'a'] = false;
                ans.deleteCharAt(ans.length() - 1);
            }
            // 把 c 加到 ans 的末尾
            ans.append(c);
            // 标记 c 在 ans 中
            inAns[c - 'a'] = true;
        }
        return ans.toString();
    }
}
