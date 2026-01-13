package com.myproj.app.algorithm.动态规划.多维动态规划;

/**
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 *     插入一个字符
 *     删除一个字符
 *     替换一个字符
 *
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 *      思路：
 *          -
 *
 * @author shenxie
 * @date 2026/1/13
 */
public class 编辑距离 {

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
    }

    /**
     * 如果我们有单词 A 和单词 B：
     *     对单词 A 删除一个字符和对单词 B 插入一个字符是等价的。例如当单词 A 为 doge，单词 B 为 dog 时，我们既可以删除单词 A 的最后一个字符 e，得到相同的 dog，也可以在单词 B 末尾添加一个字符 e，得到相同的 doge；
     *     同理，对单词 B 删除一个字符和对单词 A 插入一个字符也是等价的；
     *     对单词 A 替换一个字符和对单词 B 替换一个字符是等价的。例如当单词 A 为 bat，单词 B 为 cat 时，我们修改单词 A 的第一个字母 b -> c，和修改单词 B 的第一个字母 c -> b 是等价的。
     * 这样以来，本质不同的操作实际上只有三种：
     *     在单词 A 中插入一个字符；
     *     在单词 B 中插入一个字符；
     *     修改单词 A 的一个字符。
     *
     */
    public static int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化:
        // 相当于： word1执行了 i 次 删除操作，得到 word2
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        // 相当于：word1执行了 i 次 新增操作， 得到word2
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                // 代表： word1的新增操作： 因为D[i-1][j-1] 已经相同， 所以D[i-1][j]: 相当于word1 新增一个字符 即可
                int insert = D[i - 1][j] + 1;
                // 代表： word1的删除操作：因为D[i-1][j-1] 已经相同， 所以D[i][j-1]: 相当于word1 删除一个字符即可。
                int delete = D[i][j - 1] + 1;
                // 代表： word1的变更操作：
                int update = 0;
                // 如果 word1的第 i -1 个字符 与 word2的第 j-1 个字符相同时， 那么word1不需要执行任何的转化， 就可以得到word2.
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    update = D[i - 1][j - 1];
                }else{
                    // 如果 word1的第 i-1个字符 与 word2的第j-1个字符不同时， 那么word1 => word2：需要执行一次 变更操作
                    update = D[i - 1][j - 1] + 1;
                }
                // 在 删除 ， 插入 和 变更 中 取最小值。
                D[i][j] = Math.min(delete, Math.min(insert, update));
            }
        }
        return D[n][m];
    }
}
