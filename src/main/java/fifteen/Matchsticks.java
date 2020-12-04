package fifteen;

public class Matchsticks {

    private Matchsticks() {
        throw new java.lang.UnsupportedOperationException("This is class cannot be instantiated");
    }

    /** https://adventofcode.com/2015/day/8 */
    public static int extraCharCount(String text) {
        int charMemoryCount = 0;
        int step;
        for (int i = 1; i < text.length() - 1; i += step) {
            if (text.charAt(i) == '\\') {
                step = 2;
                if (text.charAt(i+1) == 'x') {
                    step += 2;
                }
            } else {
                step = 1;
            }
            charMemoryCount++;
        }
        return text.length() - charMemoryCount;
    }

    /** https://adventofcode.com/2015/day/8#part2 */
    public static int extraEncodedCharCount(String text) {
        int charEncodedCount = 4; // for enclosing quotes
        int step;
        for (int i = 1; i < text.length() - 1; i += step) {
            step = 1;
            if (text.charAt(i) == '\\') {
                step = 2;
                charEncodedCount += 2;
                if (text.charAt(i + 1) == 'x') {
                    step += 2;
                    charEncodedCount--;
                }
            }
        }
        return charEncodedCount;
    }
}
