package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.concurrent.ThreadLocalRandom;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int attempt = 0;
    private static final int MAX = 2;

    @Override
    public boolean retry(ITestResult result) {
        // Retry only for transient failures (you can add smarter heuristics)
        if (attempt < MAX) {
            attempt++;
            sleepWithBackoff(attempt);
            return true;
        }
        return false;
    }

    private void sleepWithBackoff(int attempt) {
        long base = (long) Math.pow(2, attempt) * 500L;
        long jitter = ThreadLocalRandom.current().nextLong(200, 600);
        try { Thread.sleep(Math.min(base + jitter, 8000)); } catch (InterruptedException ignored) {}
    }
}
