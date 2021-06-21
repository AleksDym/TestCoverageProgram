package input;

import algorithmen.TruthTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import setup.SetupClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class InputScannerTest extends SetupClass {

    protected File input;

    @Test
    void readValidTableFromFile() throws IOException {
        input = new File(INPUT_BASE_PATH+"ex0.md");
        TruthTable t = InputScanner.readTableFromFile(input);
        assertEquals(4, t.getAnzahlVonBedingugen());
        assertEquals(8, t.getTruthTable().size());
        assertEquals("| M0 | M1 | M2 | Y  |", t.getHeader());
    }

    @Test
    void readTableFromNotExistingFile() {
        assertThrows(FileNotFoundException.class, () -> InputScanner.readTableFromFile(new File("not_existing_file.md")));
    }

    @Test
    void readTableFromMalFormedHeaderFile() {
        input = new File(INVALID_INPUT_BASE_PATH+"wrong-header.md");
        Exception e = assertThrows(IOException.class, () -> InputScanner.readTableFromFile(input));
        assertTrue(e.getMessage().contains("der Header ist inkorrekt formatiert"));
    }

    @Test
    void readTableFromOnlyHeaderFile() {
        input = new File(INVALID_INPUT_BASE_PATH+"only-header.md");
        Exception e = assertThrows(IOException.class, () -> InputScanner.readTableFromFile(input));
        assertTrue(e.getMessage().contains("Nach dem Splitter gibt es keine Zeilen mehr"));
    }

    @Nested
    class readTableFromWrongLineSplitterFile {
        @Test
        void readTableFromMalFormedLineSplitterFile() {
            input = new File(INVALID_INPUT_BASE_PATH+"wrong-line-splitter.md");
            Exception e = assertThrows(IllegalArgumentException.class, () -> InputScanner.readTableFromFile(input));
            assertTrue(e.getMessage().contains("die Zeile ist inkorrekt formatiert"));
        }

        @Test
        void readTableFromWrongCountCellsOfLineSplitterFile() {
            input = new File(INVALID_INPUT_BASE_PATH+"wrong-line-splitter-cells-count.md");
            Exception e = assertThrows(IllegalArgumentException.class, () -> InputScanner.readTableFromFile(input));
            assertTrue(e.getMessage().contains("Die Anzahl von Zellen ist nicht korrekt"));
        }
    }

    @Nested
    class readTableFromWrongNumberOfCellsFile {
        @Test
        void readTableFromMalFormedNumberOfCellsFile() {
            input = new File(INVALID_INPUT_BASE_PATH+"wrong-count-cells.md");
            Exception e = assertThrows(IllegalArgumentException.class, () -> InputScanner.readTableFromFile(input));
            assertTrue(e.getMessage().contains("Die Zeile hat falsche Anzahl von Zellen"));
        }

        @Test
        void readTableFromMalFormedCellsFile() {
            input = new File(INVALID_INPUT_BASE_PATH+"wrong-cells.md");
            Exception e = assertThrows(IllegalArgumentException.class, () -> InputScanner.readTableFromFile(input));
            assertTrue(e.getMessage().contains("die Zeile ist inkorrekt formatiert"));
        }
    }
}