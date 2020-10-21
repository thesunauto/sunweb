package vn.automation.sunweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import vn.automation.sunweb.storage.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SunwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunwebApplication.class, args);
    }

}
