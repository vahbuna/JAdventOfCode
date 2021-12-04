package fifteen;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayFifteen {

    @Test
    void example() {
        ScienceForHungryPeople recipe = new ScienceForHungryPeople(Arrays.asList("Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8",
                "Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3"), 100);
        assertThat(recipe.getCookieScore()).isEqualTo(62842880);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day15input.txt").getFile();
        ScienceForHungryPeople recipe = new ScienceForHungryPeople(InputUtil.getAllLinesFromFile(input).collect(Collectors.toList()), 100);
        assertThat(recipe.getCookieScore()).isEqualTo(18965440L);
    }

    @Test
    void exampleBis() {
        ScienceForHungryPeople recipe = new ScienceForHungryPeople(Arrays.asList("Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8",
                "Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3"), 100);
        assertThat(recipe.getCalorieCookieScore(500)).isEqualTo(57600000);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day15input.txt").getFile();
        ScienceForHungryPeople recipe = new ScienceForHungryPeople(InputUtil.getAllLinesFromFile(input).collect(Collectors.toList()), 100);
        assertThat(recipe.getCalorieCookieScore(500)).isEqualTo(15862900L);
    }
}
