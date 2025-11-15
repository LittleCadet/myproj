package com.myproj.app.algorithm.二分查找;

import java.util.Arrays;

/**
 * 题目：
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1],
 * nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 * 示例 1：
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 *
 * 思路：
 *      解法1: 二分法：
 *          核心思想：
 *              1.1 找到旋转的次数
 *              1.2 排序: 即为还原 原来的升序数组
 *              1.3 二分法
 *
 *
 *      解法2： 二分查找： 在单调递增区间的使用： 把数组分成两段： 【与{@link 搜索旋转排序数组II} 完全相同】
 *          - 如果前一段：单调递增 ，则后一段：单调递减。
 *          - 如果前一段：单调递减 ，则后一段：单调递增。
 *          - 在每一段的单调递增区间中： 使用二分法即可。
 *          - 不断重复上述过程，  直到 找到 target为止。
 *
 * @author shenxie
 * @date 2023/12/30
 */
public class 搜索旋转排序数组 {

    public static void main(String[] args) {
        System.out.println(searchV3Copy(new int[]{4,5,6,7,0,1,2}, 0));
    }

    /**
     * 方法1：先还原升序数组， 再用二分法。
     */
    public static int search(int[] nums, int target) {
        int k = 0, l = 0, r = nums.length -1, mid = 0, ans = Integer.MAX_VALUE;

        // 找到旋转的次数。
        for(int i =1; i< nums.length; i++) {
            if(nums[i-1] > nums[i]){
                k = i;
            }
        }
        // 排序: 即为还原 原来的升序数组
        Arrays.sort(nums);
        // 二分法
        while(l <= r) {
            mid = l + (r - l) / 2;
            if(target > nums[mid]){
                l = mid + 1;
            }else if(target < nums[mid]){
                r = mid - 1;
            }else{
                // (mid + 旋转次数k ) % 数组长度 ：  此时数组长度 就是 nums.length , 而不是 nums.length - 1
                ans = (mid + k) % nums.length;
                break;
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans ;
    }


    /**
     * 错误解法：原因： 这种解法找target， 只能在单调递增的数组中 查找。 对于有波峰和波谷的情况， 无法处理
     */
    public static int searchV2(int[] nums, int target) {
        int l = 0 , r= nums.length - 1, mid = 0 ;
        while (l <= r) {
            mid = l + (r-l) /2;
            if(nums[mid] < target) {
                l = mid + 1;
            }else if(nums[mid] > target) {
                r = mid + 1 ;
            }else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * 解法3： 正确：
     *
     * 1 2 3 4 5 6 7 可以大致分为两类，
     * 第一类 2 3 4 5 6 7 1 这种，也就是 nums[start] <= nums[mid]。此例子中就是 2 <= 5。
     * 这种情况下，前半部分单调递增。因此如果 nums[start] <=target<nums[mid]，则在前半部分找，否则去后半部分找。
     *
     * 第二类 6 7 1 2 3 4 5 这种，也就是 nums[start] > nums[mid]。此例子中就是 6 > 2。
     * 这种情况下，后半部分单调递增。因此如果 nums[mid] <target<=nums[end]，则在后半部分找，否则去前半部分找。
     *
     */
    public static int searchV3Copy(int[] nums, int target) {
        int left = 0 , right = nums.length - 1, mid = 0 ;
        while(left <= right) {
            mid =  left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            }

            // 左节点 与 mid节点相等时：
            if(nums[left] == nums[mid]) {
                left ++ ;
            }
            // 左节点 比 mid节点小时：前半段：单调递增
            else if(nums[left] < nums[mid]) {
                if(nums[left] <= target && target <nums[mid]) {
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
            // 左节点 比 mid节点大时：  前半段： 单调递减
            else{
                if(nums[mid] < target && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                }else{
                    right = mid -1;
                }
            }
        }

        return -1;
    }
}
