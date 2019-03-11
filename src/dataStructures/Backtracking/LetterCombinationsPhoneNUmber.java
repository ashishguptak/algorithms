/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/explore/interview/card/uber/290/recursion-and-backtracking/1688/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class LetterCombinationsPhoneNUmber {

    private static final String[] map = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinationsDFS(String digits) {
        List<String> result = new ArrayList<>();
        if(digits.length() < 1) return result;

        recurse(digits, 0, result, new StringBuilder(""));
        return result;
    }

    public void recurse(String digits, int index, List<String> result, StringBuilder sb) {
        if(index == digits.length()) {
            result.add(new String(sb));
            return;
        }

        for(char ch: map[digits.charAt(index) -'2'].toCharArray()) {
            sb.append(ch);
            recurse(digits, index+1, result, sb);
            sb.deleteCharAt(sb.length() -1);
        }
    }


    public List<String> letterCombinationsBFS(String digits) {
        List<String> result = new ArrayList<>();
        if(digits.length() < 1) return result;

        result.add("");
        List<String> ls;
        List<String> temp;
        for(char ch: digits.toCharArray()) {
            ls = new ArrayList<>();
            temp = new ArrayList<>(result);
            for(char each: map[ch - '2'].toCharArray()) {
                for(String str: temp)
                    ls.add(str+each);
            }
            result = ls;
        }
        return result;
    }
}
