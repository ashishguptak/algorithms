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
