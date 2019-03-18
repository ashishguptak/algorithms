/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/path-sum-iii/description/
 * https://leetcode.com/problems/path-sum-iii/discuss/91878/17-ms-on-java-prefix-sum-method
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class PathSumIII {

    private int count=0;

    public int pathSum(TreeNode root, int sum) {
        preOrder(root, sum);
        return count;
    }

    private void preOrder(TreeNode root, int sum) {
        if(root == null) return;

        traversePath(root, 0, sum);
        preOrder(root.left, sum);
        preOrder(root.right, sum);
    }

    private void traversePath(TreeNode root, int currSum, int sum) {
        if(root == null) return;

        currSum += root.val;
        if(currSum == sum) count++;
        traversePath(root.left, currSum, sum);
        traversePath(root.right, currSum, sum);
    }

    //Better Soln
    public int pathSum2(TreeNode root, int sum) {
        if(root == null) return 0;
        return traversePath(root, sum) + pathSum2(root.left, sum)
                + pathSum2(root.right, sum);
    }

    private int traversePath(TreeNode root, int sum) {
        if(root == null) return 0;
        sum -= root.val;
        return ((sum == 0) ? 1 : 0) + traversePath(root.left, sum)
                + traversePath(root.right, sum);
    }


    //Best Soln






    //Path Sum II
    //https://leetcode.com/problems/path-sum-ii/description/
    public List<List<Integer>> pathSumII(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, sum, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TreeNode root, int sum, List<Integer> list ,
                     List<List<Integer>> result) {
        if(root == null) return;
        list.add(root.val);
        sum -= root.val;
        if(root.left == null && root.right == null && sum == 0)
            result.add(new ArrayList<>(list));
        dfs(root.left, sum, list, result);
        dfs(root.right, sum, list, result);
        list.remove(list.size() -1);
    }
}
