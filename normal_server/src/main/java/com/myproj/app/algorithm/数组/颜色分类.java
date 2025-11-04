package com.myproj.app.algorithm.数组;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 *
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 *
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 *
 *  方法1：  用HashMap将颜色归类， 之后用list 替换 原nums中的元素
 *  方法2：  用单指针：先排0 ， 再排1， 而2自然在最后了。
 *
 *
 * @author shenxie
 * @date 2025/11/3
 */
public class 颜色分类 {

    public static void main(String[] args) {
//        sortColors(new int[]{2,0,2,1,1,0});
        sortColorsV2(new int[]{2,0,2,1,1,0});
    }

    /**
     * 方法1：用HashMap将颜色归类， 之后用list 替换 原nums中的元素
     */
    public static void sortColors(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        // 0 /1/2 进入map
        for(int num : nums) {
            if(num == 0) {
                List<Integer> tmp = map.getOrDefault(0, new ArrayList<>());
                tmp.add(num);
                map.put(0,tmp);
            }else if(num == 1) {
                List<Integer> tmp = map.getOrDefault(1, new ArrayList<>());
                tmp.add(num);
                map.put(1,tmp);
            }else if (num == 2) {
                List<Integer> tmp = map.getOrDefault(2, new ArrayList<>());
                tmp.add(num);
                map.put(2,tmp);
            }
        }

        // map 按 0 、1、2的顺序转化为 list
        for(int i = 0 ; i<=2 ; i++) {
            if(map.get(i) != null) {
                list.addAll(map.get(i));
            }
        }


        // list 转换为nums
        for(int i = 0 ;i <nums.length; i++) {
            nums[i] = list.get(i);
        }
    }

    /**
     *  方法2：用单指针：先排0 ， 再排1， 而2自然在最后了。
     */
    public static void sortColorsV2(int[] nums) {
        int pre = 0;
        // 从pre开始， 将0排到nums的前面。
        for(int i = 0 ; i<nums.length; i++) {
            if(nums[i] == 0) {
                int tmp = nums[i];
                nums[i] = nums[pre];
                nums[pre] = tmp;

                pre ++;
            }
        }

        // 从0开始： pre:代表：0排序完成后，在数组nums中的最新下标
        for(int i = pre ; i<nums.length ; i++) {
            if(nums[i] == 1) {
                int tmp = nums[i];
                nums[i] = nums[pre];
                nums[pre] = tmp;
                pre ++;
            }
        }
    }
}
