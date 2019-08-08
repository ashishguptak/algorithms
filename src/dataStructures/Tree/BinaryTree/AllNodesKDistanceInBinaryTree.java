/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/articles/all-nodes-distance-k-in-binary-tree/
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143752/JAVA-Graph-%2B-BFS
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class AllNodesKDistanceInBinaryTree {

    private List<Integer> result;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        result = new ArrayList<>();
        findPath(root, target, K);
        return result;
    }

    private int findPath(TreeNode root, TreeNode target, int K) {
        if(root == null) return -1;

        if(root.val == target.val) {
            dfs(root, K, result);
            return K-1;
        }
        int leftSteps = findPath(root.left, target, K);
        if(leftSteps != -1) {
            if(leftSteps == 0)
                result.add(root.val);
            else
                dfs(root.right, leftSteps-1, result);
            return leftSteps-1;
        }

        int rightSteps = findPath(root.right, target, K);
        if(rightSteps != -1) {
            if(rightSteps == 0)
                result.add(root.val);
            else
                dfs(root.left, rightSteps-1, result);
            return rightSteps-1;
        }
        return -1;
    }

    private void dfs(TreeNode root, int curr, List<Integer> list) {
        if(root == null) return;

        if(curr == 0) {
            list.add(root.val);
            return;
        }
        dfs(root.left, curr-1, list);
        dfs(root.right, curr-1, list);
    }
}
