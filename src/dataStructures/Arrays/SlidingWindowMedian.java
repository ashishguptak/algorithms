/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays;

/**
 * https://leetcode.com/problems/sliding-window-median/description/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class SlidingWindowMedian {

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();

        int arr[]={12, 1, 78, 90, 57, 89, 56};
        int k =3;
        swm.findMedian(arr, k);
        System.out.println();
    }

    private void findMedian(int[] arr, int k) {
    }
}
