package lab4;

import lab4.exceptions.CustomNumberFormatException;

import java.io.FileWriter;
import java.io.IOException;

public class NumberParser {
    public static void main(String[] args) {
        String numberStr = "123a";

        try {
            int number = parseNumber(numberStr);
            System.out.println("Число: " + number);
        } catch (CustomNumberFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
            logException(e);
        }
    }

    public static int parseNumber(String str) throws CustomNumberFormatException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new CustomNumberFormatException("Невозможно преобразовать строку в число: " + str);
        }
    }

    public static void logException(Exception e) {
        try (FileWriter fw = new FileWriter("exception_log.txt", true)) {
            fw.write("Исключение: " + e.getClass().getName() + " - " + e.getMessage() + System.lineSeparator());
            System.out.println("Исключение записано в лог.");
        } catch (IOException ioException) {
            System.out.println("Ошибка при записи в лог: " + ioException.getMessage());
        }
    }
}
