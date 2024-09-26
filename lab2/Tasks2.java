package lab2;

import java.util.Arrays;

public class Tasks2 {
    public static String duplicateChars(String word1, String word2){
         word1 = word1.toLowerCase();
         word2 = word2.toLowerCase();
         String res = "";
         boolean flag;
         char letter;

         for (int index = 0; index < word1.length(); index++){
             letter = word1.charAt(index);
             flag = true;
             for (int index2 = 0; index2 < word2.length(); index2 ++){
                 if (letter == word2.charAt(index2)){
                     flag = false;
                     break;
                 }}
                if (flag){res += letter;}

             }

         return res;
     }
    public static int dividedByThree(int[] numbers){
        int res = 0;
        int num;

        for (int index = 0; index < numbers.length; index ++){
            num = numbers[index];
            if (num % 2 == 1 && num % 3 == 0){
                res += 1;
            }
        }
        return res;
    }
    public static String getInitials(String initials){
        String res = "";
        String[] initial = initials.split(" ");
        res += initial[1].toUpperCase().charAt(0) + ".";
        res += initial[2].toUpperCase().charAt(0) + ".";
        res += initial[0].toUpperCase().charAt(0) + initial[0].substring(1);
        return res;
    }
    public static double[] normalizaror(double[] numbers){
         double min = numbers[0];
         double max = numbers[0];

         for (int i = 1; i < numbers.length; i ++){
             if(min > numbers[i]){
                 min = numbers[i];
             }if(max < numbers[i]){
                 max = numbers[i];
             }
         }
         double maxmin = max - min;
        for (int i = 0; i < numbers.length; i++) {
            if (maxmin != 0) {
                numbers[i] = (numbers[i] - min) / maxmin;
            } else {
                numbers[i] = 0.0;
            }
        }
        return numbers;
    }
    public static int[] compressedNums(double[] numbers){
        return Arrays.stream(numbers)
                .filter(n -> n != 0)
                .mapToInt(n -> (int) n)
                .sorted()
                .toArray();
    }
    public static String camelToSnake(String word) {
        String res = "";
        char letter;
        String Upperword = word.toUpperCase();
        for (int i = 0; i < word.length(); i++) {
            letter = word.charAt(i);
            if (Character.isUpperCase(letter)) {
                res += "_" + Upperword.charAt(i);
            } else {
                res += letter;
            }
        }return res;
    }
    public static int secondBiggest(int[] numbers){
        if (numbers.length < 2) {System.out.printf("less than 2 elemets");
            return -1;}
        int max, lastmax;
        if(numbers[0] > numbers[1]){
            max = numbers[0];
            lastmax = numbers[1];
        }else{max = numbers[1];
        lastmax = numbers[0];
        }
        for (int i = 2; i < numbers.length; i ++){
            int num = numbers[i];
            if (num > lastmax){
                if (num > max){
                    lastmax = max;
                    max = num;
                }else{
                    lastmax = num;
                }
            }
        }
        return lastmax;



    }
    public static String localReverse(String sentence, char marker) {
//        hard for juniors
        int firstIndex, nextIndex, temp;
        String res = "";
        firstIndex = sentence.indexOf(marker);
        if (firstIndex == -1) {
            return sentence;
        }
        nextIndex = -1;
        boolean flag = true;
        while (flag) {
            firstIndex = sentence.indexOf(marker, nextIndex + 1);
            temp = sentence.indexOf(marker, firstIndex + 1);
            if (firstIndex == -1 || temp == -1 || firstIndex == temp) {
                break;
            }
            res += sentence.substring(nextIndex + 1, firstIndex);
            nextIndex = temp;
            String toReverse = sentence.substring(firstIndex, nextIndex + 1);
            String reversed = new StringBuilder(toReverse).reverse().toString();
            res += reversed;
        }
        res += sentence.substring(nextIndex + 1);
        return res;
    }
    public static int equal(int num1, int num2, int num3){
        if (num1 == num2){
            if (num2 == num3){
                return 3;
            }else{
                return 2;
            }
        }else if(num2 == num3 || num1 == num3){
            return 2;
        }
        return 0;

    }
    public static boolean isAnagram(String sentence1, String sentence2) {
        String cleanedStr1 = sentence1.toLowerCase().replaceAll("[^a-z]", "");
        String cleanedStr2 = sentence2.toLowerCase().replaceAll("[^a-z]", "");

        if (cleanedStr1.length() != cleanedStr2.length()) {
            return false;
        }

        char[] charArray1 = cleanedStr1.toCharArray();
        char[] charArray2 = cleanedStr2.toCharArray();

        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        return Arrays.equals(charArray1, charArray2);
    }

    public static void main(String[] args) {
//        System.out.println(duplicateChars("Barack", "Obama"));
//        System.out.println(dividedByThree(new int[]{3, 12, 7, 81, 52}));
//        System.out.println(getInitials("simonov sergei evgenievich"));
//        System.out.println(getInitials("kozhevnikova tatiana vitalevna"));
//        System.out.println(Arrays.toString(normalizaror(new double[]{3.5, 7.0, 1.5, 9.0, 5.5})));
//        System.out.println(Arrays.toString(normalizaror(new double[]{10.0, 10.0, 10.0, 10.0})));
//        System.out.println(Arrays.toString(compressedNums(new double[]{1.6, 0, 212.3, 34.8, 0, 27.5})));
//        System.out.println(camelToSnake("helloWorldIAmProgrammer"));
//        System.out.println(secondBiggest(new int[]{3, 5, 1, 9, 2, 4}));
//        System.out.println(localReverse("baobab", 'b')); // "baboab"
//        System.out.println(localReverse("Hello, I’m under the water, please help me", 'e'));
//        System.out.println(equal(8, 1, 8));
//        System.out.println(equal(5, 5, 5));
//        System.out.println(equal(4, 9, 6));
        System.out.println(isAnagram("LISTEN", "silent"));
        System.out.println(isAnagram("Eleven plus two?", "Twelve plus one!"));
        System.out.println(isAnagram("hello", "world"));
    }
}
