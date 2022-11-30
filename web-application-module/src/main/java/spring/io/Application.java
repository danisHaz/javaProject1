package spring.io;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class})
public class Application {
    
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        System.out.println(context.getApplicationName());
    }
}
