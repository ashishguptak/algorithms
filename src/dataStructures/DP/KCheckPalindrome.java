/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.DP;

import java.util.HashMap;
import java.util.Map;

/**
 * The more I think about it, the more I think your suggestion of marching leftward (or rightward) up to `rem` times is correct. So that was my mistake. You can confirm this by testing that idea on Codesignal.
 *
 * The intuition behind this problem is similar to that of Bellman-Ford. You can review problems like this for additional practice: https://www.dailycodingproblem.com/blog/how-to-find-arbitrage-opportunities-in-python/
 *
 * There were some small mistakes when discussing runtime and space complexity of the brute-force solution, as well as what shape to make the hash map for the DP (pair vs. triple). The edge case of left == right was initially missed, and we had a fix for that by the end; making the function return True for left >= right would've also handled that.
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class KCheckPalindrome {

    private boolean kpalindrome(String s, int k) {
        if(s.length() == 0) return false;

        Map<Triple, Boolean> map = new HashMap<>();

        return recurse(s, 0, s.length() -1, k, map);
    }

    private boolean recurse(String s, int left, int right, int rem, Map<Triple, Boolean> map) {
        //if(left - right == 1 && s.charAt(left-1) == s.charAt(right+1)) return true;
        if( left > right || rem < 0) return false;   // a b cc b a  d k=2, left =0, right =6, k=2
        // rem=1, left=0, right =5
        // 0 5
        if(map.containsKey(new Triple(left, right, rem)))
            return map.get(new Triple(left, right, rem));

        if(s.charAt(left) == s.charAt(right)  && (right - left == 1 || right -left == 0))
            return true;
        else if(s.charAt(left) == s.charAt(right))
            return recurse(s, left+1, right-1, rem, map);

        boolean flag =  recurse(s, left+1, right, rem-1, map) || recurse(s, left, right-1, rem-1, map);
        map.put(new Triple(left, right, rem), flag);
        return flag;
    }
}

class Triple{
    int left; int right; int rem;

    public Triple(int left, int right, int rem) {
        this.left = left;
        this.right = right;
        this.rem = rem;
    }
}

/**
 * kPalindrome(String s, Int k) -> Bool
 *
 * a - 0-palindrome, 1-palindrome, 2-pa, ...
 * ab - 1-palindrome, 2-pal
 *
 *  upto 2 chars to make it a palindrome
 *  str abcdcba  k=2
 *
 *  remove upto K char everytime in all combinations of k and then verify if its a aplindrome.
 *
 *  O(n) algo - to cehck its a palindrome
 *
 *  n C k combinations of strings
 *
 *  time O(n!(n-k)! * n)
 *  space O(n) recursive stack
 *
 *  left and right ptr on the string
 *
 *  s.charAt(left) ? s.charAt(right)
 *
 *  a bcdb  k=2
 *  a    b
 *  ^
 *
 * a (dcxyz)  b (zyxcd) b  k=2
 *
 * a (dcxyz)  pb (zyxcd) b  k=4
 *
 * pb k=2
 *
 * left - p, right -b
 *
 * left d c
 * right d c
 *
 * not-matching case
 * dp(left, right, remaining) = dp(left + 1, right, remaining - 1) or dp(left, right - 1, remaining - 1)   provided s.length()>= n-k
 *
 * are-matching case:
 * dp(left, right, remaining) = dp(left + 1, right - 1, remaining)
 *
 *  two choices left || right upto K chars diff
 *   K chars         K chars
 *
 *  3) remove both n check
 *     left+1, right-1
 *
 *  Repeated Sub problems + Optimal Substructure to go for a memoized approach
 *
 *  (0,5)
 *
 *  1,5   0, 4 k=1
 *  2,5  1,4   1,4  0, 3  k=2
 *
 *  Map<Triple<i,j,k>, Boolean>
 *  aa
 *  ^^
 *
 *  time  O(n*2*k*n)
 *  https://app.codesignal.com/interview-practice/task/x3rJpdZGEcjmYtDqv
 */
