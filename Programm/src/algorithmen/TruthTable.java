package algorithmen;

import java.util.*;

//Wahrheitstabelle

public class TruthTable {

	private String header = "";
	private int anzahlVonBedingugen = 0;

	//table is a map with keys and values
	//values as key in an Array and the condition as a key
	private final HashMap<ArrayList<Integer>, Integer> truthTable = new HashMap<>();

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public int getAnzahlVonBedingugen() {
		return anzahlVonBedingugen;
	}

	public void setAnzahlVonBedingugen(int anzahlVonBedingugen) {
		this.anzahlVonBedingugen = anzahlVonBedingugen;
	}

	public HashMap<ArrayList<Integer>, Integer> getTruthTable() {
		return truthTable;
	}
}
