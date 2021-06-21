package setup;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SetupClass {
    private static final ClassLoader classLoader = SetupClass.class.getClassLoader();
    protected static final String INPUT_BASE_PATH = Objects.requireNonNull(classLoader.getResource("resources/exercises/")).getPath();
    protected static final String OUTPUT_BASE_PATH = Objects.requireNonNull(classLoader.getResource("resources/output/")).getPath();
    protected static final String INVALID_INPUT_BASE_PATH = Objects.requireNonNull(classLoader.getResource("resources/invalidInput/")).getPath();
    protected static final String SOLUTIONS_BASE_PATH = Objects.requireNonNull(classLoader.getResource("resources/solutions/")).getPath();

    protected static void cleanOutputDirectory() throws IOException {
        File outputDir = new File(OUTPUT_BASE_PATH);
        for (File file : Objects.requireNonNull(outputDir.listFiles())) {
            System.out.println("File is: "+file.getName()+"\n");
            if (!file.getName().equals("README.md")) {
                if (file.isDirectory())
                    FileUtils.deleteDirectory(file);
                else
                    file.delete();
            }
        }
    }
}
