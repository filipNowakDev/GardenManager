package pl.filipnowakdev.gardenmanager.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfiguration {

    @Value("${mongodb.connection.string}")
    private String dbConnectionString;

    @Value("${mongodb.database.name}")
    private String dbName;

    public @Bean
    MongoClient mongoClient() {
        return MongoClients.create(dbConnectionString);
    }

    public @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), dbName);
    }
}
