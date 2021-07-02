package framework;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import util.Constants;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class WebCore {

    public static WebDriver driver;
    public static ExtentReports reports;
    public static ExtentTest logger;
    public ExtentHtmlReporter htmlReporter;

    @BeforeMethod
    @Parameters({"BrowserName"})
    public void setup(String BrowserName, Method testMethod){
        logger = reports.startTest(testMethod.getName());
        setUpDriver(BrowserName);
        driver.manage().window().maximize();
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @BeforeTest
    public void initializeReport(){
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + File.separator + "reports" + "AutomationReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);

    }

    @AfterMethod
    public void tearDown(ITestResult result)throws IOException {

        if (result.getStatus()==ITestResult.SUCCESS) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + " Passed";
            Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        }else if (result.getStatus() == ITestResult.FAILURE){
            String methodName = result.getMethod().getMethodName();
            String logText = "Test Case: " + methodName + " Failed";
            Markup markup = MarkupHelper.createLabel(logText, ExtentColor.RED);
        }
        driver.quit();
    }


    public byte[] saveScreenshot(byte[] screenShot) {

        return screenShot;
    }

    @AfterTest
    public void afterTestMethod(){
        reports.flush();
    }


    public void setUpDriver(String browserName){
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+File.separator + "drivers" + File.separator + "chromedriver.exe");
            driver = new ChromeDriver();
        }
    }
}
