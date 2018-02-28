package main.java.org.utn.compo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.TreeMap;

public class IO {

	public TreeMap<String, LinkedList<String>> read(String fileName) {
		BufferedReader br = null;
		FileReader fr = null;
		TreeMap<String, LinkedList<String>> map = new TreeMap<String, LinkedList<String>>();
		LinkedList<String> tempuratureList;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				if (!sCurrentLine.contains("#")) {
					tempuratureList = new LinkedList<String>();
					String[] stringArray = sCurrentLine.trim().split(" ");
					String year = stringArray[0];
					for (int i = 1; i < stringArray.length; i++) {
						if (!stringArray[i].trim().equals("")) {
							tempuratureList.add(stringArray[i]);
						}
					}
					map.put(year, tempuratureList);
				}
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
		return map;
	}

	public void writeTXT(String fileName, LinkedList<String> yearList, LinkedList<String> tempList) {
		System.out.println("writing txt file");
		File fout = new File(fileName + ".txt");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			for (int i = 0; i < yearList.size(); i++) {
				bw.write(yearList.get(i) + " " + tempList.get(i));
				bw.newLine();
			}
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
