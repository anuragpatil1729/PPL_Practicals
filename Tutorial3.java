import java.util.Scanner;

public class Tutorial3 {

    // Convert to Uppercase
    static String toUpper(String str) {
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= 'a' && ch[i] <= 'z') {
                ch[i] = (char) (ch[i] - 32);
            }
        }
        return new String(ch);
    }

    // Convert to Lowercase
    static String toLower(String str) {
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= 'A' && ch[i] <= 'Z') {
                ch[i] = (char) (ch[i] + 32);
            }
        }
        return new String(ch);
    }

    // Reverse String
    static String reverse(String str) {
        char[] ch = str.toCharArray();
        int start = 0, end = ch.length - 1;
        while (start < end) {
            char temp = ch[start];
            ch[start] = ch[end];
            ch[end] = temp;
            start++;
            end--;
        }
        return new String(ch);
    }

    // Compare Strings
    static boolean compare(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i))
                return false;
        }
        return true;
    }

    // Search Substring
    static boolean search(String text, String pattern) {
        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            int j;
            for (j = 0; j < pattern.length(); j++) {
                if (text.charAt(i + j) != pattern.charAt(j))
                    break;
            }
            if (j == pattern.length())
                return true;
        }
        return false;
    }

    // Replace Substring
    static String replace(String text, String oldStr, String newStr) {
        String result = "";
        int i = 0;

        while (i < text.length()) {
            int j;
            for (j = 0; j < oldStr.length(); j++) {
                if (i + j >= text.length() ||
                        text.charAt(i + j) != oldStr.charAt(j))
                    break;
            }

            if (j == oldStr.length()) {
                result += newStr;
                i += oldStr.length();
            } else {
                result += text.charAt(i);
                i++;
            }
        }
        return result;
    }

    // Extract Numbers
    static void extractNumbers(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                System.out.print(str.charAt(i));
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    // Main Menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- TextTransformer ---");
            System.out.println("1. Uppercase");
            System.out.println("2. Lowercase");
            System.out.println("3. Reverse");
            System.out.println("4. Compare Strings");
            System.out.println("5. Search Substring");
            System.out.println("6. Replace Substring");
            System.out.println("7. Extract Numbers");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter text: ");
                    System.out.println(toUpper(sc.nextLine()));
                    break;

                case 2:
                    System.out.print("Enter text: ");
                    System.out.println(toLower(sc.nextLine()));
                    break;

                case 3:
                    System.out.print("Enter text: ");
                    System.out.println(reverse(sc.nextLine()));
                    break;

                case 4:
                    System.out.print("Enter first string: ");
                    String a = sc.nextLine();
                    System.out.print("Enter second string: ");
                    String b = sc.nextLine();
                    System.out.println("Equal: " + compare(a, b));
                    break;

                case 5:
                    System.out.print("Enter text: ");
                    String t = sc.nextLine();
                    System.out.print("Enter pattern: ");
                    String p = sc.nextLine();
                    System.out.println("Found: " + search(t, p));
                    break;

                case 6:
                    System.out.print("Enter text: ");
                    String txt = sc.nextLine();
                    System.out.print("Old word: ");
                    String oldW = sc.nextLine();
                    System.out.print("New word: ");
                    String newW = sc.nextLine();
                    System.out.println(replace(txt, oldW, newW));
                    break;

                case 7:
                    System.out.print("Enter text: ");
                    extractNumbers(sc.nextLine());
                    break;
            }

        } while (choice != 0);

        sc.close();
    }
}
