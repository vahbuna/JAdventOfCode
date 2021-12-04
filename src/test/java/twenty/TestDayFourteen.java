package twenty;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayFourteen {

    @Test
    void example() throws IOException {
        List<String> program = Arrays.asList("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X", "mem[8] = 11",
                "mem[7] = 101", "mem[8] = 0");

        DockingData decoder = new DockingData(program);
        assertThat(decoder.versionOneDecoder()).isEqualTo(165);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day14input.txt").getFile();
        DockingData decoder = new DockingData(InputUtil.getAllLinesFromFile(input).collect(Collectors.toList()));
        assertThat(decoder.versionOneDecoder()).isEqualTo(6559449933360L);
    }

    @Test
    void exampleBis() throws IOException {
        List<String> program = Arrays.asList("mask = 000000000000000000000000000000X1001X", "mem[42] = 100",
                "mask = 00000000000000000000000000000000X0XX", "mem[26] = 1");
        DockingData decoder = new DockingData(program);
        assertThat(decoder.versionTwoDecoder()).isEqualTo(208);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day14input.txt").getFile();
        DockingData decoder = new DockingData(InputUtil.getAllLinesFromFile(input).collect(Collectors.toList()));
        assertThat(decoder.versionTwoDecoder()).isEqualTo(3369767240513L);
    }
}
