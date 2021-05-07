package fifteen;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayThirteen {

    @Test
    void example() {
        List<String> input = Arrays.asList("Alice would gain 54 happiness units by sitting next to Bob.",
                "Alice would lose 79 happiness units by sitting next to Carol.",
                "Alice would lose 2 happiness units by sitting next to David.",
                "Bob would gain 83 happiness units by sitting next to Alice.",
                "Bob would lose 7 happiness units by sitting next to Carol.",
                "Bob would lose 63 happiness units by sitting next to David.",
                "Carol would lose 62 happiness units by sitting next to Alice.",
                "Carol would gain 60 happiness units by sitting next to Bob.",
                "Carol would gain 55 happiness units by sitting next to David.",
                "David would gain 46 happiness units by sitting next to Alice.",
                "David would lose 7 happiness units by sitting next to Bob.",
                "David would gain 41 happiness units by sitting next to Carol.");
        KnightsOfTheDinnerTable tsp = new KnightsOfTheDinnerTable(input.stream(), false);
        assertThat(tsp.maxHappiness()).isEqualTo(330.0);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day13input.txt").getFile();
        KnightsOfTheDinnerTable tsp = new KnightsOfTheDinnerTable(InputUtil.getAllLinesFromFile(input), false);
        assertThat(tsp.maxHappiness()).isEqualTo(618.0);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day13input.txt").getFile();
        KnightsOfTheDinnerTable tsp = new KnightsOfTheDinnerTable(InputUtil.getAllLinesFromFile(input), true);
        assertThat(tsp.maxHappiness()).isEqualTo(601.0);
    }
}
