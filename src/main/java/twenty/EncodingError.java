package twenty;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public final class EncodingError {
    private final List<String> numbers = new ArrayList<>();
    private final int preamble;

    public EncodingError(Stream<String> input, int p) {
        input.forEach(numbers::add);
        preamble = p;
    }

    public int invalidNumber() {
        for (int start = preamble; start < numbers.size(); start++) {
            ReportRepair validator = new ReportRepair(numbers.subList(start - preamble, start).stream());
            int target = Integer.parseInt(numbers.get(start));
            if (validator.twoSum(target) == 0) {
                return target;
            }
        }
        throw new NoSuchElementException("All valid");
    }

    public int encryptionWeakness(int target) {
        for (int start = 0; start < numbers.size() - 1; start++) {
            int total = Integer.parseInt(numbers.get(start));
            int minNum = total;
            int maxNum = total;
            for (int i = start + 1; i < numbers.size(); i++) {
                int num = Integer.parseInt(numbers.get(i));
                if (minNum > num) {
                    minNum = num;
                } else if (maxNum < num) {
                    maxNum = num;
                }
                total += num;
                if (total == target) {
                    return minNum + maxNum;
                } else if (total > target) {
                    break;
                }
            }
        }
        throw new NoSuchElementException("Weakness not found");
    }
}
