/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-ladder/
 *  https://leetcode.com/problems/word-ladder/discuss/40707/C%2B%2B-BFS
 *
 *
 *  Time  O(V^2) + O(V+E)
 *  Space O(V + E)
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();

        for(String temp : wordList)
            map.putIfAbsent(temp, new ArrayList<>());

        for(int i=0; i< wordList.size(); i++) {
            for(int j=i+1; j<wordList.size(); j++) {
                if(diffIsOne(wordList.get(i), wordList.get(j))) {
                    map.get(wordList.get(i)).add(wordList.get(j));
                    map.get(wordList.get(j)).add(wordList.get(i));
                }
            }
        }

        int count = 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        if(!map.containsKey(endWord)) return count;

        if(map.containsKey(beginWord)) {
            queue.offer(beginWord);
            count = 1;
        } else {
            for(String each: wordList) {
                if(diffIsOne(each, beginWord)) {
                    queue.add(each);
                    visited.add(each);
                }
            }
            count = 2;
        }

        if(queue.isEmpty()) return 0;
        if(map.size() == 1) return count;

        while(!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for(int i=0; i< size; i++) {
                String temp = queue.poll();
                if(map.get(temp) != null) {
                    for(String each: map.get(temp)) {
                        if(each.equals(endWord)) return count;
                        if(visited.contains(each)) continue;
                        queue.add(each);
                        visited.add(each);
                    }
                }
            }
        }
        return 0;
    }

    private boolean diffIsOne(String a, String b) {
        int count =0;

        int i =0;
        while( i < a.length() && count <= 1) {
            if(a.charAt(i) != b.charAt(i))
                count++;
            i++;
        }
        return count == 1;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> visited = new HashSet<>();
        Set<String> dict = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        for(int len=1; !queue.isEmpty(); len++) {
            for(int i=queue.size(); i>0; i--) {
                String temp = queue.poll();
                if(temp.equals(endWord)) return len;

                for(int j=0; j< temp.length(); j++) {
                    char[] ch = temp.toCharArray();
                    for(char c= 'a'; c<='z'; c++) {
                        if(c == temp.charAt(j)) continue;
                        ch[j] = c;
                        String check = String.valueOf(ch);
                        if(visited.add(check) && dict.contains(check))
                            queue.offer(check);
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
