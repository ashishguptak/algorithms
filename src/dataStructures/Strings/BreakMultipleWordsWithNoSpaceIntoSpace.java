/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Date 08/01/2014
 * @author akonda
 *
 * Given a string and a dictionary, split this string into multiple words such that
 * each word belongs in dictionary.
 *
 * e.g peanutbutter -> pea nut butter
 * e.g Iliketoplay -> I like to play
 *
 * Solution
 * DP solution to this problem
 * if( input[i...j] belongs in dictionary) T[i][j] = i
 * else{
 *     T[i][j] = k if T[i][k-1] != -1 && T[k][j] != -1
 *
 * Test cases
 * 1) Empty string
 * 2) String where entire string is in dictionary
 * 3) String which cannot be split into words which are in dictionary
 * 3) String which can be split into words which are in dictionary
 *
 * {@link} https://leetcode.com/problems/word-break/description/
 *
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/BreakMultipleWordsWithNoSpaceIntoSpace.java
 */
public class BreakMultipleWordsWithNoSpaceIntoSpace {


    private boolean checkIfWordsExist(String word, int j, Set<String> set) {
        if(j > word.length()-1) return false;

        String left = word.substring(0,j);
        String right = word.substring(j);

        if(set.contains(left) && set.contains(right))
            return true;

        if(set.contains(left) && checkIfWordsExist(right, 1, set))
            return true;

        return checkIfWordsExist(word, j+1, set);
    }


    private boolean findWordsUsingRecursion(String word, int j, Set<String> set, List<String> result) {
        if(j > word.length() - 1) return false;

        String left = word.substring(0, j);
        String right = word.substring(j);

        if(set.contains(left) && set.contains(right)){
            result.add(left);
            result.add(right);
            return true;
        }

        if(set.contains(left) && findWordsUsingRecursion(right, 0, set, result)) {
            result.add(left);
            return true;
        }

        return findWordsUsingRecursion(word,j+1,set,result);
    }

    //bottom up approach
    private boolean breakWordDPUsing2dArray(String word, Set<String> set) {

        if(set.isEmpty() || word.isEmpty()) return false;
        if(set.contains(word)) return true;

        boolean[][] dp = new boolean[word.length()][word.length()];

        for(int l=1; l<= dp.length; l++) {
            for (int i = 0; i < dp.length - l+1; i++) {
                int j = i+l-1;
                if (set.contains(word.substring(i, j+1)))
                    dp[i][j] = true;
                else {
                    for (int k = i; k+1 <= j; k++) {
                        if (dp[i][k] && dp[k+1][j]) {
                            dp[i][j] = true;
                            break;
                        }
                    }
                }
            }
        }

        return dp[0][word.length()-1];
    }

    private boolean breakWordUsing1dArray(String word, Set<String> set, List<String> list) {
        if(set.isEmpty()) return false;

        if(set.contains(word)) return true;

        boolean[] dp = new boolean[word.length()+1];
        dp[0] = true;

        for(int i=1; i<= word.length(); i++) {
            if(set.contains(word.substring(0,i))) {
                dp[i] = true;
                list.add(word.substring(0,i));
            }
            else {
                for(int k=i-1; k>=0 ;k--) {
                    if(dp[k] && set.contains(word.substring(k,i))){
                        dp[i] = true;
                        list.add(word.substring(k,i));
                        break;
                    }
                }
            }
        }

        return dp[dp.length-1];
    }

    public static void main(String args[]){
        Set<String> dictionary = new HashSet<String>();
        dictionary.add("I");
        dictionary.add("like");
        dictionary.add("had");
        dictionary.add("play");
        dictionary.add("to");
        //String str = "Ihadliketoplay";

        String str = "pineapplepenapple";
        dictionary.add("apple");
        dictionary.add("pen");
        dictionary.add("pine");
        dictionary.add("pineapple");

        BreakMultipleWordsWithNoSpaceIntoSpace bmw = new BreakMultipleWordsWithNoSpaceIntoSpace();

        boolean result1 = bmw.checkIfWordsExist(str, 0, dictionary);

        List<String> result = new ArrayList<>();

        boolean flag = bmw.findWordsUsingRecursion(str, 0,dictionary, result);
        System.out.println(result1);
        System.out.println(!flag ? null: result);


        //Using DP
        result = new ArrayList<>();
        boolean result2 = bmw.breakWordDPUsing2dArray(str, dictionary);
        boolean result3 = bmw.breakWordUsing1dArray(str, dictionary, result);
        System.out.println(result2);
        System.out.println(result3+" "+result);
    }


}
