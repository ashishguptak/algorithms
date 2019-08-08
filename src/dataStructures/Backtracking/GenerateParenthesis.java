/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if( n<= 0) return list;
        recurse(n, 1, 1, 0, new StringBuilder("("), list);
        return list;
    }

    private void recurse(int n, int index, int open, int close, StringBuilder sb, List<String> list) {
        if(open == n && close == n) {
            list.add(new String(sb.toString()));
            return;
        }

        if(open < n) {
            recurse(n, index+1, open+1, close, sb.append('('), list);
            sb.deleteCharAt(sb.length() -1);
        }
        if( close < open) {
            recurse(n, index+1, open, close+1, sb.append(')'), list);
            sb.deleteCharAt(sb.length() -1);
        }
    }
}

/**

 n pair of parentheses, generate all combinations of well-formed parentheses

 valdiate the parenthesis - keep a running counter for no of open brackets, if closed -1.
 At any pt, if counter < 0 - unbalanced

 start from first index and open bracket, and counter =1
 At next index, have two choices - (/ )
 choose either and continue the generation. Recursion based coding

 Tree based execution - DFS
 backtracking to retain the recursion tree string object
 in the end of index, add to result set.

 time O(2^n)
 space O(n) for recursion depth

 **/
