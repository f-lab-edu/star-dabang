package dabang.star.cafe;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Map;

@EnableAspectJAutoProxy
@SpringBootApplication
public class CafeApplication {

    private static final String SPRING_CONFIG_LOCATION = "spring.config.location";
    private static final String YAMLS = "classpath:/application.yaml,classpath:/aws.yaml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(CafeApplication.class)
                .properties(Map.of(SPRING_CONFIG_LOCATION, YAMLS))
                .run(args);
    }

}
