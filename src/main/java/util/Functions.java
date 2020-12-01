package util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Functions {

    private Functions() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static String md5(String input) {
        try {
            return String.format("%032x",
                    new BigInteger(1, MessageDigest.getInstance("MD5").digest(input.getBytes(StandardCharsets.UTF_8))));
        } catch (NoSuchAlgorithmException e) {
            // pass
        }
        return "";
    }
}
