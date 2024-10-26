import java.util.*;

public class Tasks4 {
    public static String nonRepeat(String s) {
        return removeChars(s, new StringBuilder()).toString();
    }
    private static StringBuilder removeChars(String s, StringBuilder currentRes) {
        if (s.isEmpty()) {
            return currentRes;
        }
        int count = 0;
        char currentChar = s.charAt(0);

        for (char c : s.toCharArray()) {
            if (c == currentChar) {
                count++;
            }
        }
        if (count <= 3) {
            currentRes.append(s.charAt(0));
            s = s.substring(1);
        }else {
            s = s.replace(String.valueOf(currentChar), "");
        }

        return removeChars(s, currentRes);
    }

    public static List<String> bruteForce(int n, int k) {
        if (n > k) {
            return new ArrayList<>();
        }
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            numbers.add(String.valueOf(i));
        }

        return generateNumbers(n, new StringBuilder(), numbers);
    }
    private static List<String> generateNumbers(int deep, StringBuilder currentRes, List<String> numbers) {
        List<String> result = new ArrayList<>();

        if (deep == 0) {
            result.add(currentRes.toString());
            return result;
        }

        for (int i = 0; i < numbers.size(); i++) {
            String num = numbers.get(i);
            currentRes.append(num);

            List<String> newNumbers = new ArrayList<>(numbers);
            newNumbers.remove(num);
            result.addAll(generateNumbers(deep - 1, currentRes, newNumbers));
            currentRes.deleteCharAt(currentRes.length() - 1);
        }

        return result;
    }

    public static String encode(List<Integer> numbers, String key) {
        StringBuilder result = new StringBuilder();

        // Проходим по массиву чисел
        for (int i = 0; i < numbers.size(); i++) {
            // Применяем XOR между числом и символом ключа
            int encodedChar = (numbers.get(i) ^ key.charAt(i % key.length())) % 256; // Ограничим результат диапазоном [0-255]
            result.append((char) encodedChar);  // Преобразуем результат в символ
        }

        return result.toString();
    }
    public static int[] decode(String str, String key) {
        int[] result = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            result[i] = str.charAt(i) ^ key.charAt(i);
        }
        return result;
    }

    public static List<String> split(String str) {
        List<String> result = new ArrayList<>();
        int opened = 0;
        StringBuilder current = new StringBuilder();

        for (char c : str.toCharArray()) {
            current.append(c);
            if (c == '(') {
                opened++;
            }
            else {
                opened--;
                if (opened == 0) {
                    result.add(current.toString());
                    current.setLength(0);
                }
            }
        }
        return result;
    }

    public static String shortHand(String str) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        char lastLetter = str.charAt(0);
        for (char letter : str.toCharArray()) {
            if (lastLetter != letter) {
                if (count == 1) {
                    result.append(lastLetter);
                    lastLetter = letter;
                    continue;
                } else {
                    result.append(lastLetter).append("*").append(count);
                    count = 1;
                    lastLetter = letter;
                    continue;
                }
            }
            count++;
        }
        result.append(lastLetter).append("*").append(count);
        return result.toString();

    }

    public static String convertToRome(int num) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[num / 1000] +
                hundreds[(num % 1000) / 100] +
                tens[(num % 100) / 10] +
                ones[num % 10];
    }

    public static String uniqueSubstring(String s) {
        Map<Character, Integer> numbers = new HashMap<>();

        char key = '-';
        int even = 0, odd = 0, maxValue = 0;

        for (char num: s.toCharArray()) {
            if (numbers.containsKey(num)) {
                numbers.put(num, numbers.get(num) + 1);
            } else {
                numbers.put(num, 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : numbers.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                key = entry.getKey();
            }
        }

        for (int index = 0; index < s.length(); index++) {
            if (s.charAt(index) == key) {
                if (index % 2 == 0){
                    even ++;
                }
                else{
                    odd ++;
                }
            }
        }
        return even > odd ? "чет" : odd > even ? "нечет" : "чет";
    }

    private static int[] logic(int down, int right) {
        int[] res = new int[2];
        if (down > 0 && right > 0) {
            if (down > right) {
                res = new int[]{right, 1};
            } else {
                res = new int[]{down, 0};
            }
        } else if (Math.max(down, right) < 0) {
            res[0] = -1;
        }
        else if (down > 0) {
            res = new int[]{down, 0};
        } else {
            res = new int[]{right, 1};
        }
        return res;
    }
    public static List<String> labirint(int[][] matrix) {
        int n = matrix.length;
        int[][][] right_path = new int[n][n][2];

        for(int i = n - 1; i > -1; i--){
            for (int k = n - 1; k > -1; k--){
                if (matrix[i][k] < 0){
                    right_path[i][k][0] = -1;
                    continue;
                }
                if (i == n - 1 || k == n - 1){
                    if (i == n - 1 && k != n - 1){
                        right_path[i][k] = logic(-1, right_path[i][k + 1][0]);
                        right_path[i][k][0] += matrix[i][k];
                    } else if (i != n - 1 && k == n - 1) {
                        right_path[i][k] = logic(right_path[i + 1][k][0], -1);
                        right_path[i][k][0] += matrix[i][k];
                    }else{
                        right_path[i][k][0] = matrix[i][k];
                    }
                }else{
                    right_path[i][k] = logic(right_path[i + 1][k][0], right_path[i][k + 1][0]);
                    if (right_path[i][k][0] > 0){
                        right_path[i][k][0] += matrix[i][k];
                    }
                }
            }
        }
        ArrayList <String> res = new ArrayList<>();
        if (right_path[0][0][0] > 0){
            StringBuilder path = new StringBuilder();
            int l = 0, m = 0;
            for (int p = 0; p < 2 * n - 1; p ++){
                if (right_path[l][m][1] == 0){
                    path.append(matrix[l][m]).append("-");
                    l += 1;
                }else{
                    path.append(matrix[l][m]).append("-");
                    m += 1;
                }
            }

            res.add(path.reverse().substring(1));
            res.add(String.valueOf(right_path[0][0][0]));

        }else{
            res.add("Нет прохода");
        }
        return res;
    }

    public static String numericOrder(String str){
        Map <Integer, String> orderMap = new HashMap<>();

        for (String word : str.split(" ")){
            for (char letter : word.toCharArray()){
                if (Character.isDigit(letter)){
                    word = word.replace(String.valueOf(letter), "");
                    orderMap.put(Character.getNumericValue(letter), word);
                    break;
                }
            }
        }

        StringBuilder result = new StringBuilder();

        try{
           for (int index = 1; index < orderMap.size() + 1; index++){
               result.append(orderMap.get(index)).append(" ");
            }
        } catch (Exception e) {
            return  result.toString();
        }
        return result.toString();
    }


    private static Boolean isFibNumber(int number){
//        13
//        7
//        1, 1, 2, 3, 5, 8, 13, 21
//        a, b, a, b, a, b, a, b,
        int a = 1, b = 1;
        while (number > a ){
            if (number == b){
                return true;
            }
            a += b;
            b += a;
        }
        return number == b || number == a;
            }



    public static Boolean fibString(String str){
        ArrayList<Character> usedLetters = new ArrayList<>();
        int repeatNums = 0;
        for (char letter : str.toCharArray()) {
            if (usedLetters.contains(letter)) {
                repeatNums ++;
            }
            usedLetters.add(letter);
        }
        return isFibNumber(repeatNums);
    }



    public static void main(String[] args) {
        System.out.println(bruteForce(1, 5));  // ["0", "1", "2", "3", "4"]
        System.out.println(bruteForce(2, 2));  // ["01", "10"]
        System.out.println(bruteForce(5, 3));  // []

        System.out.println(nonRepeat("abracadabra"));  // "brcdbr"
        System.out.println(nonRepeat("abababcac"));    // "bbbcc"

        List<Integer> numbers = List.of(0, 31, 28, 10, 29);
        System.out.println(encode(numbers, "MKIIT"));  // "MTUCI"
        int[] encodedValues = decode("MTUCI", "MKIIT");
        for (int value : encodedValues) {
            System.out.print(value + " ");
        }

        System.out.println(split("()()()"));  // ["()", "()", "()"]
        System.out.println(split("((()))"));  // ["((()))"]
        System.out.println(split("((()))(())()()(()())"));  // ["((()))", "(())", "()", "()", "(()())"]
        System.out.println(split("((())())(()(()()))"));  // ["((())())", "(()(()()))"]

        System.out.println(shortHand("abbccc"));  // "ab*2c*3"
        System.out.println(shortHand("vvvvaajaaaaa"));  // "v*4a*2ja*5"

        System.out.println(convertToRome(8));      // "VIII"
        System.out.println(convertToRome(1234));   // "MCCXXXIV"
        System.out.println(convertToRome(52));     // "LII"

        System.out.println(uniqueSubstring("31312131"));  // "нечет"
        System.out.println(uniqueSubstring("1111111"));   // "чет"
        System.out.println(uniqueSubstring("12223234333")); // "чет"

            int[][] lab1 = {
                    {1, 3, 1},
                    {1, -1, 1},
                    {4, 2, 1}
            };

            int[][] lab2 = {
                    {2, -7, 3},
                    {-4, -1, 8},
                    {4, 5, 9}
            };
        System.out.println(labirint(lab1));  // ["1-1-1-3-1", "7"]
        System.out.println(labirint(lab2));  // ["Прохода нет"]

        System.out.println(numericOrder("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat"));

        System.out.println(fibString("CCCABDD"));  // true
        System.out.println(fibString("ABC"));      // false
        }
}
