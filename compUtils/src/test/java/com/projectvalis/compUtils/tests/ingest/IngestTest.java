package com.projectvalis.compUtils.tests.ingest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.jbehave.core.configuration.Configuration;  
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.junit.JUnitStory;  
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;  
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InjectableStepsFactory;  
import org.jbehave.core.steps.InstanceStepsFactory;  
import org.jbehave.core.steps.Steps;
import org.junit.Test;

import static org.jbehave.core.reporters.Format.CONSOLE;  
import static org.jbehave.core.reporters.Format.TXT;


/**
 * not 100% on this yet - I *think* it's linking the stories to the step logic.
 * @author funktapuss
 *
 */
public class IngestTest extends JUnitStory {
	  
   @Override  
   public Configuration configuration() {  
     return super.configuration()  
         .useStoryReporterBuilder(  
             new StoryReporterBuilder()  
                 .withDefaultFormats()  
                 .withFormats(CONSOLE, TXT));  
   }  

//   // Here we specify the steps classes  
//   @Override  
//   public InjectableStepsFactory stepsFactory() {  
//	   return new InstanceStepsFactory(
//			   configuration(), new LoadByteSteps());  
//   }  
	
	
	
//    @Override
//    public Configuration configuration() {
//        URL storyURL = null;
//        try {
//            // This requires you to start Maven from the project directory
//            storyURL = new URL("file://" + System.getProperty("user.dir")
//                    + "/src/test/resources/");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        return new MostUsefulConfiguration().useStoryLoader(
//                new LoadFromRelativeFile(storyURL)).useStoryReporterBuilder(
//                new StoryReporterBuilder().withFormats(Format.HTML));
//    }
// 
    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), new LoadByteSteps())
                .createCandidateSteps();
    }
	
   
   @Override
   @Test
   public void run() {
       try {
           super.run();
       } catch (Throwable e) {
           e.printStackTrace();
       }
   }
   
}
