package com.projectvalis.compUtils.util;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BWT {

	static Logger logger = LoggerFactory.getLogger("BWT");	

	

	/**
	 * see http://algs4.cs.princeton.edu/63suffix/SuffixArray.java.html
	 * 
	 * and
	 * 
	 * http://www.quora.com/
	 * How-I-can-optimize-burrows-wheeler-transform-
	 * and-inverse-transform-to-work-in-O-n-time-O-n-space
	 */
	public static void encode(ArrayList<Character> asHexAL) {
		
		// build suffix arr
		char[] asPrimCharARR = new char[asHexAL.size()];
		
		for (int i = 0; i < asHexAL.size(); i ++) {
			asPrimCharARR[i] = asHexAL.get(i);
		}
		
		String asHexS = new String(asPrimCharARR);
		
		for (int i = 0; i < 100; i ++) {
			System.out.print(asPrimCharARR[i]);
		}
		
System.out.println("\n\n"+asHexS.substring(0, 100));
		SuffixArray suffARR = new SuffixArray(asHexS);
		
		
		// clean asHexAL and do BWT
		asHexAL.clear();
		
		for (int k = 0; k < suffARR.length(); k ++) {
			
			if ((k % (suffARR.length() / 10) == 0)) {
				logger.info((k / (suffARR.length() / 10) + "% there!"));
			}
			
			int nextSuffArrIndexI = 
				(suffARR.index(k) + (suffARR.length() - 1)) % suffARR.length();

			asHexAL.add(suffARR.select(nextSuffArrIndexI).charAt(0));
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
