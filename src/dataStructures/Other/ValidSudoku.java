/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Other;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/valid-sudoku/description/
 *
 * https://leetcode.com/problems/valid-sudoku/discuss/15634/Sharing-my-easy-understand-java-solution-using-set
 *  Read more soln on leetcode
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        for(int i=0; i< board.length; i++) {
            if(!validate(board, 0, i, true, false) ||
                    !validate(board, i, 0, false, false))
                return false;
        }
        //box
        for(int i=0; i< board.length; i+=3)
            for(int j=0; j< board.length; j+=3)
                if(!validate(board, i, j, false, true)) return false;

        return true;
    }

    public boolean validate(char[][] board, int row, int col, boolean isRow, boolean isDiag) {
        boolean[] arr = new boolean[board.length];
        char ch;

        if(isDiag) {
            for(int k=row; k< row+3; k++) {
                for(int l=col; l < col+3; l++) {
                    ch = board[k][l];
                    if(ch == '.') continue;
                    if(arr[ch- '1']) return false;
                    arr[ch - '1'] = true;
                }
            }
        } else {
            for(int i=0; i< board.length; i++) {
                ch = isRow ? board[i][col] : board[row][i];
                if(ch == '.') continue;
                if(arr[ch- '1']) return false;
                arr[ch - '1'] = true;
            }
        }

        return true;
    }

    public boolean isValidSudoku3(char[][] board) {
        for(int i = 0; i<9; i++){
            HashSet<Character> rows = new HashSet<Character>();
            HashSet<Character> columns = new HashSet<Character>();
            HashSet<Character> cube = new HashSet<Character>();
            for (int j = 0; j < 9;j++){
                if(board[i][j]!='.' && !rows.add(board[i][j]))
                    return false;
                if(board[j][i]!='.' && !columns.add(board[j][i]))
                    return false;
                int RowIndex = 3*(i/3);
                int ColIndex = 3*(i%3);
                if(board[RowIndex + j/3][ColIndex + j%3]!='.' && !cube.add(board[RowIndex + j/3][ColIndex + j%3]))
                    return false;
            }
        }
        return true;
    }

    public boolean isValidSudoku2(char[][] board) {
        for (int i=0; i<9; i++) {
            if (!isParticallyValid(board,i,0,i,8)) return false;
            if (!isParticallyValid(board,0,i,8,i)) return false;
        }
        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if (!isParticallyValid(board,i*3,j*3,i*3+2,j*3+2)) return false;
            }
        }
        return true;
    }
    private boolean isParticallyValid(char[][] board, int x1, int y1,int x2,int y2){
        Set singleSet = new HashSet();
        for (int i= x1; i<=x2; i++){
            for (int j=y1;j<=y2; j++){
                if (board[i][j]!='.') if(!singleSet.add(board[i][j])) return false;
            }
        }
        return true;
    }
}