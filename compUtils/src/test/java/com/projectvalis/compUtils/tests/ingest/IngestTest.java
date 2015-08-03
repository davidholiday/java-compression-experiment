package com.projectvalis.compUtils.tests.ingest;


import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStory;  
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;



/**
 * not 100% on this yet - I *think* it's linking the stories to the step logic.
 * @author funktapuss
 *
 */
public class IngestTest extends JUnitStory {
	
    @Override 
    public Configuration configuration() { 
    	return new MostUsefulConfiguration(); 
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new LoadByteSteps());       
    }
   
}
