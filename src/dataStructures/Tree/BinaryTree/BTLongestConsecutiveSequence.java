/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

/**
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
 *
 *
 * Solve - Binary Tree Longest Consecutive Sequence II
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class BTLongestConsecutiveSequence {
    private int maxCount=0;

    public int longestConsecutive(TreeNode root) {
        if(root == null) return 0;
        recurse(root, 1);
        recurse(root, 0, root.val);
        int[] max = {0};
        helper(root, max);
        return max[0];
        //return maxCount;
    }

    //better soln, Top-down
    public void recurse(TreeNode root, int count, int parentVal) {
        if(root == null) return;

        if(root.val == parentVal) count++;
        else count = 1;

        maxCount = Math.max(maxCount, count);

        recurse(root.left, count, root.val+1);
        recurse(root.right, count, root.val+1);
    }


    //no instance variable, bottom-up approach
    private int helper(TreeNode root, int[] max) {
        if(root == null) return 0;

        int curMax = 1;
        int leftMax = helper(root.left, max);
        int rightMax = helper(root.right, max);

        if(root.left != null && root.val +1 == root.left.val)
            curMax+= Math.max(leftMax+1, curMax);
        if(root.right != null && root.val +1 == root.right.val)
            curMax+= Math.max(leftMax, rightMax);

        max[0] = Math.max(max[0], curMax);
        return curMax;
    }

    //TLE
    public void recurse(TreeNode root, int count) {
        if(root == null) return;

        if(root.left != null && root.left.val == root.val +1) {
            recurse(root.left, count+1);
        }else {
            maxCount = Math.max(maxCount, count);
        }
        if(root.right != null && root.right.val == root.val +1) {
            recurse(root.right, count+1);
        } else {
            maxCount = Math.max(maxCount, count);
        }
        recurse(root.left, 1);
        recurse(root.right, 1);
    }

}
