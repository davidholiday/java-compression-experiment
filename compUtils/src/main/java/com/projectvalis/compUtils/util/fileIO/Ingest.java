package com.projectvalis.compUtils.util.fileIO;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * utilities to ingest data for compression processing. 
 * 
 * @author funktapuss
 *
 */
public class Ingest {
	static Logger logger = LoggerFactory.getLogger(Ingest.class);
	
	
	/**
	 * loads a file as a byte array. requires full pathname of file.
	 * 
	 * @param fName
	 * 		the name of the file you want to load. 
	 * @return
	 * 		byte array containing loaded file. 
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
	
	
	
	/**
	 * loads a file as an ArrayList<Character>, where each char represents 
	 * a Hex nibble.
	 * 
	 * @param fName
	 * 		the name of the file you want to load. 
	 * 
	 * @return
	 * 		list of chars representing ingested file in hex.
	 */
	public static ArrayList<Character> loadFileAsHex(String fName) {
		// load file
		byte[] byteArr = loadFile(fName);
		logger.info("file size is: " + byteArr.length + " bytes");
		
		// stage some variables
		ArrayList<Character> inHexAL = new ArrayList<Character>();
		
		// process each byte
		for (int i = 0; i < byteArr.length; i ++) {	
			String hexS = Integer.toHexString(byteArr[i] & 0xFF).toUpperCase();
			if (hexS.length() == 1) hexS = "0" + hexS;
			inHexAL.add(hexS.charAt(0));
			inHexAL.add(hexS.charAt(1));
		}
		
		//return to caller
		return inHexAL;
	}
	
	
	/**
	 * load file as an ArrayList<Character>, where each char represents three 
	 * bits of data. each digit gets its own indice, each set of three 
	 * represents one byte in the file.
	 * 
	 * @param fName
	 * 		the name of the file you want to load. 
	 * @return
	 * 		list of chars representing ingested file as octal.
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
	
	
	/**
	 * assumes the file being read is comprised only of ASCII 48/49 ("0", "1").
	 * reads each as a nibble and returns an ArrayList<Character>, where each
	 * char represents a nibble of data. 
	 * 
	 * @param fName
	 * 		the name of the file you want to load. 
	 * @return
	 * 		list of chars representing ingested file as hex
	 */
	public static ArrayList<Character> loadFileAsStringHex(String fName) {
		// load file
		byte[] byteArr = loadFile(fName);
		logger.info("file size is: " + byteArr.length + " bytes");
		
		// stage some variables
		ArrayList<Character> inHexAL = new ArrayList<Character>();

		
		// process each byte
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
				logger.info("found something I can't parse, yo!");
				logger.info(byteArr[i] + " " + i);
				System.exit(8);
			}
			

		}
		
		//return to caller
		return inHexAL;
	}
	
	

}
