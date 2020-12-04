package fifteen;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDaySix {

    @Test
    void all() {
        String instruction = "turn on 0,0 through 999,999";
        ProbablyaFireHazard grid = new ProbablyaFireHazard();
        grid.follow(instruction);
        assertThat(grid.litCount()).isEqualTo(1000 * 1000);
    }

    @Test
    void firstLine() {
        String instruction = "toggle 0,0 through 999,0";
        ProbablyaFireHazard grid = new ProbablyaFireHazard();
        grid.follow(instruction);
        assertThat(grid.litCount()).isEqualTo(1000);
    }

    @Test
    void middleFourOff() {
        String instruction = "turn off 499,499 through 500,500";
        ProbablyaFireHazard grid = new ProbablyaFireHazard();
        grid.follow(instruction);
        assertThat(grid.litCount()).isZero();
    }

    @Test
    void middleFourOn() {
        String instruction = "turn on 499,499 through 500,500";
        ProbablyaFireHazard grid = new ProbablyaFireHazard();
        grid.follow(instruction);
        assertThat(grid.litCount()).isEqualTo(4);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day6input.txt").getFile();
        ProbablyaFireHazard grid = new ProbablyaFireHazard();
        InputUtil.getAllLinesFromFile(input).forEach(grid::follow);
        assertThat(grid.litCount()).isEqualTo(377891);
    }

    @Test
    void brightOne() {
        String instruction = "turn on 0,0 through 0,0";
        ProbablyaFireHazard grid = new ProbablyaFireHazard();
        grid.followBrightness(instruction);
        assertThat(grid.totalBrightness()).isOne();
    }

    @Test
    void brightMany() {
        String instruction = "toggle 0,0 through 999,999";
        ProbablyaFireHazard grid = new ProbablyaFireHazard();
        grid.followBrightness(instruction);
        assertThat(grid.totalBrightness()).isEqualTo(2000000);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day6input.txt").getFile();
        ProbablyaFireHazard grid = new ProbablyaFireHazard();
        InputUtil.getAllLinesFromFile(input).forEach(grid::followBrightness);
        assertThat(grid.totalBrightness()).isEqualTo(14110788);
    }
}
