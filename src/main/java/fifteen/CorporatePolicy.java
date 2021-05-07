package fifteen;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://adventofcode.com/2015/day/11
 */
public final class CorporatePolicy {

    private static final List<Character> illegalChars = Arrays.asList('i', 'l', 'o');

    private CorporatePolicy() {
        throw new java.lang.UnsupportedOperationException("This is class cannot be instantiated");
    }

    private static String increment(String input) {
        char divisor = 'z' - 'a' + 1;
        int carry = 1;
        StringBuilder output = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            char sum = (char) ((input.charAt(i) - 'a' + carry) % divisor);
            carry = (input.charAt(i) - 'a' + carry) / divisor;
            output.append((char) (sum + 'a'));
        }
        return output.reverse().toString();
    }

    public static String newPassword(String suggestion) {
        while (isInvalid(suggestion)) {
            suggestion = increment(suggestion);
        }
        return suggestion;
    }

    private static boolean isInvalid(String input) {
        boolean hasTrigram = false;
        for (int i = 0; i < input.length() - 2; i += 1) {
            if (illegalChars.contains(input.charAt(i))
                    || illegalChars.contains(input.charAt(i + 1))
                    || illegalChars.contains(input.charAt(i + 2))) {
                return true;
            }

            int diff = input.charAt(i + 1) - input.charAt(i);
            if (diff == 1 && diff == input.charAt(i + 2) - input.charAt(i + 1)) {
                hasTrigram = true;
            }
        }

        int step;
        Set<Character> pairs = new HashSet<>();
        for (int i = 0; i < input.length() - 1; i += step) {
            step = 1;
            if (input.charAt(i) == input.charAt(i + 1)) {
                pairs.add(input.charAt(i));
                step = 2;
            }
        }

        return !hasTrigram || pairs.size() < 2;
    }
}
