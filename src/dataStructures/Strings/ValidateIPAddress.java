/**
 * Copyright 2019 Expedia, Inc. All rights reserved.
 * EXPEDIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package dataStructures.Strings;

/**
 * https://leetcode.com/problems/validate-ip-address/description/
 *
 * @author ashish gupta (akonda@expedia.com)
 */
class ValidateIPAddress {

    public String validIPAddress(String IP) {
        if(IP.isEmpty()) return "Neither";

        String[] list = IP.split(":|\\.", -1);
        //System.out.println(Arrays.toString(list));
        if(list.length != 4 && list.length != 8)
            return "Neither";

        return list.length == 4 ? validatev4(list) ? "IPv4" : "Neither"
                : validatev6(list) ? "IPv6" : "Neither";
    }

    private boolean validatev4(String[] list) {
        for(String str: list) {
            //System.out.println(str);
            if(str.length() > 3 || str.length() < 1) return false;
            if((str.length() > 1 && str.charAt(0) == '0') || str.charAt(0) == '-') return false;
            try {
                int val = Integer.valueOf(str);
                if(val < 0 || val > 255) return false;
            } catch (NumberFormatException ex) {
                return false;
            }
        }
        return true;
    }

    private boolean validatev6(String[] list) {
        int count = 0;
        for(String str: list) {
            if(str.length() > 4 || str.length() < 1) return false;
            if(str.charAt(0) == '0') count++;
            if(count > 4) return false;
            for(char ch: str.toCharArray()) {
                if(!(ch >= '0' && ch <= '9') && !(ch >= 'A' && ch <= 'F') && !(ch >= 'a' && ch <= 'f'))
                return false;
            }
        }
        return true;
    }
}
