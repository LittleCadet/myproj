package com.myproj.app.algorithm.数组;

/**
 * 题目：
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 * 示例 1:
 * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * 思路：
 *      如果gas的总和 < cost的总和， 则肯定无解。
 *      如果gas的总和 >= cost的总和， 则肯定有解 且 解是最小差值的下一个元素。
 * @author shenxie
 * @date 2023/12/25
 */
public class 加油站 {

    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(new int[]{1, 2,3,4,5}, new int[]{3,4,5,1,2}));
//        System.out.println(canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0;
        int min = 0;
        int index = 0;
        for(int i=0; i< gas.length; i++) {
            sum += gas[i] - cost[i];
            if(sum < min) {
                min = sum;
                index = i + 1;
            }
        }

        return sum < 0? -1 : index;
    }
}
