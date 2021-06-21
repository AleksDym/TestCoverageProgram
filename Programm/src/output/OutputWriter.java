package output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

//schreiben Output in Datei
public class OutputWriter {

	public static void writeToFile(String path, Map<ArrayList<Integer>, Integer> selectedCases, String header, int size) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(path));
		String outputToWrite = String.format("%s%s%s", getHeaderLine(header), getTableSplitter(size), getRows(selectedCases.entrySet()));
		writer.write(outputToWrite);
		writer.close();
	}

	private static String getHeaderLine(String header) {
		return String.format("%s%s", header, System.lineSeparator());
	}

	private static String getTableSplitter(int size) {
		return "| -- ".repeat(Math.max(0, size)) +
				"|" + System.lineSeparator();
	}

	private static String getRows(Set<Map.Entry<ArrayList<Integer>, Integer>> rows) {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<ArrayList<Integer>, Integer> row : rows) {

			String singleRow = String.format("|  %s |  %d |" + System.lineSeparator(), row.getKey().toString().
					replaceAll(",", " | ").
					replaceAll("\\[|]", ""), row.getValue());

			builder.append(singleRow);
		}
		return builder.toString();
	}
}
