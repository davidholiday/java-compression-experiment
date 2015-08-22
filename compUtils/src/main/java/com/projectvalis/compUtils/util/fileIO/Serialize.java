package com.projectvalis.compUtils.util.fileIO;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;


/**
 * utilities to serialize processed data.
 * 
 * @author funktapuss
 *
 */
public class Serialize {

	/**
	 * takes a byte array, gzips it, then serializes it with a file name 
	 * specified by caller. method will automatically append filename extension.
	 * 
	 * @param byteArr
	 * 		byte array containing the data you want to zip + serialize.
	 * @param outName
	 * 		what you want to call the serialized file. 
	 * @throws IOException
	 */
	public static void zipAndSerialize(byte[] byteArr, String outName) 
			throws IOException {
		InputStream inStream = new ByteArrayInputStream(byteArr);
		FileOutputStream fileOutStream = new FileOutputStream(outName + ".gz");
		GZIPOutputStream gzOutStream = new GZIPOutputStream(fileOutStream);		
		
		// buffer
		byte[] bufferArr = new byte[1024];
		int count;
		
		while ((count = inStream.read(bufferArr)) >= 0) {
			gzOutStream.write(bufferArr, 0, count);
		}
		
		gzOutStream.close();
		fileOutStream.close();
		inStream.close();						
	}
	
	
	/**
	 * takes a byte array and serializes it with file name specified by caller. 
	 * this method does not append any filename extension. 
	 * 
	 * @param byteArr
	 * 		byte array containing the data you want to zip + serialize.
	 * @param outName
	 * 		what you want to call the serialized file. 
	 * @throws IOException
	 */
	public static void serializeByteArr(byte[] byteArr, String outName) 
			throws IOException {
		InputStream inStream = new ByteArrayInputStream(byteArr);
		FileOutputStream fileOutStream = new FileOutputStream(outName);
		
		// buffer
		byte[] bufferArr = new byte[1024];
		int count;
		
		while ((count = inStream.read(bufferArr)) >= 0) {
			fileOutStream.write(bufferArr, 0, count);
		}
		
		fileOutStream.close();
		inStream.close();
	}
	
	
	
	/**
	 * takes an ArrayList<Character>, treats each char as a hex nibble, then
	 * serializes it. This method is aware of the '$' header character.
	 * 
	 * @param symbolAL
	 * 		hex list representing data to be serialized. 
	 * @param outName
	 * 		what you want to call the serialized file. 
	 * @throws IOException
	 */
	public static void serializeHexSymbolList(
			ArrayList<Character> symbolAL, 
			String outName) 
			throws IOException {

		byte[] outArr = new byte[symbolAL.size() / 2];
		
		for (int i = 0; i < outArr.length; i ++) {
			String byteS = symbolAL.get(i).toString() + 
								symbolAL.get(i+1).toString();
			
			byte tempB;
			if (!byteS.contains("$")) {
				tempB = (byte)Integer.parseInt(byteS, 16);
			}
			else {
				tempB = (byte)(new Character('$').charValue());
				i++;
			}
			
			outArr[i] = tempB;		
		}

		FileOutputStream out = new FileOutputStream(outName);
		out.write(outArr);
		out.close();
		
	}
	
	
	
}
