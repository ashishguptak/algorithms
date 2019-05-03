/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.DP;

import java.math.BigDecimal;

/**
 * Given a positive integer represented as a string, and an integer K, find the largest substring of length K that you can form from the given string.

 Input: "3141592", K = 3
 Output: "592"

 Input: "3141592", K = 4
 Output: "4592"

 Input: "3141592", K = 1
 Output: "9"

 f(i, k) := maximum substring of length k from str[i..n]
 what choices do we have, with regards to str[i]

 f(i, k) = max{ str[i] + f(i+1, k-1), f(i+1, k)}


 * @author ashish gupta (akonda@expedia.com)
 */
public class FindLargestNumberOfLengthKInString {

    private BigDecimal maxTilNow = new BigDecimal("0");

    private String findMaxLengthK(String str, int K) {
        if(K > str.length()) return "-1";

        findAllSubstrings(str, 0, K, new StringBuilder(""));

        return maxTilNow.toString();
    }

    private void findAllSubstrings(String str, int index, int K, StringBuilder currStr) {
        if(K == 0) {
            maxTilNow = compareMax(maxTilNow, new BigDecimal(currStr.toString()));
            return;
        }

        if(index == str.length()) return;

        currStr.append(str.charAt(index));
        findAllSubstrings(str, index+1, K-1, currStr);
        currStr.deleteCharAt(currStr.length()-1);
        findAllSubstrings(str, index+1, K, currStr);
    }

    private BigDecimal compareMax(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) == -1 ? b : a;
    }
}
