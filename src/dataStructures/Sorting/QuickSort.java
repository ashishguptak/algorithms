package dataStructures.Sorting; /**
 * Copyright 2017 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.Arrays;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class QuickSort {

    public static void quicksort(int[] arr, int start, int end){
        if(start >= end ) return;
        int pIndex = partition(arr, start, end);
        quicksort(arr, start, pIndex-1);
        quicksort(arr, pIndex+1, end);
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int pIndex = start;
        for(int i=start; i<end; i++){
            if(arr[i] <= pivot){
                swap(arr, i, pIndex);
                pIndex++;
            }
        }
        swap(arr, pIndex, end);
        return pIndex;
    }

    private static void swap(int[] arr,int i, int j) {
        int temp = arr[i];
        arr[i] =arr[j];
        arr[j] = temp;
    }

    public static void main(String[] arg) {
        int[] nums = {7,2,18,6,5,3,4};
        System.out.println(Arrays.toString(nums));
        quicksort(nums, 0, nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
}
