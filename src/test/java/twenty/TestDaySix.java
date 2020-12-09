package twenty;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDaySix {

    @Test
    void example() {
        List<String> answers = Arrays.asList("abc", "",
                "a", "b", "c", "",
                "ab", "ac", "",
                "a", "a", "a", "a", "",
                "b", "");
        assertThat(CustomCustoms.anyoneYes(answers)).isEqualTo(11);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day6input.txt").getFile();
        assertThat(CustomCustoms.anyoneYes(InputUtil.getAllLinesFromFile(input).collect(Collectors.toList())))
                .isEqualTo(6161);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day6input.txt").getFile();
        assertThat(CustomCustoms.everyoneYes(InputUtil.getAllLinesFromFile(input).collect(Collectors.toList())))
                .isEqualTo(2971);
    }
}
