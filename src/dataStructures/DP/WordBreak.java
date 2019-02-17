/**
 * Copyright 2018 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.DP;

import java.util.List;

/**
 * https://leetcode.com/problems/word-break/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        if( wordDict.contains(s)) return true;

        for( int i=1; i< s.length(); i++) {
             if (wordDict.contains(s.substring(0,i)) &&
                    wordBreak(s.substring(i), wordDict))
                return true;
        }
        return false;
    }

}
