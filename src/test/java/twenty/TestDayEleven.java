package twenty;

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

class TestDayEleven {

    public static Map<Character, Integer> mapping = new HashMap<>();

    @BeforeAll
    static void setUp() {
        mapping.put(SeatingSystem.OCCUPIED, 2);
        mapping.put(SeatingSystem.EMPTY, 1);
        mapping.put(SeatingSystem.FLOOR, 0);
    }

    @Test
    void example() {
        List<String> seats = Arrays.asList("L.LL.LL.LL", "LLLLLLL.LL", "L.L.L..L..",
                "LLLL.LL.LL", "L.LL.LL.LL", "L.LLLLL.LL", "..L.L.....", "LLLLLLLLLL",
                "L.LLLLLL.L", "L.LLLLL.LL");
        SeatingSystem system = new SeatingSystem(seats, mapping);
        assertThat(system.stableSeats()).isEqualTo(37);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day11input.txt").getFile();
        SeatingSystem system = new SeatingSystem(InputUtil.getAllLinesFromFile(input).collect(Collectors.toList()), mapping);
        assertThat(system.stableSeats()).isEqualTo(2283);
    }

    @Test
    void exampleBis() {
        List<String> seats = Arrays.asList("L.LL.LL.LL", "LLLLLLL.LL", "L.L.L..L..",
                "LLLL.LL.LL", "L.LL.LL.LL", "L.LLLLL.LL", "..L.L.....", "LLLLLLLLLL",
                "L.LLLLLL.L", "L.LLLLL.LL");
        SeatingSystem system = new SeatingSystem(seats, mapping);
        assertThat(system.stableVisibleSeats()).isEqualTo(26);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day11input.txt").getFile();
        SeatingSystem system = new SeatingSystem(InputUtil.getAllLinesFromFile(input).collect(Collectors.toList()), mapping);
        assertThat(system.stableVisibleSeats()).isEqualTo(2054);
    }
}
