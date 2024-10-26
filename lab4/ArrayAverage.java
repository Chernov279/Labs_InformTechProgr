package lab4;

public class ArrayAverage {
    public static void main(String[] args) {
        Object[] arr = {1, 2, '3', 4, 5};
        double sum = 0;
        int count = 0;

        try {
            for (int i = 0; i < arr.length; i++) {
                sum += (int) arr[i];
                count++;
            }

            if (count == 0) {
                throw new ArithmeticException("Массив не содержит чисел для вычисления среднего(деление на 0)");
            } else {
                double average = sum / count;
                System.out.println("Среднее арифметическое: " + average);
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (ClassCastException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
