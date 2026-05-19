package infostructure;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retryer implements IRetryAnalyzer {

    private int count = 0;
    private final static int MAX_RETRY = 2;


    @Override
    public boolean retry(ITestResult iTestResult) {

        if (iTestResult.getStatus() == 2) {
            count++;
            return true;
        }
        return false;
    }

}
