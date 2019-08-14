/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/word-search-ii/
 *
 *  Intuitively, start from every cell and try to build a word in the dictionary. Backtracking (dfs) is the powerful way to exhaust every possible ways. Apparently, we need to do pruning when current character is not in any word.
 *
 * How do we instantly know the current character is invalid? HashMap?
 * How do we instantly know what's the next valid character? LinkedList?
 * But the next character can be chosen from a list of characters. "Mutil-LinkedList"?
 * Combing them, Trie is the natural choice. Notice that:
 *
 * TrieNode is all we need. search and startsWith are useless.
 * No need to store character at TrieNode. c.next[i] != null is enough.
 * Never use c1 + c2 + c3. Use StringBuilder.
 * No need to use O(n^2) extra space visited[m][n].
 * No need to use StringBuilder. Storing word itself at leaf node is enough.
 * No need to use HashSet to de-duplicate. Use "one time search" trie.
 *
 *  https://leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00)
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class SearchWordII {

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);

        for(int i=0; i< board.length; i++)
            for(int j=0; j< board[0].length; j++)
                backtrack(board, i, j, root, result);
        return result;
    }

    private void backtrack(char[][] board, int i, int j, TrieNode root, List<String> result) {
        char ch = board[i][j];

        if( ch == '.' || root.children[ch - 'a'] == null) return;

        root = root.children[ch-'a'];
        if( root.word != null) {
            result.add(root.word);
            root.word = null;
        }

        board[i][j] = '.';
        if( i < board.length -1) backtrack(board, i+1, j, root, result);
        if( i > 0) backtrack(board, i-1, j, root, result);
        if( j < board[0].length -1) backtrack(board, i, j+1, root, result);
        if( j > 0) backtrack(board, i, j-1, root, result);

        board[i][j] = ch;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for(String each: words) {
            TrieNode temp = root;
            for(char ch: each.toCharArray()) {
                if(temp.children[ch - 'a'] == null)
                    temp.children[ch - 'a'] = new TrieNode();
                temp = temp.children[ch - 'a'];
            }
            temp.word = each;
        }
        return root;
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    String word;
}