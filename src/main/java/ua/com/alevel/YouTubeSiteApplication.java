package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ua.com.alevel.properties.ImageProperties;
import ua.com.alevel.properties.MailProperties;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@EnableConfigurationProperties({
        MailProperties.class,
        ImageProperties.class
})
public class YouTubeSiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouTubeSiteApplication.class, args);
    }
}
