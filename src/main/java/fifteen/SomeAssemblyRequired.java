package fifteen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/** https://adventofcode.com/2015/day/7 */
public class SomeAssemblyRequired {
    private final Map<String, Integer> memory;
    private List<Instruction> unfinishedInstructions = new ArrayList<>();
    private final Pattern assignment = Pattern.compile("([0-9a-z]+) -> ([a-z]+)");
    private final Pattern notOp = Pattern.compile("NOT ([a-z0-9]+) -> ([a-z]+)");
    private final Pattern binOp = Pattern.compile("([a-z0-9]+) (AND|OR|LSHIFT|RSHIFT) ([a-z0-9]+) -> ([a-z]+)");

    final class Instruction {
        private final String operandOne;
        private final String operandTwo;
        private final String operation;
        private final String output;
        private boolean isDone;

        public Instruction(String op1, String op2, String oper, String out) {
            operandOne = op1;
            operandTwo = op2;
            operation = oper;
            output = out;
        }

        public void execute() {
            if (operation.equals("NOT")) {
                Integer val = eval(operandOne, operation);
                if (val != null) {
                    memory.put(output, val);
                    isDone = true;
                }
            } else if (operation.equals("ASSIGN")) {
                Integer val = eval(operandOne, null);
                if (val != null) {
                    memory.put(output, val);
                    isDone = true;
                }
            } else {
                Integer val = eval(operandOne, operandTwo, operation);
                if (val != null) {
                    memory.put(output, val);
                    isDone = true;
                }
            }
        }

        public boolean isDone() {
            return  isDone;
        }

        @Override
        public String toString() {
            return String.format("%s %s %s -> %s", operandOne, operation, operandTwo, output);
        }
    }

    public SomeAssemblyRequired() {
        memory = new HashMap<>();
    }

    public void execute(String instruction) {
        Matcher assignmentMatcher = assignment.matcher(instruction);
        Matcher notOpMatcher = notOp.matcher(instruction);
        Matcher binOpMatcher = binOp.matcher(instruction);
        if (assignmentMatcher.matches()) {
            String lhs = assignmentMatcher.group(1);
            String outputVar = assignmentMatcher.group(2);
            Integer output = eval(lhs, null);
            if (output != null) {
                memory.put(outputVar, output);
            } else {
                Instruction assignOp = new Instruction(lhs, null, "ASSIGN", outputVar);
                unfinishedInstructions.add(assignOp);
            }
        } else if (notOpMatcher.matches()) {
            String var = notOpMatcher.group(1);
            String outputVar = notOpMatcher.group(2);
            Integer output = eval(var, "NOT");
            if (output != null) {
                memory.put(outputVar, output);
            } else {
                unfinishedInstructions.add(new Instruction(var, null, "NOT", outputVar));
            }
        } else if (binOpMatcher.matches()) {
            String var1 = binOpMatcher.group(1);
            String var2 = binOpMatcher.group(3);
            String out = binOpMatcher.group(4);
            String op = binOpMatcher.group(2);
            Integer output = eval(var1, var2, op);
            if (output != null) {
                memory.put(out, output);
            } else {
                unfinishedInstructions.add(new Instruction(var1, var2, op, out));
            }
        }
        evaluateUnfinishedOperations();
    }

    private void evaluateUnfinishedOperations() {
        while (true) {
            unfinishedInstructions.forEach(Instruction::execute);
            long doneCount = unfinishedInstructions.stream().filter(Instruction::isDone).count();
            unfinishedInstructions = unfinishedInstructions.stream().filter(op -> !op.isDone()).collect(Collectors.toList());
            if (doneCount == 0) {
                break;
            }
        }
    }

    /** https://stackoverflow.com/a/28707472/11451863 */
    public int getValueOf(String var) {
        return memory.get(var) & 0xFFFF;
    }

    /** https://stackoverflow.com/a/237204/11451863 */
    public static boolean isInteger(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private Integer eval(String var) {
        if (isInteger(var)) {
            return Integer.parseInt(var);
        } else  {
            return memory.get(var);
        }
    }

    private Integer eval(String var1, String operation) {
        Integer val1 = eval(var1);
        Integer output = null;
        if (val1 != null) {
            if ("NOT".equals(operation)) {
                output = ~val1;
            } else {
                output = val1;
            }
        }
        return output;
    }

    private Integer eval(String var1, String var2, String operation) {
        Integer val1 = eval(var1);
        Integer val2 = eval(var2);
        Integer output = null;
        if (val1 != null && val2 != null) {
            if ("RSHIFT".equals(operation)) {
                output = (val1 >> val2);
            } else if ("LSHIFT".equals(operation)) {
                output = (val1 << val2);
            } else if ("AND".equals(operation)) {
                output = (val1 & val2);
            } else {
                output =  (val1 | val2);
            }
        }
        return output;
    }
}
