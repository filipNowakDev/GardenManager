package pl.filipnowakdev.gardenmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class GardenManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GardenManagerApplication.class, args);
    }

}
