package org.firstproject.misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MakeZeros {
    public static void main(String[] args) {
        MakeZeros mz = new MakeZeros();
        int[][] matrix = {
                {5, 4, 3, 9},
                {2, 0, 7, 6},
                {1, 3, 4, 0},
                {9, 8, 3, 4}
        };
        System.out.println("Original Matrix");
        mz.printMatrix(matrix);
        mz.makeZeros(matrix);
        System.out.println("\nMatrix with Zeros");
        mz.printMatrix(matrix);

        matrix = new int [] [] {
                {2, 4, 9, 13, 15},
                {3, 5, 11, 18, 22},
                {6, 8, 16, 21, 28},
                {9, 11, 20, 25, 31},
        };
        
        mz.printMatrix(matrix);
        List<Integer> result = mz.searchInMatrix(matrix, 8);
        System.out.println(result);
    }



    private void makeZeros(int[][] matrix) {
        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroColumns = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroRows.add(i);
                    zeroColumns.add(j);
                }
            }
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int x: zeroRows) {
            for (int i = 0; i < cols; i++) {
                matrix[x][i] = 0;
            }
        }
        for (int y: zeroColumns) {
            for (int i = 0; i < rows; i++) {
                matrix[i][y] = 0;
            }
        }
    }

    private void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < ints.length; j++) {
                System.out.print(ints[j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    private List<Integer> searchInMatrix(int[][] matrix, int key) {
        List<Integer> result = new ArrayList<>();
        int rows = 0;
        int cols = matrix[0].length - 1;
        while (rows < matrix.length - 1 && cols > 0){
            if(matrix[rows][cols] == key){
                result.add(rows);
                result.add(cols);
                return result;
            } else{
                if(matrix[rows][cols] > key){
                    cols--;
                } else
                    rows++;
            }
        }

        if(result.size() == 0){
            result.add(-1);
            result.add(-1);
        }
        return result;
    }
}
