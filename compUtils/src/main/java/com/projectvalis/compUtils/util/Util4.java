package com.projectvalis.compUtils.util;

import java.util.ArrayList;

public class Util4 {

	
	
	
	// for when the leading nibble is always zero
	public static ArrayList<Character> killLeadingNibbles(
			ArrayList<Character> symbolAL) {
		ArrayList<Character> tempAL = new ArrayList<Character>();
		
		for (int i = 1; i < symbolAL.size(); i = i + 2 ) {
			tempAL.add(symbolAL.get(i));	
		}
		
		return tempAL;
		
	}
	
	
	
	public static void invertSecondNibble(ArrayList<Character> symbolAL) {
		
		for (int i = 1; i < symbolAL.size(); i = i + 2 ) {
			int tempI = Integer.parseInt(symbolAL.get(i).toString(), 16);
			tempI = (tempI > 7) ? (tempI - 8) : tempI + 8;
			symbolAL.set(i, Integer.toHexString(tempI).toUpperCase().charAt(0));
			
		}
		
	}
	
	
	// smooshes n1 to n2, sets n1 to zero
	public static void nibbleConcat(ArrayList<Character> symbolAL) {
		
		for (int i = 0; i < symbolAL.size(); i = i + 2 ) {
			int n1I = Integer.parseInt(symbolAL.get(i).toString(), 16);
			int n2I = Integer.parseInt(symbolAL.get(i + 1).toString(), 16);
			
			
			if ((n1I > 3) || (n2I > 3)) continue;
			
			
			String n1S = Integer.toBinaryString(n1I);
			if (n1S.length() < 2) n1S = "0" + n1S;
			
			String n2S = Integer.toBinaryString(n2I);
			if (n2S.length() < 2) n2S = "0" + n2S;
			
			String newNibbleS = n1S + n2S;
			int newNibbleI = Integer.parseInt(newNibbleS, 2);
//System.out.println(n1S + " " + n2S + " " + newNibbleS);
			char n1C = '0';
			char n2C = Integer.toHexString(newNibbleI).toUpperCase().charAt(0);
			
			
			symbolAL.set(i, n1C);
			symbolAL.set(i + 1, n2C);
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	// try homogenizing the list by ensuring all numbers are between
	// zero and 127 (as opposed to zero and 255)
	public static byte[] byteBand(byte[] byteARR) {
		
		for (int i = 0; i < byteARR.length; i ++) {
			int tempI = byteARR[i];
			tempI = (tempI < 0) ? (tempI + 128) : tempI;			
			//tempI = (tempI > 63) ? (tempI - 64) : tempI;
			//tempI = (tempI > 32) ? (tempI - 31) : tempI;
			
//			if (tempI % 2 == 0) {
//				tempI /= 2;
//			}
//			else if (tempI % 3 == 0) {
//				tempI /= 3;
//			}
//			else if (tempI % 5 == 0) {
//				tempI /= 5;
//			}
			
			
			byteARR[i] = (byte)tempI;
		}
		
		return byteARR;
		
	}
	
	// > 187>
	// try homogenizing the list by ensuring all numbers are between
	// zero and 127 (as opposed to zero and 255)
	public static byte[] byteBandUnsigned(byte[] byteARR) {
		
		for (int i = 0; i < byteARR.length; i ++) {
			int tempI = byteARR[i] & 0xFF;
			tempI = (tempI > 187) ? (tempI - 187) : tempI;
			byteARR[i] = (byte)tempI;
		}
		
		return byteARR;
		
	}
	
	
	
	public static byte[] byteBand112(byte[] byteARR) {
		
		for (int i = 0; i < byteARR.length; i ++) {
			int tempI = byteARR[i];
			tempI = (tempI > 111) ? (tempI - 112) : tempI;
			byteARR[i] = (byte)tempI;
		}
		
		return byteARR;
		
	}
	
	
	
	public static byte[] byteBand56(byte[] byteARR) {
		
		for (int i = 0; i < byteARR.length; i ++) {
			int tempI = byteARR[i];
			tempI = (tempI > 55) ? (tempI - 56) : tempI;
			byteARR[i] = (byte)tempI;
		}
		
		return byteARR;
		
	}
	
	
	
	public static byte[] byteBand28(byte[] byteARR) {
		
		for (int i = 0; i < byteARR.length; i ++) {
			int tempI = byteARR[i];
			tempI = (tempI > 27) ? (tempI - 28) : tempI;
			byteARR[i] = (byte)tempI;
		}
		
		return byteARR;
		
	}
	
	

	public static byte[] byteBand64(byte[] byteARR) {
		
		for (int i = 0; i < byteARR.length; i ++) {
			int tempI = byteARR[i];
			tempI = (tempI > 63) ? (tempI - 64) : tempI;
			byteARR[i] = (byte)tempI;
		}
		
		return byteARR;
		
	}
	
	
	public static byte[] byteBand32(byte[] byteARR) {
		
		for (int i = 0; i < byteARR.length; i ++) {
			int tempI = byteARR[i];
			tempI = (tempI > 31) ? (tempI - 32) : tempI;
			byteARR[i] = (byte)tempI;
		}
		
		return byteARR;
		
	}
	
	public static byte[] byteBand16(byte[] byteARR) {
		
		for (int i = 0; i < byteARR.length; i ++) {
			int tempI = byteARR[i];
			tempI = (tempI > 15) ? (tempI - 16) : tempI;
			byteARR[i] = (byte)tempI;
		}
		
		return byteARR;
		
	}
	
	
	public static byte[] byteBand8(byte[] byteARR) {
		
		for (int i = 0; i < byteARR.length; i ++) {
			int tempI = byteARR[i];
			tempI = (tempI > 7) ? (tempI - 8) : tempI;
			byteARR[i] = (byte)tempI;
		}
		
		return byteARR;
		
	}
	
	
	
	public static byte[] byteBand4(byte[] byteARR) {
		
		for (int i = 0; i < byteARR.length; i ++) {
			int tempI = byteARR[i];
			tempI = (tempI > 3) ? (tempI - 4) : tempI;
			byteARR[i] = (byte)tempI;
		}
		
		return byteARR;
		
	}
	
	
	public static byte[] byteBand2(byte[] byteARR) {
		
		for (int i = 0; i < byteARR.length; i ++) {
			int tempI = byteARR[i];
			tempI = (tempI > 1) ? (tempI - 2) : tempI;
			byteARR[i] = (byte)tempI;
		}
		
		return byteARR;
		
	}
	
	
	
	// if first two bits of each nibble are either 11 or 10, xor out
	public static void hexXOR(ArrayList<Character> symbolAL) {
		
		for (int i = 0; i < symbolAL.size(); i ++) {
			int nibbleI = Integer.parseInt(symbolAL.get(i).toString(), 16);
			String nibbleS = Integer.toBinaryString(nibbleI);
//System.out.println("was: " + symbolAL.get(i));			
			while (nibbleS.length() < 4) nibbleS = "0" + nibbleS;
			
			if (nibbleS.substring(0, 2).equals("11") || 
					(nibbleS.substring(0, 2).equals("10"))) {
				nibbleI = nibbleI ^ 0xC;
			}
			
			char newNibbleC = 
					Integer.toHexString(nibbleI).toUpperCase().charAt(0);
//System.out.println("is: " + newNibbleC + "\n");
			symbolAL.set(i, newNibbleC);
			
		}
		
	}
	
	
	// xor out byte 3
	public static void hexXOR3(ArrayList<Character> symbolAL) {
		
		for (int i = 0; i < symbolAL.size(); i ++) {
			int nibbleI = Integer.parseInt(symbolAL.get(i).toString(), 16);
			String nibbleS = Integer.toBinaryString(nibbleI);
//System.out.println("was: " + symbolAL.get(i));			
			while (nibbleS.length() < 4) nibbleS = "0" + nibbleS;
			
			if (nibbleS.substring(1, 2).equals("1")) {
				nibbleI = nibbleI ^ 0x4;
			}
			
			char newNibbleC = 
					Integer.toHexString(nibbleI).toUpperCase().charAt(0);
//System.out.println("is: " + newNibbleC + "\n");
			symbolAL.set(i, newNibbleC);
			
		}
		
	}
	
	// xor out byte 2
	public static void hexXOR2(ArrayList<Character> symbolAL) {
		
		for (int i = 0; i < symbolAL.size(); i ++) {
			int nibbleI = Integer.parseInt(symbolAL.get(i).toString(), 16);
			String nibbleS = Integer.toBinaryString(nibbleI);
//System.out.println("was: " + symbolAL.get(i));			
			while (nibbleS.length() < 4) nibbleS = "0" + nibbleS;
			
			if (nibbleS.substring(2, 3).equals("1")) {
				nibbleI = nibbleI ^ 0x2;
			}
			
			char newNibbleC = 
					Integer.toHexString(nibbleI).toUpperCase().charAt(0);
//System.out.println("is: " + newNibbleC + "\n");
			symbolAL.set(i, newNibbleC);
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	// if first two bits of each nibble are either 11 or 10, xor out
	public static byte[] byteXor(byte[] byteARR) {
		
		for (int i = 0; i < byteARR.length; i ++) {
			// break into nibbles
			int byteNumI = byteARR[i] & 0xFF;
			String byteHexS = Integer.toHexString(byteNumI);
			if (byteHexS.length() == 1) byteHexS = "0" + byteHexS;
	
			boolean xorHigh1B = false;
			boolean xorHigh2B = false;
			
			int nibble1I = Integer.parseInt(byteHexS.substring(0, 1), 16);
			int nibble2I = Integer.parseInt(byteHexS.substring(1, 2), 16);
			
			String nibble1BinaryS = Integer.toBinaryString(nibble1I);
			String nibble2BinaryS = Integer.toBinaryString(nibble2I);
			
			while(nibble1BinaryS.length() < 4) 
				nibble1BinaryS = "0" + nibble1BinaryS;
			
			while(nibble2BinaryS.length() < 4) 
				nibble2BinaryS = "0" + nibble1BinaryS;
			
			// test first two bits
			if (nibble1BinaryS.substring(0, 2).equals("11") || 
					(nibble1BinaryS.substring(0, 2).equals("10"))) {
				xorHigh1B = true;
			}
			
			if (nibble2BinaryS.substring(0, 2).equals("11") || 
					(nibble2BinaryS.substring(0, 2).equals("10"))) {
				xorHigh2B = true;
			}
			
//System.out.println(nibble1BinaryS);		
			// test middle two bits
//			if (nibble1BinaryS.substring(1, 3).equals("11")) {
//				xorHigh1B = true;
//			}
//			
//			if (nibble2BinaryS.substring(1, 3).equals("11")) {
//				xorHigh2B = true;
//			}
//System.out.println(xorHigh1B + " " + xorHigh2B);
			
			
			// if both are set - xor out
			if (xorHigh1B || xorHigh2B) {
//System.out.println("was: " + byteARR[i]);
				byteARR[i] = (byte) (byteARR[i] ^ 0xCC);
//System.out.println("is: " + byteARR[i]);
			}
			

			
		}
		
		return byteARR;
		
	}
	
	
}
