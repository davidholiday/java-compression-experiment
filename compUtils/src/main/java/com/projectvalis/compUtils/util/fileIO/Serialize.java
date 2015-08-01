package com.projectvalis.compUtils.util.fileIO;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;

public class Serialize {

	public static void zipAndSerialize(byte[] byteArr) throws IOException {

		InputStream inStream = new ByteArrayInputStream(byteArr);
		//FileInputStream inStream = new FileInputStream("rfs1.pdf");
		
		FileOutputStream fileOutStream = new FileOutputStream("testout.gz");
		GZIPOutputStream gzOutStream = new GZIPOutputStream(fileOutStream);
		//ZipOutputStream zOutStream = new ZipOutputStream(fileOutStream);
		//zOutStream.putNextEntry(new ZipEntry("rfs1.pdf"));
		
		
		// buffer
		byte[] bufferArr = new byte[1024];
		int count;
		
		while ((count = inStream.read(bufferArr)) >= 0) {
			gzOutStream.write(bufferArr, 0, count);
			//zOutStream.write(bufferArr, 0, count);
		}
		
		//zOutStream.close();
		gzOutStream.close();
		fileOutStream.close();
		inStream.close();
		
				
	}
	
	
	
	public static void serializeByteArr(byte[] byteArr) throws IOException {
		InputStream inStream = new ByteArrayInputStream(byteArr);
		FileOutputStream fileOutStream = new FileOutputStream("test_out_bytes");
		
		// buffer
		byte[] bufferArr = new byte[1024];
		int count;
		
		while ((count = inStream.read(bufferArr)) >= 0) {
			fileOutStream.write(bufferArr, 0, count);
			//zOutStream.write(bufferArr, 0, count);
		}
		
		fileOutStream.close();
		inStream.close();
	}
	
	
	public static void serializeSymbolList(ArrayList<Character> symbolAL) 
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

		FileOutputStream out = new FileOutputStream("testOut");
		out.write(outArr);
		out.close();
		
	}
	
	
	
}
