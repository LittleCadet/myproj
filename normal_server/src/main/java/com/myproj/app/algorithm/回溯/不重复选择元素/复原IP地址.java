package com.myproj.app.algorithm.回溯.不重复选择元素;

import java.util.ArrayList;
import java.util.List;

/**
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
                dfs(s, j + 1, k - 1);
                t.remove(t.size() - 1);
            }
        }
    }
}
