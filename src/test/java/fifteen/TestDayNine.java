package fifteen;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayNine {

    @Test
    void example() {
        List<String> input = Arrays.asList("London to Dublin = 464", "London to Belfast = 518", "Dublin to Belfast = 141");
        AllinaSingleNight tsp = new AllinaSingleNight(input.stream());
        assertThat(tsp.shortestRoute()).isEqualTo(605.0);
    }

    @Test
    void exampleBis() {
        List<String> input = Arrays.asList("London to Dublin = 464", "London to Belfast = 518", "Dublin to Belfast = 141");
        AllinaSingleNight tsp = new AllinaSingleNight(input.stream());
        assertThat(tsp.longestDistance()).isEqualTo(982.0);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day9input.txt").getFile();
        AllinaSingleNight tsp = new AllinaSingleNight(InputUtil.getAllLinesFromFile(input));
        assertThat(tsp.shortestRoute()).isEqualTo(141.0);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day9input.txt").getFile();
        AllinaSingleNight tsp = new AllinaSingleNight(InputUtil.getAllLinesFromFile(input));
        assertThat(tsp.longestDistance()).isEqualTo(736.0);
    }
}
