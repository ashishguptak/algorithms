/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.DP;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-break/
 *
 *
 * https://leetcode.com/problems/word-break/discuss/43886/Evolve-from-brute-force-to-optimal-a-review-of-all-solutions
 * @author ashish gupta (akonda@expedia.com)
 */
public class WordBreak {
    /*
    the Dict can be convereted into a map object.
Now, start iterating the string and after every addition if you find a word in dict, start a new word from there and check for occurences of words starting from end of last char.

Go till the end of string and if all words are not present in the dict with this combination start another approach of including the next char from where we left off and check for dict occurence.

Time O(2^ n) exponential time
Space O(n)

whenever the running word matches, we have two possibilities either continue with the existing append or start a new word check.

there are Repeated subproblems for computing rest of string to be a word

Optimal subtructure

let f(i) be the possibility that word can be broken up to i chars.
f(i) = f(j) && s.substring(j+1, i+1) such that susbtring is present in the dict

time O(n^2)
space O(n)
     */
    public boolean wordBreak3(String s, List<String> wordDict) {
        Set<String> map = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;

        for(int i=1; i<=s.length(); i++) {
            for(int j=0; j<i; j++) {
                if(dp[i]) break;
                dp[i] = dp[j] && map.contains(s.substring(j, i));
            }
        }
        return dp[s.length()];
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        if( wordDict.contains(s)) return true;

        for( int i=1; i< s.length(); i++) {
             if (wordDict.contains(s.substring(0,i)) &&
                    wordBreak(s.substring(i), wordDict))
                return true;
        }
        return false;
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        if(s.isEmpty()) return true;
        Set<String> set = new HashSet<>(wordDict);
        set.add("");
        return recurse(s, 0, set);
    }

    public boolean recurse(String s, int index, Set<String> set) {
        if(index == s.length()) return false;
        String left = "";
        String right = "";

        for(int i = index; i < s.length(); i++) {
            left = left + s.charAt(i);
            right = s.substring(i+1);
            if(set.contains(left) && set.contains(right))
                return true;
            else if(set.contains(left) && recurse(right, 0, set))
                return true;
        }
        return false;
    }

}
