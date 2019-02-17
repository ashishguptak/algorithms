/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class FindLeftMostBottomValue {

    private static int count =0;

    public int findBottomLeftValue2(TreeNode root, int curr, int[] res) {
        if(curr > res[1]) {
            res[1] = curr;
            res[0] = root.val;
        }
        if(root.left != null) findBottomLeftValue2(root.left, curr+1, res);
        if(root.right != null) findBottomLeftValue2(root.right, curr+1, res);

        return res[0];
    }

    public int findBottomLeftValue(TreeNode root) {
        int level = findDepth(root);
        //System.out.println(level);
        recurse(root, 1,level);

        return count;
    }

    public boolean recurse(TreeNode root, int curr, int level) {
        if( root == null) return false;

        if(curr == level) {
            //System.out.println("sdg");
            count = root.val;
            return true;
        }

        return recurse(root.left, curr+1,level) || recurse(root.right, curr+1,level);
    }

    public int findDepth(TreeNode root) {
        if(root == null) return 0;

        return 1 + Math.max(findDepth(root.left), findDepth(root.right));
    }
}
