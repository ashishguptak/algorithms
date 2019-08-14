/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/articles/reorder-log-files/
 *
 * https://leetcode.com/problems/reorder-log-files/discuss/193872/Java-Nothing-Fancy-15-lines-5ms-all-clear.
 * @author ashish gupta (akonda@expedia.com)
 */
public class ReOrderLogFiles {

    public String[] reorderLogFiles(String[] logs) {
        if(logs.length < 2) return logs;

        Arrays.sort(logs, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int indexA = a.indexOf(" ");
                int indexB = b.indexOf(" ");
                boolean isDigitA = false, isDigitB = false;

                if(a.charAt(indexA+1) >= '0' && a.charAt(indexA+1) <= '9')
                    isDigitA = true;
                if(b.charAt(indexB+1) >= '0' && b.charAt(indexB+1) <= '9')
                    isDigitB = true;

                if(isDigitA && isDigitB) return 0;
                else if(isDigitA)
                    return 1;
                else if(isDigitB)
                    return -1;
                else {
                    int val = a.substring(indexA+1).compareTo(b.substring(indexB+1));
                    if(val == 0) return a.substring(0, indexA).compareTo(b.substring(0, indexB));
                    return val;
                }
            }
        });

        return logs;
    }

    public String[] reorderLogFiles2(String[] logs) {
        if (logs.length < 2) return logs;

        List<String> list = new ArrayList<>();
        for (String each : logs) {
            int index = each.indexOf(" ");
            if (each.charAt(index + 1) >= 'a' && each.charAt(index + 1) <= 'z')
                list.add(each);
        }

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int indexA = a.indexOf(" ");
                int indexB = b.indexOf(" ");
                int val = a.substring(indexA + 1).compareTo(b.substring(indexB + 1));
                if (val == 0)
                    return a.substring(0, indexA).compareTo(b.substring(0, indexB));
                return val;
            }
        });

        for (String each : logs) {
            int index = each.indexOf(" ");
            if (each.charAt(index + 1) >= '0' && each.charAt(index + 1) <= '9')
                list.add(each);
        }
        return list.toArray(new String[0]);
    }
}
