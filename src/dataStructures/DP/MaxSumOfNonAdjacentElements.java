/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.DP;

/**
 * http://blog.gainlo.co/index.php/2016/12/02/uber-interview-question-maximum-sum-non-adjacent-elements/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class MaxSumOfNonAdjacentElements {

    private int getMaxSum(int[] arr) {
        if(arr.length == 0) return 0;

        int[] sum = new int[arr.length+1];

        sum[0] =0; sum[1] = arr[0];

        for(int j=1; j<arr.length; j++) {

            sum[j+1] = Math.max(sum[j-1] + arr[j], sum[j]);
        }

        return sum[arr.length];
    }

    private int getMaxSum1(int[] arr) {
        if(arr.length == 0) return 0;

        int prevOne =0, prevTwo=0, curr=0;

        for(int i=0; i<arr.length; i++) {
            if( i == 0)
                curr = arr[0];
            else if (i == 1)
                curr = Math.max(arr[0], arr[1]);
            else
                curr = Math.max(arr[i] + prevTwo, prevOne);

            prevTwo = prevOne;
            prevOne = curr;
        }

        return curr;
    }

    public static void main(String[] args) {
        MaxSumOfNonAdjacentElements elements  = new MaxSumOfNonAdjacentElements();

        int arr[] = new int[]{5, 5, 10, 100, 10, 5};

        int arr1[] = new int[]{1, 0, 3, 9, 2};

        System.out.println(elements.getMaxSum1(arr));
        System.out.println(elements.getMaxSum1(arr1));

    }
}
