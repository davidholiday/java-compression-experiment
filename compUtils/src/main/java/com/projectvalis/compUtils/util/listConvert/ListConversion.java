package com.projectvalis.compUtils.util.listConvert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import com.projectvalis.compUtils.util.Util;

public class ListConversion {

	public static byte[] symbolListToByteArr(ArrayList<Character> symbolAL) {
		
		byte[] outArr = new byte[symbolAL.size() / 2];
		
		for (int i = 0; i < outArr.length; i ++) {
			//int outArrIndexI = (i == 0) ? i : i-1;
			
			int symbolIndexI = i * 2;
			
			String byteS = symbolAL.get(symbolIndexI).toString() + 
								symbolAL.get(symbolIndexI+1).toString();
			
			//if (byteS.contains("$")) continue;
			
			byte tempB = (byte)Integer.parseInt(byteS, 16);
			outArr[i] = tempB;
			
		}
		
		return outArr;
		
	}
	
	
	

		
		
		
		
		public static byte[] 
				symbolListWithHeaderToByteArr(ArrayList<Character> symbolAL) {
			

			// split list into header and content
			int delimiterIndexI = symbolAL.indexOf('$') + 8;
			List<Character> headerSymL = symbolAL.subList(0, delimiterIndexI);
			List<Character> contentSymL = 
					symbolAL.subList(delimiterIndexI, symbolAL.size());
			
			// figure out sizes of everything and build output array
			int headerByteArrSizeI = headerSymL.size() / 8;
			int contentByteArrSizeI = contentSymL.size() / 2;
			int byteArrSizeI = headerByteArrSizeI + contentByteArrSizeI;		
			byte[] outArr = new byte[byteArrSizeI];
			
			// do header part
			for (int i = 0; i < headerByteArrSizeI; i ++) {
				int headerByteIndexI = i*8;
				List<Character> headerByteSymL = 
						symbolAL.subList(headerByteIndexI, headerByteIndexI + 8);
				
				// fart code for sure :-(
				StringBuffer sb = new StringBuffer();
				
				for (int f = 0; f < 8; f ++) {
					sb.append(headerByteSymL.get(f));
				}
	//System.out.println(sb.toString());			
				// add byte to header 
				//outArr[i] = Byte.parseByte(sb.toString(), 2);
				
				if (sb.charAt(0) != '$') {
				//if (sb.indexOf("$") == -1) {
					int unsignedByteI = Integer.parseInt(sb.toString(), 2) & 0xFF;
					outArr[i] = (byte)unsignedByteI;
				}
				else {
					byte[] dARR = sb.toString().getBytes();
					
					// blerg
					for (int blarg = 0; blarg < dARR.length; blarg++) {
						int indexI = i + blarg;
						outArr[indexI] = dARR[blarg];
					}
					
				}
				
				
	//System.out.println(outArr[i] + "*");
				
			}

	System.out.println(headerByteArrSizeI + " " + contentByteArrSizeI);
			
			// do content part
			for (int i = headerByteArrSizeI + 7; i < byteArrSizeI; i ++) {
				//int outArrIndexI = (i == 0) ? i : i-1;
						
				// you are so going to regret this later...
				int symbolIndexI = 
						((i - headerByteArrSizeI) * 2) + delimiterIndexI - 14;

				
	//System.out.println(symbolIndexI);			
	//for (int a = symbolIndexI; a < symbolIndexI + 1; a ++) {
//			System.out.print(" " + symbolAL.get(a));
	//}
	//System.out.println("\n");	
				
				
				String byteS = symbolAL.get(symbolIndexI).toString() + 
									symbolAL.get(symbolIndexI+1).toString();
				//byte tempB = (byte)Integer.parseInt(byteS, 16);
				//outArr[i] = tempB;

				
				/*
				 * note ! dropping a few bytes here!
				 * .
				 * 
				 * 
				 */
				//if (byteS.contains("$")) continue;
				
				
				
				outArr[i] = (byte)Integer.parseInt(byteS, 16);

			}
			
			return outArr;
			
		}
		
		
		
		
		
		
		
		public static ArrayList<Character> byteArrToSymbolList(byte[] byteArr) {
			// jeebus fucking christ why are you still making these fucking
			// character lists?!?#!?@#!@?
				
			// stage some variables. using stringbuffer to reduce resource 
			// footprint
			ArrayList<Character> inHexAL = new ArrayList<Character>();
				
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
		
		
		public static byte[] zipStream(byte[] byteArr) throws IOException {
			InputStream inStream = new ByteArrayInputStream(byteArr);
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			GZIPOutputStream gzOutStream = new GZIPOutputStream(outStream);
			
			// buffer
			byte[] bufferArr = new byte[1024];
			int count;
			
			while ((count = inStream.read(bufferArr)) > 0) {
				gzOutStream.write(bufferArr, 0, count);
			}
			
			byte[] returnByteArr = outStream.toByteArray();
			inStream.close();
			outStream.close();
			gzOutStream.close();
			
			return returnByteArr;
		}
		
		
		
		
		
		
		
		public static ArrayList<Character> unInvert(ArrayList<Character> listIn) {
			
			int headerDelimeterI = listIn.indexOf('$') + 8;
			ArrayList<Character> invertFlagAL = 
					(ArrayList<Character>) listIn.subList(0, headerDelimeterI);
			
			ArrayList<Character> symbolAL = 
					(ArrayList<Character>) 
						listIn.subList(headerDelimeterI + 1, listIn.size());
			
			for (int i = 0; i < invertFlagAL.size(); i ++) {
				int bandIndexI = i * 8;
				
				if (invertFlagAL.get(i).equals('1')) {
					Util.invertList(
							(ArrayList<Character>) 
							symbolAL.subList(bandIndexI, bandIndexI + 8));
				}
				
				
			}
			
			return symbolAL;
			
		}
		
	
	
	
}
