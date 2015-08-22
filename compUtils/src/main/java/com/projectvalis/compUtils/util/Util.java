package com.projectvalis.compUtils.util;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.compUtils.runner.Bootstrap;

public class Util {

	static Logger logger = LoggerFactory.getLogger("UTIL");
	
	static int totalBitsSaved = 0;
	static int totalBandInversions = 0;


	
	
	
	/**
	 * generates a random stream of hex characters comprised of 0x00 and 0x01
	 * 
	 * @param numBytes
	 * @return
	 */
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
	
	
	
//	
//	
//	
//	// TODO do better than bubble sort
//	// make less wonky
//	public static ArrayList<Character> bandPass(
//			ArrayList<Character> symbolAL, int numBandsI) throws IOException {
//		
//		// :-( !
//		ArrayList<Character>invertList = new ArrayList<Character>();
//		
//		ArrayList<Character> returnAL = new ArrayList<Character>();
////System.out.print("\nasOctalAL.size is: " + asOctalAL.size());		
//		int bandSizeI = symbolAL.size() / numBandsI;	
//		int bandRemainderI = symbolAL.size() % numBandsI;
//		logger.info("numBandsI is: " + numBandsI);
//		logger.info("bandsizeI is: " + bandSizeI);
//		logger.info("bandRemainderI is: " + bandRemainderI + "\n");
//		
//		int fromI = 0;
//		int toI = bandSizeI;	
//		ArrayList<Integer> bandCountAL = new ArrayList<Integer>();
//		ArrayList<Character> subList;
//		
//		for (int i = 0; i < numBandsI; i ++) {	
//			subList = new ArrayList<Character>(symbolAL.subList(fromI, toI));
//			bandCountAL = countInstances(subList);
//			
//			
//			
//			
//			//
//			//
//			// * to test band inversion theory *
//			// make second countlist for compare
//			ArrayList<Integer> bandCount2 = 
//					(ArrayList<Integer>) bandCountAL.clone();
//			
//			for (int p = 0; p < 8; p ++) {
//				int temp1 = bandCountAL.get(p);
//				int temp2 = bandCountAL.get(p + 8);
//				bandCount2.set(p, temp2);
//				bandCount2.set(p + 8, temp1);			
//			}
//			
//			int weight1 = getWeight(bandCountAL);
//			int weight2 = getWeight(bandCount2);
////System.out.println(weight1 + " " + weight2 + " " + (weight1 - weight2));			
//			if (weight1 - weight2 > 1) {
//
//				totalBitsSaved += (weight1 - weight2);
//				totalBandInversions ++;
//				invertList.add('1');
////Util2.printList(subList);
//				//returnAL.addAll(invertList(subList));
//				invertList(subList);
////Util2.printList(subList);				
//				returnAL.addAll(subList);
//			}
//			else {
//				invertList.add('0');
//				returnAL.addAll(subList);
//			}
//			// * end band inversion test *
//			//
//			//
//						
//			
//			
//				
//			// update indicies and iterate
//			fromI = toI;
//			toI += bandSizeI;
//
//			
//			
//		}
//	
//		//hit up the remainder
//		
//
//
//		fromI = symbolAL.size() - bandRemainderI;
////System.out.println( " ** " + returnAL.size() + " " + fromI + " " + asOctalAL.size());			
//		subList = new ArrayList<Character>(
//					symbolAL.subList(fromI, symbolAL.size()));
//		bandCountAL = countInstances(subList);
//		
//		
//		
//		
//		//
//		//
//		// * to test band inversion theory *
//		// make second countlist for compare
//		ArrayList<Integer> bandCount2 = 
//				(ArrayList<Integer>) bandCountAL.clone();
//		
//		for (int p = 0; p < 8; p ++) {
//			int temp1 = bandCountAL.get(p);
//			int temp2 = bandCountAL.get(p + 8);
//			bandCount2.set(p, temp2);
//			bandCount2.set(p + 8, temp1);			
//		}
//		
//		int weight1 = getWeight(bandCountAL);
//		int weight2 = getWeight(bandCount2);
//		
//		if (weight1 - weight2 > 1) {
////System.out.println(weight1 + " " + weight2 + " " + (weight1 - weight2));
//			totalBitsSaved += (weight1 - weight2);
//			totalBandInversions ++;
//			//returnAL.addAll(invertList(subList));
//			invertList(subList);
//			returnAL.addAll(subList);
//		}
//		else {
//			returnAL.addAll(subList);
//		}
//		// * end band inversion test *
//		//
//		//
//		
//	
//		
//		//returnAL.addAll(bubbleSort(subList, bandCountAL));
////System.out.println(" returnAL size is: " + returnAL.size() + "\n");	
//
//		
//		// add delimiter 
////		for (int p = 0; p < 8; p ++) {
////			invertList.add('$');
////		}
////		
////
////		// add metadata
////		returnAL.addAll(0, invertList);
//		
//		return returnAL;
//		
//	}
//	
//	
//	
//	
//	
//	
//	// make less wonky
//	public static ArrayList<Character> bandPass4(
//			ArrayList<Character> symbolAL, int numBandsI) throws IOException {
//		
//		// :-( !
//		ArrayList<Character>invertList = new ArrayList<Character>();
//		
//		ArrayList<Character> returnAL = new ArrayList<Character>();
////System.out.print("\nasOctalAL.size is: " + asOctalAL.size());		
//		int bandSizeI = symbolAL.size() / numBandsI;	
//		int bandRemainderI = symbolAL.size() % numBandsI;
//		logger.info("numBandsI is: " + numBandsI);
//		logger.info("bandsizeI is: " + bandSizeI);
//		logger.info("bandRemainderI is: " + bandRemainderI + "\n");
//		
//		int fromI = 0;
//		int toI = bandSizeI;	
//		ArrayList<Integer> bandCountAL = new ArrayList<Integer>();
//		ArrayList<Character> subList;
//		
//		for (int i = 0; i < numBandsI; i ++) {	
//			subList = new ArrayList<Character>(symbolAL.subList(fromI, toI));
//			bandCountAL = countInstances(subList);
//			
//			
//			
//			
//			//
//			//
//			// * to test band inversion theory *
//			// make second countlist for compare
//			ArrayList<Integer> bandCount2 = 
//					(ArrayList<Integer>) bandCountAL.clone();
//			
//			for (int p = 0; p < 4; p ++) {
//				int temp1 = bandCountAL.get(p);
//				int temp2 = bandCountAL.get(p + 4);
//				bandCount2.set(p, temp2);
//				bandCount2.set(p + 4, temp1);			
//			}
//			
//			int weight1 = getWeight(bandCountAL);
//			int weight2 = getWeight(bandCount2);
////System.out.println(weight1 + " " + weight2 + " " + (weight1 - weight2));			
//			if (weight1 - weight2 > 1) {
//
//				totalBitsSaved += (weight1 - weight2);
//				totalBandInversions ++;
//				invertList.add('1');
////Util2.printList(subList);
//				//returnAL.addAll(invertList(subList));
//				invertList4(subList);
////Util2.printList(subList);				
//				returnAL.addAll(subList);
//			}
//			else {
//				invertList.add('0');
//				returnAL.addAll(subList);
//			}
//			// * end band inversion test *
//			//
//			//
//						
//			
//			
//				
//			// update indicies and iterate
//			fromI = toI;
//			toI += bandSizeI;
//
//			
//			
//		}
//	
//		//hit up the remainder
//		
//
//
//		fromI = symbolAL.size() - bandRemainderI;
////System.out.println( " ** " + returnAL.size() + " " + fromI + " " + asOctalAL.size());			
//		subList = new ArrayList<Character>(
//					symbolAL.subList(fromI, symbolAL.size()));
//		bandCountAL = countInstances(subList);
//		
//		
//		
//		
//		//
//		//
//		// * to test band inversion theory *
//		// make second countlist for compare
//		ArrayList<Integer> bandCount2 = 
//				(ArrayList<Integer>) bandCountAL.clone();
//		
//		for (int p = 0; p < 4; p ++) {
//			int temp1 = bandCountAL.get(p);
//			int temp2 = bandCountAL.get(p + 4);
//			bandCount2.set(p, temp2);
//			bandCount2.set(p + 4, temp1);			
//		}
//		
//		int weight1 = getWeight(bandCountAL);
//		int weight2 = getWeight(bandCount2);
//		
//		if (weight1 - weight2 > 1) {
////System.out.println(weight1 + " " + weight2 + " " + (weight1 - weight2));
//			totalBitsSaved += (weight1 - weight2);
//			totalBandInversions ++;
//			//returnAL.addAll(invertList(subList));
//			invertList4(subList);
//			returnAL.addAll(subList);
//		}
//		else {
//			returnAL.addAll(subList);
//		}
//		// * end band inversion test *
//		//
//		//
//		
//	
//		
//		//returnAL.addAll(bubbleSort(subList, bandCountAL));
////System.out.println(" returnAL size is: " + returnAL.size() + "\n");	
//
//		
//		// add delimiter 
////		for (int p = 0; p < 8; p ++) {
////			invertList.add('$');
////		}
////		
////
////		// add metadata
////		returnAL.addAll(0, invertList);
//		
//		return returnAL;
//		
//	}
//	
//	
//	
//	
//	
//	
//	
//	// make less wonky
//	public static ArrayList<Character> bandPass2(
//			ArrayList<Character> symbolAL, int numBandsI) throws IOException {
//		
//		// :-( !
//		ArrayList<Character>invertList = new ArrayList<Character>();
//		
//		ArrayList<Character> returnAL = new ArrayList<Character>();
////System.out.print("\nasOctalAL.size is: " + asOctalAL.size());		
//		int bandSizeI = symbolAL.size() / numBandsI;	
//		int bandRemainderI = symbolAL.size() % numBandsI;
//		logger.info("numBandsI is: " + numBandsI);
//		logger.info("bandsizeI is: " + bandSizeI);
//		logger.info("bandRemainderI is: " + bandRemainderI + "\n");
//		
//		int fromI = 0;
//		int toI = bandSizeI;	
//		ArrayList<Integer> bandCountAL = new ArrayList<Integer>();
//		ArrayList<Character> subList;
//		
//		for (int i = 0; i < numBandsI; i ++) {	
//			subList = new ArrayList<Character>(symbolAL.subList(fromI, toI));
//			bandCountAL = countInstances(subList);
//			
//			
//			
//			
//			//
//			//
//			// * to test band inversion theory *
//			// make second countlist for compare
//			ArrayList<Integer> bandCount2 = 
//					(ArrayList<Integer>) bandCountAL.clone();
//			
//			for (int p = 0; p < 2; p ++) {
//				int temp1 = bandCountAL.get(p);
//				int temp2 = bandCountAL.get(p + 2);
//				bandCount2.set(p, temp2);
//				bandCount2.set(p + 2, temp1);			
//			}
////System.out.println("***");
////Util2.printList(bandCountAL);
////System.out.println("");
////Util2.printList(bandCount2);
//
//			int weight1 = getWeight(bandCountAL);
//			int weight2 = getWeight(bandCount2);
////System.out.println(weight1 + " " + weight2 + " " + (weight1 - weight2));
//
//			if (weight1 - weight2 >= 1) {
//
//				totalBitsSaved += (weight1 - weight2);
//				totalBandInversions ++;
//				invertList.add('1');
////Util2.printList(subList);
////System.out.println("");
//				//returnAL.addAll(invertList(subList));
//				invertList2(subList);
////Util2.printList(subList);	
////System.out.println("***");
//				returnAL.addAll(subList);
//			}
//			else {
//				invertList.add('0');
//				returnAL.addAll(subList);
//			}
//			// * end band inversion test *
//			//
//			//
//						
//			
//			
//				
//			// update indicies and iterate
//			fromI = toI;
//			toI += bandSizeI;
//
//			
//			
//		}
//	
//		//hit up the remainder
//		
//
//
//		fromI = symbolAL.size() - bandRemainderI;
////System.out.println( " ** " + returnAL.size() + " " + fromI + " " + asOctalAL.size());			
//		subList = new ArrayList<Character>(
//					symbolAL.subList(fromI, symbolAL.size()));
//		bandCountAL = countInstances(subList);
//		
//		
//		
//		
//		//
//		//
//		// * to test band inversion theory *
//		// make second countlist for compare
//		ArrayList<Integer> bandCount2 = 
//				(ArrayList<Integer>) bandCountAL.clone();
//		
//		for (int p = 0; p < 2; p ++) {
//			int temp1 = bandCountAL.get(p);
//			int temp2 = bandCountAL.get(p + 2);
//			bandCount2.set(p, temp2);
//			bandCount2.set(p + 2, temp1);			
//		}
//
//		int weight1 = getWeight(bandCountAL);
//		int weight2 = getWeight(bandCount2);
//		
//		if (weight1 - weight2 >= 1) {
////System.out.println(weight1 + " " + weight2 + " " + (weight1 - weight2));
//			totalBitsSaved += (weight1 - weight2);
//			totalBandInversions ++;
//			//returnAL.addAll(invertList(subList));
//			invertList2(subList);
//			returnAL.addAll(subList);
//		}
//		else {
//			returnAL.addAll(subList);
//		}
//		// * end band inversion test *
//		//
//		//
//		
//	
//		
//		//returnAL.addAll(bubbleSort(subList, bandCountAL));
////System.out.println(" returnAL size is: " + returnAL.size() + "\n");	
//
//		
//		// add delimiter 
////		for (int p = 0; p < 8; p ++) {
////			invertList.add('$');
////		}
////		
////
////		// add metadata
////		returnAL.addAll(0, invertList);
//		
//		return returnAL;
//		
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	

//	
//	
//	
//	
//	
//	public static ArrayList<Character> bubbleSort(
//			ArrayList<Character> subList, ArrayList<Integer> bandCountAL) {
//		
//		boolean doneB = false;
//		
//		while (!doneB) {
//			doneB = true;
//	
//			for (int a = 0; a < bandCountAL.size() - 1; a ++) {
//// System.out.println("** " + a + " **");
//				int val1I = bandCountAL.get(a);
//				int val2I = bandCountAL.get(a + 1);
//				
////for (int k = 0; k < bandCountAL.size(); k ++) {
////	System.out.print(bandCountAL.get(k));						
////}
////System.out.println(" ");
//				
//				if (val2I > val1I) {	
//					Bootstrap.swapCount ++;
//					doneB = false;
//					bandCountAL.set(a, val2I);
//					bandCountAL.set(a + 1, val1I);
//					
////System.out.println("\nin swap");
////for (int k = 0; k < bandCountAL.size(); k ++) {
////	System.out.print(bandCountAL.get(k));					
////}
////System.out.println("\n");
////	System.exit(0);
//					
//					swapSubListChars(subList, a, a+1);					
//				}
//						
//			}		
//// System.out.println("done is: " + doneB);	
//		}
////System.out.print("b> " );
////for (int k = 0; k < bandCountAL.size(); k ++) {
////	System.out.print(bandCountAL.get(k));
////}
////
////System.out.print("\ns> " );
////for (int k = 0; k < subList.size(); k ++) {
////	System.out.print(subList.get(k));
////}
////	
////System.out.println("\n");
//		
//		return subList;
//		
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	public static ArrayList<Character> swapSubListChars (
//			ArrayList<Character> listIn, int val1I, int val2I) {
//		
//		char val1C = Integer.toString(val1I, 16).charAt(0);
//		char val2C = Integer.toString(val2I, 16).charAt(0);
//		
//		for (int i = 0; i < listIn.size(); i ++) {
//
////System.out.print("** sublist size is: " + listIn.size());			
////for (int k = 0; k < listIn.size(); k ++) {
////	System.out.print(listIn.get(k));
////}
////System.out.println("\n***");		
////			
////System.out.println(listIn.get(i) + " " + val1I + " " + val2I);
//			
//			int listValI = Integer.parseInt(listIn.get(i).toString(), 16);
//			
//			if (listValI == val1I) {
//				listIn.set(i, val2C);
////System.out.println("swap1");
//			}
//			else if (listValI == val2I) {
//				listIn.set(i, val1C);
////System.out.println("swap2");
//			}
////System.out.print("** sublist size is: " + listIn.size() + "\n");				
////			
////for (int k = 0; k < listIn.size(); k ++) {
////	System.out.print(listIn.get(k));
////	
////	if (i == 4) {System.exit(0);}
////}
////System.out.println("\n+++");			
//
//			
//
//		}
//		
//		return listIn;
//		
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	// make less wonky
//	public static ArrayList<Character> bandPass_invertOnes(
//			ArrayList<Character> symbolAL, int numBandsI) throws IOException {
//		
//		
//		ArrayList<Character> returnAL = new ArrayList<Character>();
//		int bandSizeI = symbolAL.size() / numBandsI;	
//		int bandRemainderI = symbolAL.size() % numBandsI;
//		logger.info("numBandsI is: " + numBandsI);
//		logger.info("bandsizeI is: " + bandSizeI);
//		logger.info("bandRemainderI is: " + bandRemainderI + "\n");
//		
//		int fromI = 0;
//		int toI = bandSizeI;	
//		ArrayList<Integer> bandCountAL = new ArrayList<Integer>();
//		ArrayList<Character> subList;
//		
//		for (int i = 0; i < numBandsI; i ++) {	
//			subList = new ArrayList<Character>(symbolAL.subList(fromI, toI));
//	
//			String tempS = new String();
//			for (int k = 0 ; k < subList.size(); k ++) {
//				tempS = tempS + subList.get(k).toString();
//			}
//			
////System.out.println("tempS is: " + tempS);
//			
//			if (tempS.equals("0101")) {
//				subList.set(1, '0');
//				subList.set(3, '0');
//			}
//				
//			// update indicies and iterate
//			fromI = toI;
//			toI += bandSizeI;
//
//			returnAL.addAll(subList);
//			
//		}
//	
//		//hit up the remainder
//		
//
//		
//		
//		
//
//		
//		return returnAL;
//		
//	}
//	
	
	
	
	
	
	
}




