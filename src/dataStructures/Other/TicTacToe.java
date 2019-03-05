/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Other;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
class TicTacToe {
    private int[][] board;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        board = new int[n][n];
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        board[row][col] = player;
        int win = checkWinX();
        if(win > 0) return win;
        win = checkWinY();
        if(win > 0) return win;
        win = checkWinDiag();

        return win;
    }

    private int checkWinX() {
        for(int i=0; i< board.length; i++) {
            for(int j=0; j< board.length; j++) {
                if(board[i][j] == 0) break;
                if(board[i][0] != board[i][j]) break;
                if(j == board.length -1)
                    return board[i][j];
            }
        }
        return 0;
    }

    private int checkWinY() {
        for(int j=0; j< board.length; j++) {
            for(int i=0; i< board.length; i++) {
                if(board[i][j] == 0) break;
                if(board[0][j] != board[i][j]) break;
                if(i == board.length -1)
                    return board[i][j];
            }
        }
        return 0;
    }

    private int checkWinDiag() {
        for(int i = 1; i< board.length; i++) {
            if(board[i][i] == 0) break;
            if(board[0][0] != board[i][i]) break;
            if(i == board.length -1) return board[0][0];
        }

        for(int i = 0; i< board.length; i++) {
            if(board[i][board.length - i - 1] == 0) break;
            if (board[0][board.length - 1] != board[i][board.length - i - 1]) break;
            if(i == board.length -1) return board[0][board.length - 1];
        }
        return 0;
    }

    public static void main(String[] args) {
        String str = null + "SRgsh";
        System.out.println(str);
    }
}