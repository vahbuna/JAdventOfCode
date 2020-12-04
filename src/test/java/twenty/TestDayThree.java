package twenty;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayThree {

    private static List<String> terrain;

    @BeforeAll
    static void createTerrain() {
        terrain = Arrays.asList("..##.........##.........##.........##.........##.........##.......",
                "#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..",
                ".#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.",
                "..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#",
                ".#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.",
                "..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....",
                ".#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#",
                ".#........#.#........#.#........#.#........#.#........#.#........#",
                "#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...",
                "#...##....##...##....##...##....##...##....##...##....##...##....#",
                ".#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#");
    }
    @Test
    void example() {
        assertThat(TobogganTrajectory.getTreeCount(terrain, 3, 1)).isEqualTo(7);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day3input.txt").getFile();
        int treeCnt = TobogganTrajectory.getTreeCount(InputUtil.getAllLinesFromFile(input).collect(Collectors.toList()), 3, 1);
        assertThat(treeCnt).isEqualTo(184);
    }

    @Test
    void stepRightOne() {
        assertThat(TobogganTrajectory.getTreeCount(terrain, 1, 1)).isEqualTo(2);
    }

    @Test
    void stepRightFive() {
        assertThat(TobogganTrajectory.getTreeCount(terrain, 5, 1)).isEqualTo(3);
    }

    @Test
    void stepRightSeven() {
        assertThat(TobogganTrajectory.getTreeCount(terrain, 7, 1)).isEqualTo(4);
    }

    @Test
    void stepRightOneDownTwo() {
        assertThat(TobogganTrajectory.getTreeCount(terrain, 1, 2)).isEqualTo(2);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day3input.txt").getFile();
        List<String> terrain = InputUtil.getAllLinesFromFile(input).collect(Collectors.toList());
        int[] right = new int[] {1, 3, 5, 7, 1};
        int[] down = new int[] {1, 1, 1, 1, 2};
        long answer = 1;
        for (int i = 0; i < right.length; i++) {
            answer *= TobogganTrajectory.getTreeCount(terrain, right[i], down[i]);
        }
        assertThat(answer).isEqualTo(2431272960L);
    }
}
