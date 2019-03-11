/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class BSTValidate {

    public boolean isValidBSTRecursive(TreeNode root) {
        return recurse(root, (long) Integer.MIN_VALUE-1, (long) Integer.MAX_VALUE+1);
    }

    public boolean recurse(TreeNode root, long min, long max) {
        if(root == null) return true;

        if( (long) root.val <= min || (long) root.val >= max)
            return false;

        return recurse(root.left, min, root.val) &&
                recurse(root.right, root.val, max);
    }

    public boolean inorder2(TreeNode root, List<Integer> list) {
        if( root == null) return true;

        boolean flag = inorder2(root.left, list);
        if(!list.isEmpty() && list.get(list.size() -1) >= root.val)
            return false;
        else
            list.add(root.val);
        flag = flag && inorder2(root.right, list);

        return flag;
    }

    private boolean validateBST2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        long curr = (long)Integer.MIN_VALUE -1;
        TreeNode temp = root;

        while(!stack.isEmpty() || temp != null) {
            if (temp != null) {
                stack.push(temp);
                while (temp.left != null) {
                    stack.push(temp.left);
                    temp = temp.left;
                }
            }
            temp = stack.pop();
            //System.out.println(temp.val);
            if (curr >= temp.val) return false;
            curr = temp.val;
            temp = temp.right;
        }
        return true;
    }

    public static void main(String[] args) {
        BSTValidate tree = new BSTValidate();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        TreeNode.printInorder(root);

        root = new TreeNode(2147483647);

        System.out.println(tree.isValidBSTRecursive(root));
    }

}
