package infostructure;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

public class ElementNotFountRetryer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY = 2; // максимум 2 попытки (всего 3 запуска)

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY && shouldRetryBecauseOfElementNotFound(result)) {
            retryCount++;
            System.out.println("Retrying test for attempt " + retryCount +
                    " due to: " + getFailureReason(result));
            return true;
        }
        return false;
    }

    private boolean shouldRetryBecauseOfElementNotFound(ITestResult result) {
        Throwable throwable = result.getThrowable();
        if (throwable == null) return false;

        // Проверяем исключение и его причины
        return hasNoSuchElementException(throwable) ||
                hasTimeoutWithElementNotFound(throwable) ||
                containsElementNotFoundMessage(throwable);
    }

    private boolean hasNoSuchElementException(Throwable t) {
        Throwable current = t;
        while (current != null) {
            if (current instanceof NoSuchElementException) {
                return true;
            }
            current = current.getCause();
        }
        return false;
    }

    private boolean hasTimeoutWithElementNotFound(Throwable t) {
        Throwable current = t;
        while (current != null) {
            if (current instanceof TimeoutException) {
                String msg = current.getMessage();
                if (msg != null && (msg.contains("Element not found") ||
                        msg.contains("no such element"))) {
                    return true;
                }
            }
            current = current.getCause();
        }
        return false;
    }

    private boolean containsElementNotFoundMessage(Throwable t) {
        Throwable current = t;
        while (current != null) {
            String msg = current.getMessage();
            if (msg != null && msg.toLowerCase().contains("element not found")) {
                return true;
            }
            current = current.getCause();
        }
        return false;
    }

    private String getFailureReason(ITestResult result) {
        Throwable t = result.getThrowable();
        return t != null ? t.getMessage() : "Unknown error";
    }
}