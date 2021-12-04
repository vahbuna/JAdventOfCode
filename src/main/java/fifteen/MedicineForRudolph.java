package fifteen;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MedicineForRudolph {

    private Set<String> generatedMolecules = new HashSet<>();

    public MedicineForRudolph(List<String> molecules) {
        String medicine = molecules.get(molecules.size() - 1);
        for (int i = 0; i < molecules.size() - 2; i++) {
            String[] fields = molecules.get(i).split(" => ");
            Pattern p = Pattern.compile(fields[0]);
            Matcher m = p.matcher(medicine);
            while (m.find()) {
                generatedMolecules.add(medicine.substring(0, m.start()) + fields[1] + medicine.substring(m.end()));
            }
        }
    }

    public long distinctMolecules() {
        return generatedMolecules.size();
    }
}
