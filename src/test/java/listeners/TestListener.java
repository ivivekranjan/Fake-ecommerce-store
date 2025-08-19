package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override public void onStart(ITestContext context) { System.out.println("Suite started: " + context.getName()); }
    @Override public void onFinish(ITestContext context) { System.out.println("Suite finished: " + context.getName()); }

    @Override public void onTestStart(ITestResult result) { System.out.println("Test started: " + result.getName()); }
    @Override public void onTestSuccess(ITestResult result) { System.out.println("PASS: " + result.getName()); }
    @Override public void onTestFailure(ITestResult result) {
        System.out.println("FAIL: " + result.getName());
        if (result.getThrowable() != null) result.getThrowable().printStackTrace();
    }
    @Override public void onTestSkipped(ITestResult result) { System.out.println("SKIP: " + result.getName()); }
}
