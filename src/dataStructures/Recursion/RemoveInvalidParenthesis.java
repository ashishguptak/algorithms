/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/description/
 * https://leetcode.com/problems/remove-invalid-parentheses/discuss/75034/Easiest-9ms-Java-Solution
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class RemoveInvalidParenthesis {

    Set<String> set;

    public List<String> removeInvalidParentheses(String s) {
        List<String> list = new ArrayList<>();
        set = new HashSet<>();
        if(s.length() < 1) {
            list.add(s);
            return list;
        }
        int[] pair = calculateInvalid(s);

        recurse(s, 0, 0, pair[0], pair[1], new StringBuilder(""));
        list.addAll(set);
        return list;
    }

    private void recurse(String s, int index, int currSum, int open, int close, StringBuilder sb) {
        if(currSum < 0) return;
        if(index == s.length()) {
            if(close == 0 && open == 0)
                set.add(sb.toString());
            return;
        }

        if(s.charAt(index) == '(' && open > 0)
            recurse(s, index+1, currSum, open-1, close, sb);
        else if (s.charAt(index) == ')' && close > 0)
            recurse(s, index+1, currSum, open, close-1, sb);

        if(s.charAt(index) == '(') currSum +=1;
        else if(s.charAt(index) == ')') currSum -=1;

        recurse(s, index+1, currSum, open, close, sb.append(s.charAt(index)));
        sb.deleteCharAt(sb.length()-1);
    }

    private int[] calculateInvalid(String s) {
        int[] pair = new int[2];
        int sum =0;
        for(char ch : s.toCharArray()) {
            if(ch == ')' && sum <= 0)
                pair[1]++;
            else if(ch == ')')
                sum--;
            else if(ch == '(')
                sum++;
        }
        if(sum > 0) pair[0] = sum;
        return pair;
    }
}
