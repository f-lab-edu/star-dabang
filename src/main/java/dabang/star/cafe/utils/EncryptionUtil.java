package dabang.star.cafe.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class EncryptionUtil {

    /**
     * SHA-256 Encryption
     *
     * @param original
     * @return
     */
    public static String encrypt(String original) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = md.digest(original.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < encodedhash.length; i++) {
                sb.append(Integer.toString((encodedhash[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }
}
