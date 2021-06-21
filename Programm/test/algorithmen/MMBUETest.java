package algorithmen;

import input.InputScanner;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import setup.SetupClass;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MMBUETest extends SetupClass {
    protected File input;
    protected String solutionPath = SOLUTIONS_BASE_PATH+ "MMBUE/";

    @Test
    void testGetPairs() {
        ArrayList<Integer> conditionEntries = new ArrayList<>(Arrays.asList(0, 0, 0));
        List<ArrayList<Integer>> actual = MMBUE.getPairs(conditionEntries);
        assertEquals(Arrays.asList(1, 0, 0), actual.get(0));
        assertEquals(Arrays.asList(0, 1, 0), actual.get(1));
        assertEquals(Arrays.asList(0, 0, 1), actual.get(2));
    }

    @Nested
    class testMMBUEAlgorithm {
        @Test
        void testMMBUEAlgorithmWithEx0() throws IOException {
            input = new File(INPUT_BASE_PATH+"ex0.md");
            TruthTable t = InputScanner.readTableFromFile(input);
            Map<ArrayList<Integer>, Integer> actualResult = MMBUE.runMmbue(t);
            TruthTable expectedTable = InputScanner.readTableFromFile(new File(solutionPath+"ex0.md"));
            assertEquals(actualResult, expectedTable.getTruthTable());
        }

        @Test
        void testMMBUEAlgorithmWithEx1() throws IOException {
            input = new File(INPUT_BASE_PATH+"ex1.md");
            TruthTable t = InputScanner.readTableFromFile(input);
            Map<ArrayList<Integer>, Integer> actualResult = MMBUE.runMmbue(t);
            TruthTable expectedTable = InputScanner.readTableFromFile(new File(solutionPath+"ex1.md"));
            assertEquals(actualResult, expectedTable.getTruthTable());
        }

        @Test
        void testMMBUEAlgorithmWithEx2() throws IOException {
            input = new File(INPUT_BASE_PATH+"ex2.md");
            TruthTable t = InputScanner.readTableFromFile(input);
            Map<ArrayList<Integer>, Integer> actualResult = MMBUE.runMmbue(t);
            TruthTable expectedTable = InputScanner.readTableFromFile(new File(solutionPath+"ex2.md"));
            assertEquals(actualResult, expectedTable.getTruthTable());
        }

        @Test
        void testMMBUEAlgorithmWithEx3() throws IOException {
            input = new File(INPUT_BASE_PATH+"ex3.md");
            TruthTable t = InputScanner.readTableFromFile(input);
            Map<ArrayList<Integer>, Integer> actualResult = MMBUE.runMmbue(t);
            TruthTable expectedTable = InputScanner.readTableFromFile(new File(solutionPath+"ex3.md"));
            assertEquals(actualResult, expectedTable.getTruthTable());
        }

        @Test
        void testMMBUEAlgorithmWithMissingPair() throws IOException {
            input = new File(INVALID_INPUT_BASE_PATH+"missing-pair.md");
            TruthTable t = InputScanner.readTableFromFile(input);
            Exception e = assertThrows(IllegalArgumentException.class, () -> MMBUE.runMmbue(t));
            assertTrue(e.getMessage().contains("Es gibt keine Paare fuer: [0, 1]")); // [0, 1] missing in table
        }
    }
}