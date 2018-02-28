package main.java.org.utn.compo;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import main.java.org.utn.compo.IO;

public class Main {

	public static void main(String[] args) {
		Options options = new Options();
		options.addOption("f", true, "file name");
		options.addOption("y", true, "year range");
		options.addOption("o", true, "output txt");

		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine cmd = parser.parse(options, args);
			String fileName = null;
			String yearRange = null;
			String outputFileName = null;
			if (cmd.hasOption("f")) {
				fileName = cmd.getOptionValue("f");
			} else {
				System.err.println("file name required");
			}

			if (cmd.hasOption("y")) {
				yearRange = cmd.getOptionValue("y");
			} else {
				System.err.println("year range required");
			}

			if (cmd.hasOption("o")) {
				outputFileName = cmd.getOptionValue("o");
			}

			if (fileName != null && yearRange != null) {
				IO file = new IO();
				TreeMap<String, LinkedList<String>> map = file.read(fileName);
				map = filterYearRange(map, yearRange);
				LinkedList<String> yearList = new LinkedList<String>();
				LinkedList<String> tempList = new LinkedList<String>();
				double increment = 0.083;
				double inrc = 0;
				double keyDouble = 0;
				LinkedList<String> value;
				for (String key : map.keySet()) {
					keyDouble = Double.parseDouble(key);
					value = map.get(key);
					for (int i = 0; i < value.size(); i++) {
						inrc = (i * increment);
						yearList.add(keyDouble + inrc + "");
						tempList.add(value.get(i));
					}
				}
				file.writeTXT(outputFileName, yearList, tempList);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static TreeMap<String, LinkedList<String>> filterYearRange(TreeMap<String, LinkedList<String>> yearMap,
			String yearRange) {
		TreeMap<String, LinkedList<String>> map = new TreeMap<String, LinkedList<String>>();
		String[] yearRangeArray = yearRange.split("-");
		String yearOneString = yearRangeArray[0];
		String yearTwoString = yearRangeArray[1];
		int yearOne = Integer.parseInt(yearOneString);
		int yearTwo = Integer.parseInt(yearTwoString);
		int year = 0;
		Set<String> set = yearMap.keySet();
		for (String key : set) {
			year = Integer.parseInt(key);
			if (year >= yearOne && year <= yearTwo) {
				map.put(key, yearMap.get(key));
			}
		}
		return map;
	}

}
