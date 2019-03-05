/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Other;

/**
 * https://leetcode.com/problems/solve-the-equation/description/
 * refer soln - https://leetcode.com/problems/solve-the-equation/discuss/236011/2ms-beats-100-java-submission
 * https://leetcode.com/articles/solve-the-equation/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class SolveLinearEquation {

    public String solveEquation(String equation) {
        if(equation.isEmpty()) return "No solution";

        String[] hands = equation.split("=");
        String[] left = parseEachSide(hands[0]);
        String[] right = parseEachSide(hands[1]);
        /*
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
        */
        if((left[0].equals(right[0])) &&  (left[1].equals(right[1])))
            return "Infinite solutions";
        else if((left[0].equals(right[0])) &&  !(left[1].equals(right[1])))
            return "No solution";
        else {
            int val = (Integer.valueOf(right[1]) - Integer.valueOf(left[1]))/ (Integer.valueOf(left[0]) - Integer.valueOf(right[0]));
            return "x=" + val;
        }
    }

    private String[] parseEachSide(String str) {
        String rem ="0", coeff = "0";
        char sign= '+';
        int val=0; boolean zero = false;
        for(char ch: str.toCharArray()) {
            if( ch >= '0' && ch <= '9') {
                val = val*10 + ch-'0';
                if(val == 0) zero = true;
            } else if (ch == 'x') {
                if(val == 0 && !zero) val = 1;
                if(sign == '-') val = -1*val;
                coeff = String.valueOf(Integer.valueOf(coeff) + val);
                val = 0;
            } else {
                if(sign == '-') val = -1*val;
                sign = ch;
                rem = String.valueOf(Integer.valueOf(rem) + val);
                val=0;
            }
        }

        if(sign == '-') val = -1*val;
        rem = String.valueOf(Integer.valueOf(rem) + val);

        return new String[]{coeff, rem};
    }
}
