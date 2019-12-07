package app.balancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:/profile.properties", "classpath:/application.properties"})
@EnableZuulProxy
@EnableDiscoveryClient
public class BalancerApp {

    public static void main(String [] args) {
        SpringApplication.run(BalancerApp.class, args);
    }

}
