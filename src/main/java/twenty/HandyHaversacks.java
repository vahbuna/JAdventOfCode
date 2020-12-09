package twenty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class HandyHaversacks {

    private final Map<Bag, List<Bag>> parentBagMap = new HashMap<>();
    private final Map<String, Bag> bagMap = new HashMap<>();

    static class Bag {
        private final String colour;
        private final Map<Bag, Integer> bagCount = new HashMap<>();

        public Bag (final String color) {
            colour = color;
        }

        public void addBag(final Bag childBag, final int count) {
            bagCount.put(childBag, count);
        }

        @Override
        public String toString() {
            return colour;
        }

        @Override
        public int hashCode() {
            return colour.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final Bag other = (Bag) obj;
            return (colour.equals(other.colour));
        }
    }

    public HandyHaversacks(final List<String> baggageRules) {
        for (String rule : baggageRules) {
            String [] fields = rule.split(" bags contain ");
            Bag parentBag = new Bag(fields[0]);
            bagMap.putIfAbsent(parentBag.colour, parentBag);
            if (fields[1].equals("no other bags.")) {
                continue;
            }
            for (String bag : fields[1].split(", ")) {
                String [] tokens = bag.split(" ");
                String childColour = tokens[1] + " " + tokens[2];
                Bag childBag = bagMap.getOrDefault(childColour, new Bag(childColour));
                parentBag.addBag(childBag, Integer.parseInt(tokens[0]));
                parentBagMap.putIfAbsent(childBag, new ArrayList<>());
                parentBagMap.get(childBag).add(parentBag);
            }
        }
    }

    public int getParentCount(final String bagColour) {
        Bag bag = bagMap.get(bagColour);
        List<Bag> parents = parentBagMap.get(bag);
        Set<Bag> uniqueParents = new HashSet<>();
        while (!parents.isEmpty()) {
            Bag parent = parents.remove(0);
            uniqueParents.add(parent);
            if (parentBagMap.containsKey(parent)) {
                parents.addAll(parentBagMap.get(parent));
            }
        }
        return uniqueParents.size();
    }

    public int getCapacity(String bagColour) {
        Bag currentBag = bagMap.get(bagColour);
        int capacity = 0;
        for(Map.Entry<Bag, Integer> bagAndCount : currentBag.bagCount.entrySet()) {
            capacity += bagAndCount.getValue() + bagAndCount.getValue() * getCapacity(bagAndCount.getKey().colour);
        }
        return capacity;
    }
}
