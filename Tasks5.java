import java.util.*;

import static java.lang.Math.*;

public class Tasks5 {
    public static boolean sameLetterPattern(String s1, String s2){
        HashMap wordToWord = new HashMap<Character, Character>();
        char[] char_s1 = s1.toCharArray();
        char[] char_s2 = s2.toCharArray();

        if(char_s1.length != char_s2.length){return false;}

        for (int i = 0; i < char_s1.length; i++){
            char word1 = char_s1[i];
            if (wordToWord.containsKey(word1)){
                if ((char) wordToWord.get(word1) != char_s2[i]){
                    return false;
                }
            }else{
                wordToWord.put(word1, char_s2[i]);
            }

        }
        return true;
    }

    public static int memeSum(int num1, int num2){
        String biggerNum;
        String smallerNum;

        String str_num1 = Integer.toString(num1);
        String str_num2 = Integer.toString(num2);

        StringBuilder result = new StringBuilder();

        int lenghthDif = abs(str_num1.length() - str_num2.length());
        if (str_num1.length() > str_num2.length()){
            biggerNum = str_num1;
            smallerNum = str_num2;
        }else{
            biggerNum = str_num2;
            smallerNum = str_num1;
        }

        result.append(biggerNum, 0, lenghthDif);

        for (int i = 0; i < smallerNum.length(); i++){
            result.append(Character.getNumericValue(smallerNum.charAt(i)) +
                    Character.getNumericValue(biggerNum.charAt(lenghthDif + i)));
        }

        return Integer.parseInt(result.toString());
    }

    public static int digitsCount(long num) {
        num = abs(num);
        if (num < 10) {
            return 1;
        }
        return 1 + digitsCount(num / 10);
    }

    private static boolean isValidWord(String guess, Map<Character, Integer> wordMap) {
        for (char letter : guess.toCharArray()) {
            if (wordMap.getOrDefault(letter, 0) > 0){
                wordMap.put(letter, wordMap.get(letter) - 1);
            }else{
                return false;
            }
        }
        return true;
    }
    public static int totalPoints(String[] guesses, String word) {
        Map<Character, Integer> mainWord = new HashMap<>();
        for (char c : word.toCharArray()) {
            mainWord.put(c, mainWord.getOrDefault(c, 0) + 1);
        }
        int totalScore = 0;
        for (String guess : guesses) {
            if (isValidWord(guess, new HashMap<>(mainWord))){
                totalScore += max(0, guess.length() - 2);
                if (guess.length() == 6) {
                    totalScore += 50;
                }
            }
        }

        return totalScore;
    }

    public static int longestRun(int[] nums) {
        if (nums.length == 0) return 0;

        int maxLength = 1;
        int currentLength = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1 || nums[i] == nums[i - 1] - 1) {
                currentLength++;
                maxLength = Math.max(maxLength, currentLength);
            } else {
                currentLength = 1;
            }
        }

        return maxLength;
    }

    public static String takeDownAverage(String[] scores) {
        double totalSum = 0;
        for (String score : scores) {
            totalSum += Integer.parseInt(score.replace("%", ""));
        }
        int classCount = scores.length;
        double currentAverage = totalSum / classCount;
        double newAverage = currentAverage - 5;
        double newScore = (newAverage * (classCount + 1)) - totalSum;
        return round(newScore) + "%";
    }

    public static boolean canMove(String figure, String start, String end) {
        int startCol = start.charAt(0) - 'A';
        int startRow = start.charAt(1) - '1';
        int endCol = end.charAt(0) - 'A';
        int endRow = end.charAt(1) - '1';

        for (int par : new int[]{startCol, startRow, endCol, endRow}){
            if (par < 0 | par > 7){
                return false;
            }
        }

        switch (figure.toLowerCase()) {
            case "rook":
                return startCol == endCol || startRow == endRow;
            case "bishop":
                return Math.abs(startCol - endCol) == Math.abs(startRow - endRow);
            case "queen":
                return startCol == endCol || startRow == endRow ||
                        Math.abs(startCol - endCol) == Math.abs(startRow - endRow);
            case "king":
                return Math.abs(startCol - endCol) <= 1 && Math.abs(startRow - endRow) <= 1;
            case "knight":
                return Math.abs(startCol - endCol) * Math.abs(startRow - endRow) == 2;
            case "pawn":
                return (startRow == 1 ?
                        (endRow - startRow <= 2 & startCol == endCol)
                        : endRow - startRow == 1 &&
                        startCol >= endCol - 1 && startCol <= endCol + 1
                );
            default:
                return false;
        }
    }

    public static int maxPossible(int num1, int num2) {
        char[] num1Chars = String.valueOf(num1).toCharArray();
        char[] num2Chars = String.valueOf(num2).toCharArray();
        Arrays.sort(num2Chars);
        int index = num2Chars.length - 1;

        for (int i = 0; i < num1Chars.length && index >= 0; i++) {
            if (num1Chars[i] < num2Chars[index]) {
                num1Chars[i] = num2Chars[index--];
            }
        }
        return Integer.parseInt(new String(num1Chars));
    }

    public static String timeDifference(String cityA, String timestamp, String cityB){

        Map<String, Double> citiesDiff = Map.ofEntries(
                Map.entry("Los Angeles", -8.0),
                Map.entry("New York", -5.0),
                Map.entry("Caracas", -4.5),
                Map.entry("Buenos Aires", -3.0),
                Map.entry("London", 0.0),
                Map.entry("Rome", 1.0),
                Map.entry("Moscow", 3.0),
                Map.entry("Tehran", 3.5),
                Map.entry("New Delhi", 5.5),
                Map.entry("Beijing", 8.0),
                Map.entry("Canberra", 10.0)
        );
        String[] monthsArray = {
                "January", "February", "March", "April",
                "May", "June", "July", "August",
                "September", "October", "November", "December"
        };

        List<String> months = Arrays.asList(monthsArray);

        Map<String, Integer> dayInMonths = Map.ofEntries(
                Map.entry("January", 31),
                Map.entry("February", 29),
                Map.entry("March", 31),
                Map.entry("April", 30),
                Map.entry("May", 31),
                Map.entry("June", 30),
                Map.entry("July", 31),
                Map.entry("August", 31),
                Map.entry("September", 30),
                Map.entry("October", 31),
                Map.entry("November", 30),
                Map.entry("December", 31)
        );

        String[] timestamp_arr = timestamp.split(" ");
        int hour, minute, year = Integer.parseInt(timestamp_arr[2]);

        boolean plus = true;
        boolean changeHour = false, changeDay = false;
        int month = months.indexOf(timestamp_arr[0]);

        int difMinute = ((int)(citiesDiff.get(cityB) - citiesDiff.get(cityA)) * 10 % 10) * 6;
        int getMinute = Integer.parseInt(timestamp_arr[3].split(":")[1]);
        minute = getMinute + difMinute;

        if (minute < 0){
            changeHour = true;
            minute %= 60;
            plus = false;
        }
        if (minute > 60){
            changeHour = true;
            minute %= 60;
        }


        int difHour = (int)(citiesDiff.get(cityB) - citiesDiff.get(cityA));
        int getHour = Integer.parseInt(timestamp_arr[3].split(":")[0]);
        hour = getHour + difHour;

        if(changeHour){
            if (plus){hour += 1;}
            else{hour -= 1;}
        }

        if (hour < 0){
            changeDay = true;
            hour %= 24;
            plus = false;
        }
        if (hour > 23){
            hour %= 24;
            changeDay = true;
            plus = true;
        }

        int day = Integer.parseInt(timestamp_arr[1].replace(",", ""));
        if(changeDay){
            if(plus){day += 1;}
            else{day -= 1;}
        }


        if(day < 1){
            month = (months.indexOf(timestamp_arr[0]) - 1) % 12;
            if(month == 11){
                year = Integer.parseInt(timestamp_arr[2]) - 1;
                day = 31;
            }
            else{
                day = dayInMonths.get(monthsArray[month]);
            }
        }
        if(day > dayInMonths.get(timestamp_arr[0])){
            month = (months.indexOf(timestamp_arr[0]) + 1) % 12;
            if(month == 0) {
                year = Integer.parseInt(timestamp_arr[2]) + 1;
            }
            day = 1;
        }

        return year + "-" + (month + 1) + "-" + day + " " + (hour / 10) + (hour % 10) + ":" + (minute / 10) + (minute % 10);
    }

    public static boolean isNewNumber(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        Arrays.sort(digits);

        if (digits[0] == '0') {
            for (int i = 1; i < digits.length; i++) {
                if (digits[i] != '0') {
                    char temp = digits[0];
                    digits[0] = digits[i];
                    digits[i] = temp;
                    break;
                }
            }
        }
        int smallest = Integer.parseInt(new String(digits));
        return smallest == num;
    }




    public static void main(String[] args) {
//        System.out.println(sameLetterPattern("ABAB", "CDCD"));
//        System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
//        System.out.println(sameLetterPattern("FFGG", "CDCD"));
//        System.out.println(sameLetterPattern("FFFF", "ABCD"));

//        System.out.println(memeSum(26, 39));
//        System.out.println(memeSum(122, 81));
//        System.out.println(memeSum(1222, 30277));

//        System.out.println(digitsCount(4666));
//        System.out.println(digitsCount(544));
//        System.out.println(digitsCount(121317));
//        System.out.println(digitsCount(0));
//        System.out.println(digitsCount(12345));
//        System.out.println(digitsCount(1289396387328L));

//        System.out.println(totalPoints(new String[]{"cat", "create", "sat"}, "caster"));
//        System.out.println(totalPoints(new String[]{"trance", "recant"}, "recant"));
//        System.out.println(totalPoints(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed"));

//        System.out.println(longestRun(new int[]{1, 2, 3, 5, 6, 7, 8, 9}));
//        System.out.println(longestRun(new int[]{1, 2, 3, 10, 11, 15}));
//        System.out.println(longestRun(new int[]{5, 4, 2, 1}));
//        System.out.println(longestRun(new int[]{3, 5, 7, 10, 15}));

//        System.out.println(takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"}));
//        System.out.println(takeDownAverage(new String[]{"10%"}));
//        System.out.println(takeDownAverage(new String[]{"53%", "79%"}));

//        System.out.println(canMove("Rook", "A8", "H8"));
//        System.out.println(canMove("Bishop", "A7", "G1"));
//        System.out.println(canMove("Queen", "C4", "D6"));
//        System.out.println(canMove("Knight", "B1", "C3"));
//        System.out.println(canMove("Pawn", "D2", "D4"));
//        System.out.println(canMove("King", "E4", "F5"));

//        System.out.println(maxPossible(9328, 456));
//        System.out.println(maxPossible(523, 76));
//        System.out.println(maxPossible(9132, 5564));
//        System.out.println(maxPossible(8732, 91255));

//        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
//            // "2011-4-2 17:23"
//        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome"));
//            // "1983-8-1 00:01"
//        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));
//            // "1971-1-1 02:40"

//        System.out.println(isNewNumber(3));
//        System.out.println(isNewNumber(30000341));
//        System.out.println(isNewNumber(123));
//        System.out.println(isNewNumber(321));



    }
}
