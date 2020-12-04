package twenty;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import util.InputUtil;

class TestDayFour {

    @Test
    void example() {
        List<String> passports = Arrays.asList("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
                "byr:1937 iyr:2017 cid:147 hgt:183cm",
                "",
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
                "hcl:#cfa07d byr:1929",
                "",
                "hcl:#ae17e1 iyr:2013",
                "eyr:2024",
                "ecl:brn pid:760753108 byr:1931",
                "hgt:179cm",
                "",
                "hcl:#cfa07d eyr:2025 pid:166559648",
                "iyr:2011 ecl:brn hgt:59in",
                "");

        PassportProcessing passportPlease = new PassportProcessing(passports);
        assertThat(passportPlease.numValidPassports()).isEqualTo(2);
    }

    @Test
    void partOne() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day4input.txt").getFile();
        PassportProcessing passportPlease = new PassportProcessing(InputUtil.getAllLinesFromFile(input)
                .collect(Collectors.toList()));
        assertThat(passportPlease.numValidPassports()).isEqualTo(202);
    }

    @Test
    void partTwo() throws IOException {
        String input = Thread.currentThread().getContextClassLoader().getResource("2020day4input.txt").getFile();
        PassportProcessing passportPlease = new PassportProcessing(InputUtil.getAllLinesFromFile(input)
                .collect(Collectors.toList()));
        passportPlease.reValidatePassports();
        assertThat(passportPlease.numValidPassports()).isEqualTo(137);
    }
}
