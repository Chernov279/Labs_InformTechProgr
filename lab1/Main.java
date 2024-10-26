package lab1;

//Point2d.java
//public class Main {
///// ** координата X **/
//    private double xCoord;
//
///// ** координата Y **/
//    private double yCoord;
///// ** Конструктор инициализации **/
//    public Point2d (double x, double y) {
//        xCoord = x;
//        yCoord = y;
//    }
///// ** Конструктор по умолчанию. **/
//    public Point2d () {
//        this(0, 0);
//    }
///// ** Возвращение координаты X **/
//    public double getX () {
//        return xCoord;
//    }
///// ** Возвращение координаты Y **/
//    public double getY () {
//        return yCoord;
//    }
///// ** Установка значения координаты X. **/
//    public void setX ( double val) {
//        xCoord = val;
//    }
///// ** Установка значения координаты Y. **/
//    public void setY ( double val) {
//        yCoord = val;
//    }}
//
//
//




//import java.util.Scanner;
//
//class lab1.Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int num = Math.abs(scanner.nextInt());
//        int count, sum;
//        count = 0;
//        sum = 0;
//        while (num != 0){
//            if (count == 3){
//                System.out.println("ERROR");
//                return;}
//            if ((num % 10) % 2 == 1){
//                sum += num % 10;
//            }
//            count += 1;
//            num /= 10;
//            }
//        if (count != 3){
//            System.out.println("ERROR");
//            return;}
//        if (sum == 0){
//            System.out.println("NO");
//            return;}
//
//        System.out.print(sum);
//    }}

//import java.util.Scanner;
//
//class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int num = scanner.nextInt();
//        double tariph = scanner.nextDouble();
//        if (num <= 0 || tariph <= 0){
//            System.out.println("ERROR");
//            return;
//        }
//        if (num < 20){
//            System.out.printf("%.2f%n", Math.round((tariph * num) * 100.0)/ 100.0);
//            return;
//        }
//        if (num <= 40){
//            System.out.printf("%.2f%n", Math.round((tariph * 1.5 * (num - 20) + 20 * tariph) * 100.0) / 100.0);
//            return;
//        }
//        double roundedResult = Math.round((tariph * 1.5 * 20 + 20 * tariph + tariph * 2 * (num - 40)) * 100.0) / 100.0;
//        System.out.printf("%.2f%n", roundedResult);
//    }
//}