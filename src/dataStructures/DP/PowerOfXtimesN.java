/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.DP;

/**
 * https://leetcode.com/problems/powx-n/discuss/19593/O-(logn)-solution-in-Java
 * https://leetcode.com/problems/powx-n/discuss/19544/5-different-choices-when-talk-with-interviewers
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class PowerOfXtimesN {

    public double myPow(double x, int n) {
        if(n==0) return 1;
        double t = myPow(x,n/2);
        if(Math.abs(n)%2 == 1) return n<0 ? 1/x*t*t : x*t*t;
        return t*t;
    }

    //My Soln
    public double myPow2(double x, int n) {
        double result = 0.0;
        //result = compute(x, x > 0 && n == Integer.MIN_VALUE ? Math.abs(n-1): Math.abs(n), map);
        result = compute(x, x > 0 && n == Integer.MIN_VALUE ? Math.abs(n-1): Math.abs(n));
        return n < 0 ? 1/result : result;
    }

    private double compute(double x, int n) {
        if(n == 0) return 1.0;
        else if(n == 1) return x;

        double result = compute(x, n/2);
        return n%2 == 1 ? x*result*result: result*result;
    }
}
