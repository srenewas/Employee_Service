package employee.Mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.MongoCredential;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import java.util.Collections;

@Configuration
public class MongoConfig {

    private final Logger logger = LogManager.getLogger(MongoConfig.class);

    @Autowired
    private MongoProperties mongoProperties;

    /*Your Spring Boot app authenticates against employeedb,
    but unless you explicitly set employeedb as the default database when creating the MongoTemplate or using the repository, the data will go to the default test database.
    This is because:
    By default, MongoTemplate uses the database named "test" if no DB is explicitly specified in its bean setup.*/

    @Bean
    public MongoDatabaseFactory mongoDbFactory(MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(
                mongoClient,
                mongoProperties.getDatabase()
        );
    }

    @Bean
    public MongoClient mongoClient() {
        logger.info("Creating MongoClient with host: {} and port: {}", mongoProperties.getHost(), mongoProperties.getPort());
        MongoCredential credential = MongoCredential.createCredential(
                mongoProperties.getUsername(),
                mongoProperties.getDatabase(),
                mongoProperties.getPassword().toCharArray()
        );
        logger.info("MongoCredential created for user: {}", mongoProperties.getUsername());

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(Collections.singletonList(
                                new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort())
                        ))
                )
                .credential(credential)
                .build();
        logger.info("MongoClientSettings built successfully");
        logger.info("MongoDB Connectivity built Successfully...............");

        return MongoClients.create(settings);
    }
}

/*use employeedb
db.createUser({
    user: "admin",
            pwd: "admin",
            roles: [
    { role: "readWrite", db: "employeedb" }
  ]
})*/

