package twenty;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayOne {

    @Test
    void one() {
        String[] expenses = new String[] {"1721", "979", "366", "299", "675", "1456"};
        ReportRepair report = new ReportRepair(Arrays.stream(expenses));
        assertThat(report.twoSum(2020)).isEqualTo(514579);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day1input.txt").getFile();
        ReportRepair report = new ReportRepair(InputUtil.getAllLinesFromFile(input));
        assertThat(report.twoSum(2020)).isEqualTo(955584);
    }

    @Test
    void two() {
        String[] expenses = new String[] {"1721", "979", "366", "299", "675", "1456"};
        ReportRepair report = new ReportRepair(Arrays.stream(expenses));
        assertThat(report.threeSum2020()).isEqualTo(241861950);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day1input.txt").getFile();
        ReportRepair report = new ReportRepair(InputUtil.getAllLinesFromFile(input));
        assertThat(report.threeSum2020()).isEqualTo(955584);
    }
}
