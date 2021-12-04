package twenty;

import static util.Functions.padToN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class DockingData {

    private final List<String> code;

    private final Pattern memory = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");

    public DockingData(List<String> program) {
        code = program;
    }

    /**
     * https://adventofcode.com/2020/day/14
     */
    public long versionOneDecoder() {
        Map<Integer, Character> maskMap = new HashMap<>();
        Map<Integer, Long> memoryMap = new HashMap<>();
        for (String line : code) {
            if (line.startsWith("mask")) {
                maskMap.clear();
                String mask = line.split(" = ")[1];
                char[] bits = mask.toCharArray();
                for (int i = 0; i < bits.length; i++) {
                    if (bits[i] != 'X') {
                        maskMap.put(i, bits[i]);
                    }
                }
            } else {
                Matcher memMatcher = memory.matcher(line);
                if (memMatcher.find()) {
                    int location = Integer.parseInt(memMatcher.group(1));
                    char[] binary = padToN(Integer.toBinaryString(Integer.parseInt(memMatcher.group(2))), 36).toCharArray();
                    // bitmask is applied to value: a 0 or 1 overwrites the corresponding bit in the value,
                    // while an X leaves the bit in the value unchanged.
                    for (Map.Entry<Integer, Character> manip : maskMap.entrySet()) {
                        binary[manip.getKey()] = manip.getValue();
                    }
                    // value is written to the memory
                    memoryMap.put(location, Long.parseLong(new String(binary), 2));
                }
            }
        }
        // sum of all values left in memory after the initialization program completes
        return memoryMap.values().stream().mapToLong(i -> i).sum();
    }

    /**
     * https://adventofcode.com/2020/day/14#part2
     */
    public long versionTwoDecoder() {
        Map<Integer, Character> maskMap = new HashMap<>();
        Map<Long, Integer> memoryMap = new HashMap<>();
        for (String line : code) {
            if (line.startsWith("mask")) {
                maskMap.clear();
                String mask = line.split(" = ")[1];
                char[] bits = mask.toCharArray();
                for (int i = 0; i < bits.length; i++) {
                    if (bits[i] != '0') {
                        maskMap.put(i, bits[i]);
                    }
                }
            } else {
                Matcher memMatcher = memory.matcher(line);
                if (memMatcher.find()) {
                    int location = Integer.parseInt(memMatcher.group(1));
                    int value = Integer.parseInt(memMatcher.group(2));
                    char[] binary = padToN(Integer.toBinaryString(location), 36).toCharArray();
                    // If the bitmask bit is 1, the corresponding memory address bit is overwritten with 1
                    // If the bitmask bit is X, the corresponding memory address bit is floating.
                    for (Map.Entry<Integer, Character> manip : maskMap.entrySet()) {
                        binary[manip.getKey()] = manip.getValue();
                    }
                    // value is written to the memory
                    for (Long newLocation : generateLocations(binary)) {
                        memoryMap.put(newLocation, value);
                    }
                }
            }
        }
        // sum of all values left in memory after the initialization program completes
        return memoryMap.values().stream().mapToLong(i -> i).sum();
    }

    private List<Long> generateLocations(char[] floatingAddress) {
        List<Integer> floatingBits = new ArrayList<>();
        List<Long> newLocations = new ArrayList<>();
        for (int i = 0; i < floatingAddress.length; i++) {
            if (floatingAddress[i] == 'X') {
                floatingBits.add(i);
            }
        }
        for (int i = 0; i < Math.pow(2.0, floatingBits.size()); i++) {
            String floatingValues = padToN(Integer.toBinaryString(i), floatingBits.size());
            for (int j = 0; j < floatingValues.length(); j++) {
                floatingAddress[floatingBits.get(j)] = floatingValues.charAt(j);
            }
            newLocations.add(Long.parseLong(new String(floatingAddress), 2));
        }
        return newLocations;
    }
}
