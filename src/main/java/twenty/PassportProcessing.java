package twenty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public final class PassportProcessing {
    private List<Map<String, String>> validPassports = new ArrayList<>();
    private final Map<String, Validators> validatorsMap;

    /** https://stackoverflow.com/a/6721432/11451863 */
    enum Validators {

        BIRTH_YEAR() {
            @Override
            public boolean isValid(String strValue) {
                if (strValue.length() != 4) {
                    return false;
                }
                try {
                    int year = Integer.parseInt(strValue);
                    return year >= 1920 && year <= 2002;
                } catch (NumberFormatException notInt) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return "byr";
            }
        },
        ISSUE_YEAR() {
            @Override
            public boolean isValid(String strValue) {
                if (strValue.length() != 4) {
                    return false;
                }
                try {
                    int year = Integer.parseInt(strValue);
                    return year >= 2010 && year <= 2020;
                } catch (NumberFormatException notInt) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return "iyr";
            }
        },
        EXPIRATION_YEAR() {
            @Override
            public boolean isValid(String strValue) {
                if (strValue.length() != 4) {
                    return false;
                }
                try {
                    int year = Integer.parseInt(strValue);
                    return year >= 2020 && year <= 2030;
                } catch (NumberFormatException notInt) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return "eyr";
            }
        },
        HEIGHT() {
            @Override
            public boolean isValid(String strValue) {
                String unit = strValue.substring(strValue.length() - 2);
                String strHeight = strValue.substring(0, strValue.length() - 2);
                try {
                    int height = Integer.parseInt(strHeight);
                    return  (("cm".equals(unit) && height >= 150 && height <= 193)
                            || ("in".equals(unit) && height >= 59 && height <= 76));
                } catch (NumberFormatException notInt) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return "hgt";
            }
        },
        HAIR_COLOR() {
            @Override
            public boolean isValid(String strValue) {
                Matcher hairColorMatcher = validHairColor.matcher(strValue);
                return hairColorMatcher.matches();
            }

            @Override
            public String toString() {
                return "hcl";
            }
        },
        EYE_COLOR() {
            @Override
            public boolean isValid(String strValue) {
                return validEyeColors.contains(strValue);
            }

            @Override
            public String toString() {
                return "ecl";
            }
        },
        PASSPORT_ID() {
            @Override
            public boolean isValid(String strValue) {
                if (strValue.length() == 9) {
                    try {
                        Integer.parseInt(strValue);
                        return true;
                    } catch (ValueException notAnInt) {
                        return false;
                    }
                }
                return false;
            }

            @Override
            public String toString() {
                return "pid";
            }
        };
        private static final List<String> validEyeColors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
        private static final Pattern validHairColor = Pattern.compile("#[0-9a-z]{6}");
        public abstract boolean isValid(String strValue);
    }

    public PassportProcessing(List<String> passports) {
        validatorsMap = Arrays.stream(Validators.values())
                .collect(Collectors.toMap(Validators::toString, Function.identity()));
        List<String> validCodes = Arrays.stream(Validators.values())
                .map(Validators::toString)
                .collect(Collectors.toList());
        Map<String, String> passportData = new HashMap<>();
        for (String passport : passports) {
            if (passport.isEmpty()) {
                if(passportData.size() == validCodes.size()) {
                    validPassports.add(passportData);
                }
                passportData = new HashMap<>();
            } else {
                passportData.putAll(Arrays.stream(passport.split(" "))
                        .map(field -> field.split(":"))
                        .filter(code -> validCodes.contains(code[0]))
                        .collect(Collectors.toMap(code -> code[0], code -> code[1])));
            }
        }
    }

    public int numValidPassports() {
        return validPassports.size();
    }

    public void reValidatePassports() {
        List<Map<String, String>> revalidatedPassports = new ArrayList<>();
        for (Map<String, String> passport : validPassports) {
            if(passport.entrySet()
                    .stream()
                    .allMatch(entry -> validatorsMap.get(entry.getKey()).isValid(entry.getValue()))) {
                revalidatedPassports.add(passport);
            }
        }
        validPassports = revalidatedPassports;
    }
}
