/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Trie;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
class Trie {

    /** Initialize your data structure here. */
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        //if(word.isEmpty()) return;

        TrieNode temp = root;
        for(char ch: word.toCharArray()) {
            if(temp.child[ch-'a'] == null)
                temp.child[ch-'a'] = new TrieNode();

            temp = temp.child[ch-'a'];
        }
        temp.isLeaf = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        //if(word.isEmpty()) return false;

        TrieNode temp = root;
        for(char ch: word.toCharArray()) {
            if(temp.child[ch-'a'] == null)
                return false;
            temp = temp.child[ch-'a'];
        }

        return temp.isLeaf;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        //if(prefix.isEmpty()) return false;

        TrieNode temp = root;
        for(char ch: prefix.toCharArray()) {
            if(temp.child[ch-'a'] == null)
                return false;
            temp = temp.child[ch-'a'];
        }

        return true;
    }

    class TrieNode {
        boolean isLeaf = false;
        TrieNode[] child;

        TrieNode() {
            child = new TrieNode[26];
        }
    }
}