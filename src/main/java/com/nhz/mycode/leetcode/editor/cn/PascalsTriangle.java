//给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。 
//
// 
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。 
//
// 示例: 
//
// 输入: 5
//输出:
//[
//     [1],
//    [1,1],
//   [1,2,1],
//  [1,3,3,1],
// [1,4,6,4,1]
//] 
// Related Topics 数组

package com.nhz.mycode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for(int count = 0 ; count < numRows; ++count ){
            ArrayList<Integer> row = new ArrayList<>();
            for(int i = 0; i <= count; ++i){
                if (i == 0 || i == count ) {
                    row.add(1);
                }else {
                    row.add(ans.get(count - 1).get(i - 1) + ans.get(count - 1).get(i));
                }
            }
            ans.add(row);
        }
        return ans;
    }

    public static void main(String[] args) {
        PascalsTriangle pascalsTriangle = new PascalsTriangle();
        List<List<Integer>> generate = pascalsTriangle.generate(3);
        for (List<Integer> subList : generate) {
            for(Integer item : subList){
                System.out.print(item);
            }
            System.out.println();
        }
    }
}