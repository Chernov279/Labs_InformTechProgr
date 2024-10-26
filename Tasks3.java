import java.util.Arrays;

public class Tasks3 {
    public static boolean isStrangePair(String word1, String word2){
    if (!word1.isEmpty() && !word2.isEmpty()) {
        return (word1.charAt(0) == word2.charAt(word2.length() - 1)) &&
                (word2.charAt(0) == word1.charAt(word1.length() - 1));
    }
    return (word1.isEmpty() && word2.isEmpty());
    }
    public static String[][] sale(String[][] items, double discount) {
        for (int i = 0; i < items.length; i++) {
            int price = Integer.parseInt(items[i][1]);
            int finalPrice = (int) Math.max(1, Math.round(price * (1 - discount / 100)));
            items[i][1] = Integer.toString(finalPrice);
        }
        return items;
    }
    public static boolean sucsessShoot(int x, int y, int r, int m, int n){
        return r > Math.sqrt(Math.pow(m - x, 2) + Math.pow(n - y, 2));
    }
    public static boolean hasSameParity(int num) {
        int sum = 0;
        int originalNum = num;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return (originalNum % 2 == sum % 2);
    }
    public static String rps(String move1, String move2){
        if (move1.equals(move2)) {
            return "Tie";
        }
        if ((move1.equals("rock") && move2.equals("scissors")) ||
                (move1.equals("scissors") && move2.equals("paper")) ||
                (move1.equals("paper") && move2.equals("rock"))) {
            return "Player 1 wins";
        }
        return "Player 2 wins";
    }
    public static int bugger(int num){
        int res = 0, num2 = 1;
        while(num / 10 > 0){
            num2 = 1;
            res += 1;
            while (num != 0){
                num2 *= num % 10;
                num /= 10;
            }
            num = num2;
        }
        return res;
    }
    public static String mostExpensive(String[][] inventory) {
        String mostExpensiveItem = "";
        int maxTotalCost = 0, price, quantity, totalCost;
        for (String[] item : inventory) {
            price = Integer.parseInt(item[1]);
            quantity = Integer.parseInt(item[2]);
            totalCost = price * quantity;

            if (totalCost > maxTotalCost) {
                maxTotalCost = price * quantity;
                mostExpensiveItem = item[0];
            }
        }
        return "Наиб. общ. стоимость у предмета " + mostExpensiveItem + " - " + maxTotalCost;
    }
    public static String longestUnique(String str) {
        int left = 0;
        int right = 0;
        int maxLength = 0;
        String longestSubstring = "";
        char[] charArray = new char[256];
        while (right < str.length()) {
            char letter = str.charAt(right);
            if (charArray[letter] == 0) {
                charArray[letter]++;
            } else {
                while (charArray[letter] > 0) {
                    charArray[str.charAt(left)]--;
                    left++;
                }
                charArray[letter]++;
            }
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                longestSubstring = str.substring(left, right + 1);
            }
            right++;
        }
        return longestSubstring;
    }
    public static boolean isPrefix(String word, String prefix) {
        if (prefix.endsWith("-")) {
            return word.startsWith(prefix.substring(0, prefix.length() - 1));
        }
        return false;
    }
    public static boolean isSuffix(String word, String suffix) {
        if (suffix.startsWith("-")) {
            return word.endsWith(suffix.substring(1)); // Убираем тире с начала
        }
        return false;
    }
    public static boolean doesBrickFit(int a, int b, int c, int w, int h) {
        int[] brickParams = {a, b, c};
        Arrays.sort(brickParams);

        int[] holeParams = {w, h};
        Arrays.sort(holeParams);

        return (brickParams[0] <= holeParams[0]) && (brickParams[1] <= holeParams[1]);
    }
    public static void main(String[] args) {
//        System.out.println(isStrangePair("ratio", "orator"));
//        System.out.println(isStrangePair("sparkling", "groups"));
//        System.out.println(isStrangePair("bush", "hubris"));
//        System.out.println(isStrangePair("", ""));

//        String[][] items = {
//                {"Laptop", "124200"},
//                {"Phone", "51450"},
//                {"Headphones", "13800"}
//        };
//        double discount = 25;
//        String[][] result = sale(items, discount);
//        for (String[] item : result) {
//            System.out.println(item[0] + ": " + item[1]);
//        }

//        System.out.println(sucsessShoot(0, 0, 5, 2, 2));
//        System.out.println(sucsessShoot(-2, -3, 4, 5, -6));

//        System.out.println(hasSameParity(243));
//        System.out.println(hasSameParity(12));
//        System.out.println(hasSameParity(3));

//        System.out.println(rps("rock", "paper"));
//        System.out.println(rps("paper", "rock"));
//        System.out.println(rps("paper", "scissors"));
//        System.out.println(rps("scissors", "scissors"));
//        System.out.println(rps("scissors", "paper"));

//        System.out.println(bugger(39));
//        System.out.println(bugger(999));
//        System.out.println(bugger(4));

//        String[][] inventory = {
//                {"Скакалка", "550", "8"},
//                {"Шлем", "3750", "4"},
//                {"Мяч", "2900", "10"}
//        };
//        System.out.println(mostExpensive(inventory));

//        System.out.println(longestUnique("abcba"));
//        System.out.println(longestUnique("bbb"));
//        System.out.println(longestUnique("pwwkew"));
//        System.out.println(longestUnique(""));
//        System.out.println(longestUnique("abcabcbb"));

//        System.out.println(isPrefix("automation", "auto-")); // true
//        System.out.println(isSuffix("arachnophobia", "-phobia")); // true
//        System.out.println(isPrefix("retrospect", "sub-")); // false
//        System.out.println(isSuffix("vocation", "-logy")); // false

//        System.out.println(doesBrickFit(1, 1, 1, 1, 1));
//        System.out.println(doesBrickFit(1, 2, 1, 1, 1));
//        System.out.println(doesBrickFit(1, 2, 2, 1, 1));
    }
}
