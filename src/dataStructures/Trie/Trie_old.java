package dataStructures.Trie; /**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class Trie_old {

    public static final int ALPHABET_SIZE= 26;

    public static class TrieNode {
        TrieNode[] child;
        boolean isLeaf;

        TrieNode() {
            child = new TrieNode[ALPHABET_SIZE];
        }
    }

    public static void printTrieb4(TrieNode node){
        for(int i=0; i<ALPHABET_SIZE; i++)
            System.out.println(node.child[i]);
    }

    static TrieNode root;

    public static void insert(String s){
        if(s.isEmpty()) return;

        TrieNode crawl= root;
        char[] str = s.toCharArray();

        for( int i=0;i<str.length; i++) {
            if( crawl.child[str[i]-'a'] == null){
                crawl.child[str[i]-'a'] = new TrieNode();
            }
            crawl = crawl.child[str[i]-'a'];
        }
        crawl.isLeaf = true;
    }

    public static boolean search(String s){
        if(s.isEmpty()) return true;

        TrieNode crawl = root;
        char[] str = s.toCharArray();
        int i=0;
        while(i < str.length){
            if(crawl.child[str[i]-'a'] ==null)
                return false;
            crawl = crawl.child[str[i]-'a'];

            if(i == str.length-1 && !crawl.isLeaf )
                return false;

            i++;
        }
        return true;
    }

    public static List<String> print(TrieNode root) {
        if(root == null) return new ArrayList<>();

        List<String> words = new ArrayList<>();

        checkWords(root, words, "");
        return words;
    }

    public static void checkWords(TrieNode root, List<String> words, String str){
        if(root == null) return;

        for(int i=0;i<ALPHABET_SIZE; i++){
            if(root.child[i] == null) continue;
            char ch = (char) ('a' + i);

            if(root.child[i].isLeaf)
                words.add(str+ ch);

            checkWords(root.child[i],words,str+ch);
        }
    }

    public static void main(String[] args){
        // Input keys (use only 'a' through 'z' and lower case)
        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        String output[] = {"Not present in trie", "Present in trie"};


        root = new TrieNode();

        //printTrieb4(root);

        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++)
            insert(keys[i]);

        List<String> words = print(root);
        System.out.println(words);

        // Search for different keys
        if(search("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);

        if(search("these") == true)
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);

        if(search("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);

        if(search("thaw") == true)
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);
    }
}
