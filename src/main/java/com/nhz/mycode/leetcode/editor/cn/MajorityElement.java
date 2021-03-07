//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [3,2,3]
//输出: 3 
//
// 示例 2: 
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
// 
// Related Topics 位运算 数组 分治算法

package com.nhz.mycode.leetcode.editor.cn;

import java.util.*;

public class MajorityElement {

    static class DataNode {
        public int count;
        public int num;

        public DataNode(int count, int num) {
            this.count = count;
            this.num = num;
        }
    }

    public static int majorityElemen1t(int[] nums) {
        HashMap<Integer, DataNode> map = new HashMap<>();
        PriorityQueue<DataNode> queue = new PriorityQueue<>(new Comparator<DataNode>() {
            public int compare(DataNode data1, DataNode data2) {
                return data2.count - data1.count;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).count++;
            } else {
                map.put(nums[i], new DataNode(1, nums[i]));
            }
        }
        Iterator<Integer> iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            Integer next = iterator.next();
            queue.offer(map.get(next));
        }
        return queue.poll().num;
    }

    public static int majorityElement(int[] nums, int i) {
        int count = 0;
        int max = 0;
        for (int num : nums) {
            if (count == 0) {
                max = num;
            }
            if (num == max) {
                count++;
            } else {
                count--;
            }
        }
        return max;
    }

    public static int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }
        return majorityEntry.getKey();

        //Optional<Map.Entry<Integer, Integer>> max = map.entrySet().stream().max((x1, x2) -> Integer.compare(x1.getValue(), x2.getValue()));
        // Optional<Map.Entry<Integer, Integer>> max = map.entrySet().stream().max(Map.Entry.comparingByValue());
        // return max.get().getKey();
    }

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{1, 3, 4, 2, 4, 6, 7, 4, 2, 4, 4, 4, 4}, 0));
    }
}