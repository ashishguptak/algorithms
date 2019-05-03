/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Arrays;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/discuss/14701/A-very-simple-Java-solution-with-only-one-binary-search-algorithm
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/discuss/14707/9-11-lines-O(log-n)
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class FirstandLastPositionofElementSortedArray {

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if(nums.length < 1) return result;

        //bs(nums, target, result, 0, nums.length-1);

        result[0] = findElemSmall(nums, target);
        if( result[0] != -1)
            result[1] = findElemLarge(nums, target);


        int val = findFirstGreaterThanEqual(nums, target);
        if(val == nums.length || nums[val] != target) return result;
        result[0] = val;
        result[1] = findFirstGreaterThanEqual(nums, target+1) - 1;

        return result;
    }

    // nice approach
    public int[] searchRange2(int[] nums, int target) {
        int res[] = { -1, -1 };
        if (nums.length == 0)
            return res;
        int left = binarySearch(nums, target - 0.5);
        int right = binarySearch(nums, target + 0.5);
        if (right - left == 0)
            return res;
        res[0] = left;
        res[1] = right - 1;
        return res;
    }

    private int binarySearch(int[] arr, double target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > target)
                right = mid - 1;
            else if(arr[mid] < target)
                left = mid + 1;
        }
        return left;
    }


    private void bs(int[] nums, int target, int[] result, int l, int r) {
        if(l > r) return;

        int mid = l + (r- l)/2;
        if(nums[mid] == target) {
            if(mid == 0) result[0] = mid;
            if (mid == nums.length-1) result[1] = mid;

            if(mid > 0 && nums[mid-1] == target) {
                bs(nums, target, result, l, mid-1);
            }
            if (mid > 0 && nums[mid-1] != target) {
                result[0] = mid;
                bs(nums, target, result, mid+1, r);
            }
            if (mid < nums.length -1 && nums[mid+1] != target) {
                result[1] = mid;
                bs(nums, target, result, l, mid -1);
            }
            if( mid < nums.length -1 && nums[mid+1] == target) {
                bs(nums, target, result, mid+1, r);
            }

        }
        else if(nums[mid] < target)
            bs(nums, target, result, mid+1, r);
        else
            bs(nums, target, result, l, mid-1);
    }

    private int findElemSmall(int[] nums, int target) {
        int mid =0 , l=0, r = nums.length -1;
        while(l <= r) {
            mid = (l+r)/2;
            if (nums[mid] == target) {
                if (mid == 0) return 0;
                else if(nums[mid-1] == target)
                    r = mid -1;
                else
                    break;
            } else if (nums[mid] < target)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return nums[mid] == target ? mid : -1;
    }

    private int findElemLarge(int[] nums, int target) {
        int mid =0 , l=0, r = nums.length -1;
        while(l <= r) {
            mid = (l+r)/2;
            if (nums[mid] == target) {
                if (mid == nums.length -1 ) return mid;
                if(nums[mid+1] == target)
                    l = mid + 1;
                else
                    break;
            } else if (nums[mid] < target)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return mid;
    }
    /**
     *  find the first number greater than or equal to target by doing a modifiedBS logic twice with target and target+1,
     *  I can fetch the indexes. In second search, the actual index will be index -1;
     *
     *
     Here, my helper function is a simple binary search, telling me the first index where I could insert a number n into nums to keep it sorted. Thus, if nums contains target, I can find the first occurrence with search(target). I do that, and if target isn't actually there, then I return [-1, -1]. Otherwise, I ask search(target+1), which tells me the first index where I could insert target+1, which of course is one index behind the last index containing target, so all I have left to do is subtract 1.
     */
    private int findFirstGreaterThanEqual(int[] nums, int target) {
        int mid =0, l = 0, r = nums.length;
        while(l < r) {
            mid = l + (r-l)/2;
            if(nums[mid] >= target) {
                r = mid;
            } else
                l = mid+1;
        }
        return l;
    }
}
