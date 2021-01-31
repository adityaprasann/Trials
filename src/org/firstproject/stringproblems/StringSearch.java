package org.firstproject.stringproblems;

import java.util.HashMap;
import java.util.Map;

public class StringSearch {
    public static void main(String[] args) {
        String text = "My name is Joe!";
        String pattern = "name";
        StringSearch ss = new StringSearch();
        int idx = ss.searchBruteForceWay(text, pattern);
        System.out.println("The text was found at " + idx);
        int idx2 = ss.searchBoyreMooreWay(text, pattern);
        System.out.println("The text was found at " + idx2);
    }



    private int searchBruteForceWay(String text, String pattern) {
        int textLen = text.length();
        int patternLen = pattern.length();
        for (int i = 0; i <= textLen-patternLen; i++) {
            int j;
            for (j = 0; j < patternLen; j++) {
                if(text.charAt(i+j) != pattern.charAt(j))
                    break;
            }
            if(j == patternLen)
                return i;
        }
        return -1;
    }

    private int searchBoyreMooreWay(String text, String pattern) {
        int textLen = text.length();
        int patternLen = pattern.length();
        Map<Character, Integer> badMatchTable = new HashMap<>();
        for (int i = 0; i < patternLen; i++) {
            badMatchTable.put(pattern.charAt(i), Math.max(1, patternLen - i - 1));
        }
        int numOfSkips;
        for (int i = 0; i <= textLen - patternLen ; i+=numOfSkips) {
            numOfSkips = 0;
            int j;
            for (j = patternLen - 1; j >= 0 ; j--) {
                if(text.charAt(i+j) != pattern.charAt(j)){
                    numOfSkips = badMatchTable.get(pattern.charAt(j)) != null ? badMatchTable.get(pattern.charAt(j)) : patternLen;
                }
             if(numOfSkips == 0)
                 return i;
            }
        }
        return -1;
    }
}
