//给定一个二维网格和一个单词，找出该单词是否存在于网格中。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例: 
//
// board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//给定 word = "ABCCED", 返回 true
//给定 word = "SEE", 返回 true
//给定 word = "ABCB", 返回 false 
//
// 
//
// 提示： 
//
// 
// board 和 word 中只包含大写和小写英文字母。 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// 1 <= word.length <= 10^3 
// 
// Related Topics 数组 回溯算法

package com.nhz.mycode.leetcode.editor.cn;

public class WordSearch {
    private static int dx[] = new int[]{0, 0, -1, 1};
    private static int dy[] = new int[]{-1, 1, 0, 0};

    public static boolean exist(char[][] board, String word) {
        boolean exist = false;
        for (int x = 0; x < board.length && exist == false; x++) {
            for (int y = 0; y < board[0].length && exist == false; y++) {
                if (find(board, word, x, y, 0)) {
                    exist = true;
                }
            }
        }
        return exist;
    }

    public static boolean find(char[][] board, String word, int x, int y, int index) {
        if (index >= word.length()) {
            return true;
        }
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
            return false;
        }
        if (board[x][y] != word.charAt(index)) {
            return false;
        }
        char renumber = board[x][y];
        board[x][y] = '#';
        boolean exist = false;
        for (int i = 0; i < 4; i++) {
            if (find(board, word, x + dx[i], y + dy[i], index + 1)) {
                exist = true;
            }
        }
        board[x][y] = renumber;
        return exist;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        System.out.println(exist(board, "ABCCED"));
    }
}