package twenty;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayEight {

    @BeforeEach
    void reset() {
        HandheldHalting.resetAccumulator();
    }

    @Test
    void example() {
        Stream<String> input = Arrays.asList("nop +0",
                "acc +1",
                "jmp +4",
                "acc +3",
                "jmp -3",
                "acc -99",
                "acc +1",
                "jmp -4",
                "acc +6").stream();
        HandheldHalting parser = new HandheldHalting(input);
        assertThat(parser.accValBeforeLoop()).isEqualTo(5);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day8input.txt").getFile();
        HandheldHalting parser = new HandheldHalting(InputUtil.getAllLinesFromFile(input));
        assertThat(parser.accValBeforeLoop()).isEqualTo(1600);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day8input.txt").getFile();
        HandheldHalting parser = new HandheldHalting(InputUtil.getAllLinesFromFile(input));
        parser.repair();
        assertThat(parser.accVal()).isEqualTo(1543);
    }

    @Test
    void exampleBis() {
        Stream<String> input = Arrays.asList("nop +0",
                "acc +1",
                "jmp +4",
                "acc +3",
                "jmp -3",
                "acc -99",
                "acc +1",
                "jmp -4",
                "acc +6").stream();
        HandheldHalting parser = new HandheldHalting(input);
        parser.repair();
        assertThat(parser.accVal()).isEqualTo(8);
    }

}
