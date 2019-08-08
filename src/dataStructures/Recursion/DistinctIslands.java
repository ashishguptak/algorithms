/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Recursion;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/number-of-distinct-islands/discuss/108475/Java-very-Elegant-and-concise-DFS-Solution(Beats-100)
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class DistinctIslands {

    public int numDistinctIslands(int[][] grid) {
        if(grid == null) return 0;
        Set<String> set = new HashSet<>();

        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j] != 1) continue;
                StringBuilder sb = new StringBuilder();
                dfs(grid, i, j, sb, 'o');
                set.add(sb.toString());
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, int i, int j,  StringBuilder sb, char ch) {
        if( i < 0 || i > grid.length -1 || j < 0 || j > grid[0].length - 1
                || grid[i][j] != 1) return;

        grid[i][j] = 2;
        sb.append(ch);
        dfs(grid, i+1, j, sb, 'd');
        dfs(grid, i-1, j, sb, 'u');
        dfs(grid, i, j-1, sb, 'l');
        dfs(grid, i, j+1, sb, 'r');
        sb.append(" ");
    }
}
