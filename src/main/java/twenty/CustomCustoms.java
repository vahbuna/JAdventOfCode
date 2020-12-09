package twenty;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class CustomCustoms {

    private CustomCustoms() {
        throw new java.lang.UnsupportedOperationException("This is class cannot be instantiated");
    }

    public static int anyoneYes(final List<String> answers) {
        int sumCounts = 0;
        Set<Integer> groupAnswer = new HashSet<>();
        for (String answer : answers) {
            if (answer.isEmpty()) {
                sumCounts += groupAnswer.size();
                groupAnswer = new HashSet<>();
                continue;
            }
            groupAnswer.addAll(answer.chars().boxed().collect(Collectors.toSet()));
        }
        return sumCounts;
    }

    public static int everyoneYes(final List<String> answers) {
        int sumCounts = 0;
        int answerCount = 0;
        Map<Integer, Integer> groupAnswerCount = new HashMap<>();
        for (String answer : answers) {
            if (answer.isEmpty()) {
                final int count = answerCount;
                sumCounts += groupAnswerCount.entrySet().stream().filter(q -> q.getValue() == count).count();
                groupAnswerCount = new HashMap<>();
                answerCount = 0;
                continue;
            }
            for (char question : answer.toCharArray()) {
                int cnt = groupAnswerCount.getOrDefault(question - 'a', 0);
                groupAnswerCount.put(question - 'a', cnt + 1);
            }
            answerCount++;
        }
        return sumCounts;
    }
}
