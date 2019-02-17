/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/basic-calculator/description/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class BasicCalculator {

    public int calculate(String s) {
        if(s.isEmpty()) return 0;

        int result =0;
        Deque<Integer> deque = new ArrayDeque<>();
        int num=0, sign = 1;
        for(char ch: s.toCharArray()) {
            if( ch == ' ') continue;
            else if(Character.isDigit(ch)) {
                num = num*10 + ch-'0';
            } else if (ch == '(') {
                deque.push(result);
                deque.push(sign);
                result = 0;
                sign = 1;
            } else if (ch == ')') {
                result += sign*num;
                result = deque.pop()*result + deque.pop();
                num = 0;
            } else {
                result += sign*num;
                sign = ch=='+' ? 1: -1;
                num = 0;
            }
        }
        if(num != 0) result += sign*num;
        return result;
    }
}
