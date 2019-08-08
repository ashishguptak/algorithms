/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays;

/**
 * https://leetcode.com/articles/longest-common-prefix/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if(strs.length < 1) return "";

        String lcp = strs[0]; //["flower","flow","flight"]
        for(int i=1; i<strs.length; i++) {
            lcp = checkCommonPrefix(lcp, strs[i]);
            if(lcp.isEmpty()) break;
        }
        return lcp;
    }

    private String checkCommonPrefix(String a, String b) {
        String common ="";

        int index=0;
        while(index < Math.min(a.length(), b.length())) {
            if(a.charAt(index) != b.charAt(index)) break;
            common = common + a.charAt(index);
            index++;
        }
        return common;
    }
}

/**

 longest common prefix among an array of strings

 are the strings fixed or can be added with time? yes
 can all the strings fit in memory?
 is the lcpQuery called frequently?

 the longest prefix cannot be more than the length of shortest string
 size m , n words

 compare the first two words by each character and then add the common prefix string object.
 Now, for rest of string elements check if the common prefix matches.

 associative property of objects

 lcp(a, b, c) = lcp(lcp(a,b), c)

 time O(m*n)
 space O(1)

 If words are added and lcpQuery is called frequently?
then iterating all the strings is not an efficient op, to reduce the time complexity, we can pre-process the strings
and store in a Trie -based data structure.

 ["flower","flow","flight"]
                root
                f
               l
              i o
             g     w (leaf)
            h        e
    (leaf) t           r (leaf)


 lcp is the deepest char node till which only one child exists.
 fl
 **/
