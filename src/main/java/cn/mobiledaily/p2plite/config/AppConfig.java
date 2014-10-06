package cn.mobiledaily.p2plite.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.inject.Inject;

/**
 * Created by johnson on 14-10-6.
 */
@Configuration
@PropertySource("classpath:/config.properties")
@EnableMongoRepositories(basePackages = "cn.mobiledaily.p2plite.repository")
@ComponentScan(basePackages = {"cn.mobiledaily.p2plite"}, includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = {"cn.mobiledaily.p2plite.web.*"}))
public class AppConfig {
    @Inject
    Environment environment;

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        final String host = environment.getProperty("db.host");
        final int port = Integer.valueOf(environment.getProperty("db.port"));
        final String dbName = environment.getProperty("db.name");
        return new SimpleMongoDbFactory(new MongoClient(host, port), dbName);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
}
