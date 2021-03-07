package com.nhz.mycode.leetcode.editor.cn.sword;

import com.nhz.mycode.leetcode.editor.cn.model.TreeNode;

public class kthLargest {

    private int current ;
    private int target ;
    private int ans = -1;
    public int kthLargest(TreeNode root, int k) {
        current = 0;
        target = k;
        traversal(root);
        return ans;
    }
    public void traversal(TreeNode root){
        if(root==null) return ;
        traversal(root.right);
        current++;
        if(current==target){
            ans = root.val;
            return;
        }
        traversal(root.left);
    }
}
