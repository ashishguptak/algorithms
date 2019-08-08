/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-ladder/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();

        if(dict.contains(endWord)) return 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        for(int len=1; !queue.isEmpty(); len++) {
            for(int i= queue.size(); i>0; i--) {
                String temp = queue.poll();
                if(temp.equals(endWord)) return len;

                for(int j=0;j <temp.length(); j++) {
                    char[] ch = temp.toCharArray();
                    for(char c= 'a'; c <= 'z'; c++) {
                        //if(c == )
                    }
                }
            }
        }
        return 0;
    }
}

/*
are the words of same length?
what does one letter mean? length diff of 1 with other chars same?
does end word exist in wordList?

shortest transformation sequence from beingWrd to endWrd

Treat each word in wordList as a node in the graph

build a undirected, unweighted graph -
- adjList rep of nodes
    for each pair of words check if diff in char is 1 and then add to both the adjLists

how to get the shortest seq?

BFS traversal of a unweighted graph will give the shortest dist among any two nodes.
avoid cycles

Find the start points for BFS traversal if word is not in wordList

time O(V + E)
space O(V + E)
 */
