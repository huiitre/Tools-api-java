package fr.huiitre.tools.config;

import fr.huiitre.tools.middlewares.TokenInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Crée un bean pour ton intercepteur
    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Enregistre ton intercepteur sur toutes les routes,
        // ou adapte le pattern selon tes besoins.
        registry.addInterceptor(tokenInterceptor())
            .addPathPatterns("/**");
    }
}
