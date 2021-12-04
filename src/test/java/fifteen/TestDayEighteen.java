package fifteen;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayEighteen {

    public static Map<Character, Integer> mapping = new HashMap<>();

    @BeforeAll
    static void setUp() {
        mapping.put(LikeAGifForYourYard.ON, 1);
        mapping.put(LikeAGifForYourYard.OFF, 0);
    }

    @Test
    void example() {
        List<String> lights = Arrays.asList(".#.#.#", "...##.", "#....#",
                "..#...", "#.#..#", "####..");
        LikeAGifForYourYard system = new LikeAGifForYourYard(lights, mapping);
        assertThat(system.numLightsOn(4)).isEqualTo(4);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day18input.txt").getFile();
        LikeAGifForYourYard system = new LikeAGifForYourYard(InputUtil.getAllLinesFromFile(input).collect(Collectors.toList()), mapping);
        assertThat(system.numLightsOn(100)).isEqualTo(768);
    }

    @Test
    void exampleBis() {
        List<String> lights = Arrays.asList("##.#.#", "...##.", "#....#",
                "..#...", "#.#..#", "####.#");
        LikeAGifForYourYard system = new LikeAGifForYourYard(lights, mapping);
        assertThat(system.numLightsOnConway(5)).isEqualTo(17);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day18input.txt").getFile();
        LikeAGifForYourYard system = new LikeAGifForYourYard(InputUtil.getAllLinesFromFile(input).collect(Collectors.toList()), mapping);
        assertThat(system.numLightsOnConway(100)).isEqualTo(781);
    }
}
