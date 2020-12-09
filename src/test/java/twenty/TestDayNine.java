package twenty;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayNine {

    @Test
    void example() {
        List<String> input = IntStream.rangeClosed(1, 25).mapToObj(String::valueOf).collect(Collectors.toList());
        input.addAll(Arrays.asList("26", "49", "100", "50"));
        EncodingError test = new EncodingError(input.stream(), 25);
        assertThat(test.invalidNumber()).isEqualTo(100);
    }

    @Test
    void exampleBis() {
        List<String> input = Arrays.asList("35", "20", "15", "25", "47",
                "40", "62", "55", "65", "95", "102", "117", "150", "182",
                "127", "219", "299", "277", "309", "576");
        EncodingError test = new EncodingError(input.stream(), 5);
        assertThat(test.invalidNumber()).isEqualTo(127);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day9input.txt").getFile();
        EncodingError report = new EncodingError(InputUtil.getAllLinesFromFile(input), 25);
        Assertions.assertThat(report.invalidNumber()).isEqualTo(14144619);
    }

    @Test
    void exampleTer() {
        List<String> input = Arrays.asList("35", "20", "15", "25", "47",
                "40", "62", "55", "65", "95", "102", "117", "150", "182",
                "127", "219", "299", "277", "309", "576");
        EncodingError test = new EncodingError(input.stream(), 5);
        assertThat(test.encryptionWeakness(127)).isEqualTo(62);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day9input.txt").getFile();
        EncodingError report = new EncodingError(InputUtil.getAllLinesFromFile(input), 25);
        Assertions.assertThat(report.encryptionWeakness(14144619)).isEqualTo(1766397);
    }
}
