package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public final class InputUtil {

    private InputUtil() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String getFirstLineFromFile(String file) throws IOException {
        Stream<String> lineStream = Files.lines(Paths.get(file));
        try {
            return lineStream.findFirst().orElse(null);
        }
        finally {
            lineStream.close();
        }
    }

    public static Stream<String> getAllLinesFromFile(String file) throws IOException {
        return Files.lines(Paths.get(file));
    }
}
