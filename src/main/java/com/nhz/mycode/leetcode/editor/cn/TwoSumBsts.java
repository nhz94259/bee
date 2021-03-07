//给出两棵二叉搜索树，请你从两棵树中各找出一个节点，使得这两个节点的值之和等于目标值 Target。 
//
// 如果可以找到返回 True，否则返回 False。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：root1 = [2,1,4], root2 = [1,0,3], target = 5
//输出：true
//解释：2 加 3 和为 5 。
// 
//
// 示例 2： 
//
// 
//
// 输入：root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
//输出：false 
//
// 
//
// 提示： 
//
// 
// 每棵树上最多有 5000 个节点。 
// -10^9 <= target, node.val <= 10^9 
// 
// Related Topics 二叉搜索树

package com.nhz.mycode.leetcode.editor.cn;

import com.nhz.mycode.leetcode.editor.cn.model.TreeNode;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class TwoSumBsts {
    public static void main(String[] args) {
        Solution solution = new TwoSumBsts().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
            if (root1 == null || root2 == null) return false;
            Deque<TreeNode> stack = new LinkedList<>();
            HashSet<Integer> hash = new HashSet<>();
            while (!stack.isEmpty() || root1 != null) {
                while (root1 != null) {
                    stack.push(root1);
                    root1 = root1.left;
                }
                root1 = stack.pop();
                hash.add(root1.val);
                root1 = root1.right;
            }
            while (!stack.isEmpty() || root2 != null) {
                while (root2 != null) {
                    stack.push(root2);
                    root2 = root2.left;
                }
                root2 = stack.pop();
                if (hash.contains(target - root2.val)) {
                    return true;
                }
                root2 = root2.right;
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}