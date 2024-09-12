package lab1;

public class Tasks {
    public static double convert(int gallon) {
        return 3.785 * gallon;
    }

    public static int fitCalc(int minute, int kkal){
        return minute * kkal;
    }

    public static int containers(int small, int medium, int huge){
        return small * 20 + medium * 50 + huge * 100;
    }

    public static String triangleType(int x ,int y, int z){
        if (x > 0 && y > 0 && z > 0 && x + y > z && x + z > y && y + z > x){
            if (y == x || x == z || y == z){
                if (y == x && z == x){
                    return "равносторонний";
                }else return "равнобедренный";
            }else return "разносторонний";
        }else return "не является треугольником";
    }

    public static int ternaryEvaluation(int a, int b){
        return  a > b ? a:b;
    }


    public static int howManyItems(int n, double w, double h) {
        return (int) (n / w * h); // ???
    }


    public static int factorial(int num) {
        if (num < 0) {
            return -1; //не вычисляется
        }
        int result = 1;
        while (num > 1){
        result *= num;
        num -= 1;}
        return result;
    }

    public static int gcd(int a, int b) {
        if (a < 0 || b < 0) {
            return -1;
        }
        int min = a < b ? a:b;
        for (int k = min; k > 0; k--) {
            if (a % k == 0 && b % k == 0) {
                return k;
            }
        }
        return 1;
    }

    public static int ticketSaler(int amount, double price){
        return (int) (price * 72 / 100  * amount);
    }

    public static int tables(int students, int desk){
        return Math.max(0, students - (desk * 2));
    }
}
