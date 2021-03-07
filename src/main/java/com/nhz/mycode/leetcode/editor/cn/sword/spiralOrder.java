package com.nhz.mycode.leetcode.editor.cn.sword;

import java.util.Arrays;

public class spiralOrder {

    public int[] spiralOrder(int[][] matrix) {
        int rn = matrix.length ;
        int cn = matrix[0].length ;
        int t = 0;
        int b = rn ;
        int l = 0;
        int r = cn ;
        int ansLen = rn*cn;
        int[] ans = new int[ansLen];
        int i = 0;
        while(i < ansLen){
            for(int ti = l ; ti < r ; ti++ ){   //上
                ans[i++] = matrix[t][ti];
            }
            t++;
            for(int ri = t ; ri < b ; ri++){    //右
                ans[i++] = matrix[ri][r-1];
            }
            r--;
            for(int bi = r - 1 ; bi >= l ; bi--){//下
                ans[i++] = matrix[b-1][bi];
            }
            b--;
            for(int li = b -1  ; li >= t ; li--){   //左
                ans[i++] = matrix[li][l];
            }
            l++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        spiralOrder method = new spiralOrder();
        Arrays.toString(method.spiralOrder(matrix));
    }
}
