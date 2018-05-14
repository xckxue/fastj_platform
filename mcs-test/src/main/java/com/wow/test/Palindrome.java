package com.wow.test;

/**
 * Created by wow on 2018/4/8.
 */
public class Palindrome {
    public String findPalindrome(String str) {
        String maxString = "";
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length() + 1; j++) {
                String tmp = str.substring(i, j);
                if (isPalindrome(tmp) && tmp.length() > maxString.length()) {
                    maxString = tmp;
                }
            }
        }

        if (maxString.length() > 1) {
            return maxString;
        }
        return null;
    }

    public boolean isPalindrome(String str) {
        StringBuffer sb = new StringBuffer(str);
        return str.equals(sb.reverse().toString());
    }

    public static void main(String[] args) {
        Palindrome p = new Palindrome();
        System.out.println(p.findPalindrome("ABCBCA"));
    }

}
