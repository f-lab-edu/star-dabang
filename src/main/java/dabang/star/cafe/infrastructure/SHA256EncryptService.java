package dabang.star.cafe.infrastructure;

import dabang.star.cafe.domain.member.EncryptService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public class SHA256EncryptService implements EncryptService {

    /**
     * SHA-256 Encryption
     *
     * @param original
     */
    public String encrypt(String original) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] encoders = md.digest(original.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < encoders.length; i++) {
                sb.append(Integer.toString((encoders[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }
}
