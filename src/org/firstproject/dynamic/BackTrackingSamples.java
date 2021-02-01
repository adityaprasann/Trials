package org.firstproject.dynamic;

import java.util.LinkedHashSet;

public class BackTrackingSamples {
    public static void main(String[] args) {
        BackTrackingSamples bts = new BackTrackingSamples();
        String anagram = "god";
        boolean[] used = new boolean[anagram.toCharArray().length];
        bts.makeAnagram(anagram.toCharArray(), new LinkedHashSet<>(), used);
    }

    private void makeAnagram(char[] arr, LinkedHashSet<Character> partial, boolean[] used) {
        if(partial.size() == arr.length){
            System.out.println(partial);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if(!used[i]){
                partial.add(arr[i]);
                used[i] = true;
                makeAnagram(arr, partial, used);
                used[i] = false;
                partial.remove(arr[i]);
            }
        }
    }
}
