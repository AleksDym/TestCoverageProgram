package algorithmen;

import input.InputScanner;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import setup.SetupClass;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MCDCTest extends SetupClass {
    protected File input;
    protected String solutionPath = SOLUTIONS_BASE_PATH+ "MCDC/";

    @Test
    void testGetPair() {
        ArrayList<Integer> conditionEntries = new ArrayList<>(Arrays.asList(0, 0, 0));
        ArrayList<Integer> actual = MCDC.getPair(conditionEntries, 0);
        assertEquals(Arrays.asList(1, 0, 0), actual);
        conditionEntries = new ArrayList<>(Arrays.asList(1, 0, 0));
        actual = MCDC.getPair(conditionEntries, 0);
        assertEquals(Arrays.asList(0, 0, 0), actual);
    }

    @Nested
    class testMCDCAlgorithm {
        @Test
        void testMCDCAlgorithmWithEx0() throws IOException {
            input = new File(INPUT_BASE_PATH+"ex0.md");
            TruthTable t = InputScanner.readTableFromFile(input);
            Map<ArrayList<Integer>, Integer> actualResult = MCDC.runMcdc(t);
            TruthTable expectedTable = InputScanner.readTableFromFile(new File(solutionPath+"ex0.md"));
            assertTrue(isSubMapOf(actualResult, expectedTable.getTruthTable()));
        }

        @Test
        void testMCDCAlgorithmWithEx1() throws IOException {
            input = new File(INPUT_BASE_PATH+"ex1.md");
            TruthTable t = InputScanner.readTableFromFile(input);
            Map<ArrayList<Integer>, Integer> actualResult = MCDC.runMcdc(t);
            TruthTable expectedTable = InputScanner.readTableFromFile(new File(solutionPath+"ex1.md"));
            assertTrue(isSubMapOf(actualResult, expectedTable.getTruthTable()));
        }

        @Test
        void testMCDCAlgorithmWithEx2() throws IOException {
            input = new File(INPUT_BASE_PATH+"ex2.md");
            TruthTable t = InputScanner.readTableFromFile(input);
            Map<ArrayList<Integer>, Integer> actualResult = MCDC.runMcdc(t);
            TruthTable expectedTable = InputScanner.readTableFromFile(new File(solutionPath+"ex2.md"));
            assertTrue(isSubMapOf(actualResult, expectedTable.getTruthTable()));
        }

        @Test
        void testMCDCAlgorithmWithEx3() throws IOException {
            input = new File(INPUT_BASE_PATH+"ex3.md");
            TruthTable t = InputScanner.readTableFromFile(input);
            Map<ArrayList<Integer>, Integer> actualResult = MCDC.runMcdc(t);
            TruthTable expectedTable = InputScanner.readTableFromFile(new File(solutionPath+"ex3.md"));
            assertTrue(isSubMapOf(actualResult, expectedTable.getTruthTable()));
        }

        @Test
        void testMCDCAlgorithmWithMissingPair() throws IOException {
            input = new File(INVALID_INPUT_BASE_PATH+"missing-pair.md");
            TruthTable t = InputScanner.readTableFromFile(input);
            Exception e = assertThrows(IllegalArgumentException.class, () -> MCDC.runMcdc(t));
            assertTrue(e.getMessage().contains("Es gibt keine Paare fuer: [0, 1]")); // [0, 1] missing in table
        }
    }


    /** Hilfsfunktion, um zu bestimmen ob ein Map<ArrayList<Integer>, Integer> ein Subteil
     *  von einem anderen Map<ArrayList<Integer>, Integer> ist
     * @param subMap
     * @param map
     * @return subMap ist ein Teil von map
     */
    private static boolean isSubMapOf(Map<ArrayList<Integer>, Integer> subMap, Map<ArrayList<Integer>, Integer> map) {
        for (Map.Entry<ArrayList<Integer>, Integer> entry : subMap.entrySet()) {
            if (!map.containsKey(entry.getKey()) || !map.get(entry.getKey()).equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }
}