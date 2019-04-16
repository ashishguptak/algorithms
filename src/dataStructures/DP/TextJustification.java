/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.DP;

/**
 *
 * https://leetcode.com/problems/text-justification/description/
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

 You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

 Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

 For the last line of text, it should be left justified and no extra space is inserted between words.
 * @author ashish gupta (akonda@expedia.com)
 */
public class TextJustification {
}

/**


 words = ["This", "is", "an", "example", "of", "text", "justification."]
 maxWidth = 16

 o/p -
 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]

 Explanation

 badness(i, j) =  (pageWidth - totalWidth)^3 ..(1)
                    infinity if not fit in a line

 1) Define Subproblems - suffixes word[i:j]
    #subproblems = n

 brute-force algo - try all partitions of strings, For every word does it start a line or not
 algo O(2^n)

 2) Guess (part of soln)
    where second line begins
    #choices for guess <= n-i = O(n)

 3) Recurrence relation- DP(i)
        for( j in range (i+1, n+1))
                min{DP(j) + badness(i,j)};

    #time per subprob = O(n)
    base case DP(n) = 0;

 4) Topological Order for subproblems
    we are expressing i as a function of larger values of i in above eq.
    So order of computation is n, n-1, n-2 ..

    #total time = #subprobs * time per subprob
                = O(n^2)

 5) Original problem
    DP(0)

 **/