package lab1;

public class Primes {
    public static boolean isPrime(int number){
        for(int k = 2; k < number; k ++){
            if (number % k == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        for(int num = 3; num < 100; num ++) {
            if (isPrime(num)) {
                System.out.print(num + " ");
            }
        }
    }
}