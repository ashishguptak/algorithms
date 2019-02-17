/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Strings;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * https://leetcode.com/problems/decode-string/discuss/87534/simple-java-solution-using-stack
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class DecodeString {

    public String decodeString1(String s) {
        if( s.length() < 1) return "";

        Deque<String> strStack = new ArrayDeque<>();
        Deque<Integer> stack = new ArrayDeque<>();

        String temp = "";
        strStack.push(temp);
        int counter=0;

        for(int i=0; i<s.length(); i++) {
            if(Character.isDigit(s.charAt(i))) {
                counter = 10*counter + s.charAt(i)-'0';
            } else if(s.charAt(i) == '[') {
                stack.push(counter);
                counter =0;
                strStack.push("");
            } else if(s.charAt(i) == ']') {

                int val = stack.pop();
                String inter = strStack.pop();
                StringBuilder sbinter = new StringBuilder();
                while(val > 0) {
                    sbinter.append(inter);
                    val--;
                }
                strStack.push(strStack.pop()+sbinter.toString());
            } else
                strStack.push(strStack.pop()+s.charAt(i));
        }

        return strStack.pop();
    }

    public String decodeString(String s) {
        String res = "";
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<String> resStack = new ArrayDeque<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            } else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder(resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            } else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }

}
