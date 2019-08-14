/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/longest-absolute-file-path/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class LongestAbsoluteFilePath {

    public int lengthLongestPath(String input) {
        Deque<Integer> stack = new ArrayDeque<>();
        String[] arr = input.split("\n");
        int maxLen = 0;
        stack.push(0); //dummy null length
        for (String s : arr) {
            /*
            numOfTabs is the number of "\t", numOfTabs = 0
            when "\t" is not found, because s.lastIndexOf("\t") returns -1.
            So normally, the first parent "dir" have numOfTabs 0.
            */
            int numOfTabs = s.lastIndexOf("\t") + 1;
            /* Level is defined as numOfTabs + 1.
            For example, in "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext",
            dir is level 1, subdir1 and subdir2 are level 2, file.ext is level3
            */
                int level = numOfTabs + 1;
            /*
            The following part of code is the case that we want to consider when there are
            several subdirectories in a same level. We want to remove
            the path length of the directory or the file of same level
            that we added during previous step, and calculate
            the path length of current directory or file that we are currently looking at.
            */
            while (level < stack.size()) stack.poll();
            int curLen = stack.peek() + s.length() - numOfTabs + 1;
            stack.push(curLen);
            if (s.contains("."))
                maxLen = Math.max(maxLen, curLen - 1); //Only update the maxLen when a file is discovered,
            // And remove the "/" at the end of file
        }
        return maxLen;
    }
}

/*
Are we guarantedd that string is valid like it actually represents file system?
how to recognize file and dir diff?
\n represents the end of directory/file path
# \t will give the depth of file system for that dir/file

find the shortest path for files alone
Stack up the length of path till that point in the path

add a new element to stack at every new level.

len is directory char len + 1

Size of stack will represent the level in the path
if a particular path -( dfs) is finished,
 pop the # elemments in stack which are more than or equal to \t times

Continue with rest of the string.

 Time O(n)
 Space O(n)
 */