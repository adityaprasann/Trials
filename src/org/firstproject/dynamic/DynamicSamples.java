package org.firstproject.dynamic;

public class DynamicSamples {
    public static void main(String[] args) {
        DynamicSamples ds = new DynamicSamples();
        int fib1 = ds.getFibRecWay(10);
        System.out.println(fib1);
        int fib2 = ds.getFibDynamicWay(10);
        System.out.println(fib2);
        int[] v = new int[]{-4, 2, -5, 1, 2, 3, 6, -5, 1};
        System.out.println("Sum of largest subarray: " + ds.findMaxSumSubArray(v));
        int[] arr = new int[]{1, -1, 6, -4, 2, 2};
        System.out.println("Max sum of nonadjacent subsequence: " + ds.findMaxSumNonadjacent(arr));
        System.out.println("Number of ways score 5 can be reached = " + ds.scoringOptions(5));
    }


    private int getFibRecWay(int num) {
        if(num == 0 || num == 1)
            return num;
        return getFibRecWay(num - 1) + getFibRecWay(num - 2);
    }

    private int getFibDynamicWay(int num) {
        int count = 2;
        int a = 1;
        int b = 1;
        while(count < num){
            int temp = b;
            b = a + b;
            a = temp;
            count++;
        }
        return b;
    }

    private int findMaxSumSubArray(int[] arr) {
        int currMax = arr[0];
        int globalMax = arr[0];
        for (int i = 1; i < arr.length -  1; i++) {
            if(currMax < 0)
                currMax = arr[i];
            else
                currMax = currMax + arr[i];
            if(globalMax < currMax)
                globalMax = currMax;
        }
        return globalMax;
    }

    private int findMaxSumNonadjacent(int[] arr) {
        int[] result = new int[arr.length];
        result[0] = arr[0];
        for (int i = 1; i < arr.length; i++)  {
            result[i] = Math.max(arr[i], result[i - 1]);
            if ((i - 2) >= 0) {
                result[i] = Math.max(result[i], arr[i] + result[i - 2]);
            }
        }
        return result[arr.length - 1];
    }

    private int scoringOptions(int n) {
        if(n <= 0)
            return 0;

        // Max score is 4. Hence we need to save
        // last 4 ways to calculate the number of ways
        // for a given n
        int[] result = new int[4];

        //save the base case on last index of the vector
        result[3] = 1;

        for(int i = 1; i <= n; i++) {
            int sum = result[3] + result[2] + result[0];

            //slide left all the results in each iteration
            //result for current i will be saved at last index
            result[0] = result[1];
            result[1] = result[2];
            result[2] = result[3];
            result[3] = sum;
        }
        return result[3];
    }
}
