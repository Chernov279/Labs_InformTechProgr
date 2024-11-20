package lab5;

import java.util.regex.*;

public class Regulars {
    public static void NumberFinder () {
        String text = "The price of the product is $19.99, and the discount is 5% on day -1.";

        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(text);

        System.out.println("Найденные числа:");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    public static void PasswordValidator () {
        String password = "aaaaAaaa";
        Pattern pattern = Pattern.compile("^[A-Za-z\\d]{8,16}$");
        Matcher matcher = pattern.matcher(password);

        Pattern patternDigit = Pattern.compile("\\d");
        Matcher matcherDigit = patternDigit.matcher(password);

        Pattern patternCapital = Pattern.compile("[A-Z]");
        Matcher matcherCapital = patternCapital.matcher(password);

        if (matcher.find() & matcherDigit.find() & matcherCapital.find()) {
            System.out.println("Пароль корректен.");
        } else {
            System.out.println("Пароль некорректен. Он должен содержать от 8 до 16 символов, " +
                    "содержать латинские буквы, хотя бы одну заглавную букву и одну цифру.");
        }
    }

    public static void UpperAfterLowerFinder() {
        String text = "This is aTest example with mMultiple Cases.";
        Pattern pattern = Pattern.compile("([a-z])([A-Z])");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            text = text.replace(matcher.group(), matcher.group().charAt(0) + "!" + matcher.group(1));
        }

        System.out.println("Результат: " + text);
    }


    public static void  IPAddressValidator() {
        String ip = "0.0.0.0";

        Pattern pattern = Pattern.compile("^(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)){3}$");
        Matcher matcher = pattern.matcher(ip);

        if (matcher.matches()) {
            System.out.println("IP-адрес корректеный.");
        } else {
            System.out.println("IP-адрес некорректеный");
        }
    }


    public static void WordFinder () {
        String text = "qcsacsd awxsac a saS SD";
        String startLetter = "s";
        Pattern pattern = Pattern.compile("\\b" + startLetter + "\\w*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        System.out.println("Найденные слова:");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    public static void main(String[] args) {
        NumberFinder();
        PasswordValidator();
        UpperAfterLowerFinder();
        IPAddressValidator();
        WordFinder();
    }
}
