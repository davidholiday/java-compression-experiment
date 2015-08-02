package com.projectvalis.compUtils.tests.ingest;

import java.io.File;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.embedder.Embedder;

import com.projectvalis.compUtils.util.fileIO.Ingest;


/**
 * BDD tests for the ingest class
 * @author funktapuss
 *
 */
public class LoadByteSteps extends Embedder {

	private String fNameS;
	private byte[] byteARR;
		
	@Given("a file, $filename")
	public void setFileName(String filename) {
		fNameS = filename;
	}
	
	@When("the caller loads the file as a byte array")
	public void loadFile() {
		byteARR = Ingest.loadFile(fNameS);
	}
	
	@Then("the byte array that is returned contains the "
			+ "correct number of bytes.") 
	public void checkArrSize() {
		File file = new File(fNameS);
		assert ((long)byteARR.length == file.length());
	}

	
	
}
