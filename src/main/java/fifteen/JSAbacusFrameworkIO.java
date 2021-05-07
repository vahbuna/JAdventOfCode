package fifteen;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSAbacusFrameworkIO {

    private static final Pattern num = Pattern.compile("-?\\d+\\b");

    private JSAbacusFrameworkIO() {

    }

    /**
     * https://adventofcode.com/2015/day/12
     */
    public static int sumAll(String json) {
        Matcher numMatcher = num.matcher(json);
        int sum = 0;
        while (numMatcher.find()) {
            sum += Integer.parseInt(numMatcher.group());
        }
        return sum;
    }

    /**
     * https://adventofcode.com/2015/day/12#part2
     */
    public static int sumExceptRed(String json) {
        return getJsonSum(JsonParser.parseString(json));
    }

    private static int getJsonSum(JsonElement element) {
        int total = 0;
        if (element.isJsonObject()) {
            for (Map.Entry<String, JsonElement> entry : element.getAsJsonObject().entrySet()) {
                if (entry.getValue().isJsonPrimitive() &&
                        entry.getValue().toString().equals("\"red\"")) {
                    return total;
                }
            }
            for (Map.Entry<String, JsonElement> entry : element.getAsJsonObject().entrySet()) {
                total += getJsonSum(entry.getValue());
            }
        } else if (element.isJsonArray()) {
            for (JsonElement entry : element.getAsJsonArray()) {
                total += getJsonSum(entry);
            }
        } else if (element.isJsonPrimitive()) {
            return getPrivateInt(element);
        }
        return total;
    }

    private static int getPrivateInt(JsonElement primitive) {
        try {
            return primitive.getAsInt();
        } catch (NumberFormatException numExp) {
            return 0;
        }
    }
}
