/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/description/
 *
 *  https://leetcode.com/articles/binary-search-tree-iterator/
 *
 * This is in fact average O(1) time. The while loop is misleading you to think it is not.
 * Think about the number of times a node has been visited after iterating the whole tree.
 * Each node has been visited twice. In some cases the while loop doesn't execute, so that node at that call is only visited once.
 * Where does the other visit go? It goes to the while loop when you visit another node. That's why it's "average" O(1) time.
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class BSTIterator {

    Deque<TreeNode> stack;
    TreeNode temp;
    int val;

    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        temp = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(temp == null && stack.isEmpty())
            return false;
        return true;
    }

    /** @return the next smallest number */
    public int next() {

        while(temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
        temp = stack.pop();
        val = temp.val;
        temp = temp.right;

        return val;
    }
}
