package fifteen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public final class DoesntHeHaveInternElvesForThis {
    private static final List<String> vowels = Arrays.asList("a", "e", "i", "o", "u");
    private static final List<String> dirtyBigrams = Arrays.asList("ab", "cd", "pq", "xy");

    private DoesntHeHaveInternElvesForThis() {
        throw new java.lang.UnsupportedOperationException("This is class cannot be instantiated");
    }

    public static boolean isStringNice(String text) {
        int vowelCount = 0;
        boolean hasDoubleLetter = false;
        for (int i = 0; i < text.length() - 1; i++) {
            String candidate = text.substring(i, i + 2);
            if (dirtyBigrams.contains(candidate)) {
                return false;
            }
            if (candidate.charAt(0) == candidate.charAt(1)) {
                hasDoubleLetter = true;
            }
            candidate = text.substring(i, i + 1);
            if (vowels.contains(candidate)) {
                vowelCount++;
            }
        }
        if (vowels.contains(text.substring(text.length() - 1))) {
            vowelCount++;
        }
        return vowelCount > 2 && hasDoubleLetter;
    }

    public static long niceCount(Stream<String> strings) {
        return strings.filter(DoesntHeHaveInternElvesForThis::isStringNice).count();
    }

    public static boolean isStringNicer(String text) {
        Map<String, Integer> bigramCount = new HashMap<>();
        boolean hasPalindrome = false;
        String prevBigram = "";
        int prevBigramPos = -1;
        for (int i = 0; i < text.length() - 1; i ++) {
            String bigram = text.substring(i, i + 2);
            if (bigram.equals(prevBigram) && prevBigramPos == i - 1) {
                continue;
            }
            int count = bigramCount.getOrDefault(bigram, 0);
            bigramCount.put(bigram, count + 1);
            prevBigram = bigram;
            prevBigramPos = i;
        }

        for (int i = 0; i < text.length() - 2; i++) {
            if (text.charAt(i) == text.charAt(i + 2)) {
                hasPalindrome = true;
                break;
            }
        }
        long pairCount = bigramCount.values().stream()
                .filter(i -> i >= 2)
                .count();
        return hasPalindrome && pairCount > 0;
    }

    public static long nicerCount(Stream<String> strings) {
        return strings.filter(DoesntHeHaveInternElvesForThis::isStringNicer).count();
    }
}
