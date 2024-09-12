package lab1;

public class Palindrome {
    public static String reverseString(String s) {
        String reversedPalindrome = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            reversedPalindrome += s.charAt(i);
        }
        return reversedPalindrome;
    }

    public static boolean isPalindrome(String s) {
        String reversedPalindrome = reverseString(s);
        return s.equals(reversedPalindrome);
    }

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            if (isPalindrome(s)) {
                System.out.print("It is a palindrome.");
            } else {
                System.out.print("It is not a palindrome.");
            }
        }
    }
}