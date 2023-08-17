package testRunner;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		//features= {".//features"},         //to run all features files in features folder
		//features= {".//features/LoginDDTWithoutExcel.feature"},  //to run only specific feature file
		features= {".//features//Login.feature"},
	  //features= {".//features/Login.feature",".//features/LoginDDTWithExce.feature"},  //to run some specific features
	 //features="@target/rerun.txt",   // Runs only failures
		
		glue="stepDefinitions",                     //providing package of stepDefinitions
	    plugin= {
	    		"pretty", "html:reports/myreport.html",    //reports with html and json format
        		"json:reports/myreport.json",
        		"rerun:target/rerun.txt"   //Mandatory to capture failures
	    		
	    },
		dryRun=false,      //default value of dryrun is true. If it is true it checks only
		                   //whether corresponding method is available for each step
		                   //or not it doesn't run methods if dry run value is true
		monochrome=true ,  //removes junk letter in the console
		
		
		tags = "@sanity"	//Scenarios tagged with @sanity,
	    //tags = "@sanity and @regression"	//Scenarios tagged with both @sanity and @regression
	    //tags = "@sanity or @regression"	 //Scenarios tagged with either @sanity or @regression
	   //tags = "@sanity and not @regression" //Scenarios tagged with @sanity but not tagged with @regression
		)

public class TestRunner {

}
