package yappingbot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Helper class for jUnit tests, like capturing STDOUT to compare.
 */
public class TestHelper {
    final ByteArrayOutputStream uiPrintOut = new ByteArrayOutputStream();

    public void captureStdOut() {
        System.setOut(new PrintStream(uiPrintOut));
    }

    public void stopCapture() throws IOException {
        System.setOut(System.out);
        uiPrintOut.close();
    }

    @Override
    public String toString() {
        return uiPrintOut.toString();
    }
}
