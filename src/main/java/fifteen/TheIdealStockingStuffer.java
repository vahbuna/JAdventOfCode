package fifteen;

import util.Functions;

public final class TheIdealStockingStuffer {
    private TheIdealStockingStuffer() {
        throw new java.lang.UnsupportedOperationException("This is class cannot be instantiated");
    }

    /** https://adventofcode.com/2015/day/4 */
    public static long bruteForce(String secretKey, int n) {
        long answer = 0;
        StringBuilder zeroes = new StringBuilder();
        for (int i = 0; i < n; i++) {
            zeroes.append("0");
        }
        while (answer < Integer.MAX_VALUE) {
            if (Functions.md5(secretKey + answer).startsWith(zeroes.toString())) {
                return answer;
            }
            answer++;
        }
        return -1;
    }
}
