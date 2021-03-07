//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。 
//
// 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。 
//
// 
//
// 例如，给定三角形： 
//
// [
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
// 
//
// 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。 
//
// 
//
// 说明： 
//
// 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。 
// Related Topics 数组 动态规划

package com.nhz.mycode.leetcode.editor.cn;


import java.util.List;

public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        n = triangle.size();
        dfs(0, 0, triangle.get(0).get(0), triangle);
        return best;
    }

    private int n = 0;
    private int best = Integer.MAX_VALUE;

    public void dfs(int x, int y, int sum, List<List<Integer>> triangle) {
        if (x == n) {
            return;
        }
        if (sum < best) {
            best = sum;
        }
        dfs(x + 1, y, triangle.get(x + 1).get(y), triangle);
        dfs(x + 1, y + 1, triangle.get(x + 1).get(y + 1), triangle);
    }
}