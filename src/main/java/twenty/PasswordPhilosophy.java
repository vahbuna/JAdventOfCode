package twenty;

import java.util.stream.IntStream;

/** https://adventofcode.com/2020/day/2 */
public final class PasswordPhilosophy {

    private PasswordPhilosophy() {
        throw new java.lang.UnsupportedOperationException("This is class cannot be instantiated");
    }

    public static boolean isPasswordValid(String policy) {
        String[] fields = policy.split(" ");
        String[] range = fields[0].split("-");
        int minimum = Integer.parseInt(range[0]);
        int maximum = Integer.parseInt(range[1]);
        String searchChar = fields[1].substring(0, 1);
        String password = fields[2];
        long count = IntStream.range(0, password.length())
                .mapToObj(i -> password.substring(i, i+1))
                .filter(searchChar::equals)
                .count();
        return count >= minimum && count <= maximum;
    }

    public static boolean isPasswordValidNew(String policy) {
        String[] fields = policy.split(" ");
        String[] positions = fields[0].split("-");
        int firstPosition = Integer.parseInt(positions[0]);
        int secondPosition = Integer.parseInt(positions[1]);
        char searchChar = fields[1].charAt(0);
        String password = fields[2];
        int count = 0;
        if (password.charAt(firstPosition - 1) == searchChar) {
            count++;
        }
        if (password.charAt(secondPosition - 1) == searchChar) {
            count++;
        }

        return count == 1;
    }
}
