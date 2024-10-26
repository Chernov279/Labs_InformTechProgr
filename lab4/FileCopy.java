package lab4;

import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        String sourceFilePath = "C:\\Users\\Валентин\\labs2kurs\\lab4\\file1.txt";
        String destinationFilePath = "C:\\Users\\Валентин\\labs2kurs\\lab4\\file2.txt";

        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(sourceFilePath);
            outputStream = new FileOutputStream(destinationFilePath);

            int byteData;
            while ((byteData = inputStream.read()) != -1) {
                outputStream.write(byteData);
            }

            System.out.println("Файл успешно скопирован!");

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при копировании файла: " + e.getMessage());
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии файла: " + e.getMessage());
            }
        }

}}
