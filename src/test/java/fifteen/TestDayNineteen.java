package fifteen;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayNineteen {
    @Test
    void example() {
        List<String> replacements = Arrays.asList("H => HO", "H => OH", "O => HH",
                "", "HOH");
        MedicineForRudolph nuclearPlant = new MedicineForRudolph(replacements);
        assertThat(nuclearPlant.distinctMolecules()).isEqualTo(4);
    }

    @Test
    void exampleBis() {
        List<String> replacements = Arrays.asList("H => HO", "H => OH", "O => HH",
                "", "HOHOHO");
        MedicineForRudolph nuclearPlant = new MedicineForRudolph(replacements);
        assertThat(nuclearPlant.distinctMolecules()).isEqualTo(7);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2015day19input.txt").getFile();
        MedicineForRudolph nuclearPlant = new MedicineForRudolph(InputUtil.getAllLinesFromFile(input).collect(Collectors.toList()));
        assertThat(nuclearPlant.distinctMolecules()).isEqualTo(535);
    }

}
