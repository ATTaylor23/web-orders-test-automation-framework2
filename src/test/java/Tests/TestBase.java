package Tests;

import Utils.Driver;
import Utils.PropertyReader;
import Utils.SeleniumUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.time.Duration;

public class TestBase {

        protected WebDriver driver;
        protected static ExtentReports report;
        protected static ExtentSparkReporter htmlReport;
        protected static ExtentTest logger;

        @BeforeSuite (alwaysRun = true)
        public void setUpReport(){
            report = new ExtentReports();
            String path = System.getProperty("user.dir") + "target/extentReports/extentReport.html";
            htmlReport = new ExtentSparkReporter(path);
            report.attachReporter(htmlReport);

            report.setSystemInfo("Name", "Web Orders Test Automation results");
            report.setSystemInfo("Automation Engineer", "Jane Doe");
            report.setSystemInfo("OS", System.getProperty("OS.name"));
            report.setSystemInfo("browser", PropertyReader.getTheProperties("browser"));

        }


        @BeforeMethod (alwaysRun = true)
        public void setupMethod(Method method){

            driver = Driver.getDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            logger = report.createTest(method.getName());

        }

        @AfterMethod (alwaysRun = true)
        public void tearDownMethod(ITestResult testResult){
            if(testResult.getStatus() == ITestResult.SUCCESS){
                logger.pass("Test PASSED: " + testResult.getTestName());
            }else if(testResult.getStatus() == ITestResult.SKIP){
                logger.skip("Test SKIPPED: " + testResult.getTestName());
            }else if(testResult.getStatus() == ITestResult.FAILURE){
                logger.fail("Test FAILED: " + testResult.getTestName());
                logger.fail(testResult.getThrowable());
                logger.addScreenCaptureFromPath(SeleniumUtils.getScreenshotOnFailure());
            }

            Driver.quitDriver();
        }

         @AfterSuite (alwaysRun = true)
         public void tearDownReport(){
            report.flush();     // write the output to HTML file
    }

}
