package app.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:/profile.properties", "classpath:/application.properties"})
public class AppManagement {

    public static void main(String [] args) {
        SpringApplication.run(AppManagement.class, args);
    }

}
