package fifteen;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayTwo {
    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day2input.txt").getFile();
        IWasToldThereWouldBeNoMath partOne = new IWasToldThereWouldBeNoMath(InputUtil.getAllLinesFromFile(input));
        assertThat(partOne.wrappingPaper()).isEqualTo(1606483);
    }

    @Test
    void partTwp() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day2input.txt").getFile();
        IWasToldThereWouldBeNoMath partTwo = new IWasToldThereWouldBeNoMath(InputUtil.getAllLinesFromFile(input));
        assertThat(partTwo.ribbonAndBow()).isEqualTo(3842356);
    }
}
