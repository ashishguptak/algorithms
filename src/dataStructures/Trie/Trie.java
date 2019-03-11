/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Trie;

/**
 * https://leetcode.com/articles/implement-trie-prefix-tree/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
class Trie {

    /** Initialize your data structure here. */
    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode('0');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode temp = root;
        for(char ch: word.toCharArray()) {
            if(temp.children[ch-'a'] == null)
                temp.children[ch-'a'] = new TrieNode(ch);
            temp = temp.children[ch-'a'];
        }
        temp.isLeaf = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode temp = root;
        for(char ch: word.toCharArray()) {
            if(temp.children[ch-'a'] == null) return false;
            temp = temp.children[ch-'a'];
        }
        return temp.isLeaf;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode temp = root;
        for(char ch: prefix.toCharArray()) {
            if(temp.children[ch-'a'] == null) return false;
            temp = temp.children[ch-'a'];
        }
        return true;
    }

    class TrieNode {
        char letter;
        TrieNode[] children;
        boolean isLeaf;

        public TrieNode(char letter) {
            this.letter = letter;
            this.children = new TrieNode[26];
            this.isLeaf = false;
        }
    }
}