/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.matrix;

/**
 * https://leetcode.com/problems/word-search/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class SearchWord {
    public boolean exist(char[][] board, String word) {
        if(board.length == 0 || word.isEmpty()) return false;

        for(int i=0; i< board.length; i++)
            for(int j=0; j<board[0].length; j++)
                if(backtrack(board, word, 0, i, j))
                    return true;
        return false;
    }

    private boolean backtrack(char[][] board, String word, int index, int i, int j) {
        if(index == word.length()) return true;

        if(i < 0 || i >= board.length || j <0 || j >= board[0].length || board[i][j] == '.')
            return false;

        if(word.charAt(index) != board[i][j]) return false;
        board[i][j] = '.';

        boolean flag = backtrack(board, word, index+1, i+1, j) ||
                backtrack(board, word, index+1, i-1, j) ||
                backtrack(board, word, index+1, i, j-1) ||
                backtrack(board, word, index+1, i, j+1);
        board[i][j] = word.charAt(index);
        return flag;
    }
}

/*
One way to approach the problem is to mark the node in the matrix as visited for every matched char in word and then increment the index for word till all chars in word are sxhuasted.

For each char, try in four directions and then unmark the visited node if char is mismatched.

backtracking based

time O(4*m*n*l)
space O(m*n) where m is word.length recursion depth

*/