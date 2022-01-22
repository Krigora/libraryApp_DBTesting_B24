package com.cydeo.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions( features="src/test/resources/features" ,
                  glue="com/cydeo/steps",
                  publish = true,
                  plugin = {
                          "html:target/cucumber-report.html",
                          "rerun:target/rerun.txt", //store failed scenario names into rerun.txt file
                  },

          dryRun = false
          ,tags="@wip"

)
public class TestRunner {
}
