/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Tree.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class ViewsOfBinaryTree {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;

        int size = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while( !queue.isEmpty()) {
            size = queue.size();
            for(int i=0; i < size; i++) {
                TreeNode temp = queue.poll();
                if(i == 0)
                    list.add(temp.val);
                if(temp.right != null)
                    queue.offer(temp.right);
                if(temp.left != null)
                    queue.offer(temp.left);
            }
        }

        return list;
    }

    //soln 2 with recursion
    public void recurse(TreeNode root, int level, List<Integer> list) {
        if(root == null) return;
        if(list.size() == level)
            list.add(root.val);

        recurse(root.right, level+1, list);
        recurse(root.left, level+1, list);
    }
}
