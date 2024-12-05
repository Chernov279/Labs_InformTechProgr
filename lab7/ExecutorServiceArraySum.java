package lab7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceArraySum {

    private static int calculateSum(int[] array, int start, int end) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += array[i];
        }
        return sum;
    }

    private static int[][] divideArray(int[] array, int numberOfThreads) {
        int threadArraySize = array.length / numberOfThreads;
        int[][] parts = new int[numberOfThreads][2];

        for (int i = 0; i < numberOfThreads; i++) {
            parts[i][0] = i * threadArraySize;
            if (i == numberOfThreads - 1){
                parts[i][1] = array.length;
            }else{
                parts[i][1] = (i + 1) * threadArraySize;
            }
        }
        return parts;
    }

    public static void main(String[] args) {
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        int numberOfThreads = 4;
        int[][] parts = divideArray(array, numberOfThreads);

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        try {
            int totalSum = 0;
            Future<Integer>[] results = new Future[numberOfThreads];

            for (int i = 0; i < numberOfThreads; i++) {
                final int start = parts[i][0];
                final int end = parts[i][1];
                results[i] = executor.submit(() -> calculateSum(array, start, end));
            }

            for (Future<Integer> result : results) {
                totalSum += result.get();
            }

            System.out.println("Total sum: " + totalSum);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}

//Executors.newFixedThreadPool(numberOfThreads) создает пул потоков с фиксированным числом потоков (здесь numberOfThreads).
//Потоки из этого пула будут использоваться для выполнения задач.
//Почему пул потоков? Это позволяет управлять потоками, повторно использовать их и контролировать их завершение.

//Future<Integer>[] results = new Future[numberOfThreads];
//Создается массив объектов Future, чтобы хранить результаты выполнения задач.
//Future позволяет асинхронно получать результат выполнения потока.

//Лямбда-выражение () -> calculateSum(array, start, end) в контексте этого метода будет восприниматься
//как реализация интерфейса Callable<Integer>, где Integer — это тип результата (в данном случае сумма).