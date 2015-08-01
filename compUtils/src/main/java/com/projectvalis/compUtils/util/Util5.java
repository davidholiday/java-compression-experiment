package com.projectvalis.compUtils.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Util5 {
	
static Logger logger = LoggerFactory.getLogger("UTIL5");	
	

	// dumb way to do this, i know...
	// why am i apologizing for my crap code?
	// who the hell else is going to read this but me? 
	// seriously - what kind of ego do you have that you think you're 
	// crap code is going to be read by someone other than you? 
	// you think that someday your genius will be discovered and your 'papers' 
	// will be poured over for generations by academics and enthusiasts looking
	// to gleen just one more cogent insight into the mind that engendered
	// such brilliance unto the world? 
	// dude. get over yourself. 
	// 
	public static void sortByNibble (
			ArrayList<Character> symbolAL, int delimiter) {
		
		ArrayList<Character> bucketOneAL = new ArrayList<Character>();
		ArrayList<Character> bucketTwoAL = new ArrayList<Character>();
		
		for (int i = 0; i < symbolAL.size(); i ++) {
			int tempI = Integer.parseInt(symbolAL.get(i).toString(), 16);
			
			if (tempI > delimiter) {
				bucketTwoAL.add(symbolAL.get(i));
			}
			else {
				bucketOneAL.add(symbolAL.get(i));
			}
			
		}
		
		symbolAL.clear();
		symbolAL.addAll(bucketOneAL);
		symbolAL.addAll(bucketTwoAL);
			
	}
	
	
	// sorts the char list by delimiter -- returns transition point
	public static int sortByByte (
			List<Character> symbolAL, int delimiter) {
		
		ArrayList<Character> bucketOneAL = new ArrayList<Character>();
		ArrayList<Character> bucketTwoAL = new ArrayList<Character>();
		
		for (int i = 0; i < symbolAL.size(); i+=2) {
			String byteS = 
					symbolAL.get(i).toString() + symbolAL.get(i + 1).toString();
			
			int tempI = Integer.parseInt(byteS, 16);
			
			if (tempI > delimiter) {
				bucketTwoAL.add(symbolAL.get(i));
				bucketTwoAL.add(symbolAL.get(i + 1));
			}
			else {
				bucketOneAL.add(symbolAL.get(i));
				bucketOneAL.add(symbolAL.get(i + 1));
			}
			
		}
		
		symbolAL.clear();
		symbolAL.addAll(bucketOneAL);
		symbolAL.addAll(bucketTwoAL);
		
		// divided by two because you're dealing with nibbles, yo
		return bucketOneAL.size() / 2;
			
	}
	
	
	
	
	// sorts the char list by delimiter -- returns transition point
	public static ArrayList<ArrayList<Character>> sortByByteDeux (
			List<Character> symbolAL, int delimiter) {
		
		ArrayList<Character> bucketOneAL = new ArrayList<Character>();
		ArrayList<Character> bucketTwoAL = new ArrayList<Character>();
		
		for (int i = 0; i < symbolAL.size(); i+=2) {
			String byteS = 
					symbolAL.get(i).toString() + symbolAL.get(i + 1).toString();
			
			int tempI = Integer.parseInt(byteS, 16);
			
			if (tempI > delimiter) {
				bucketTwoAL.add(symbolAL.get(i));
				bucketTwoAL.add(symbolAL.get(i + 1));
			}
			else {
				bucketOneAL.add(symbolAL.get(i));
				bucketOneAL.add(symbolAL.get(i + 1));
			}
			
		}
		
		ArrayList<ArrayList<Character>> returnL = 
				new ArrayList<ArrayList<Character>>();
		returnL.add(bucketOneAL);
		returnL.add(bucketTwoAL);	
		return returnL;
			
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	// returns new byte arr and pivot point
	public static Pair<byte[], Integer> sortBy (byte[] byteARR, int delimiter) {
		
		ArrayList<Byte> bucketOneAL = new ArrayList<Byte>();
		ArrayList<Byte> bucketTwoAL = new ArrayList<Byte>();
		
		for (int i = 0; i < byteARR.length; i ++) {
			int tempI = byteARR[i] & 0xFF;
			
			if (tempI > delimiter) {
				bucketTwoAL.add(byteARR[i]);
			}
			else {
				bucketOneAL.add(byteARR[i]);
			}
			
		}
		
		byte[] returnARR = new byte[byteARR.length];
		
		for (int i = 0; i < bucketOneAL.size(); i ++) {
			returnARR[i] = bucketOneAL.get(i);
		}
		
		for (int i = 0; i < bucketTwoAL.size(); i ++) {
			int indexI = bucketOneAL.size() + i;
			returnARR[indexI] = bucketTwoAL.get(i);
		}
			
		return new Pair(returnARR, bucketOneAL.size());
		
	}
	
	
	
	
	
	// shell sort
	// you should probably learn more about gap sequences...
	//
	// why no working???
	public static void shellSort (ArrayList<Character> symbolAL) {
		int[] gapsARR = {1750, 701, 301, 132, 57, 23, 10, 4, 1};
		
		for (int i = 0; i < gapsARR.length; i ++) {
			
			for (int k = 0; k < symbolAL.size() - gapsARR[i]; k += gapsARR[i]) {
				int charOneI = Integer.parseInt(symbolAL.get(k).toString(), 16);
				int charTwoI = 
						Integer.parseInt(
								symbolAL.get(k + gapsARR[i]).toString(), 16);
				
				if (charTwoI > charOneI) {
					char tempChar = symbolAL.get(k);
					symbolAL.set(k, symbolAL.get(k + gapsARR[i]));
					symbolAL.set(k + gapsARR[i], tempChar);
				}			
				
			}
					
		}
		
		
	}
	

	
	public static void rosettaShell (ArrayList<Character> a) {
		int increment = a.size() / 2;
		
		int estimatedBytesI = 0;
		
		//while (increment > 0) {
		for (int p = 0; p < 5; p ++) {
			logger.info("increment is: " + increment);		
			int count = 0;
			
			for (int i = increment; i < a.size(); i++) {
				
				count ++;
				
				int j = i;
				char temp = a.get(i);
				while (j >= increment && a.get(j - increment) > temp) {
					a.set(j, a.get(j - increment));
					j = j - increment;
				}
				a.set(j, temp);
			}
			if (increment == 2) {
				increment = 1;
			} else {
				increment *= (5.0 / 11);
			}
			
			logger.info(count+ "");
			logger.info("estimated bytes is: " + (count /8));
			estimatedBytesI += (count / 8); 
			
		}
		
		logger.info("estimated bytes of meta data is: " 
											+ estimatedBytesI
											+ "\n");
	}
	
	
	
	/*
	 * method to determine how many sets of three bytes in the
	 * stream are candidates for magic square - ie - 
	 * adhere to 0 < a < b < c-a
	 */
	public static void countMSCandidates(byte[] byteARR) {
		
		int candidateCountI = 0;
		int noHitCountI = 0;
		int byteNumI = 3;
		int toI = byteARR.length - byteNumI;
		ArrayList<Integer> tempAL = new ArrayList<Integer>();
		
		for (int i = 0; i < toI; i += byteNumI) {
			
			for (int k = 0; k < 3; k ++) {
				tempAL.add((int)byteARR[i + k] + 1);
			}
			
			Collections.sort(tempAL);
			
			if ( (tempAL.get(0) < tempAL.get(1)) && 
					(tempAL.get(1) < (tempAL.get(2) - tempAL.get(0)) )) {
				
				candidateCountI++;
			}
			else {
				noHitCountI++;
			}
			
			tempAL.clear();
			
		}
		
		
		logger.info("candidate count is: " + candidateCountI + 
				" / " + noHitCountI);
		
	}
	
	
	
	/**
	 * grabs three bytes at a time - only divides them thusly for testing
	 * as candidates for magic square.
	 * 
	 * nibble1 = a
	 * nibble2,3 = b
	 * nibble4,5,6 = c
	 * 
	 * note that the nibbles aren't sorted, like in the previous method.
	 * 
	 * @param asHexAL
	 */
	public static void countMSCandidates_m2(ArrayList<Character> asHexAL) {
		
		int countI = 0;
		int noHitCountI = 0;
		int nibbleNumI = 6;
		int toI = asHexAL.size() / 6;
	
		for (int i = 0; i < toI; i +=6) {
			String aS = asHexAL.get(i).toString();
			String bS = 
					asHexAL.get(i+1).toString() + 
					asHexAL.get(i+2).toString();
			
			String cS = 
					asHexAL.get(i+3).toString() + 
					asHexAL.get(i+4).toString() +
					asHexAL.get(i+5).toString();
			
			int aI = Integer.parseInt(aS, 16);
			int bI = Integer.parseInt(bS, 16);
			int cI = Integer.parseInt(cS, 16);
		
			//logger.info(aI + " " + bI + " " + cI);
			
			if ( (aI < bI) && (bI < (cI - aI)) ) {		
				countI++;
			}
			else {
				noHitCountI ++;
			}
			
		}
		
		
		logger.info("candidate count is: " + countI + " / " + noHitCountI);
		
		
	}
	
	
}












