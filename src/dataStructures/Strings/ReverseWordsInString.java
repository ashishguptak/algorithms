/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Strings;

/**
 * @author ashish gupta (akonda@expedia.com)
 */
public class ReverseWordsInString {

    public String reverseWords(String s) {
        if(s.length() < 2) return s;
        int head = 0, tail = s.length() -1;

        while(head < s.length() && s.charAt(head) == ' ') head++;
        if(head == s.length()) return "";
        while(s.charAt(tail) == ' ') tail--;

        s = s.substring(head, head + (tail - head+1));
        char[] str = reverse(s.toCharArray(), 0, s.length() -1);
        //System.out.println(str);
        int curr =0, end, start = 0;

        while(start < s.length()) {
            while(str[start] == ' ') start++;

            end = start+1;
            while(end < str.length && str[end] != ' ') end++;

            char[] rev = reverse(str, start, end-1);

            for(int i= start; i<end; i++)
                str[curr++] = rev[i];
            start = end;
            if(curr < str.length) str[curr++] =' ';
        }
        //System.out.println(str);
        return String.valueOf(str, 0, curr);
    }

    private char[] reverse(char[] str, int i, int j) {
        int head = i, tail = j;
        while(head < tail) {
            char temp = str[tail];
            str[tail] = str[head];
            str[head] = temp;
            head++; tail--;
        }
        //System.out.println(str);
        return str;
    }
    public static void main(String[] args) {
        ReverseWordsInString rev = new ReverseWordsInString();
        System.out.println(rev.reverseWords("a good   example"));
    }
}
