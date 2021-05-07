package twenty;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayTwelve {

    @Test
    void example() {
        List<String> dirs = Arrays.asList("F10", "N3", "F7", "R90", "F11");
        assertThat(RainRisk.manhattan(dirs)).isEqualTo(25);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day12input.txt").getFile();
        assertThat(RainRisk.manhattan(InputUtil.getAllLinesFromFile(input).collect(Collectors.toList()))).isEqualTo(1601);
    }

    @Test
    void exampleBis() {
        List<String> dirs = Arrays.asList("F10", "N3", "F7", "R90", "F11");
        assertThat(RainRisk.manhattanBis(dirs)).isEqualTo(286);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day12input.txt").getFile();
        assertThat(RainRisk.manhattanBis(InputUtil.getAllLinesFromFile(input).collect(Collectors.toList()))).isEqualTo(13340);
    }
}
