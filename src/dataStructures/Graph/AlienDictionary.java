/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://www.geeksforgeeks.org/given-sorted-dictionary-find-precedence-characters/
 * There is a new alien language which uses the latin alphabet. However, the order among letters
 * are unknown to you. You receive a list of words from the dictionary, where words are sorted
 * lexicographically by the rules of this new language. Derive the order of letters in this language.
 *
 * https://leetcode.com/problems/alien-dictionary/
 *
 * There is a new alien language which uses the latin alphabet.
 However, the order among letters are unknown to you.
 You receive a list of non-empty words from the dictionary,
 where words are sorted lexicographically by the rules of this new language.
 Derive the order of letters in this language.
 Example 1:
 Given the following words in dictionary,
 [
 "wrt",
 "wrf",
 "er",
 "ett",
 "rftt"
 ]
 The correct order is: "wertf".
 Example 2:
 Given the following words in dictionary,
 [
 "z",
 "x"
 ]
 The correct order is: "zx".
 Example 3:
 Given the following words in dictionary,
 [
 "z",
 "x",
 "z"
 ]
 The order is invalid, so return "".
 Note:
 You may assume all letters are in lowercase.
 You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 If the order is invalid, return an empty string.
 There may be multiple valid order of letters, return any one of them is fine.
 *
 */
public class AlienDictionary {


    private String alienOrder(String[] words) {
        String result ="";

        if(words.length == 0) return result;

        Graph graph = buildGraph(words);
        if(!graph.valid) return result;
        Deque<Character> deque = topologicalSort(graph);

        return deque.toString();
    }

    private Deque<Character> topologicalSort(Graph graph) {
        Deque<Character> deque = new ArrayDeque<>();
        Set<Character> visited = new HashSet<>();

        for(Character ch: graph.getCharacters()) {
            dfs(graph, ch, visited, deque);
        }

        return deque;
    }
    //Use a StringBuilder for cleaner stacking
    private void dfs(Graph graph, Character ch, Set<Character> visited, Deque<Character> deque) {
        if(visited.contains(ch)) return;

        visited.add(ch);

        if(graph.getAdjList().get(ch) != null) {
            for (Character x : graph.getAdjList().get(ch)) {
                dfs(graph, x, visited, deque);
            }
        }

        deque.addFirst(ch);
    }

    private Graph buildGraph(String[] words) {
        Graph graph = new Graph();
        graph.setCharacters(findChars(words));

        for(int i=0; i< words.length-1; i++) {
            String diff = findDiff(words[i], words[i+1]);
            if(diff.isEmpty()) continue;
            if(!graph.checkNotCircularEdge(diff.charAt(1), diff.charAt(0))) {
                graph.valid = false;
                break;
            }
            graph.addEdge(diff.charAt(0), diff.charAt(1));
        }

        return graph;
    }

    private String findDiff(String word, String word1) {
        String diff = "";

        for(int i=0; i< Math.min(word.length(), word1.length()); i++) {
            if( word.charAt(i) != word1.charAt(i)) {
                diff = String.valueOf(word.charAt(i)) + String.valueOf(word1.charAt(i));
                break;
            }
        }

        return diff;
    }

    private Set<Character> findChars(String[] words) {
        Set<Character> characters = new HashSet<>();
        for(String word: words) {
            for (Character ch : word.toCharArray()) {
                characters.add(ch);
            }
        }
        return characters;
    }


    public static void main(String args[]) {
        AlienDictionary ad = new AlienDictionary();
        String[] words1 = {"zy", "zx"};
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        String[] words2 = {"wrtkj", "wrt"};
        String[] words3 = { "z","x","z"};
        String result = ad.alienOrder(words3);
        if(result.isEmpty())
            System.out.print( "invalid i/p");
        System.out.print(result);
    }

    public class Graph {

        Set<Character> characters = new HashSet<>();

        Map<Character, List<Character>> adjList = new HashMap<>();

        boolean valid = true;

        public Map<Character, List<Character>> getAdjList() {
            return adjList;
        }

        void addEdge(Character a, Character b) {
            if(adjList.containsKey(a)) {
                List<Character> vertexList = adjList.get(a);
                vertexList.add(b);
                adjList.put(a, vertexList);
            } else {
                List<Character> vertexList = new ArrayList<>();
                vertexList.add(b);
                adjList.put(a, vertexList);
            }
        }

        public Set<Character> getCharacters() {
            return characters;
        }

        public Graph setCharacters(Set<Character> characters) {
            this.characters = characters;
            return this;
        }

        public boolean checkNotCircularEdge(Character a, Character b) {
            if(adjList.get(a) != null) {
                if(adjList.get(a).contains(b))
                    return false;
            }
            return true;
        }
    }

}
