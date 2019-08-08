/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Sorting;

import java.util.Arrays;

/**
 * Divide the array into two equal sets and then subdivide them further into two equal sets till you find only 2 elements in each set.
 * Now, compare the two elements and then merge the resultant sorted sets. By this approach, we have reduced the # comparisons among elements
 *  and decreased the time complexity of the algorithm
 *
 * Merge Sort is a divide and conquer algorithm based on the idea of breaking down a list into several sub-lists until each sublist consists od a single element and merging those sublists in a manner that results in a sorted list.
 *
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class MergeSort {

    private void mergeSort(int[] a) {
        mergeSort(a, new int[a.length], 0, a.length-1);
    }

    private void mergeSort(int[] a, int[] temp, int low, int high) {
        if(low >= high) return;

        int mid = (low+high)/2;
        mergeSort(a, temp, low, mid);
        mergeSort(a, temp, mid+1, high);
        merge(a, temp, low, mid, high);
    }

    private void merge(int[] a, int[] temp, int low, int mid, int high) {
        System.arraycopy(a, low, temp, low, high-low+1);

        int i=low, j=mid+1, k=low;

        while( i<= mid && j <= high){
            if(temp[i] < temp[j])
                a[k++] = temp[i++];
            else
                a[k++] = temp[j++];
        }

        while( i<= mid)
            a[k++] = temp[i++];

        while( j<= high)
            a[k++] = temp[j++];
    }

    public static void main(String[] args) {
        int[] arr = {6, 3, 2, 0, 12, 5, 8, 2};
        MergeSort sort = new MergeSort();
        sort.mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
