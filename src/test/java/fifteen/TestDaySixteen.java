package fifteen;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDaySixteen {

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day16input.txt").getFile();
        AuntSue whichAunt = new AuntSue();
        int answer = whichAunt.getAuntNumber(InputUtil.getAllLinesFromFile(input).collect(Collectors.toList()));
        assertThat(answer).isEqualTo(40);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day16input.txt").getFile();
        AuntSue whichAunt = new AuntSue();
        int answer = whichAunt.getRealAuntSue(InputUtil.getAllLinesFromFile(input).collect(Collectors.toList()));
        assertThat(answer).isEqualTo(241);
    }
}
