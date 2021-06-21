package output;

import algorithmen.MCDC;
import org.junit.jupiter.api.Test;
import setup.SetupClass;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OutputWriterTest extends SetupClass {
    @Test
    void writeTruthTableToFile() throws IOException {
        String outputFile = OUTPUT_BASE_PATH+ File.separator + "written-output-file.md";

        try {
            Map<ArrayList<Integer>, Integer> tableEntry = new HashMap<>();
            tableEntry.put(new ArrayList<>(List.of(0, 0, 0)), 0);

            OutputWriter.writeToFile(outputFile, tableEntry, "| A | B | C | Cond |", 4);

            List<String> lines = Files.readAllLines(Path.of(new File(outputFile).getPath()), StandardCharsets.UTF_8);
            assertEquals(3, lines.size());
            assertTrue(lines.contains("| A | B | C | Cond |"));
            assertTrue(lines.contains("| -- | -- | -- | -- |"));
            assertTrue(lines.contains("|  0 |  0 |  0 |  0 |"));
        } finally {
            new File(outputFile).delete();
        }
    }
}