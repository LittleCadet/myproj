package com.myproj.app.algorithm.回溯.不重复选择元素;

import com.myproj.app.algorithm.动态规划.解码方法;
import com.myproj.app.algorithm.字符串.验证IP地址;
import com.myproj.app.algorithm.数组.比较版本号;
import java.util.ArrayList;
import java.util.List;

/**
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *     例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 *
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 *
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 *
 * 示例 3：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *      思路：
 *          - 回溯：
 *              - 注意前导0的表达方式：
 *                  - 本题：s.charAt(i) == '0' && j > i： eg: 01
 *                  - {@link 验证IP地址}： t[i].length() > 1 && t[i].charAt(0) == '0'： eg: 01
 *                  - {@link 解码方法}： i > 1 && s.charAt(i - 2) != '0'： eg: 01
 *                  - {@link 比较版本号}： 直接通过Integer.parseInt(str)的方式处理即可。eg: 001
 *
 * @author shenxie
 * @date 2025/11/14
 */
public class 复原IP地址 {

    public static void main(String[] args) {
        List<String> results = restoreIpAddresses("101023");
        System.out.println(results);
    }

    static List<String> ans = new ArrayList<>();
    static List<String> t = new ArrayList<>();
    public static List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 4);   // 4表示需要有4段
        return ans;
    }

    public static void dfs(String s, int i, int k) {
        if (k == 0) {
            // 当 i = s.length()时， 代表ans中已经填充了4个元素，且 s中所有元素均已用完。
            // 这里不能使用 t.size == 4, 因为s中的元素即使没有用完， 也会满足。
            if (i == s.length()) {
                // 通过string.join()将list形成 ip
                ans.add(String.join(".", t));
            }
            // 在不满足时， 才返回。
            return;
        }
        // j从i开始 而不是0 , 因为不能选择元素。
        // j不能超过s.length, 且 j < i+3: 因为ip的一个地址段是3位数。
        for (int j = i; j < s.length() && j < i + 3; ++j) {
            // 需要j > i的原因： 不能是前导0： s.substring(i, j+1)：假如：s为01， 那么substring后的结果是在01的时候才不合法，但0可以。
            if (s.charAt(i) == '0' && j > i) {
                return;
            }
            // 注意： s.substring()的起始点。
            int v = Integer.parseInt(s.substring(i, j + 1));
            if (v >= 0 && v <= 255) {
                t.add(s.substring(i, j + 1));
                // 此处必定用 i + 1， 而不是 index + 1: 原因：
                // - index + 1: 代表： i 和 index的增长速度不一致， 会导致 i 比 index大， 从而导致 nums[1] 比 nums[0]大 【即为 选择之前选择过的元素】， 这是不符合题意的【看 例子】。
                // - i + 1: 代表： i 和 index的增长速度一致， 即为 nums[1] > nums[0] 永远成立。
                dfs(s, j + 1, k - 1);
                t.remove(t.size() - 1);
            }
        }
    }
}
