package fifteen;

/**
 * https://adventofcode.com/2015/day/10
 */
public final class ElvesLookElvesSay {

    private ElvesLookElvesSay() {
        throw new java.lang.UnsupportedOperationException("This is class cannot be instantiated");
    }

    public static String lookSay(final String sequence) {
        int step;
        StringBuilder newSequence = new StringBuilder();
        for (int i = 0; i < sequence.length(); i += step) {
            char digit = sequence.charAt(i);
            int count = 1;
            step = 1;
            while (i + step < sequence.length() && sequence.charAt(i + step) == digit) {
                count++;
                step++;
            }
            newSequence.append(count);
            newSequence.append(digit);
        }
        return newSequence.toString();
    }

    public static String process(final String input, final int times) {
        String sequence = input;
        for (int i = 0; i < times; i++) {
            sequence = lookSay(sequence);
        }
        return sequence;
    }
}
