package lab7;

import java.util.concurrent.*;

public class MaxElementInMatrix {
    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int numberOfThreads = matrix.length;

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        Future<Integer>[] results = new Future[numberOfThreads];

        try {
            for (int i = 0; i < numberOfThreads; i++) {
                final int row = i;
                results[i] = executor.submit(() -> findMaxInRow(matrix, row));
                    }

            int maxElement = Integer.MIN_VALUE;
            for (Future<Integer> result : results) {
                maxElement = Math.max(maxElement, result.get());
            }

            System.out.println("Maximum element in the matrix: " + maxElement);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    public static int findMaxInRow(int[][] matrix, int row) {
        int max = Integer.MIN_VALUE;
        for (int value : matrix[row]) {
            max = Math.max(max, value);
        }
        return max;
    }
}