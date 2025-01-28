//package Utility;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//
//import com.aventstack.extentreports.reporter.ExtentReporter;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//import org.openqa.selenium.devtools.v129.page.model.Screenshot;
//import org.testng.IReporter;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import java.awt.event.ItemListener;
//
//public class ExtentReportListener implements ITestListener {
//    public ExtentSparkReporter expExtentSparkReporter;
//    public ExtentReports extentReports;
//    static public ExtentTest extentTest;
//
//    @Override
//    public void onStart(ITestContext context) {
//        expExtentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/ExtentTestReport/TestReport.html");
//        expExtentSparkReporter.config().setTheme(Theme.DARK);
//        expExtentSparkReporter.config().setDocumentTitle("Test Automation Report");
//
//        extentReports = new ExtentReports();
//        extentReports.attachReporter(expExtentSparkReporter);
//        extentReports.setSystemInfo("Machine Used", "Local System");
//        extentReports.setSystemInfo("Environment", "Test Environment 1");
//        extentReports.setSystemInfo("User", "Preethi");
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        extentTest = extentReports.createTest(result.getName());
//        extentTest.log(Status.INFO, "Test Started");
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        extentTest.log(Status.PASS, "Test Passed " + result.getName());
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        extentTest = extentReports.createTest(result.getName());
//        extentTest.log(Status.FAIL, "Test Failed " + result.getName());
//
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        extentTest = extentReports.createTest(result.getName());
//        extentTest.log(Status.SKIP, "Test Skipped " + result.getName());
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        extentReports.flush();
//    }
//}
