package com.projectvalis.compUtils.runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.compUtils.util.Util;
import com.projectvalis.compUtils.util.Util5;
import com.projectvalis.compUtils.util.fileIO.Ingest;



public class Bootstrap {
	static Logger logger = LoggerFactory.getLogger("MAIN");
	static final int DESIRED_BAND_SIZE = 4;  // 3 seemed to work, but now 5?
	public static int swapCount = 0;
	
    public static void main( String[] args ) throws IOException {
    	 
    	
//    	ArrayList<Character> asHexAL = Util.loadFileAsStringHex("stipper_out");
//    	asHexAL = 
//    			new ArrayList<Character>(
//    					asHexAL.subList(0, asHexAL.size() / 2));
//    	ArrayList<Character> asHexAL = Util.genRnd01(4194304);
//    	ArrayList<Character> asHexAL = Util.loadFileAsHex("a.log");  
//    	ArrayList<Integer>	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
    	
    	logger.info ("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n\n");
    	
//    	Util5.countMSCandidates(Ingest.symbolListToByteArr(asHexAL));
//    	Util5.countMSCandidates_m2(asHexAL);
    	
    	
    	
//    	int numBandsI = asHexAL.size() / DESIRED_BAND_SIZE;
//    	asHexAL = Util.bandPass_invertOnes(asHexAL, numBandsI);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
//    	
//    	
//    	BWT.encode(asHexAL);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
    	
    	
    	
    	
    	

    	
//    	byte[] byteARR = Util3.symbolListToByteArr(asHexAL);
//    	int delim_127I = Util5.sortByByte(asHexAL, 127);
//    	System.out.println(delim_127I);
    	
    	// kill off nibbles 8-F
    	// 2bits per byte
//     	Util4.hexXOR(asHexAL);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
//
//    	
//    	// kill off nibbles 4-7
//    	// 2bits per byte
//    	Util4.hexXOR3(asHexAL);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);    	
//
//    
//    	// kill off nibbles 2-3
//    	// 2bits per byte   	
//    	Util4.hexXOR2(asHexAL);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
    	
    	
    	
//    	int numBandsI = asHexAL.size() / DESIRED_BAND_SIZE;
//    	asHexAL = Util.bandPass_invertOnes(asHexAL, numBandsI);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
		

    	
    	
    	
    	
//		ArrayList<ArrayList<Character>> subAL = 
//				Util5.sortByByteDeux(asHexAL, 127);
//
//		
//		ArrayList<Character> processedAL = new ArrayList<Character>();
//		ArrayList<Integer> delimAL = new ArrayList<Integer>();
//		
//		delimAL.add(127);
//    	
//    	for (int i = 1; i < 7; i ++) { //7
//System.out.println("\n\ni is: " + i);    		
//    		
//    		int curSubAlSizeI = subAL.size();
//System.out.println("curSubAlSizeI is: " + curSubAlSizeI);
//
////    		for (int k = 0; k < Math.pow(2, i+1) - 1; k++) {
//			for (int k = 0; k < Math.pow(2, i); k++) {
//	
//    			ArrayList<Character> splitterfiedSubAL = subAL.get(k);
//    			
//    			int delimiter = ((int)(Math.pow(2, (7 - i)) -1) * (k + 1)) + k;
//    			int tempK = k;
//    			
//    			while (delimAL.contains(delimiter)) {
//    				tempK++;
//    				delimiter = 
//    					((int)(Math.pow(2, (7 - i)) -1) * (tempK + 1)) + tempK;
//    			}
//
//    			delimAL.add(delimiter);
//    			
//System.out.println("\ndelimiter is: " + delimiter);
//
//    			ArrayList<ArrayList<Character>> tempSplitARRofARR = 
//    					Util5.sortByByteDeux(splitterfiedSubAL, delimiter);
//System.out.println("tempSplitARRofARR size is: " + tempSplitARRofARR.size());   
//
//    			for (int d = 0; d < tempSplitARRofARR.size(); d++) {
//System.out.println(
//		"tempARR: " + d + " size is: " + tempSplitARRofARR.get(d).size());
//    				subAL.add(tempSplitARRofARR.get(d));
//    			}
//    			
//    			
//
//    			
//    		}
//    		
//    		
//    		// remove old spliterfied sublists
//    		int stopI = (int)Math.pow(2, i);
//    		for (int t = 0; t < stopI; t ++) {
//    			subAL.remove(0);
//    		}
//	
//    		
//    	}
//    	
//    	
//    	
////    	int startI = subAL.size() - 126;
////	
//		for (int a = 0; a < subAL.size(); a ++) {
//			
//			ArrayList<Character> blergAL = subAL.get(a);
//			processedAL.addAll(blergAL);  			
//		}    	
//    	
//    	
//System.out.println("\n\n\nsubAL size is: " + subAL.size());    
//System.out.println("processedAL size is: " + processedAL.size());
//    	
//asHexAL = processedAL;
    	
    	
//    	Ingest.zipAndSerialize(Ingest.symbolListToByteArr(asHexAL));
//    	Ingest.serializeByteArr(Ingest.symbolListToByteArr(asHexAL)); 
    	
    	

//    	Arrays.sort(byteARR);
//    	asHexAL = Util3.byteArrToSymbolList(byteARR);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
    	
   	
    	
    	
//    	
//    	/*
//    	 * pass 1 -- 127
//    	 */
//    	Pair returnP = 
//    			Util5.sortBy(Util3.symbolListToByteArr(asHexAL), 127);
//    	
//    	byte[] byteARR_first_pass = (byte[]) returnP.getLeft();
//    	int pivotPoint1 = (int) returnP.getRight();
//    	
//    	byte[] byteARR_fp_1 = 
//    			Arrays.copyOfRange(byteARR_first_pass, 0, pivotPoint1 - 1);
//    	
//    	byte[] byteARR_fp_2 = 
//    			Arrays.copyOfRange(
//    			byteARR_first_pass, pivotPoint1 , byteARR_first_pass.length);
//    	
//    	
//    	/*
//    	 * pass 2 -- 64
//    	 */
//    	Pair returnP_p2_a = Util5.sortBy(byteARR_fp_1, 63);
//    	Pair returnP_p2_b = Util5.sortBy(byteARR_fp_2, 192);
//    	
//    	int pivotPoint_p2_a = (int) returnP_p2_a.getRight();
//    	int pivotPoint_p2_b = (int) returnP_p2_b.getRight();
//    	
//    	byte[] byteARR_second_pass_a = (byte[]) returnP_p2_a.getLeft();
//    	byte[] byteARR_second_pass_b = (byte[]) returnP_p2_b.getLeft();
//    	
//    	
//    	byte[] byteARR_p2_a1 = 
//    			Arrays.copyOfRange(
//    					byteARR_second_pass_a, 
//    					0, 
//    					pivotPoint_p2_a - 1);
//    	
//    	byte[] byteARR_p2_a2 = 
//    			Arrays.copyOfRange(
//    					byteARR_second_pass_a, 
//    					pivotPoint_p2_a, 
//    					byteARR_second_pass_a.length);
//    	
//    	
//    	byte[] byteARR_p2_b1 = 
//    			Arrays.copyOfRange(
//    					byteARR_second_pass_b, 
//    					0, 
//    					pivotPoint_p2_b - 1);
//    	
//    	
//    	byte[] byteARR_p2_b2 = 
//    			Arrays.copyOfRange(
//    					byteARR_second_pass_b, 
//    					pivotPoint_p2_b, 
//    					byteARR_second_pass_b.length);
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
//    	byte[] returnARR = ArrayUtils.addAll(byteARR_fp_1, byteARR_fp_2);
//
//    	

    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
//    	// [0 - 127] |*127*| [128 - 255]
//    	int pivot127I = Util5.sortByByte(asHexAL, 127);
//  	
//    	
//    	// [0 - 63] |*64*| [64 - 127] |*127*| [128 - 192] |*192*| [193 - 255]
//    	int pivot64I = Util5.sortByByte(asHexAL.subList(0, pivot127I), 64);
//    	int pivot192I = 
//    			Util5.sortByByte(
//    					asHexAL.subList(pivot127I + 1, asHexAL.size()-1), 64);
//    	
//    	pivot192I += asHexAL.size() / 2;
//    	
//    	
//    	
//    	// [0 - 31] |*32*| [32 - 63] |*64*| [64 - 96] |*96*| [97 - 127] |*127*| 
//    	//	[128 - 160] |*160*| [161 - 192] |*192*| [193-225] |*225*| [226-255]
//    	int pivot32I = Util5.sortByByte(asHexAL.subList(0, pivot64I), 32);
//    	int pivot96I = 
//    			Util5.sortByByte(
//    					asHexAL.subList(pivot64I + 1, asHexAL.size()-1), 32);
//    	
// 	
//    	int pivot160I = 
//    			Util5.sortByByte(asHexAL.subList(pivot127I, pivot192I - 1), 32);
//    	int pivot225I = 
//    			Util5.sortByByte(
//    					asHexAL.subList(pivot64I + 1, asHexAL.size()-1), 32);
    	
    	
    	
    	
    	
    	
//    	Util5.sortBy(asHexAL, 1);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);

    	

    	
    	
    	
    	
    	
//    	byte[] asByteARR = Util4.byteBand(Util3.symbolListToByteArr(asHexAL));
//    	asHexAL = Util3.byteArrToSymbolList(asByteARR);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
//    	
//    	   	   	
//    	
//    	asByteARR = Util4.byteBand64(Util3.symbolListToByteArr(asHexAL));
//    	asHexAL = Util3.byteArrToSymbolList(asByteARR);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
//
// 	 	   	
//
//    	asByteARR = Util4.byteBand32(Util3.symbolListToByteArr(asHexAL));
//    	asHexAL = Util3.byteArrToSymbolList(asByteARR);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);	    	
//    	
//    	
//    	
//    	
//    	

    	
    	
//    	Util5.rosettaShell(asHexAL);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
    	
    	
    	
//		int numBandsI = asHexAL.size() / DESIRED_BAND_SIZE;
//    	asHexAL = Util.bandPass(asHexAL, numBandsI);
//    	asHexAL = Util.bandPass4(asHexAL, numBandsI);
//    	asHexAL = Util.bandPass2(asHexAL, numBandsI);
    	
//    	byte[] xorByteARR = Util4.byteBandUnsigned(
//    				(Util3.symbolListToByteArr(asHexAL)));
//    	asHexAL = Util3.byteArrToSymbolList(xorByteARR);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
    	
    	
    	
    	
 	
    	
    	
 	
    	
    	

    	
    	
    	
    	
//    	asByteARR = Util4.byteBand16(Util3.symbolListToByteArr(asHexAL));
//    	asHexAL = Util3.byteArrToSymbolList(asByteARR);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);    	
    	
    	
    	
//    	Util4.nibbleConcat(asHexAL);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
    	

   
    	
    	
    	
    	
    	


//    	  	
//    	
//    	asByteARR = Util4.byteBand4(Util3.symbolListToByteArr(asHexAL));
//    	asHexAL = Util3.byteArrToSymbolList(asByteARR);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
//    	
//    	
//    	
//    	asByteARR = Util4.byteBand2(Util3.symbolListToByteArr(asHexAL));
//    	asHexAL = Util3.byteArrToSymbolList(asByteARR);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
    	
    	
    	

    	

    	
//    	Util4.invertSecondNibble(asHexAL);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
    	
//    	asHexAL = Util4.killLeadingNibbles(asHexAL);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
    	
    	
	

    	
//		int numBandsI = asHexAL.size() / DESIRED_BAND_SIZE;
//    	asHexAL = Util.bandPass(asHexAL, numBandsI);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
    	
    	
    	
    	
    	


//    	
//    	Util4.invertSecondNibble(asHexAL);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
    	
    	
//    	asByteARR = Util4.byteBand16(Util3.symbolListToByteArr(asHexAL));
//    	asHexAL = Util3.byteArrToSymbolList(asByteARR);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
    	
	

//    	
//    	
//    	asByteARR = Util4.byteBand2(Util3.symbolListToByteArr(asHexAL));
//    	asHexAL = Util3.byteArrToSymbolList(asByteARR);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
    	
    	


    	
    	
//		int numBandsI = asHexAL.size() / DESIRED_BAND_SIZE;
//    	asHexAL = Util.bandPass(asHexAL, numBandsI);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
//    	
//    	
//    	byte[] asByteARR = Util4.byteBand4(Util3.symbolListToByteArr(asHexAL));
//    	asHexAL = Util3.byteArrToSymbolList(asByteARR);
//    	countAL = Util.countInstances(asHexAL);
//    	Util.report(asHexAL, countAL);
    	
    	


    	
    	

    	
    	


    	
    	
//    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
//    	for (int i = 15; i > 9; i --) {
//    		Character filterChar = Integer.toHexString(i).charAt(0);
//    		asHexAL = Util2.variBandPass(filterChar, 16, asHexAL);
//    		countAL = Util.countInstances(asHexAL);
//    		Util.report(asHexAL, countAL);
//    		
////    		Character filterChar = Integer.toOctalString(i).charAt(0);
////    		asOctalAL = Util2.variBandPass(filterChar, 8, asOctalAL);
////    		countAL = Util.countInstances(asOctalAL);
////    		Util.report(asOctalAL, countAL);
//    		
//    	}

    	
    
    	
    	//Util2.xorTest();
    	
    	

    }
    

}

	// SMALL BAND SIZE WORKS BEST!
	
	// try breaking into 1024 byte chunks
	// band size <= 10
	// 128 bytes meta data per band -- bubble sort is o(n^2) - set of 8 --
	// 8^2 = 64. 64 *2 (two glyphs per swap note) = 128
	// 12.8k meta data for 100 bands
	// *!* can be made smaller by prefix coding the swap chars 0-7 *!*
	//
	// reversing a merge sort might help
	// see wiki page pic for visual
	// you can encode the first transition (from fully broken apart) in
	// a single byte (bit pairs that serve as flags - which went first?)
	// you can then encode each swap pair in six bits (three per octal 
	// char). so for nlogn perf - ~64 bits a band?
	//
	// maybe a nibble per band and only one swap - each nibble denotes
	// what was swapped for lowest weighted val?
	//
	// if band size is 4 and we merge sort
	// then first pass is a four bit mask
	// blerg - still too much
	// for randy - you have 768 bands of 4 size each
	// yielding a new size of 731
	// you'd need to include no more than 2bits of meta data
	// for a total size of 923
	//
	// still - i *think* a merge sort where n=4 can be reversed with
	// a single byte of info
	// 
	// what about a band size of three? 
	// you should be able to represent each merge sort in two bits (four 
	// states per two bit code - how many possible transitions between a
	// set of three and a new ordered set of three?? you can only make
	// two swaps at that point
	//
	// *!* muy importante! *!* 
	// grab the octal chars from the stream itself - don't encode the bytes,
	// you'll save a TON on that way - because you aren't blowing up the
	// list!!! (so each byte has 2 + remainder num of octal chars - 
	// each oc is 3 bits )
    //
    //
    //
    //
    // ** band size of 3 - do it in hex - trust me...


	
	
	
