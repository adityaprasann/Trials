package org.firstproject.dynamic;

import java.util.Arrays;

public class Recursionsamples {
    public static void main(String[] args) {
        Recursionsamples rs = new Recursionsamples();
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("The array " + Arrays.toString(arr) + " is in sequence - " + rs.isSequence(arr, 0, arr.length -2));
        System.out.println("The sum of array" + Arrays.toString(arr) + " is " + rs.arrSum(arr, 0, arr.length - 1));
    }

    private boolean isSequence(int[] arr, int idx, int length) {
        if(idx == length)
            return true;
        return (arr[idx+1] - arr[idx] == 1) && isSequence(arr, ++idx, length);
    }

    private int arrSum(int[] arr, int idx, int length){
        if(idx == length)
            return arr[idx];
        return arr[idx] + arrSum(arr, ++idx, length);
    }
}
