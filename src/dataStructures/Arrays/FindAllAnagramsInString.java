/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/minimum-window-substring/discuss/26808/here-is-a-10-line-template-that-can-solve-most-substring-problems
 *
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class FindAllAnagramsInString {

    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if(p.isEmpty() || s.isEmpty()) return list;

        int left =0, right =0;
        int[] map = new int[26];
        for(char ch: p.toCharArray())
            map[ch-'a']++;

        for(int i=0; i<map.length; i++)
            if(map[i] == 0)   //s: "cbaebabacd" p: "abc"
                map[i] = -1;

        while( left <= right && right < s.length()) {
            int val = map[s.charAt(right) - 'a'];
            if( val == -1) {
                while(left < right)
                    if(map[s.charAt(left) - 'a'] > -1)
                        map[s.charAt(left++) - 'a']++;
                left = right+1;
            } else if(val > 0) {
                map[s.charAt(right)- 'a']--;
            } else if(val == 0) {
                while(s.charAt(left) != s.charAt(right))
                    map[s.charAt(left++)- 'a']++;
                left +=1;
            }
            right++;
            if(right - left == p.length()) {
                list.add(left);
                map[s.charAt(left++) - 'a']++;
            }
        }
        return list;
    }

    /**
     how to check if two strings are anagrams?

     make a map(of ascii chars) of one string and #times it occurs

     iterate the second string and then cancel every occurence -1.

     one way is to create substrings of length p in s, and check for anagram.
     time O(k*n)

     to optimize, because only one char is changing in every substring, we can cancel the previous element and add new char occurence.

     Sliding window approach -
     create a map of all occurenecs of p. Iterate s each ch,
     1) if ch not exists - restart map from ch +1
     2) if ch exists - decrement map[ch- 'a']
     if left and right ptr diff is p.length() include to result
     left++; map[s[left] - 'a']++;
     3) if ch exists and is equal to zero // which means repeated element.
     traverse till the first occurence of ch from left and then start
     left = first occ + 1
     right++;

     time O(n)

     **/

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
        int[] hash = new int[256]; //character hash
        //record each character in p to hash
        for (char c : p.toCharArray()) {
            hash[c]++;
        }
        //two points, initialize count to p's length
        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            //move right everytime, if the character exists in p's hash, decrease the count
            //current hash value >= 1 means the character is existing in p
            if (hash[s.charAt(right++)]-- >= 1) count--;

            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if (count == 0) list.add(left);

            //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0) count++;
        }
        return list;
    }
}
