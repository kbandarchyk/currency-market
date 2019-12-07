package app.adminserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
@PropertySource({"classpath:/profile.properties", "classpath:/application.properties"})
public class AdminServerApp {

    public static void main(String [] args){
        SpringApplication.run(AdminServerApp.class, args);
    }
}
