package dabang.star.cafe.infrastructure.service;

import dabang.star.cafe.domain.user.EncryptService;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
public class SHA256EncryptService implements EncryptService {

    @Override
    public String encrypt(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte[] byteData = messageDigest.digest();

            StringBuilder stringBuilder = new StringBuilder();
            for (byte byteDatum : byteData) {
                String hex = Integer.toHexString(0xff & byteDatum);
                if (hex.length() == 1) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(hex);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
