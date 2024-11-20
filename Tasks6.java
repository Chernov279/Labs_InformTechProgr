import java.util.*;

public class Tasks6 {
    public static String hiddenAnagram(String sentence, String word) {

        String cleanSentence = sentence.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String cleanWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

        Map<Character, Integer> wordMap = new HashMap<>();
        for (char c : cleanWord.toCharArray()) {
            wordMap.put(c, wordMap.getOrDefault(c, 0) + 1);
        }

        int windowSize = cleanWord.length();
        for (int i = 0; i <= cleanSentence.length() - windowSize; i++) {
            String substring = cleanSentence.substring(i, i + windowSize);
            Map<Character, Integer> substringMap = new HashMap<>();
            for (char c : substring.toCharArray()) {
                substringMap.put(c, substringMap.getOrDefault(c, 0) + 1);
            }
            if (substringMap.equals(wordMap)) {
                return substring;
            }
        }
        return "not found";
    }

    public static String stripUrlParams(String url, String... exclude) {
        if (!url.contains("?")) return url;

        List<String> excludeArr = exclude.length > 0 ? new ArrayList<>(List.of(exclude)) : new ArrayList<>();
      String[] parts = url.split("\\?");
        String domenUrl = parts[0];
        String[] params = parts[1].split("&");

        Map<String, String> paramMap = new HashMap<>();

        for (String param : params) {
            String[] keyValue = param.split("=");
            String key = keyValue[0];
            String value = keyValue.length > 1 ? keyValue[1] : "";
            if (!paramMap.containsKey(key) & !excludeArr.contains(key)) {
                paramMap.put(key, value);
            }
        }

        StringBuilder result = new StringBuilder(domenUrl);
        if (!paramMap.isEmpty()) {
            result.append("?");
            paramMap.forEach((key, value) -> result.append(key).append("=").append(value).append("&"));
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }

    public static String nicoCipher(String message, String key) {
        char[] keyChars = key.toCharArray();
        int[] keyOrder = new int[key.length()];
        int keyLen = keyOrder.length, messageLen = message.length();

        char[] sortedKeyChars = keyChars.clone();
        Arrays.sort(sortedKeyChars);
        StringBuilder sortedKeyStringB = new StringBuilder(new String(sortedKeyChars));

        for (int i = 0; i < keyLen; i++) {
            int indexChar = sortedKeyStringB.indexOf(String.valueOf(key.charAt(i)));
            keyOrder[i] = indexChar;
            sortedKeyStringB.replace(indexChar, indexChar + 1, " ");
        }

        List<String> rows = new ArrayList<>();
        for (int i = 0; i < messageLen; i += keyLen) {
            rows.add(message.substring(i, Math.min(i + keyLen, messageLen)));
        }

        StringBuilder lastRow = new StringBuilder(rows.get(rows.size() - 1));
        while (lastRow.length() < keyLen) {
            lastRow.append(" ");
        }
        rows.set(rows.size() - 1, lastRow.toString());

        StringBuilder result = new StringBuilder();

        for (String row : rows) {
            char[] charOrder = new char[keyLen];
            for (int index = 0; index < keyLen; index++){
                charOrder[keyOrder[index]] = row.charAt(index);
            }
            result.append(charOrder);
        }
        return result.toString();
    }

    public static int[] twoProduct(int[] arr, int n) {
        int answer;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if(n % arr[i] == 0){
                answer = n / arr[i];
            }else{
                continue;
            }
            for (int j = i + 1; j < len; j++) {
                if (arr[j] == answer) {
                    return new int[] {arr[i], arr[j]};
                }
            }
        }
        return new int[] {};
    }


    public static int[] isExact(int n) {
        return isExactFactorial(n, 1, 1);
    }
    private static int[] isExactFactorial(int n, int fact, int i) {
        if (fact == n) {
            return new int[] {n, i};
        } else if (fact > n) {
            return new int[] {};
        } else {
            return isExactFactorial(n, fact * (i + 1), i + 1);
        }
    }

    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    public static String fractions(String num) {
        if (!num.contains("(")) {
            return num;
        }

        String integerPart = num.split("\\.")[0];
        String fractionalPart = num.split("\\.")[1];
        String nonRepeating = fractionalPart.split("\\(")[0];
        String repeating = fractionalPart.split("\\(")[1].split("\\)")[0];

        int powTen = nonRepeating.length() + repeating.length();

        long nineX = Long.parseLong(integerPart + nonRepeating + repeating) -  Long.parseLong(integerPart + nonRepeating);
        int num2 = (int) Math.pow(10, powTen) - (int) Math.pow(10, nonRepeating.length());

        long delit = gcd(nineX, num2);
        return nineX / delit + "/" + num2 / delit;
    }

    public static String pilishString(String txt) {
        int[] piDigits = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9};

        if (txt.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int index = 0;

        for (int digit : piDigits) {
            if (index >= txt.length()) {
                break;
            }

            if (index + digit > txt.length()) {
                StringBuilder word = new StringBuilder(txt.substring(index));
                while (word.length() < digit) {
                    word.append(word.charAt(word.length() - 1));
                }
                result.append(word);
            } else {
                result.append(txt, index, index + digit);
            }

            result.append(" ");
            index += digit;
        }

        return result.toString().trim();
    }

    public static boolean formula(String formula) {
        String[] equations = formula.split("=");

        int result = evaluateSimpleExpression(equations[0]);

        for (int i = 1; i < equations.length; i++) {
            if (result != evaluateSimpleExpression(equations[i])) {
                return false;
            }
        }
        return true;
    }
    private static int evaluateSimpleExpression(String expr) {
        String[] exprArr = expr.trim().split(" ");
        if (exprArr.length == 1) {
            return Integer.parseInt(exprArr[0]);
        }
        return switch (exprArr[1]) {
            case "+" -> Integer.parseInt(exprArr[0].trim()) + Integer.parseInt(exprArr[2].trim());
            case "-" -> Integer.parseInt(exprArr[0].trim()) - Integer.parseInt(exprArr[2].trim());
            case "*" -> Integer.parseInt(exprArr[0].trim()) * Integer.parseInt(exprArr[2].trim());
            case "/" -> Integer.parseInt(exprArr[0].trim()) / Integer.parseInt(exprArr[2].trim());
            default -> throw new IllegalStateException("Unexpected operation: " + expr.trim());
        };
    }

    public static String isValid(String str) {
        Map<Character, Integer> lettersMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            lettersMap.put(c, lettersMap.getOrDefault(c, 0) + 1);
        }

        Map<Integer, Integer> countLetsMap = new HashMap<>();
        for (int count : lettersMap.values()) {
            countLetsMap.put(count, countLetsMap.getOrDefault(count, 0) + 1);
        }

        if (countLetsMap.size() == 1) {
            return "YES";
        }
        if (countLetsMap.size() == 2) {
            List<Integer> keys = new ArrayList<>(countLetsMap.keySet());
            Collections.sort(keys);
            int less = keys.get(0), more = keys.get(1);

            if (countLetsMap.get(more) == 1 && (more - 1 == less || more - 1 == 0)) {
                return "YES";
            }
        }
        return "NO";
    }

    public static boolean palindromeDescendant(int num) {
        String str = String.valueOf(num);

        while (str.length() > 1) {
            if (isPalindrome(str)) {
                return true;
            }
            str = generateDescendant(str);
        }
        return false;
    }
    private static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    private static String generateDescendant(String str) {
        StringBuilder descendant = new StringBuilder();
        for (int i = 0; i < str.length() - 1; i += 2) {
            int sum = Character.getNumericValue(str.charAt(i)) +
                    Character.getNumericValue(str.charAt(i + 1));
            descendant.append(sum);
        }
        return descendant.toString();
    }

    public static void main(String[] args) {
//        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
//        System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));
//        System.out.println(hiddenAnagram("Banana? margaritas", "ANAGRAM"));

//        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
//        System.out.println(stripUrlParams("https://edabit.com", new String[]{"b"}));
//        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{"b"}));

//        System.out.println(nicoCipher("mubashirhassan", "crazy"));
//        System.out.println(nicoCipher("myworldevolvesinhers", "tesh"));
//        System.out.println(nicoCipher("edabitisamazing", "matt"));
//        System.out.println(nicoCipher("iloveher", "612345"));

//        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15, 3}, 45)));
//        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 15, 3, 5}, 45)));
//        System.out.println(Arrays.toString(twoProduct(new int[]{1,  2, -1,  4,  5,  6,  10, 7}, 20)));
//        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 4, 5,  6, 7, 8, 9, 10}, 10)));
//        System.out.println(Arrays.toString(twoProduct(new int[]{100, 12, 4, 1, 2}, 15)));

//        System.out.println(Arrays.toString(isExact(6)));
//        System.out.println(Arrays.toString(isExact(24)));
//        System.out.println(Arrays.toString(isExact(125)));
//        System.out.println(Arrays.toString(isExact(720)));
//        System.out.println(Arrays.toString(isExact(1024)));
//        System.out.println(Arrays.toString(isExact(40320)));

//        System.out.println(fractions("0.(6)"));
//        System.out.println(fractions("1.(1)"));
//        System.out.println(fractions("3.(142857)"));
//        System.out.println(fractions("0.19(2367)"));
//        System.out.println(fractions("0.1097(3)"));

//        System.out.println(pilishString("33314444"));
//        System.out.println(pilishString("TOP"));
//        System.out.println(pilishString("X"));
//        System.out.println(pilishString(""));
//        System.out.println(pilishString("HOWINEEDADRINKALCOHOLICINNATUREAFTERTHEHEAVYLECTURESINVOLVINGQUANTUMMECHANICS"));
//        System.out.println(pilishString("CANIMAKEAGUESSNOW"));

//        System.out.println(formula("6 * 4 = 24"));
//        System.out.println(formula("18 / 17 = 2"));
//        System.out.println(formula("16 * 10 = 160 = 14 + 120"));
//        System.out.println(formula("42 = 42"));

//        System.out.println(isValid("aabbcd"));
//        System.out.println(isValid("aabbccddeefghi"));
//        System.out.println(isValid("abcdefghhgfedecba"));

//        System.out.println(palindromeDescendant(11211230));
//        System.out.println(palindromeDescendant(13001120));
//        System.out.println(palindromeDescendant(23336014));
//        System.out.println(palindromeDescendant(11));



    }







}
