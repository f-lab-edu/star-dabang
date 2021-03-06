package dabang.star.cafe.infrastructure.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Log4j2
@Configuration
public class FCMConfig {

    @Configuration
    @Profile("!prod")
    static class NotProdFCMConfig {

        @Value("${firebase.config}")
        private String FIREBASE_CONFIG_PATH;

        private FirebaseApp firebaseApp;

        @PostConstruct
        public void init() {
            try {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(FIREBASE_CONFIG_PATH).getInputStream()))
                        .build();

                if (FirebaseApp.getApps().isEmpty()) {
                    this.firebaseApp = FirebaseApp.initializeApp(options);
                    log.info("Firebase has been initialized");
                } else {
                    this.firebaseApp = FirebaseApp.getInstance();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Configuration
    @Profile("prod")
    static class ProdFCMConfig {

        @Value("${firebase.config}")
        private String FIREBASE_CONFIG;

        private FirebaseApp firebaseApp;

        @PostConstruct
        public void init() {
            try {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(FIREBASE_CONFIG.getBytes())))
                        .build();

                if (FirebaseApp.getApps().isEmpty()) {
                    this.firebaseApp = FirebaseApp.initializeApp(options);
                    log.info("Firebase has been initialized");
                } else {
                    this.firebaseApp = FirebaseApp.getInstance();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
