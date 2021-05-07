package twenty;

import java.io.IOException;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayTen {

    @Test
    void example() {
        Stream<String> input = Stream.of("16", "10", "15", "5", "1", "11", "7", "19", "6", "12", "4");
        AdapterArray report = new AdapterArray(input);
        Assertions.assertThat(report.joltage()).isEqualTo(35);
    }

    @Test
    void exampleBis() {
        Stream<String> input = Stream.of("16", "10", "15", "5", "1", "11", "7", "19", "6", "12", "4");
        AdapterArray report = new AdapterArray(input);
        Assertions.assertThat(report.permutations()).isEqualTo(8);
    }

    @Test
    void exampleTer() {
        Stream<String> input = Stream.of("28", "33", "18", "42", "31", "14", "46", "20", "48", "47", "24",
                "23", "49", "45", "19", "38", "39", "11", "1", "32", "25", "35", "8", "17", "7", "9", "4",
                "2", "34", "10", "3");
        AdapterArray report = new AdapterArray(input);
        Assertions.assertThat(report.permutations()).isEqualTo(19208);
    }


    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day10input.txt").getFile();
        AdapterArray report = new AdapterArray(InputUtil.getAllLinesFromFile(input));
        Assertions.assertThat(report.permutations()).isEqualTo(3947645370368L);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day10input.txt").getFile();
        AdapterArray report = new AdapterArray(InputUtil.getAllLinesFromFile(input));
        Assertions.assertThat(report.joltage()).isEqualTo(2244);
    }
}
