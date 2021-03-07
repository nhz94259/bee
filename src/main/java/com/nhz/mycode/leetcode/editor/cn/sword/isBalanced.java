package com.nhz.mycode.leetcode.editor.cn.sword;

import com.nhz.mycode.leetcode.editor.cn.model.TreeNode;

public class isBalanced {

    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        int left = maxDeep(root.left);
        int right = maxDeep(root.right);
        return Math.abs(left-right)<=1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int maxDeep(TreeNode root){
        return (root==null)?0:Math.max(maxDeep(root.left),maxDeep(root.right))+1;
    }

}
