/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class ZigZagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode temp = null; int size =0, level = 0;

        while(!queue.isEmpty()) {
            size = queue.size();
            LinkedList<Integer> ls = new LinkedList<>();
            for(int i=0; i< size; i++) {
                temp = queue.poll();

                if(level % 2 == 0)
                    ls.add(temp.val);
                else
                    ls.addFirst(temp.val);

                if(temp.left != null) queue.addLast(temp.left);
                if(temp.right != null) queue.addLast(temp.right);
            }
            level+=1;
            result.add(ls);
        }

        return result;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        preOrder(root, result, 1);
        return result;
    }

    private void preOrder(TreeNode root, List<List<Integer>> result, int level) {
        if(root == null) return;

        if(result.size() < level)
            result.add(new LinkedList<>());

        if(level % 2 == 1) {
            LinkedList<Integer> ls = (LinkedList) result.get(level-1);
            ls.addLast(root.val);
        }
        else {
            LinkedList<Integer> ls = (LinkedList) result.get(level-1);
            ls.addFirst(root.val);
        }

        preOrder(root.left, result, level+1);
        preOrder(root.right, result, level+1);
    }
}
