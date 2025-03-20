package fr.huiitre.tools;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToolsApiApplication {

    public static void main(String[] args) {
        // Charger le fichier .env
        Dotenv dotenv = Dotenv.load();
        
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
        
        SpringApplication.run(ToolsApiApplication.class, args);
    }
}
