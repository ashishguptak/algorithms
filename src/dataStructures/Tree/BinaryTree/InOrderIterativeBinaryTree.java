/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Space complexity is height of tree for Stack size
 *
 * https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class InOrderIterativeBinaryTree {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        if(root == null) return list;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode temp = root;

        while(true) {
            if( temp != null) {
                stack.push(temp);

                temp = temp.left;
                while(temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
            if(stack.size() == 0) break;

            temp = stack.pop();
            list.add(temp.val);
            temp = temp.right;
        }

        return list;
    }
}
