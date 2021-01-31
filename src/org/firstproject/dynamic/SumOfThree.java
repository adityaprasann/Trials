package org.firstproject.dynamic;

import java.util.Arrays;

public class SumOfThree {
    public static void main(String[] args) {
        SumOfThree sumOfThree = new SumOfThree();
        int[] arr = {3, 7, 1, 2, 8, 4, 5};
        System.out.println("Original Array: " + Arrays.toString(arr));
        System.out.println("Sum 20 exists " + sumOfThree.findSumOfThreeBForce(arr, 20));
        System.out.println("Sum 10 exists " + sumOfThree.findSumOfThreeBForce(arr, 10));
        System.out.println("Sum 21 exists " + sumOfThree.findSumOfThreeBForce(arr, 21));

        Arrays.sort(arr);
        System.out.println("Original Array: " + Arrays.toString(arr));
        System.out.println("Sum 20 exists " + sumOfThree.findSumOfThree(arr, 20));
        System.out.println("Sum 10 exists " + sumOfThree.findSumOfThree(arr, 10));
        System.out.println("Sum 21 exists " + sumOfThree.findSumOfThree(arr, 21));
    }

    private boolean findSumOfThreeBForce(int[] arr, int key) {
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                for (int k = i + 2; k < arr.length; k++) {
                    if((i != j) && (j != k) && (i != k)){
                        if(key == arr[i] + arr[j] +  arr[k])
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean findSumOfThree(int[] arr, int key) {
        for (int i = 0; i < arr.length - 2; i++) {
            int l = i;
            int r = arr.length - 1;
            while(l < r){
                if(key == arr[i] + arr[l] +  arr[r])
                    return true;
                else{
                    if(arr[i] + arr[l] +  arr[r] < key)
                        l++;
                    else
                        r--;
                }
            }
        }
        return false;
    }
}
