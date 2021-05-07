package twenty;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestDayThirteen {

    @Test
    void example() {
        List<String> input = Arrays.asList("939", "7,13,x,x,59,x,31,19");
        ShuttleSearch shuttle = new ShuttleSearch(input);
        assertThat(shuttle.prodIdAndDelay()).isEqualTo(295);
    }

    @Test
    void partOne() {
        List<String> input = Arrays.asList("1000067",
                "17,x,x,x,x,x,x,x,x,x,x,37,x,x,x,x,x,439,x,29,x,x,x,x,x,x,x,x,x,x,13,x,x,x,x,x,x,x,x,x,23,x,x,x,x,x,x,x,787,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,19");
        ShuttleSearch shuttle = new ShuttleSearch(input);
        assertThat(shuttle.prodIdAndDelay()).isEqualTo(205);
    }

    @Test
    void exampleBruteForce() {
        List<String> input = Arrays.asList("939", "7,13,x,x,59,x,31,19");
        ShuttleSearch shuttle = new ShuttleSearch(input);
        assertThat(shuttle.bruteForce()).isEqualTo(1068781);
    }

    @Test
    void exampleBruteForceBis() {
        List<String> input = Arrays.asList("939", "17,x,13,19");
        ShuttleSearch shuttle = new ShuttleSearch(input);
        assertThat(shuttle.bruteForce()).isEqualTo(3417);
    }

    @Test
    void exampleSieve() {
        List<String> input = Arrays.asList("939", "67,7,x,59,61");
        ShuttleSearch shuttle = new ShuttleSearch(input);
        assertThat(shuttle.searchBySieving()).isEqualTo(1261476);
    }

    @Test
    void exampleSieveBis() {
        List<String> input = Arrays.asList("939", "1789,37,47,1889");
        ShuttleSearch shuttle = new ShuttleSearch(input);
        assertThat(shuttle.searchBySieving()).isEqualTo(1202161486);
    }

    @Test
    void partTwo() {
        List<String> input = Arrays.asList("1000067",
                "17,x,x,x,x,x,x,x,x,x,x,37,x,x,x,x,x,439,x,29,x,x,x,x,x,x,x,x,x,x,13,x,x,x,x,x,x,x,x,x,23,x,x,x,x,x,x,x,787,x,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,19");
        ShuttleSearch shuttle = new ShuttleSearch(input);
        assertThat(shuttle.crt()).isEqualTo(803025030761664L);
    }

    @Test
    void exampleCrt() {
        List<String> input = Arrays.asList("939", "67,x,7,59,61");
        ShuttleSearch shuttle = new ShuttleSearch(input);
        assertThat(shuttle.crt()).isEqualTo(779210);
    }

    @Test
    void exampleCrtBis() {
        List<String> input = Arrays.asList("939", "67,7,59,61");
        ShuttleSearch shuttle = new ShuttleSearch(input);
        assertThat(shuttle.crt()).isEqualTo(754018);
    }
}
