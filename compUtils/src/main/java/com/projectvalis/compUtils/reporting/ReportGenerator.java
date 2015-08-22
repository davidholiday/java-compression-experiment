package com.projectvalis.compUtils.reporting;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * methods to create reports on the efficacy of compression techniques. 
 * 
 * @author funktapuss
 *
 */
public class ReportGenerator {

	// create logger to log stuff to the log
	static Logger logger = LoggerFactory.getLogger(ReportGenerator.class);
		
	
	// hex weight values
	public static int weightZeroI = 1;
	public static int weightOneI = 2;
	public static int weightTwoI = 3;
	public static int weightThreeI = 3;
	public static int weightFourI = 4;
	public static int weightFiveI = 4;
	public static int weightSixI = 5;
	public static int weightSevenI = 5;
	public static int weightEightI = 6;
	public static int weightNineI = 6;
	public static int weightTenI = 7;
	public static int weightElevenI = 7;
	public static int weightTwelveI = 8;
	public static int weightThirteenI = 8;
	public static int weightFourteenI = 9;
	public static int weightFifteenI = 9;
	
	/**
	 * reports on the status of the currentlist with respect to proportions of
	 * elements
	 * 
	 * @param symbolAL
	 * 		the symbol list
	 * @param countAL
	 * 		the count of each symbol type in the symbol list
	 */
	public static void report(ArrayList<Character> symbolAL, 
			ArrayList<Integer> countAL) {
		
		// stage symbol count var
		int totalCountI = 0;
		
		// go through count list and figure out how many of each symbol there 
		// are, and what percent of the whole list is comprised of that 
		// particular symbol type. 
    	for (int i = 0; i < countAL.size(); i ++) {
    		String hexSymS = Integer.toHexString(i).toUpperCase();
    		logger.info("count for " + hexSymS + " is: " + countAL.get(i));
    		totalCountI += countAL.get(i);
    		
    		double percentD = ((double)countAL.get(i) / 
    							(double)symbolAL.size()) * 100.0;
    		
    		double percentRoundedD = Math.round(percentD * 100);
    		
    		percentRoundedD = percentRoundedD / 100;
    		logger.info("percent of whole is: " + percentRoundedD + "%\n");  		
    	}
    	
    	// get byte count
    	int totalSizeInBytesI = getWeight(countAL) / 8;
    	
    	// report findings in log
    	logger.info("total symbol count is: " + totalCountI);
    	logger.info("total size on disk in bytes is: " + totalSizeInBytesI);	
	}
	
	
	
	
	/**
	 * reads an arraylist<Integer> as an array of hex digits (0-15) and weights
	 * them via huffman standard code. purpose is to figure out what the total
	 * number of bits written to disk will be if the stream is huffman encoded. 
	 * 
	 * @param countAL
	 * 		list of hex char counts
	 * @return
	 * 		int representing the total number of bits required to serialize 
	 * a given dataset. 
	 */
	public static int getWeight(ArrayList<Integer> countAL) {
		
    	// get prefix bit count
    	int totalSizeI = (countAL.get(0) * weightZeroI)+ 
    					 (countAL.get(1) * weightOneI) + 
    					 (countAL.get(2) * weightTwoI) +
    					 (countAL.get(3) * weightThreeI) +
    					 (countAL.get(4) * weightFourI) + 
    					 (countAL.get(5) * weightFiveI) +
    					 (countAL.get(6) * weightSixI) +
    					 (countAL.get(7) * weightSevenI) +
    					 (countAL.get(8) * weightEightI) + 
    					 (countAL.get(9) * weightNineI) +
    					 (countAL.get(10) * weightTenI) +
    					 (countAL.get(11) * weightElevenI) + 
    					 (countAL.get(12) * weightTwelveI) +
    					 (countAL.get(13) * weightThirteenI) +
    					 (countAL.get(14) * weightFourteenI) + 
    					 (countAL.get(15) * weightFifteenI);
    	
    	return totalSizeI;
	}
	
	
	
	

	/**
	 * counts the number of instances of each hex char in the array. 
	 * returns array where each indice contains the count of its brethren 
	 * in the input array. 
	 * 
	 * @param symbolAL
	 * 		list of chars representing a dataset.
	 * @return
	 * 		list containing counts for each char symbol in symbolAL
	 */
	public static ArrayList<Integer> countInstances (
			ArrayList<Character> symbolAL) {
		
		// setup return list
		ArrayList<Integer> hexCountAL = new ArrayList<Integer>();	
		for (int a = 0; a < 16; a ++) { hexCountAL.add(0); }
		
		// do the count
		for (int i = 0; i < symbolAL.size(); i ++) {
			if (symbolAL.get(i).equals('$')) continue;
			int elemI = Integer.parseInt(symbolAL.get(i).toString(), 16);
			hexCountAL.set(elemI, hexCountAL.get(elemI) + 1);
		}

		//return to caller
		return hexCountAL;
	}
	
	
	
}
