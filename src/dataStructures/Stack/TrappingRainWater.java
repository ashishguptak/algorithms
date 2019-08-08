/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Stack;

import java.util.Stack;

/**
 * https://leetcode.com/articles/trapping-rain-water/
 *  https://leetcode.com/problems/trapping-rain-water/discuss/17414/A-stack-based-solution-for-reference-inspired-by-Histogram
 * https://leetcode.com/problems/trapping-rain-water/discuss/17425/Intuitive-Java-solution-with-a-detailed-explanation
 *
 *  For each element in array, we find the max level of water it can trap after th rain, which is equal to the min of max height of bars on both sides minus its own height
 *  Algorithm-
 *  Iniitase ans=0, maxleft=0, maxright=0;
 *  Iterate array from left to right
 *  Iterate from the curr element to the beginning of array updating
 *  maxleft = Math.max(maxleft, height[j])
 *  similar for right
 *
 *  ans += min(maxleft, maxright) - height[i]
 *
 *
 * The level of the water on top of each bar is determined by the smaller of the two values: leftMax and rightMax.
 * leftMax is the maximum height of the bars that are located to the left of the bar.
 * rightMax is the maximum height of the bars that are located to the right of the bar.
 *
 * For example, if our array is [13,4,10,2,5,7] and we would like to find the level of the water at index 3. We would search for the largest bar to the left (leftMax = 13), and the largest bar to the right (rightMax = 7). So the level of the water is going to be min(13,7) = 7.
 *
 * Notice that in order to find the amount of water that was trapped in a particular bar, we would take the the height of the bar and subtract it from the level of the water. So in our example, the actual amount of trapped water at index 3 is 7 - 2 = 5.
 * Therefore, the following invariant holds:
 *
 *  time O(n2)
 *
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class TrappingRainWater {

    //using DP
    public int trap3(int[] height) {
        if(height.length < 3) return 0;

        int ans=0;
        int[] maxleft = new int[height.length];
        int[] maxright = new int[height.length];

        maxleft[0] = height[0];
        for(int i=1; i< height.length; i++)
            maxleft[i] = Math.max(maxleft[i-1], height[i]);

        maxright[height.length-1] = height[height.length-1];
        for(int i= height.length-2; i>=0; i--)
            maxright[i] = Math.max(maxright[i+1], height[i]);

        for(int i=0; i< height.length; i++)
            ans += Math.min(maxleft[i], maxright[i]) - height[i];

        return ans;
    }

    //brute force
    public int trap2(int[] height) {
        if(height.length < 3) return 0;

        int ans=0;
        int maxleft =0, maxright =0;

        for(int i=0; i< height.length; i++) {
            int j=i;
            maxleft = 0;
            while ( j >= 0)
                maxleft = Math.max(maxleft, height[j--]);
            j = i; maxright = 0;
            while( j< height.length)
                maxright = Math.max(maxright, height[j++]);
            ans += Math.min(maxleft, maxright) - height[i];
        }
        return ans;
    }

    /**
     * We keep a stack and iterate over the array
     * WE add the index of the bar to the stack if bar is smaller than or equal to the bar at top of stack, which means the current bar is bounded by the previous bar in the stack. If we found a bar longer than that at the top, we are sure that the bar at top of stack is bounded by curr bar and previous bar.Hence we can pop it and add resulting trapped water to ans
     *
     *
     * Use th stack to store the indices
     * Iterate the array -
     *  while stack is not empty and height[curr] > height[stack.top()]
     *   it means the stack elemtn can be popped.
     *   find the
     *
     *
     */

    public int trap(int[] height) {
        if (height == null || height.length < 2) return 0;

        Stack<Integer> stack = new Stack<>();
        int water = 0, i = 0;
        while (i < height.length) {
            if (stack.isEmpty() || height[i] <= height[stack.peek()]) {
                stack.push(i++);
            } else {
                int pre = stack.pop();
                if (!stack.isEmpty()) {
                    // find the smaller height between the two sides
                    int minHeight = Math.min(height[stack.peek()], height[i]);
                    // calculate the area
                    water += (minHeight - height[pre]) * (i - stack.peek() - 1);
                }
            }
        }
        return water;
    }
}
