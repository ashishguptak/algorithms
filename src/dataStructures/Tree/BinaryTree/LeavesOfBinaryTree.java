/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-leaves-of-binary-tree/
 *
 * For this question we need to take bottom-up approach. The key is to find the height of each node. Here the definition of height is:
 * The height of a node is the number of edges from the node to the deepest leaf. --CMU 15-121 Binary Trees
 *
 * I used a helper function to return the height of current node. According to the definition, the height of leaf is 0. h(node) = 1 + max(h(node.left), h(node.right)).
 * The height of a node is also the its index in the result list (res). For example, leaves, whose heights are 0, are stored in res[0]. Once we find the height of a node, we can put it directly into the result.
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class LeavesOfBinaryTree {

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        traversal(root, result);
        return result;
    }

    private int traversal(TreeNode root, List<List<Integer>> result) {
        if(root == null) return -1;

        int left = traversal(root.left, result);
        int right = traversal(root.right, result);
        int level = 1 + Math.max(left, right);
        if(result.size() == level)
            result.add(new ArrayList<>());
        result.get(level).add(root.val);

        return level;
    }
}
