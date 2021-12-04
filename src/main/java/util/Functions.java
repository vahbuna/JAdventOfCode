package util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Functions {

    private Functions() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String md5(String input) {
        try {
            return String.format("%032x",
                    new BigInteger(1, MessageDigest.getInstance("MD5").digest(input.getBytes(StandardCharsets.UTF_8))));
        } catch (NoSuchAlgorithmException e) {
            // pass
        }
        return "";
    }

    /**
     * source: https://stackoverflow.com/a/14444037/11451863
     */
    public static <T extends Object> List<List<T>> permutate(List<T> input) {
        List<List<T>> permutations = new ArrayList<>();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            arr.add(i);
        }
        permutations.add(getPermutation(arr, input));
        boolean hasNext = true;
        while (hasNext) {
            hasNext = false;
            for (int tail = arr.size() - 1; tail > 0; tail--) {
                if (arr.get(tail - 1) < arr.get(tail)) {//still increasing

                    //find last element which does not exceed ind[tail-1]
                    int s = arr.size() - 1;
                    while (arr.get(tail - 1) >= arr.get(s))
                        s--;

                    Collections.swap(arr, tail - 1, s);
                    //reverse order of elements in the tail
                    for (int i = tail, j = arr.size() - 1; i < j; i++, j--) {
                        Collections.swap(arr, i, j);
                    }
                    permutations.add(getPermutation(arr, input));
                    hasNext = true;
                    break;
                }
            }
        }
        return permutations;
    }

    private static <T extends Object> List<T> getPermutation(List<Integer> indices, List<T> input) {
        return indices.stream().map(input::get).collect(Collectors.toList());
    }

    /**
     * source: https://introcs.cs.princeton.edu/java/23recursion/Partition.java.html
     */
    public static List<List<Integer>> partition(int n, final int max, final int numPartitions, List<Integer> partition) {
        List<List<Integer>> partitions = new ArrayList<>();
        if (n == 0 && partition.size() == numPartitions) {
            partitions.add(partition);
            return partitions;
        }

        if (partition.size() > numPartitions - 1) {
            return partitions;
        }

        for (int i = Math.min(max, n); i >= 0; i--) {
            List<Integer> newPartition = new ArrayList<>(partition);
            newPartition.add(i);
            partitions.addAll(partition(n - i, i, numPartitions, newPartition));
        }
        return partitions;
    }

    public static String padToN(String binary, int n) {
        StringBuilder sb = new StringBuilder();
        int padding = n - binary.length();
        IntStream.range(0, padding).forEach(i -> sb.append("0"));
        sb.append(binary);
        return sb.toString();
    }

    public static List<Integer> factors(int i) {
        Set<Integer> factors = new HashSet<>();
        factors.add(1);
        factors.add(i);
        for (int k = 2; k < i/2; k++) {
            if (i % k == 0) {
                factors.add(k);
                factors.add(i / k);
            }
        }
        return new ArrayList<>(factors);
    }
}
