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

import java.util.Collections;

@Configuration
public class MongoConfig {

    private final Logger logger = LogManager.getLogger(MongoConfig.class);

    @Autowired
    private MongoProperties mongoProperties;

    @Bean
    public MongoClient mongoClient() {
        logger.info("Creating MongoClient with host: {} and port: {}", mongoProperties.getHost(), mongoProperties.getPort());
        MongoCredential credential = MongoCredential.createCredential(
                mongoProperties.getUsername(),
                mongoProperties.getDatabase(),
                mongoProperties.getPassword().toCharArray()
        );
        logger.debug("MongoCredential created for user: {}", mongoProperties.getUsername());

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
    { role: "readWrite", db: "employeedb" },
    { role: "dbAdmin", db: "employeedb" }
  ]
})*/

