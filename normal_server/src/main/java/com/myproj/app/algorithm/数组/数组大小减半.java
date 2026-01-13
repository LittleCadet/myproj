package com.myproj.app.algorithm.数组;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个整数数组 arr。你可以从中选出一个整数集合，并删除这些整数在数组中的每次出现。
 * 返回 至少 能删除数组中的一半整数的整数集合的最小大小。
 *
 * 示例 1：
 * 输入：arr = [3,3,3,3,5,5,5,2,2,7]
 * 输出：2
 * 解释：选择 {3,7} 使得结果数组为 [5,5,5,2,2]、长度为 5（原数组长度的一半）。
 * 大小为 2 的可行集合有 {3,5},{3,2},{5,2}。
 * 选择 {2,7} 是不可行的，它的结果数组为 [3,3,3,3,5,5,5]，新数组长度大于原数组的二分之一。
 *
 * 示例 2：
 * 输入：arr = [7,7,7,7,7,7]
 * 输出：1
 * 解释：我们只能选择集合 {7}，结果数组为空。
 *
 *
 *      思路：
 *          - HashMap
 *
 * @author shenxie
 * @date 2026/1/12
 */
public class 数组大小减半 {

    public static void main(String[] args) {
        System.out.println(minSetSize(new int [] {3,3,3,3,5,5,5,2,2,7}));
    }

    public static int minSetSize(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int sum = 0, ans = 0  ;

        for(Integer num : arr) {
            int times = map.getOrDefault(num , 0);
            map.put( num ,  ++ times);
        }
        list.addAll(map.values());
        Collections.sort(list);

        // 倒序：
        for(int i = list.size() - 1; i >= 0 ; i--) {
            sum += list.get(i);
            ans ++;
            if(sum >= arr.length / 2) {
                break;
            }
        }
        return  ans;
    }
}
