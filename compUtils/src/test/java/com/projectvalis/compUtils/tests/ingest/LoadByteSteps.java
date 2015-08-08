package com.projectvalis.compUtils.tests.ingest;

import java.io.File;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;

import com.projectvalis.compUtils.util.fileIO.Ingest;


/**
 * BDD tests for the ingest class
 * @author funktapuss
 *
 */
public class LoadByteSteps extends Steps {

	private String fNameS;
	private byte[] byteARR;
		
	@Given("a file, $filename")
	public void setFileName(@Named("filename") String filename) {
		File file = new File(getClass().getResource("/" + filename).getFile());
		fNameS = file.getPath();
	}
	
	@When("the caller loads the file as a byte array")
	public void loadFile() {
		byteARR = Ingest.loadFile(fNameS);
	}
	
	@Then("the byte array that is returned contains the "
			+ "correct number of bytes.") 
	public void checkArrSize() {
		File file = new File(fNameS);
		Assert.assertTrue(
				"loading error - "
				+ "the file and the resultant byte array are different sizes!", 
				(long)byteARR.length == file.length());
	}

	
	
}
