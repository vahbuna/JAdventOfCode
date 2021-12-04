package fifteen;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayFourteen {

    @Test
    void example() {
        ReindeerOlympics comptt = new ReindeerOlympics(Stream.of("Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.",
                "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds."));
        assertThat(comptt.firstScoring(1000)).isEqualTo(1120);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day14input.txt").getFile();
        ReindeerOlympics comptt = new ReindeerOlympics(InputUtil.getAllLinesFromFile(input));
        assertThat(comptt.firstScoring(2503)).isEqualTo(2655);
    }

    @Test
    void exampleBis() {
        ReindeerOlympics comptt = new ReindeerOlympics(Stream.of("Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.",
                "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds."));
        assertThat(comptt.secondScoring(1000)).isEqualTo(689);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day14input.txt").getFile();
        ReindeerOlympics comptt = new ReindeerOlympics(InputUtil.getAllLinesFromFile(input));
        assertThat(comptt.secondScoring(2503)).isEqualTo(1059);
    }
}
