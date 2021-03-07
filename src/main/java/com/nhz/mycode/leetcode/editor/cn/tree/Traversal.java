package com.nhz.mycode.leetcode.editor.cn.tree;

import com.nhz.mycode.leetcode.editor.cn.model.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Traversal {
    public static TreeNode root = null;
    //     4
    //   2   6
    // 1 3  5  7

    static {
        TreeNode  LL2 = new TreeNode(1);
        TreeNode  LR2 = new TreeNode(3);
        TreeNode  L1 = new TreeNode(2,LL2,LR2);
        TreeNode  RL2 = new TreeNode(5);
        TreeNode  RR2 = new TreeNode(7);
        TreeNode  R1 = new TreeNode(6,RL2, RR2) ;
        root = new TreeNode(4,L1 , R1);
    }

    /**
     * @description 先根法递归遍历二叉树
     */
    public static ArrayList<Integer> recursion_pre_traversal(TreeNode root, ArrayList<Integer> result){
        if(root == null) return result;
        result.add(root.val);
        recursion_pre_traversal(root.left,result);
        recursion_pre_traversal(root.right,result);
        return result;
    }

    /**
     * @description 中根法递归遍历二叉树
     */
    public static ArrayList<Integer> recursion_mid_traversal(TreeNode root, ArrayList<Integer> result){
        if(root == null) return result;
        recursion_mid_traversal(root.left,result);
        result.add(root.val);
        recursion_mid_traversal(root.right,result);
        return result;
    }
    /**
     * @description 先根法迭代遍历二叉树
     */
    public static ArrayList<Integer> iterate_pre_traversal(TreeNode root, ArrayList<Integer> result){
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                result.add(root.val);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return result;
    }

    /**
     * @description 中根法迭代遍历二叉树
     */
    public static ArrayList<Integer> iterate_mid_traversal(TreeNode root, ArrayList<Integer> result){
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }

    /**
     * @description 后根法迭代遍历二叉树
     */
    public static ArrayList<Integer> iterate_post_traversal(TreeNode root, ArrayList<Integer> result){
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode lastVisited = null,node ;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            node = stack.peek();
            if (node.right == null || node.right == lastVisited) {
                node = stack.pop();
                result.add(node.val);
                lastVisited = node;
            } else {
                root = node.right;
            }
        }
        return result;
    }

    /**
     * @description 层序遍历二叉树
     */
    public static ArrayList<Integer> level_traversal(TreeNode root, ArrayList<Integer> result){
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            result.add(root.val);
            if (root.left != null) {
                queue.add(root.left);
            }
            if (root.right != null) {
                queue.add(root.right);
            }
        }
        return result;
    }


    /**
     * @description 层序遍历二叉树
     * @return 返回的是每一层值的数组值
     */
    public static ArrayList<ArrayList<Integer>> level_array_traversal(TreeNode root,ArrayList<ArrayList<Integer>> result){
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelCont = queue.size();
            ArrayList<Integer> sub = new ArrayList<>();
            for (int i = 0; i < levelCont; i++) {
                root = queue.poll();
                sub.add(root.val);
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
            }
            result.add(sub);
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList result = new ArrayList();
        iterate_post_traversal(root,result);
        System.out.println(result.toString());
    }
}
