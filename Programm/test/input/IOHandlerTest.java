package input;

import enums.FileFormat;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import setup.SetupClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.NotDirectoryException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Die Methode "checkInputPath" nimmt:
 *  - als @input den Path zu einer bestimmten Datei oder den Path zu einem Directory, der dann meherere Dateien enthält
 *  - als @outputPath den Path zum Directory, wo die Ergebnisse gespeichert werden sollen
 *  - als @format die Extension, in der die Ergebnisdatei gespeichert werden soll: MD/CSV
 *  - @mcdc und/oder @bbmue: welcher Algorithmus muss ausgefürht werden
 */
class IOHandlerTest extends SetupClass {
    protected File input;
    protected String outputPath;

    @BeforeEach
    void setUp() {
        input = new File(INPUT_BASE_PATH+"ex0.md");
    }

    @AfterAll
    static void cleanUp() throws IOException {
        cleanOutputDirectory();
    }

    /**
     * Testing with non existing output path. Program should create new directory for output
     */
    @Test
    void checkInputPathWithNotExistingOutputPath() throws NotDirectoryException {
        outputPath = OUTPUT_BASE_PATH+"non_existing_path/";
        IOHandler.checkInputPath(input, outputPath, FileFormat.MD, true, false);
        assertTrue(new File(outputPath+"ex0-mcdc-output.md").exists());
    }

    /**
     * Testing with existing output path. Program should create output file in the output directory
     */
    @Test
    void checkInputPathWithExistingOutputPath() throws NotDirectoryException {
        outputPath = OUTPUT_BASE_PATH;
        IOHandler.checkInputPath(input, outputPath, FileFormat.MD, true, false);
        assertTrue(new File(outputPath+"ex0-mcdc-output.md").exists());
    }

    /**
     * Testing with not valid output path. Program should throw NotDirectoryException
     */
    @Test
    void checkInputPathWithNotValidOutputPath() {
        outputPath = OUTPUT_BASE_PATH+"README.md";
        assertThrows(NotDirectoryException.class, () -> {
            IOHandler.checkInputPath(input, outputPath, FileFormat.MD, true, false);
        });
    }

    /**
     * Testing with input path as a directory. Program should create output files for each files in the input directory
     */
    @Test
    void checkInputPathWithInputAsDirectory() throws NotDirectoryException {
        File inputDir = new File(INPUT_BASE_PATH);
        outputPath = OUTPUT_BASE_PATH+"multiple_output/";
        IOHandler.checkInputPath(inputDir, outputPath, FileFormat.MD, true, false);
        for (int i = 0; i < 4; i++) {
            assertTrue(new File(outputPath+"ex"+i+"-mcdc-output.md").exists());
        }
    }

    /**
     * Testing with invalid input file. Program should throw NotDirectoryException
     */
    @Test
    void checkInputPathWithInvalidInputFormatFile() throws NotDirectoryException {
        File invalidInput = new File(INVALID_INPUT_BASE_PATH+"invalid_input.txt");
        outputPath = OUTPUT_BASE_PATH;
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            IOHandler.checkInputPath(invalidInput, outputPath, FileFormat.MD, true, false);
        });
        assertTrue(e.getMessage().contains("The input file format is not correct!"));
    }
}