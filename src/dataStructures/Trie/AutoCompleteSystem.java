/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/explore/interview/card/uber/295/design/1726/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class AutoCompleteSystem {

        String newSentence;
        TrieNode root;

        public AutoCompleteSystem(String[] sentences, int[] times) {
            newSentence = "";
            root = new TrieNode('$');
            for(int i=0; i < sentences.length; i++)
                addNewString(sentences[i], times[i]);
        }

        public List<String> input(char c) {
            List<String> result = new ArrayList<>();
            if(c == '#') {
                addNewString(newSentence, 1);
                newSentence ="";
                return result;
            }
            newSentence = newSentence + String.valueOf(c);
            returnSearchResults(newSentence, result);
            return result;
        }

        private void returnSearchResults(String str, List<String> result) {
            TrieNode temp = root;
            for(char ch: str.toCharArray()) {
                int count = ch == ' '? 26 : ch - 'a';
                if(temp.children[count] == null) return;
                temp = temp.children[count];
            }
            fetchTopSentences(temp, result);
        }

        private void fetchTopSentences(TrieNode temp, List<String> result) {
            PriorityQueue<Map.Entry<String, Integer>> heap =
                    new PriorityQueue<>((a,b) -> a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) :
                            b.getValue() - a.getValue());

            for(Map.Entry<String, Integer> entry : temp.getMap().entrySet())
                heap.offer(entry);

            while(!heap.isEmpty()) {
                result.add(heap.poll().getKey());
                if(result.size() == 3) break;
            }
        }

        private void addNewString(String str, int times) {
            if(str.isEmpty()) return;

            TrieNode temp = root;
            for(char ch: str.toCharArray()) {
                int count = ch == ' '? 26 : ch - 'a';
                if(temp.children[count] == null)
                    temp.children[count] = new TrieNode(ch);
                temp = temp.children[count];
                temp.getMap().put(str, temp.getMap().getOrDefault(str, 0) + times);
            }
            temp.isLeaf = true;
        }

        class TrieNode {
            char val;
            TrieNode[] children;
            Map<String, Integer> map;
            boolean isLeaf;

            private TrieNode(char ch) {
                this.val = ch;
                this.children = new TrieNode[27];
                map = new HashMap<>();
                this.isLeaf = false;
            }

            public Map<String, Integer> getMap() {
                return this.map;
            }
    }

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
}
