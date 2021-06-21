/*
import com.sun.tools.javac.Main;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import setup.SetupClass;

import java.io.*;

@ExtendWith(MockitoExtension.class)
class ProgramStarterTest extends SetupClass {
    protected File input;
    protected String outputPath;

    @BeforeEach
    void setUp() {
        input = new File(INPUT_BASE_PATH+"ex0.md");
        outputPath = OUTPUT_BASE_PATH+"programStarterOutput/";
    }

    @AfterAll
    static void cleanUp() throws IOException {
        cleanOutputDirectory();
    }

    @Test
    void testMain() throws Exception {
        BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
        //when(bufferedReader.readLine()).thenReturn(input+" "+outputPath+" M --mcdc");
        //Mockito.lenient().when(bufferedReader.readLine()).thenReturn(input+" "+outputPath+" M --mcdc");
        ProgramStarter.main(new String[] {});
        assertTrue(true);
    }
}*/
