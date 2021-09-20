package patrik.worldui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class StarterWorldUi {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(StarterWorldUi.class);

        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8081"));
        app.run(args);
    }
}
