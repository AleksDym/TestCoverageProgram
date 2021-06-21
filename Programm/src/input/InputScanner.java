package input;

import algorithmen.TruthTable;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputScanner {

    //die Datei mit Scanner lesen und neue Tabelle erzeugen
    public static TruthTable readTableFromFile(File file) throws IOException {
        Scanner sc =  new Scanner(file, StandardCharsets.UTF_8);;
        TruthTable truthTable = new TruthTable();
        try {

            int neededNumberOfCells;

            if (sc.hasNextLine()) {
                String header = sc.nextLine();
                checkHeader(header); //save the header

                truthTable.setHeader(header);

                neededNumberOfCells = header.split("\\|").length - 1; //calculate amount of cells
                truthTable.setAnzahlVonBedingugen(neededNumberOfCells);

                if (sc.hasNextLine()) {
                    checkLine(sc.nextLine(), neededNumberOfCells); //this is the splitter line
                } else {
                    throw new IOException("Nach dem Splitter gibt es keine Zeilen mehr");
                }
                while (sc.hasNextLine()) { //goo thorugh all the lines
                    String line = sc.nextLine();
                    checkCells(line, neededNumberOfCells);

                    List<String> values = Arrays.asList(line.replace(" ", "").split("\\|"));

                    //adding the line to the table in map format: values as key in an Array and the condition as a key
                    truthTable.getTruthTable().put(new ArrayList<>(values.subList(1, values.size() - 1) //those are cell values as a key
                     .stream().map(Integer::parseInt).collect(Collectors.toList())),
                     Integer.parseInt(values.get(values.size() - 1)));

                }
            } else {
                throw new IOException("Die Datei ist leer\n");
            }
        } finally {
                sc.close(); //closing the scanner
        }
        return truthTable;
    }

    //Tabellenüberschrift ist korrekt formatiert

    static void checkHeader(String line) throws IOException {
        if (!line.matches("\\|(\\s*.+\\s*\\|{1})+")) {
            throw new IOException(String.format("der Header ist inkorrekt formatiert:  %s", line));
        }
    }

    //Linie, die Header und Zellen splittet, ist korrekt formatiert

    static void checkLine(String line, int numberOfNeededCells) throws IllegalArgumentException {
        if (line.matches("\\|(\\s*--\\s*\\|{1})+")) {
            int actualCells = line.split("\\|").length - 1;
            if (actualCells != numberOfNeededCells) {
                throw new IllegalArgumentException(String.format("Die Anzahl von Zellen ist nicht korrekt: %s ", line));
            }
        } else {
            throw new IllegalArgumentException(String.format("die Zeile ist inkorrekt formatiert:  %s", line));
        }
    }

    // Tabelleneintrag ist korrekt formatiert
    static void checkCells(String line, int numberOfNeededCells) throws IllegalArgumentException {
        if (line.matches("\\|(\\s*[0,1]{1}\\s*\\|{1})+")) {
            int actualCells = line.split("\\|").length - 1;
            if (actualCells != numberOfNeededCells) {
                throw new IllegalArgumentException(String.format("Die Zeile hat falsche Anzahl von Zellen %s ", line));
            }
        } else {
            throw new IllegalArgumentException(String.format("die Zeile ist inkorrekt formatiert:  %s", line));
        }
    }
}
