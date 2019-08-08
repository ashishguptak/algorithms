/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays;

/**
 * https://leetcode.com/articles/minimum-window-substring/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class MinimumWindowSubString {

//Template structure for sliding window problems

/*        int findSubstring(string s){
            vector<int> map(128,0);
            int counter; // check whether the substring is valid
            int begin=0, end=0; //two pointers, one point to tail and one  head
            int d; //the length of substring

            for() { *//* initialize the hash map here *//* }

            while(end<s.size()){

                if(map[s[end++]]-- ?){  *//* modify counter here *//* }

                while(*//* counter condition *//*){

                    *//* update d here if finding minimum*//*

                    //increase begin to make it invalid/valid again

                    if(map[s[begin++]]++ ?){ *//*modify counter here*//* }
                }

                *//* update d here if finding maximum*//*
            }
            return d;
        }*/

    public String minWindow(String s, String t) {
        int[] map = new int[256];

        for(char ch: t.toCharArray())
            map[ch]++;

        int start =0, end=0, minStart=0, minLen = Integer.MAX_VALUE, counter = t.length();

        while( end < s.length()) {
            char ch = s.charAt(end);
            if(map[ch] > 0) counter--;
            map[ch]--;
            end++;
            while(counter == 0) {
                if( minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                }
                char ch2 = s.charAt(start);
                map[ch2]++;
                if(map[ch2] > 0) counter++;
                start++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}
