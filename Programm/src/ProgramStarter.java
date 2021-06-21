import enums.FileFormat;
import input.IOHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramStarter {

    ////format of input:
    // /Users/sasa/Desktop/ex0.md /Users/sasa/Desktop/CT/erg MD --mcdc
    public static void main(String[] args) throws IOException {

        String input;
        String output;
        FileFormat fileFormat;
        boolean mcdc = false;
        boolean mmbue = false;
        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Bitte geben Sie das Kommando ein. Das Format ist: Path_von_input Path_von_Output Format --mcdc --mmbue \n");

            String inputPath = br.readLine();

            String[] parameters = inputPath.split(" ");
            input = parameters[0];
            output = parameters[1];
            String fileFormatString = parameters[2]; //MD oder CVS

            if (fileFormatString.equals("MD")) {
                fileFormat = FileFormat.MD;
            } else if (fileFormatString.equals("CSV")) {
                fileFormat = FileFormat.CSV;
            } else throw new IllegalArgumentException("File Format invalid");

            if (parameters.length == 4) {
                if (parameters[3].equals("--mcdc")) {
                    mcdc = true;
                    mmbue = false;
                } else if (parameters[3].equals("--mmbue")) {
                    mcdc = false;
                    mmbue = true;
                } else throw new IllegalArgumentException("Command is invalid");
            } else if (parameters.length == 5) {
                if (parameters[3].equals("--mcdc") && (parameters[4].equals("--mmbue")) || (parameters[3].equals("--mmbue") && (parameters[4].equals("--mcdc")))) {
                    mcdc = true;
                    mmbue = true;
                } else throw new IllegalArgumentException("Commands are invalid");
            }
            IOHandler.checkInputPath(new File(input), output, fileFormat, mcdc, mmbue);
        }
    }
}


