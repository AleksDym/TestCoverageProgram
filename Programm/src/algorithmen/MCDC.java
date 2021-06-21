package algorithmen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MCDC {


    public static Map<ArrayList<Integer>, Integer> runMcdc(TruthTable truthTable) throws IllegalArgumentException {

        Map<ArrayList<Integer>, Integer> selectedtestCases = new HashMap<>();
            //values as key in an Array and the condition as a key

        for (int i = 0; i < truthTable.getAnzahlVonBedingugen() - 1; i++) { //ohne Ergebnisse

            //iterate through each key-value pair in the  table
            for (Map.Entry<ArrayList<Integer>, Integer> entry : truthTable.getTruthTable().entrySet()) {

                ArrayList<Integer> pair = getPair(entry.getKey(), i); //i= which column we are handling now

                if (truthTable.getTruthTable().get(pair) == null) {
                    throw new IllegalArgumentException(String.format("Es gibt keine Paare fuer: %s", pair.toString()));
                }

                if (!truthTable.getTruthTable().get(pair).equals(entry.getValue())) {
                    // Beide Werte des Paars müssen hinzugefügt werden.
                    selectedtestCases.put(entry.getKey(), entry.getValue());
                    selectedtestCases.put(pair, truthTable.getTruthTable().get(pair));
                    break; // es reicht schon ein Paar für eine Bedingung zu finden bei MC/DC, daher machen wir weiter mit einer weiteren Bedingung...
                }
            }
        }
        return selectedtestCases;
    }

    public static ArrayList<Integer> getPair(ArrayList<Integer> entryKey, int position) {

        ArrayList<Integer> pair = new ArrayList<>(entryKey); //of a size of the entrykey
        //fuer jeder Paarbekommen wir der Gegenwert
        if (entryKey.get(position) == 0) {
            pair.set(position, 1);
        } else {
            pair.set(position, 0);
        }
        return pair;
    }
}
