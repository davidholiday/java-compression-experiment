package com.projectvalis.st1;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {

	static Logger logger = LoggerFactory.getLogger("UTIL");
	
	static int totalBitsSaved = 0;
	static int totalBandInversions = 0;

	
	/*
	 * load file as straight byte array
	 */
	public static byte[] loadFile(String fName) {
		
		try {
			DataInputStream dis = 
					new DataInputStream(new FileInputStream(fName));
			byte[] byteArr = new byte[dis.available()];
			dis.readFully(byteArr);
			dis.close();
			return byteArr;		
		} catch (IOException e) {
			System.out.println("error loading file! " + e);
		}
		
		return null;	
	}
	
	
	
	
	public static ArrayList<Character> loadFileAsHex(String fName) {
		// load file
		byte[] byteArr = loadFile(fName);
		logger.info("file size is: " + byteArr.length + " bytes");
		
		// stage some variables. using stringbuffer to reduce resource 
		// footprint
		ArrayList<Character> inHexAL = new ArrayList<Character>();
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		
		// process each byte
		// ugly ugly ugly -- you know better
		for (int i = 0; i < byteArr.length; i ++) {	
			String hexS = Integer.toHexString(byteArr[i] & 0xFF).toUpperCase();
			if (hexS.length() == 1) hexS = "0" + hexS;
			inHexAL.add(hexS.charAt(0));
			inHexAL.add(hexS.charAt(1));
		}
		
		//return to caller
		return inHexAL;
	}
	
	
	
	// assumes file is a string representation of hex
	// assumes file contains only 30/31 (00, 01)
	public static ArrayList<Character> loadFileAsStringHex(String fName) {
		// load file
		byte[] byteArr = loadFile(fName);
		logger.info("file size is: " + byteArr.length + " bytes");
		
		// stage some variables. using stringbuffer to reduce resource 
		// footprint
		ArrayList<Character> inHexAL = new ArrayList<Character>();

		
		// process each byte
		// ugly ugly ugly -- you know better
		for (int i = 0; i < byteArr.length; i ++) {	
			
			
			if ((int)byteArr[i] == 48) {
				inHexAL.add('0');
				inHexAL.add('0');
			}
			else if ((int)byteArr[i] == 49) {
				inHexAL.add('0');
				inHexAL.add('1');
			}
			else {			
				//logger.info("found something I can't parse, yo!");
				//logger.info(byteArr[i] + " " + i);
				//System.exit(8);
			}
			

		}
		
		//return to caller
		return inHexAL;
	}
	
	
	
	
	// translates a file into a random sequence of 00x0 and 01x0
	public static ArrayList<Character> genRnd01(int numBytes) {

		ArrayList<Character> inHexAL = new ArrayList<Character>();
		
		Random randy = new Random();
		int min = 0;
		int max = 1;

		for (int i = 0; i < numBytes; i ++) {	
			
			// we know the first char will always be zero
			inHexAL.add('0');
			int randomI = randy.nextInt((max - min) + 1) + min;
		
			// so now we just add the second zero, or the one
			if (randomI == 0) {
				inHexAL.add('0');
			}
			else {
				inHexAL.add('1');
			}
			
		}
		
		//return to caller
		return inHexAL;
	}
	
	
	
	
	
	
	
	/*
	 * load file as an array of base8 integers. each digit gets it's own 
	 * indice, each set of three represents one byte in the file. 
	 */
	public static ArrayList<Character> loadFileAsOctal(String fName) {
		// load file
		byte[] byteArr = loadFile(fName);
		logger.info("file size is: " + byteArr.length + " bytes");
		
		// stage some variables. using stringbuffer to reduce resource 
		// footprint
		ArrayList<Character> inOctalAL = new ArrayList<Character>();
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		
		// process each byte
		for (int i = 0; i < byteArr.length; i ++) {
			//get unsigned int representation of byte
			int unsignedByteI = byteArr[i] & 0xFF;
			
			// append it to the buffer
			sb1.append(unsignedByteI);
			
			// normally don't like to pack things in like this - hoping 
			// to avoid generating a lot of string objects that hang around
			// by having this all be in one statement. 
			sb2.append(
					Integer.toOctalString(
							Integer.parseInt(sb1.toString())));
			
			while (sb2.length() < 3) {
				sb2.insert(0, '0');
			}

			// add chars to the octal array
			for (int k = 0; k < sb2.length(); k ++) {
				inOctalAL.add(sb2.charAt(k));
			}
			
			// flush the buffers and iterate 
			sb1.delete(0, sb1.length());
			sb2.delete(0, sb2.length());
		}
		
		// report to caller
		// three bits per octal symbol
		// effectively it's now nine bits/byte now instead of eight
		int newSizeI = (inOctalAL.size() * 3) / 8;
		logger.info("file size encoded in octal is: " + newSizeI + " bytes\n");
		
		//return to caller
		return inOctalAL;
	}
	
	
	
	
	
	/*
	 * counts the number of instances of each octal char in the array. 
	 * returns array where each indice contains the count of it's brethren 
	 * in the input array. 
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

	
	
	// returns weight in bits
	public static int getWeight(ArrayList<Integer> countAL) {
		
    	// get prefix bit count
    	int totalSizeI = countAL.get(0) + 
    					 (countAL.get(1) * 2) + 
    					 (countAL.get(2) * 3) +
    					 (countAL.get(3) * 3) +
    					 (countAL.get(4) * 4) + 
    					 (countAL.get(5) * 4) +
    					 (countAL.get(6) * 5) +
    					 (countAL.get(7) * 5) +
    					 (countAL.get(8) * 6) + 
    					 (countAL.get(9) * 6) +
    					 (countAL.get(10) * 7) +
    					 (countAL.get(11) * 7) + 
    					 (countAL.get(12) * 8) +
    					 (countAL.get(13) * 8) +
    					 (countAL.get(14) * 9) + 
    					 (countAL.get(15) * 9);
    	
    	return totalSizeI;
	}
	
	
	
	
	
	// reports on the status of the current list with respect to porportions
	// of elements 
	public static void report(ArrayList<Character> symbolAL, 
			ArrayList<Integer> countAL) {
		
		int totalCountI = 0;
		
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
    	
    	// get prefix bit count
    	int totalSizeI = countAL.get(0) + 
    					 (countAL.get(1) * 2) + 
    					 (countAL.get(2) * 3) +
    					 (countAL.get(3) * 3) +
    					 (countAL.get(4) * 4) + 
    					 (countAL.get(5) * 4) +
    					 (countAL.get(6) * 5) +
    					 (countAL.get(7) * 5) +
    					 (countAL.get(8) * 6) + 
    					 (countAL.get(9) * 6) +
    					 (countAL.get(10) * 7) +
    					 (countAL.get(11) * 7) + 
    					 (countAL.get(12) * 8) +
    					 (countAL.get(13) * 8) +
    					 (countAL.get(14) * 9) + 
    					 (countAL.get(15) * 9);
    	
    	// ensure everything is in bytes
//    	int metaDataSizeI = (
//    			(symbolAL.size() / Bootstrap.DESIRED_BAND_SIZE) * 128) / 8;
    	
    	// get byte count
    	totalSizeI /= 8;
    	
    	logger.info("total symbol count is: " + totalCountI);
//    	logger.info(
//    		"estimated meta-data size in bytes is (un prefixed) is: " + 
//    			metaDataSizeI);
//    	logger.info("swap count is: " + Bootstrap.swapCount);
    	logger.info("total size on disk in bytes is: " + totalSizeI);
    	logger.info("NOTE - total size accounts for only first 8 elements! "
    			+ " may not be accurate beyond that!!");
    	
		
	}
	
	
	
	
	
	// used for testing only
	// egad this is jenky...
//	public static ArrayList<Integer> fakeBand(ArrayList<Character> asOctalAL) {
//		int bandSizeI = asOctalAL.size() / 100000;
//		logger.info("bandsizeI is: " + bandSizeI);
//		int fromI = 0;
//		int toI = bandSizeI;
//		
//		ArrayList<Integer> newCountAL = new ArrayList<Integer>();
//		ArrayList<Integer> bufferAL = new ArrayList<Integer>();
//		for (int i = 0; i < 8; i ++) { newCountAL.add(0); }
//		
//		for (int i = 0; i < 100000; i ++) {	
//			
//			ArrayList<Character> subList = 
//					new ArrayList<Character>(asOctalAL.subList(fromI, toI));
//			
//			bufferAL = countInstances(subList);
//			Collections.sort(bufferAL);
//					
//			for (int a = 0; a < 8; a ++) {
//				newCountAL.set(a, newCountAL.get(a) + bufferAL.get(a));
//			}
//			
//			fromI = toI -1;
//			toI += bandSizeI;
//		}
//		
//		//hit up the remainder
//		Collections.sort(asOctalAL.subList(fromI, asOctalAL.size()));
//		return newCountAL;
//	}
	
	
	
	
	
	
	// TODO do better than bubble sort
	// make less wonky
	public static ArrayList<Character> bandPass(
			ArrayList<Character> symbolAL, int numBandsI) throws IOException {
		
		// :-( !
		ArrayList<Character>invertList = new ArrayList<Character>();
		
		ArrayList<Character> returnAL = new ArrayList<Character>();
//System.out.print("\nasOctalAL.size is: " + asOctalAL.size());		
		int bandSizeI = symbolAL.size() / numBandsI;	
		int bandRemainderI = symbolAL.size() % numBandsI;
		logger.info("numBandsI is: " + numBandsI);
		logger.info("bandsizeI is: " + bandSizeI);
		logger.info("bandRemainderI is: " + bandRemainderI + "\n");
		
		int fromI = 0;
		int toI = bandSizeI;	
		ArrayList<Integer> bandCountAL = new ArrayList<Integer>();
		ArrayList<Character> subList;
		
		for (int i = 0; i < numBandsI; i ++) {	
			subList = new ArrayList<Character>(symbolAL.subList(fromI, toI));
			bandCountAL = countInstances(subList);
			
			
			
			
			//
			//
			// * to test band inversion theory *
			// make second countlist for compare
			ArrayList<Integer> bandCount2 = 
					(ArrayList<Integer>) bandCountAL.clone();
			
			for (int p = 0; p < 8; p ++) {
				int temp1 = bandCountAL.get(p);
				int temp2 = bandCountAL.get(p + 8);
				bandCount2.set(p, temp2);
				bandCount2.set(p + 8, temp1);			
			}
			
			int weight1 = getWeight(bandCountAL);
			int weight2 = getWeight(bandCount2);
//System.out.println(weight1 + " " + weight2 + " " + (weight1 - weight2));			
			if (weight1 - weight2 > 1) {

				totalBitsSaved += (weight1 - weight2);
				totalBandInversions ++;
				invertList.add('1');
//Util2.printList(subList);
				//returnAL.addAll(invertList(subList));
				invertList(subList);
//Util2.printList(subList);				
				returnAL.addAll(subList);
			}
			else {
				invertList.add('0');
				returnAL.addAll(subList);
			}
			// * end band inversion test *
			//
			//
						
			
			
				
			// update indicies and iterate
			fromI = toI;
			toI += bandSizeI;

			
			
		}
	
		//hit up the remainder
		


		fromI = symbolAL.size() - bandRemainderI;
//System.out.println( " ** " + returnAL.size() + " " + fromI + " " + asOctalAL.size());			
		subList = new ArrayList<Character>(
					symbolAL.subList(fromI, symbolAL.size()));
		bandCountAL = countInstances(subList);
		
		
		
		
		//
		//
		// * to test band inversion theory *
		// make second countlist for compare
		ArrayList<Integer> bandCount2 = 
				(ArrayList<Integer>) bandCountAL.clone();
		
		for (int p = 0; p < 8; p ++) {
			int temp1 = bandCountAL.get(p);
			int temp2 = bandCountAL.get(p + 8);
			bandCount2.set(p, temp2);
			bandCount2.set(p + 8, temp1);			
		}
		
		int weight1 = getWeight(bandCountAL);
		int weight2 = getWeight(bandCount2);
		
		if (weight1 - weight2 > 1) {
//System.out.println(weight1 + " " + weight2 + " " + (weight1 - weight2));
			totalBitsSaved += (weight1 - weight2);
			totalBandInversions ++;
			//returnAL.addAll(invertList(subList));
			invertList(subList);
			returnAL.addAll(subList);
		}
		else {
			returnAL.addAll(subList);
		}
		// * end band inversion test *
		//
		//
		
	
		
		//returnAL.addAll(bubbleSort(subList, bandCountAL));
//System.out.println(" returnAL size is: " + returnAL.size() + "\n");	

		
		// add delimiter 
//		for (int p = 0; p < 8; p ++) {
//			invertList.add('$');
//		}
//		
//
//		// add metadata
//		returnAL.addAll(0, invertList);
		
		return returnAL;
		
	}
	
	
	
	
	
	
	// make less wonky
	public static ArrayList<Character> bandPass4(
			ArrayList<Character> symbolAL, int numBandsI) throws IOException {
		
		// :-( !
		ArrayList<Character>invertList = new ArrayList<Character>();
		
		ArrayList<Character> returnAL = new ArrayList<Character>();
//System.out.print("\nasOctalAL.size is: " + asOctalAL.size());		
		int bandSizeI = symbolAL.size() / numBandsI;	
		int bandRemainderI = symbolAL.size() % numBandsI;
		logger.info("numBandsI is: " + numBandsI);
		logger.info("bandsizeI is: " + bandSizeI);
		logger.info("bandRemainderI is: " + bandRemainderI + "\n");
		
		int fromI = 0;
		int toI = bandSizeI;	
		ArrayList<Integer> bandCountAL = new ArrayList<Integer>();
		ArrayList<Character> subList;
		
		for (int i = 0; i < numBandsI; i ++) {	
			subList = new ArrayList<Character>(symbolAL.subList(fromI, toI));
			bandCountAL = countInstances(subList);
			
			
			
			
			//
			//
			// * to test band inversion theory *
			// make second countlist for compare
			ArrayList<Integer> bandCount2 = 
					(ArrayList<Integer>) bandCountAL.clone();
			
			for (int p = 0; p < 4; p ++) {
				int temp1 = bandCountAL.get(p);
				int temp2 = bandCountAL.get(p + 4);
				bandCount2.set(p, temp2);
				bandCount2.set(p + 4, temp1);			
			}
			
			int weight1 = getWeight(bandCountAL);
			int weight2 = getWeight(bandCount2);
//System.out.println(weight1 + " " + weight2 + " " + (weight1 - weight2));			
			if (weight1 - weight2 > 1) {

				totalBitsSaved += (weight1 - weight2);
				totalBandInversions ++;
				invertList.add('1');
//Util2.printList(subList);
				//returnAL.addAll(invertList(subList));
				invertList4(subList);
//Util2.printList(subList);				
				returnAL.addAll(subList);
			}
			else {
				invertList.add('0');
				returnAL.addAll(subList);
			}
			// * end band inversion test *
			//
			//
						
			
			
				
			// update indicies and iterate
			fromI = toI;
			toI += bandSizeI;

			
			
		}
	
		//hit up the remainder
		


		fromI = symbolAL.size() - bandRemainderI;
//System.out.println( " ** " + returnAL.size() + " " + fromI + " " + asOctalAL.size());			
		subList = new ArrayList<Character>(
					symbolAL.subList(fromI, symbolAL.size()));
		bandCountAL = countInstances(subList);
		
		
		
		
		//
		//
		// * to test band inversion theory *
		// make second countlist for compare
		ArrayList<Integer> bandCount2 = 
				(ArrayList<Integer>) bandCountAL.clone();
		
		for (int p = 0; p < 4; p ++) {
			int temp1 = bandCountAL.get(p);
			int temp2 = bandCountAL.get(p + 4);
			bandCount2.set(p, temp2);
			bandCount2.set(p + 4, temp1);			
		}
		
		int weight1 = getWeight(bandCountAL);
		int weight2 = getWeight(bandCount2);
		
		if (weight1 - weight2 > 1) {
//System.out.println(weight1 + " " + weight2 + " " + (weight1 - weight2));
			totalBitsSaved += (weight1 - weight2);
			totalBandInversions ++;
			//returnAL.addAll(invertList(subList));
			invertList4(subList);
			returnAL.addAll(subList);
		}
		else {
			returnAL.addAll(subList);
		}
		// * end band inversion test *
		//
		//
		
	
		
		//returnAL.addAll(bubbleSort(subList, bandCountAL));
//System.out.println(" returnAL size is: " + returnAL.size() + "\n");	

		
		// add delimiter 
//		for (int p = 0; p < 8; p ++) {
//			invertList.add('$');
//		}
//		
//
//		// add metadata
//		returnAL.addAll(0, invertList);
		
		return returnAL;
		
	}
	
	
	
	
	
	
	
	// make less wonky
	public static ArrayList<Character> bandPass2(
			ArrayList<Character> symbolAL, int numBandsI) throws IOException {
		
		// :-( !
		ArrayList<Character>invertList = new ArrayList<Character>();
		
		ArrayList<Character> returnAL = new ArrayList<Character>();
//System.out.print("\nasOctalAL.size is: " + asOctalAL.size());		
		int bandSizeI = symbolAL.size() / numBandsI;	
		int bandRemainderI = symbolAL.size() % numBandsI;
		logger.info("numBandsI is: " + numBandsI);
		logger.info("bandsizeI is: " + bandSizeI);
		logger.info("bandRemainderI is: " + bandRemainderI + "\n");
		
		int fromI = 0;
		int toI = bandSizeI;	
		ArrayList<Integer> bandCountAL = new ArrayList<Integer>();
		ArrayList<Character> subList;
		
		for (int i = 0; i < numBandsI; i ++) {	
			subList = new ArrayList<Character>(symbolAL.subList(fromI, toI));
			bandCountAL = countInstances(subList);
			
			
			
			
			//
			//
			// * to test band inversion theory *
			// make second countlist for compare
			ArrayList<Integer> bandCount2 = 
					(ArrayList<Integer>) bandCountAL.clone();
			
			for (int p = 0; p < 2; p ++) {
				int temp1 = bandCountAL.get(p);
				int temp2 = bandCountAL.get(p + 2);
				bandCount2.set(p, temp2);
				bandCount2.set(p + 2, temp1);			
			}
//System.out.println("***");
//Util2.printList(bandCountAL);
//System.out.println("");
//Util2.printList(bandCount2);

			int weight1 = getWeight(bandCountAL);
			int weight2 = getWeight(bandCount2);
//System.out.println(weight1 + " " + weight2 + " " + (weight1 - weight2));

			if (weight1 - weight2 >= 1) {

				totalBitsSaved += (weight1 - weight2);
				totalBandInversions ++;
				invertList.add('1');
//Util2.printList(subList);
//System.out.println("");
				//returnAL.addAll(invertList(subList));
				invertList2(subList);
//Util2.printList(subList);	
//System.out.println("***");
				returnAL.addAll(subList);
			}
			else {
				invertList.add('0');
				returnAL.addAll(subList);
			}
			// * end band inversion test *
			//
			//
						
			
			
				
			// update indicies and iterate
			fromI = toI;
			toI += bandSizeI;

			
			
		}
	
		//hit up the remainder
		


		fromI = symbolAL.size() - bandRemainderI;
//System.out.println( " ** " + returnAL.size() + " " + fromI + " " + asOctalAL.size());			
		subList = new ArrayList<Character>(
					symbolAL.subList(fromI, symbolAL.size()));
		bandCountAL = countInstances(subList);
		
		
		
		
		//
		//
		// * to test band inversion theory *
		// make second countlist for compare
		ArrayList<Integer> bandCount2 = 
				(ArrayList<Integer>) bandCountAL.clone();
		
		for (int p = 0; p < 2; p ++) {
			int temp1 = bandCountAL.get(p);
			int temp2 = bandCountAL.get(p + 2);
			bandCount2.set(p, temp2);
			bandCount2.set(p + 2, temp1);			
		}

		int weight1 = getWeight(bandCountAL);
		int weight2 = getWeight(bandCount2);
		
		if (weight1 - weight2 >= 1) {
//System.out.println(weight1 + " " + weight2 + " " + (weight1 - weight2));
			totalBitsSaved += (weight1 - weight2);
			totalBandInversions ++;
			//returnAL.addAll(invertList(subList));
			invertList2(subList);
			returnAL.addAll(subList);
		}
		else {
			returnAL.addAll(subList);
		}
		// * end band inversion test *
		//
		//
		
	
		
		//returnAL.addAll(bubbleSort(subList, bandCountAL));
//System.out.println(" returnAL size is: " + returnAL.size() + "\n");	

		
		// add delimiter 
//		for (int p = 0; p < 8; p ++) {
//			invertList.add('$');
//		}
//		
//
//		// add metadata
//		returnAL.addAll(0, invertList);
		
		return returnAL;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	// makes all the symbols (8-15) into (0-7)s and visa versa
	public static void /*ArrayList<Character> */
		invertList (ArrayList<Character> sublist) {
//Util2.printList(sublist);
//System.out.println(" ");

		for (int i = 0; i < sublist.size(); i ++) {
			int curValI = Integer.parseInt(sublist.get(i).toString(), 16);
			int newValI = (curValI > 7) ? (curValI - 8) : (curValI + 8);
			sublist.set(i, 
					Integer.toHexString(newValI).toUpperCase().charAt(0));
		}
		
//Util2.printList(sublist);
//System.exit(0);
		//return sublist;

	}
	
	
	
	
	// makes all the symbols (8-15) into (0-7)s and visa versa
	public static void /*ArrayList<Character> */
		invertList4 (ArrayList<Character> sublist) {
//Util2.printList(sublist);
//System.out.println(" ");

		for (int i = 0; i < sublist.size(); i ++) {
			int curValI = Integer.parseInt(sublist.get(i).toString(), 16);
			int newValI = (curValI > 3) ? (curValI - 4) : (curValI + 4);
			sublist.set(i, 
					Integer.toHexString(newValI).toUpperCase().charAt(0));
		}
		
//Util2.printList(sublist);
//System.exit(0);
		//return sublist;

	}
	
	
	
	// makes all the symbols (8-15) into (0-7)s and visa versa
	public static void /*ArrayList<Character> */
		invertList2 (ArrayList<Character> sublist) {
//Util2.printList(sublist);
//System.out.println(" ");

		for (int i = 0; i < sublist.size(); i ++) {
			int curValI = Integer.parseInt(sublist.get(i).toString(), 16);
			int newValI = (curValI > 1) ? (curValI - 2) : (curValI + 2);
			sublist.set(i, 
					Integer.toHexString(newValI).toUpperCase().charAt(0));
		}
		
//Util2.printList(sublist);
//System.exit(0);
		//return sublist;

	}
	
	
	
	
	
	public static ArrayList<Character> bubbleSort(
			ArrayList<Character> subList, ArrayList<Integer> bandCountAL) {
		
		boolean doneB = false;
		
		while (!doneB) {
			doneB = true;
	
			for (int a = 0; a < bandCountAL.size() - 1; a ++) {
// System.out.println("** " + a + " **");
				int val1I = bandCountAL.get(a);
				int val2I = bandCountAL.get(a + 1);
				
//for (int k = 0; k < bandCountAL.size(); k ++) {
//	System.out.print(bandCountAL.get(k));						
//}
//System.out.println(" ");
				
				if (val2I > val1I) {	
					Bootstrap.swapCount ++;
					doneB = false;
					bandCountAL.set(a, val2I);
					bandCountAL.set(a + 1, val1I);
					
//System.out.println("\nin swap");
//for (int k = 0; k < bandCountAL.size(); k ++) {
//	System.out.print(bandCountAL.get(k));					
//}
//System.out.println("\n");
//	System.exit(0);
					
					swapSubListChars(subList, a, a+1);					
				}
						
			}		
// System.out.println("done is: " + doneB);	
		}
//System.out.print("b> " );
//for (int k = 0; k < bandCountAL.size(); k ++) {
//	System.out.print(bandCountAL.get(k));
//}
//
//System.out.print("\ns> " );
//for (int k = 0; k < subList.size(); k ++) {
//	System.out.print(subList.get(k));
//}
//	
//System.out.println("\n");
		
		return subList;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public static ArrayList<Character> swapSubListChars (
			ArrayList<Character> listIn, int val1I, int val2I) {
		
		char val1C = Integer.toString(val1I, 16).charAt(0);
		char val2C = Integer.toString(val2I, 16).charAt(0);
		
		for (int i = 0; i < listIn.size(); i ++) {

//System.out.print("** sublist size is: " + listIn.size());			
//for (int k = 0; k < listIn.size(); k ++) {
//	System.out.print(listIn.get(k));
//}
//System.out.println("\n***");		
//			
//System.out.println(listIn.get(i) + " " + val1I + " " + val2I);
			
			int listValI = Integer.parseInt(listIn.get(i).toString(), 16);
			
			if (listValI == val1I) {
				listIn.set(i, val2C);
//System.out.println("swap1");
			}
			else if (listValI == val2I) {
				listIn.set(i, val1C);
//System.out.println("swap2");
			}
//System.out.print("** sublist size is: " + listIn.size() + "\n");				
//			
//for (int k = 0; k < listIn.size(); k ++) {
//	System.out.print(listIn.get(k));
//	
//	if (i == 4) {System.exit(0);}
//}
//System.out.println("\n+++");			

			

		}
		
		return listIn;
		
	}
	
	
	
	
	
	
	
	
	
	// make less wonky
	public static ArrayList<Character> bandPass_invertOnes(
			ArrayList<Character> symbolAL, int numBandsI) throws IOException {
		
		
		ArrayList<Character> returnAL = new ArrayList<Character>();
		int bandSizeI = symbolAL.size() / numBandsI;	
		int bandRemainderI = symbolAL.size() % numBandsI;
		logger.info("numBandsI is: " + numBandsI);
		logger.info("bandsizeI is: " + bandSizeI);
		logger.info("bandRemainderI is: " + bandRemainderI + "\n");
		
		int fromI = 0;
		int toI = bandSizeI;	
		ArrayList<Integer> bandCountAL = new ArrayList<Integer>();
		ArrayList<Character> subList;
		
		for (int i = 0; i < numBandsI; i ++) {	
			subList = new ArrayList<Character>(symbolAL.subList(fromI, toI));
	
			String tempS = new String();
			for (int k = 0 ; k < subList.size(); k ++) {
				tempS = tempS + subList.get(k).toString();
			}
			
//System.out.println("tempS is: " + tempS);
			
			if (tempS.equals("0101")) {
				subList.set(1, '0');
				subList.set(3, '0');
			}
				
			// update indicies and iterate
			fromI = toI;
			toI += bandSizeI;

			returnAL.addAll(subList);
			
		}
	
		//hit up the remainder
		

		
		
		

		
		return returnAL;
		
	}
	
	
	
	
	
	
	
}




