//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针

package com.nhz.mycode.leetcode.editor.cn;

import java.util.*;

public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        List ans = new ArrayList<>(nums.length / 3);
        if (nums == null || nums.length < 3) {
            return ans;
        }
        Arrays.sort(nums);
        int left, right = 0;
        for (int i = 0; i < nums.length; ) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            left = i + 1;
            right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    ans.add(new int[]{nums[i], nums[left], nums[right]});
                    right = findNextIndex(nums, false, right);
                    left = findNextIndex(nums, true, left);
                } else if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else {
                    left++;
                }
            }
            //发现一样的后直接忽视跳过，避免重复
            i = findNextIndex(nums, true, i);
        }
        return ans;
    }

    //左边界++ ,右边界--
    private static int findNextIndex(int[] nums, boolean left, int currentIndex) {
        int offset = left ? 1 : -1;
        while ((currentIndex + offset) > currentIndex &&
                (currentIndex + offset) < nums.length &&
                nums[currentIndex] == nums[currentIndex + offset]) {
            currentIndex += offset;
        }
        currentIndex += offset;
        return currentIndex;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        System.out.println(lists);
    }
    //leetcode submit region end(Prohibit modification and deletion)

}