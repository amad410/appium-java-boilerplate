package runner;

import cucumber.api.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;

//@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features"
        ,glue= {"definitions"}
        /*,tags = {"@foo","not@bar"}*/
        /* ,plugin= {"pretty","html:target/cucumber-html-report.html"}*/
        ,plugin = { "pretty","html:target/cucumber","summary"}
        ,snippets = CAMELCASE
        ,dryRun = false
        ,monochrome = true)
public class Runner2Test  {




}
