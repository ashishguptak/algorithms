/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.binarySearch;

/**
 * @author ashish gupta (akonda@expedia.com)
 */

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class FirstBadVersion {
    public int firstBadVersion(int n) {
        if(n < 1) return -1;

        int mid= 0, start =1, end =n;
        while(start <= end) {
            mid = start + (end-start)/2;
            if(isBadVersion(mid)) {
                if(mid == 1) return mid;
                if(!isBadVersion(mid-1)) return mid;
                end = mid-1;
            }else
                start = mid+1;
        }
        return mid;
    }

    private boolean isBadVersion(int n){
        return false;
    }
}

/*
    1 2 3 4 5

    1+5/2 = 3

    4+5/2 = 4
   start =1  end = 2
    mid = 1

 range of ver [1, 2, 3,..n]

    1, 2, 3..i, i+1,,,,n i on wards quality ceck fails

    isBadVersion(version) whether version is bad

    since version no are in seq, we could potentially calls at mid-pt in that range and check if its bad version.

    yes -> {
        i-1 > 0 and isBad er(i-1) returns false
        then return i;
        else
            end = mid -1;
    }
    no -> {
        start = mid +1;
    }
*/