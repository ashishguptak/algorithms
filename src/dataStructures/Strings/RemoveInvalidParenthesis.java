/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/description/
 *
 * best soln : https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution
 *
 * https://leetcode.com/problems/remove-invalid-parentheses/discuss/75032/Share-my-Java-BFS-solution
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class RemoveInvalidParenthesis {


    private List<String> removeInvalidParenthesesUsingDFS(String s) {
        List<String> result = new ArrayList<>();

        Map<Integer, Integer> map;

        int left=0, right=0;

        for(char ch: s.toCharArray()) {
            if(ch == '(')
                left++;
            else if( ch == ')') {
                if(left == 0)
                    right++;
                else
                    left--;
            }
        }

        if(left == 0 && right == 0) {
            result.add(s);
            return result;
        }

        String temp="";
        Set<String> list = new HashSet<>();
        recurseDFS(s, 0, left, right, temp, list, 0);

        result.addAll(list);
        return result;
    }


    private void recurseDFS(String s, int i, int left, int right,
                            String temp, Set<String> list, int count) {

        if( left < 0 || right < 0 || count < 0) return;

        if( i == s.length()) {
            //System.out.println(temp);
            if(left == 0 && right == 0 && count == 0)
                list.add(temp);
            //System.out.println(result);
            return;
        }

        if(s.charAt(i) == '(') {
            recurseDFS(s, i+1, left-1, right, temp, list, count);
            recurseDFS(s, i+1, left, right, temp+'(', list, count+1);
        } else if(s.charAt(i) == ')') {
            recurseDFS(s, i+1, left, right-1, temp, list, count);
            recurseDFS(s, i+1, left, right, temp+')', list, count -1);
        } else
            recurseDFS(s, i+1, left, right, temp + s.charAt(i), list, count);
    }


    public static void main(String[] args) {

        RemoveInvalidParenthesis parenthesis = new RemoveInvalidParenthesis();
        String s1 = "()())()";

        System.out.println(parenthesis.removeInvalidParenthesesUsingDFS(s1));
    }
}
