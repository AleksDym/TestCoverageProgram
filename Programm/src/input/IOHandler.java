package input;

import algorithmen.MCDC;
import algorithmen.MMBUE;
import algorithmen.TruthTable;
import enums.AlgorithmToExecute;
import enums.FileFormat;
import output.OutputWriter;

import java.io.*;
import java.nio.file.NotDirectoryException;
import java.util.*;
import java.util.stream.Collectors;

public class IOHandler {

	//Pruefen unser input Path und bearbeiten weiter, abhaengig davon, ob das Directory oder File ist
	public static void checkInputPath(File input, String outputPath, FileFormat format, boolean mcdc, boolean mmbue) throws NotDirectoryException {
		//if output file does not exist - make a new one
		if (!new File(outputPath).exists()) {
			new File(outputPath).mkdirs();
		}

		//if output is not of a type directory
		if (!new File(outputPath).isDirectory()) {
			System.out.println("Bitte geben Sie den Ordner als Output Path\n" + System.lineSeparator());
			throw new NotDirectoryException("Your output path must be a directory!");
		}

		if (input.isDirectory()) {

			List<File> allFiles = getFiles(input, format); //collecting all files that we need to handle
			allFiles.forEach(file -> executeForFile(file, outputPath, format, mcdc, mmbue));
			//for each file we work separetly

		} else {
			//in case input is a single file
			if (input.getPath().substring(input.getPath().lastIndexOf(".") + 1).equals(format.getFileEnding())) {
				executeForFile(input, outputPath, format, mcdc, mmbue);
			} else {
				System.out.println(" Der Format von der Datei ist nicht korrekt");
				throw new IllegalArgumentException("The input file format is not correct!");
			}
		}
	}

	/*private static List<File> getFiles(File dir, FileFormat format) {
		System.out.println("Ordner Size: " + dir.list().length);
		if (Objects.requireNonNull(dir.list()).length == 0) {
			throw new NullPointerException("Ordner ist leer");
		}
		File[] listWithFiles = dir.listFiles();
		List<File> resultList = new ArrayList<>();

		for (File f : listWithFiles) {
			if (f.isDirectory()) {
				System.out.println("Ordner wurde gefunden - wird nicht bearbeitet");
			} else if (!f.getPath().substring(f.getPath().lastIndexOf(".") + 1).equals(format.getFileEnding())) {
				System.out.println("Format von der Input Datei ist inkorrekt - wird nicht bearbeitet");
			}
			resultList.add(f);
		}
		return resultList;
	}*/

	private static List<File> getFiles(File dir, FileFormat format) {
		return Arrays.stream(dir.listFiles()).
				filter(file -> !file.isDirectory()).
				filter(file -> file.getPath().substring(file.getPath().lastIndexOf(".") + 1).equals(format.getFileEnding())).
				collect(Collectors.toList());
	}

	//Bearbeiten den File und generieren die Test Cases

	private static void executeForFile(File file, String outputPath, FileFormat format, boolean mcdc, boolean mmbue) {
		try {
			//falls  file von Typ MD dann->>>>
			if (format == FileFormat.MD) {
				TruthTable table = InputScanner.readTableFromFile(file);
				if (mcdc) {

					String outputFileName = file.getName().replace(".", String.format("-%s-output.", AlgorithmToExecute.MCDC.getName()));
					String generatedName = outputPath + File.separator + outputFileName;

					OutputWriter.writeToFile(generatedName, MCDC.runMcdc(table), table.getHeader(), table.getAnzahlVonBedingugen());
				}
				if (mmbue) {
					String outputFileName = file.getName().replace(".", String.format("-%s-output.", AlgorithmToExecute.MMBUE.getName()));
					String generatedName = outputPath + File.separator + outputFileName;

					OutputWriter.writeToFile(generatedName, MMBUE.runMmbue(table), table.getHeader(), table.getAnzahlVonBedingugen());
				}
			} else {
				throw new IllegalArgumentException("Dieser Format wurde noch nicht implementiert");
			}
		} catch (IOException e) {
			System.out.println(e + "Test Case Generierung war nicht erfolgreich");
		}
	}

}

