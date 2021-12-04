package fifteen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuntSue {

    private final Map<String, Integer> gifts = new HashMap<>();

    private static final Pattern sueNumber = Pattern.compile("^Sue ([0-9]+)");

    private static final Pattern gift = Pattern.compile("([a-z]+): ([0-9]+)");

    public AuntSue() {
        gifts.put("children", 3);
        gifts.put("cats", 7);
        gifts.put("samoyeds", 2);
        gifts.put("pomeranians", 3);
        gifts.put("akitas", 0);
        gifts.put("vizslas", 0);
        gifts.put("goldfish", 5);
        gifts.put("trees", 3);
        gifts.put("cars", 2);
        gifts.put("perfumes", 1);
    }

    /**
     * https://adventofcode.com/2015/day/16
     */
    public int getAuntNumber(List<String> aunties) {
        int auntNum = -1;
        for (String aunty : aunties) {
            Matcher auntMatcher = sueNumber.matcher(aunty);
            if (auntMatcher.find()) {
                auntNum = Integer.parseInt(auntMatcher.group(1));
            }
            Matcher giftMatcher = gift.matcher(aunty);
            boolean allMatch = true;
            while (giftMatcher.find()) {
                int auntGiftCount = Integer.parseInt(giftMatcher.group(2));
                if (gifts.get(giftMatcher.group(1)) != auntGiftCount) {
                    allMatch = false;
                    break;
                }
            }
            if (allMatch) {
                return auntNum;
            }
        }
        return -1;
    }

    /**
     * https://adventofcode.com/2015/day/16#part2
     */
    public int getRealAuntSue(List<String> aunties) {
        int auntNum = 0;
        for (String aunty : aunties) {
            auntNum++;
            Matcher giftMatcher = gift.matcher(aunty);
            boolean allMatch = true;
            while (giftMatcher.find() && allMatch) {
                int auntGiftCount = Integer.parseInt(giftMatcher.group(2));
                String auntGift = giftMatcher.group(1);
                if (auntGift.equals("cats") || auntGift.equals("trees")) {
                    if (auntGiftCount <= gifts.get(auntGift)) {
                        allMatch = false;
                    }
                } else if (auntGift.equals("pomeranians") || auntGift.equals("goldfish")) {
                    if (auntGiftCount >= gifts.get(auntGift)) {
                        allMatch = false;
                    }
                } else if (gifts.get(auntGift) != auntGiftCount) {
                    allMatch = false;
                }
            }
            if (allMatch) {
                return auntNum;
            }
        }
        return -1;
    }
}
