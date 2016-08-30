package app;

import client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Vasil on 25.08.2016.
 */
@Configuration
public class AppConfig {


    @Bean
    public Client getClient(){
        return new Client("7","Mark","Booo");
    }
}
