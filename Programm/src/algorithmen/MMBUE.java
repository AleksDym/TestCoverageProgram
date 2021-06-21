package algorithmen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MMBUE {

    public static Map<ArrayList<Integer>, Integer> runMmbue(TruthTable truthTable) throws IllegalArgumentException {
        //new List to save all the test cases
        Map<ArrayList<Integer>, Integer> selectedTestCases = new HashMap<>();

        for (Map.Entry<ArrayList<Integer>, Integer> entry : truthTable.getTruthTable().entrySet()) { //iterate through pairs in map = table

            List<ArrayList<Integer>> pairs = getPairs(entry.getKey());
            List<ArrayList<Integer>> pairsWithDiffConds = pairs.stream().filter(pair -> {
                if (truthTable.getTruthTable().get(pair) == null) {
                    throw new IllegalArgumentException(String.format("Es gibt keine Paare fuer: %s", pair.toString()));
                } else {
                    return !truthTable.getTruthTable().get(pair).equals(entry.getValue());
                }
            }).collect(Collectors.toList());
            if (pairsWithDiffConds.size() > 0) {
                pairsWithDiffConds.forEach(pair -> selectedTestCases.put(pair, truthTable.getTruthTable().get(pair)));
                selectedTestCases.put(entry.getKey(), entry.getValue());
            }
        }
        return selectedTestCases;
    }

    public static List<ArrayList<Integer>> getPairs(ArrayList<Integer> entryKey) {
        //jede Zeite hat nicht 1 Paar-sondern mehrere
        List<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < entryKey.size(); i++) {
            int replaceValue;
            if (entryKey.get(i) == 0) {
                replaceValue = 1;
            } else {
                replaceValue = 0;
            }
            ArrayList<Integer> neighbour = new ArrayList<>(entryKey);
            neighbour.set(i, replaceValue);
            result.add(neighbour);
        }
        return result;
    }


}
