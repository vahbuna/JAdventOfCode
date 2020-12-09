package twenty;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayFive {

    @Test
    void example() {
        List<String> passes = Arrays.asList("FBFBBFFRLR", "BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL");
        BinaryBoarding allAboard = new BinaryBoarding(passes.stream());
        assertThat(allAboard.getMaxSeatId()).isEqualTo(820);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day5input.txt").getFile();
        BinaryBoarding allAboard = new BinaryBoarding(InputUtil.getAllLinesFromFile(input));
        assertThat(allAboard.getMaxSeatId()).isEqualTo(908);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day5input.txt").getFile();
        BinaryBoarding allAboard = new BinaryBoarding(InputUtil.getAllLinesFromFile(input));
        assertThat(allAboard.getMissingSeatId()).isEqualTo(619);
    }

}
