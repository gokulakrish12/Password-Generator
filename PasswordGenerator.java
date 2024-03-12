import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*_=+-/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the length of the password: ");
        int length = scanner.nextInt();

        boolean includeUpper = getYesNoInput("Include uppercase letters? (Y/N): ", scanner);
        boolean includeLower = getYesNoInput("Include lowercase letters? (Y/N): ", scanner);
        boolean includeDigits = getYesNoInput("Include digits? (Y/N): ", scanner);
        boolean includeSpecial = getYesNoInput("Include special characters? (Y/N): ", scanner);

        System.out.print("Enter the number of passwords to generate: ");
        int count = scanner.nextInt();

        for (int i = 0; i < count; i++) {
            System.out.println("Generated Password " + (i + 1) + ": " +
                    generatePassword(length, includeUpper, includeLower, includeDigits, includeSpecial));
        }
    }

    public static boolean getYesNoInput(String message, Scanner scanner) {
        while (true) {
            System.out.print(message);
            String input = scanner.next();
            if (input.equalsIgnoreCase("Y")) {
                return true;
            } else if (input.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
            }
        }
    }

    public static String generatePassword(int length, boolean includeUpper, boolean includeLower,
                                           boolean includeDigits, boolean includeSpecial) {
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        String allChars = "";
        if (includeUpper) allChars += UPPER;
        if (includeLower) allChars += LOWER;
        if (includeDigits) allChars += DIGITS;
        if (includeSpecial) allChars += SPECIAL;

        if (allChars.isEmpty()) {
            System.out.println("Error: Please include at least one character set.");
            return "";
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allChars.length());
            password.append(allChars.charAt(randomIndex));
        }

        return password.toString();
    }
}
