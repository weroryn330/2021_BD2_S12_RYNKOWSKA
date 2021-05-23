package polsl.tab.skiresort.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry cors){
        cors.addMapping("/api/**").allowedOrigins("http://localhost:4200").allowedMethods("GET","POST");
    }
}
