package infostructure;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Start: POG♿нали" );
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Success: всё норм, тест прошел");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Fail: тестик провалился почему то");
    }

}
