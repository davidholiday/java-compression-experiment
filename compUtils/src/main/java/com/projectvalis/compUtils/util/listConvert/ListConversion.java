package com.projectvalis.compUtils.util.listConvert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * methods to convert lists of data into lists which encode the data 
 * differently.
 * 
 * @author snerd
 *
 */
public class ListConversion {
	
	
	/**
	 * converts a hex symbol list into a byte array
	 * 
	 * @param symbolAL
	 * 		symbol list to be converted
	 * @return
	 * 		byte array containing the dataset
	 */
	public static byte[] symbolListToByteArr(ArrayList<Character> symbolAL) {		
		byte[] outArr = new byte[symbolAL.size() / 2];
		
		for (int i = 0; i < outArr.length; i ++) {			
			int symbolIndexI = i * 2;
			
			String byteS = symbolAL.get(symbolIndexI).toString() + 
								symbolAL.get(symbolIndexI+1).toString();
						
			byte tempB = (byte)Integer.parseInt(byteS, 16);
			outArr[i] = tempB;
		}
		
		return outArr;
	}
		
		
	/**
	 * converts a hex symbol list that contains the header delimiter into a 
	 * byte array.
	 * 	
	 * @param symbolAL
	 * 		symbol list with header delimiter to be converted.
	 * @return
	 * 		byte array version of the dataset.  
	 */
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
					symbolAL.subList(
							headerByteIndexI, headerByteIndexI + 8);

			// fart code for sure :-(
			StringBuffer sb = new StringBuffer();

			for (int f = 0; f < 8; f ++) {
				sb.append(headerByteSymL.get(f));
			}

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

		}


		// do content part
		for (int i = headerByteArrSizeI + 7; i < byteArrSizeI; i ++) {
			//int outArrIndexI = (i == 0) ? i : i-1;

			// you are so going to regret this later...
			int symbolIndexI = 
					((i - headerByteArrSizeI) * 2) + delimiterIndexI - 14;	


					String byteS = symbolAL.get(symbolIndexI).toString() + 
							symbolAL.get(symbolIndexI+1).toString();

					outArr[i] = (byte)Integer.parseInt(byteS, 16);

		}

		return outArr;

	}
		
		
			
	/**
	 * converts a byte array into a hex symbol list
	 * 	
	 * @param byteArr
	 * 		byte array contianing data to be converted
	 * @return
	 * 		list containing hex representation of dataset. 
	 */
	public static ArrayList<Character> byteArrToSymbolList(byte[] byteArr) {
		ArrayList<Character> inHexAL = new ArrayList<Character>();

		// process each byte
		// ugly ugly ugly -- you know better
		for (int i = 0; i < byteArr.length; i ++) {	
			String hexS = 
					Integer.toHexString(byteArr[i] & 0xFF).toUpperCase();
			if (hexS.length() == 1) hexS = "0" + hexS;
			inHexAL.add(hexS.charAt(0));
			inHexAL.add(hexS.charAt(1));
		}

		//return to caller
		return inHexAL;

	}


	
	/**
	 * takes a byte array, zips it, and returns the zipped dataset in the form
	 * of another byte array.
	 * 
	 * @param byteArr
	 * 		byte array to be zipped. 
	 * @return
	 * 		zipped byte array
	 * @throws IOException
	 */
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
		
		
		
		
	
	/**
	 * takes all the symbols in the passed list and inverts all symbols 
	 * (8-15) into (0-7)s, and visa versa. 
	 * 
	 * @param symbolL
	 * 		list to be inverted 
	 */
	public static void invertList (ArrayList<Character> symbolL) {

		for (int i = 0; i < symbolL.size(); i ++) {
			int curValI = Integer.parseInt(symbolL.get(i).toString(), 16);
			int newValI = (curValI > 7) ? (curValI - 8) : (curValI + 8);
			symbolL.set(i, 
					Integer.toHexString(newValI).toUpperCase().charAt(0));
		}

	}
	
	
	
	
	/**
	 * takes all the symbols in the passed list and inverts all symbols 
	 * (4-7) into (0-3)s, and visa versa. 
	 * 
	 * @param symbolL
	 * 		list to be inverted 
	 */
	public static void invertList4 (ArrayList<Character> sublist) {

		for (int i = 0; i < sublist.size(); i ++) {
			int curValI = Integer.parseInt(sublist.get(i).toString(), 16);
			int newValI = (curValI > 3) ? (curValI - 4) : (curValI + 4);
			sublist.set(i, 
					Integer.toHexString(newValI).toUpperCase().charAt(0));
		}

	}
	
	
	
	/**
	 * takes all the symbols in the passed list and inverts all symbols 
	 * (2-3) into (0-1)s, and visa versa. 
	 * 
	 * @param symbolL
	 * 		list to be inverted 
	 */
	public static void invertList2 (ArrayList<Character> sublist) {

		for (int i = 0; i < sublist.size(); i ++) {
			int curValI = Integer.parseInt(sublist.get(i).toString(), 16);
			int newValI = (curValI > 1) ? (curValI - 2) : (curValI + 2);
			sublist.set(i, 
					Integer.toHexString(newValI).toUpperCase().charAt(0));
		}

	}		
		
	
	
	
	
	/**
	 * the reverse of invertList - takes all the symbols in the passed list and
	 * inverts all symbols (0-7) into (8-15)s, and visa versa. 
	 * 
	 * @param listIn
	 * @return
	 */
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
				invertList( (ArrayList<Character>) 
					symbolAL.subList(bandIndexI, bandIndexI + 8));
			}

		}

		return symbolAL;
	}
		
	
	
	
}
