package lab4;

import lab4.exceptions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class ExtenceCheck {
    public static void logException(Exception e) {
        try (FileWriter fw = new FileWriter("exception_log.txt", true)) {
            fw.write("Исключение: " + e.getClass().getName() + " - " + e.getMessage() + System.lineSeparator());
            System.out.println("Исключение записано в лог.");
        } catch (IOException ioException) {
            System.out.println("Ошибка при записи в лог: " + ioException.getMessage());
        }
    }


    public static int except1(int num1, int num2) throws CustomDivisionException {
        if (num2 == 0){
            throw new CustomDivisionException("деление на 0");
        }
        return num1/num2;
    }

    public static int except2(int age) throws CustomAgeException {
        if (age < 0 || age > 120){
            throw new CustomAgeException("неподходящий возраст");
        }
        return age;
    }

    public static void except3(String filename) throws CustomFileNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new CustomFileNotFoundException("Файл " + filename + " не найден.");
        }
        System.out.println("Файл " + filename + " успешно найден.");
    }

    public static void except4(String str) throws CustomNumberFormatException {
        try {
            int number = Integer.parseInt(str);
            System.out.println("Число: " + number);
        } catch (NumberFormatException e) {
            throw new CustomNumberFormatException("Некорректное число: " + str);
        }
    }

    static class CustomStack {
        private Stack<Integer> stack = new Stack<>();

        public void push(int value) {
            stack.push(value);
        }

        public int pop() throws CustomEmptyStackException {
            if (stack.isEmpty()) {
                throw new CustomEmptyStackException("Ошибка: стек пуст.");
            }
            return stack.pop();
        }
    }

    public static void except6(Scanner scanner) throws CustomInputMismatchException {
        try {
            System.out.print("Введите число: ");
            int number = scanner.nextInt();
            System.out.println("Вы ввели: " + number);
        } catch (InputMismatchException e) {
            throw new CustomInputMismatchException("Некорректный ввод: ожидалось число.");
        }
    }

    public static void except7(String email) throws CustomEmailFormatException {
        if (!email.matches("^[\\w-\\.]+@[\\w-\\.]+\\.[a-z]{2,3}$")) {
            throw new CustomEmailFormatException("Некорректный формат email: " + email);
        }
        System.out.println("Email верный: " + email);
    }


    public static void except8(String operation) throws CustomUnsupportedOperationException {
        switch (operation) {
            case "add":
                System.out.println("Операция сложения.");
                break;
            case "subtract":
                System.out.println("Операция вычитания.");
                break;
            case "multiply":
                System.out.println("Операция умножения.");
                break;
            case "division":
                System.out.println("Операция деления.");
                break;
            default:
                throw new CustomUnsupportedOperationException("Операция " + operation + " не поддерживается.");
        }
    }

    public static void main(String[] args){
        try {
            except1(10, 0);
        } catch (CustomDivisionException e) {
            System.out.println(e.getMessage());
            logException(e);
        }

        try {
            except2(130);
        } catch (CustomAgeException e) {
            System.out.println(e.getMessage());
            logException(e);
        }

        try {
            except3("file.txt");
        } catch (CustomFileNotFoundException e) {
            System.out.println(e.getMessage());
            logException(e);
        }

        try {
            except4("123");
        } catch (CustomNumberFormatException e) {
            System.out.println(e.getMessage());
            logException(e);
        }

        CustomStack stack = new CustomStack();
        try {
            stack.pop();
        } catch (CustomEmptyStackException e) {
            System.out.println(e.getMessage());
            logException(e);
        }

        Scanner scanner = new Scanner(System.in);
        try {
            except6(scanner);
        } catch (CustomInputMismatchException e) {
            System.out.println(e.getMessage());
            logException(e);
        }

        try {
            except7("wrong-emai@gmail.com");
        } catch (CustomEmailFormatException e) {
            System.out.println(e.getMessage());
            logException(e);
        }

        try {
            except8("multiply");
        } catch (CustomUnsupportedOperationException e) {
            System.out.println(e.getMessage());
            logException(e);
        }

    }
}
