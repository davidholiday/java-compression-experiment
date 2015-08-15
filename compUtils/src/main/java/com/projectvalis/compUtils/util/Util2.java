//package com.projectvalis.compUtils.util;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class Util2 {
//	static Logger logger = LoggerFactory.getLogger("UTIL2");
//	
//	public static ArrayList<Integer> bandIndexesAL = new ArrayList<Integer>();
//	public static ArrayList<Character> swapValsAL = new ArrayList<Character>();
//	
//	
//	
//	/*
//	 * 
//	 * !! not working -- you're dropping symbols at every pass
//	 * for some reason!!! 
//	 * 
//	 * 
//	 */
//	public static ArrayList<Character> variBandPass(
//			Character filterChar, 
//			int alphabetSizeI, 
//			ArrayList<Character> symbolAL) {
//		
//		ArrayList<Character> returnAL = new ArrayList<Character>();
//		ArrayList<Integer> bandCountAL = new ArrayList<Integer>();
//		ArrayList<Character> subList;
//
//		int filterCharI = Integer.parseUnsignedInt(
//				filterChar.toString(), alphabetSizeI);
//		
//		// sort this pig
////		for (int i = startCharI; i > 10; i --) {
//			returnAL.clear();
//			//int fromI = 0;
//			//int toI = 1;
//			
//			// find the first instance of the char we're trying to get rid of
//			int fromI = symbolAL.get(filterChar);
//			int toI = fromI += 1;
//			
//			boolean nextBandB = false;
//				
//			while (!nextBandB) {
//				subList = 
//						new ArrayList<Character>(symbolAL.subList(fromI, toI));
////logger.info("sublist size is: " + subList.size());			
//				bandCountAL = Util.countInstances(subList);
//
//				// if this list has more than one zero we can expand the size
//				// of the band
//				//
//				// also, if the first instance of zero in the list is greater
//				// than the current symbol we're working on
//				// go back one, process, and continue
//				int firstInstanceZeroI = bandCountAL.indexOf(0);
//				Collections.sort(bandCountAL);
//				
//				// what's actually happening here is that we're quickly
//				// running into situations where the character we're trying
//				// to drop is the only one not present in the list! 
//				if (firstInstanceZeroI >= filterCharI) {
//					
//					// back that list up
//					while (firstInstanceZeroI >= filterCharI) {
//						toI -= 1;
//						
////printList(subList);						
//						subList = new ArrayList<Character>(
//										symbolAL.subList(fromI, toI));
//						
////System.out.println("\n++");
////printList(subList);
//						
//						bandCountAL = Util.countInstances(subList);
//						firstInstanceZeroI = bandCountAL.indexOf(0);
//					}
//					
//					
////System.out.println("\n**");
////printList(bandCountAL);
//					// get the count again 
//					bandCountAL = Util.countInstances(subList);					
//					int swapIndexI = bandCountAL.indexOf(0);
//					
////System.out.println("\n" + i + " " + swapIndexI);
////printList(bandCountAL);
////System.out.println("");
////printList(subList);
////System.exit(0);
//				
//					returnAL.addAll(
//							Util.swapSubListChars(
//									subList, filterCharI, swapIndexI));
//					
//					//update lists
//					bandIndexesAL.add(toI);
//					swapValsAL.add(Integer.toHexString(swapIndexI).charAt(0));
//					//returnAL.addAll(subList);
//					
//					fromI = toI;
//					toI += 1;
//				}		
//				else if (bandCountAL.get(1).equals(0)) {
//					toI += 1;
//					
//					// check to see if we've overrun the end of the list
//					// if so, move on to the next band
//					// remember, we can't process out the last bit if there
//					// are more than one empty slots for it to go into! 
//					if (toI >= symbolAL.size()) { 
//						returnAL.addAll(subList);
//						nextBandB = true; 
//					}	
//
//
//					continue;
//				}
//				// otherwise process band and reset vars
//				else {
//					// get the count again 
//					bandCountAL = Util.countInstances(subList);					
//					int swapIndexI = bandCountAL.indexOf(0);
////System.out.println("\n***");
////printList(subList);
//					returnAL.addAll(
//							Util.swapSubListChars(
//									subList, filterCharI, swapIndexI));
//					//returnAL.addAll(subList);
////System.out.println("");
////printList(subList);
////System.exit(0);
//					
//					//update lists
//					bandIndexesAL.add(toI);
//					swapValsAL.add(Integer.toHexString(swapIndexI).charAt(0));
//					
//					fromI = toI;
//					toI += 1;			
//				}
//
//				// returnAL.addAll(bubbleSort(subList, bandCountAL));				
//			}
//			
//		//}
//		logger.info("num bands was: " + swapValsAL.size());
//		return returnAL;
//		
//	}
//	
//	
//	
//	public static void printList(List list) {
//		
//		for (int i = 0; i < list.size(); i ++) {
//			System.out.print(list.get(i) + "");
//		}
//		
//		System.out.println("");
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
//	// hit all 16 hex digits against every other possible hex digit to see 
//	// if there isn't one hex digits that will distrupt the balance of symbols
//	// in the list
//	public static void xorTest() {
//		
//		ArrayList<Integer> hexList1 = new ArrayList<Integer>();
//		ArrayList<Integer> hexList2 = new ArrayList<Integer>();
//		ArrayList<Character> symbolList = new ArrayList<Character>();
//		
//		// populate lists
//		for (int i = 0; i < 16; i ++) {
//			hexList1.add(i);
//			hexList2.add(i);
//		}
//		
//		//check every permutation of one against the other and report
//		for (int i = 0; i < 2; i ++) {
//		
//			logger.info("xor-ing: " + i);
//			
//			for (int k = 0; k < 16; k ++) {
//				int byteOut = hexList1.get(i) ^ hexList2.get(k);
//				symbolList.add(
//						Integer.toHexString(byteOut).toUpperCase().charAt(0));
//			}
//			
//			ArrayList<Integer> countList = Util.countInstances(symbolList);
//			Util.report(symbolList, countList);
//			Util2.printList(symbolList);
//			
//			symbolList.clear();
//		}
//		
//	
//		
//	}
//	
//	
//	
//	
//	
//}
//
//
//
