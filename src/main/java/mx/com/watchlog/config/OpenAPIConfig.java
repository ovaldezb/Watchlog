package mx.com.watchlog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI(){
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8090");
        devServer.setDescription("Server URL for dev");

        Contact contact = new Contact();
        contact.setEmail("omar@gmail.com");
        contact.url("www.omegarho.me.co");
        contact.setName("Omega Rho");
        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
        Info info = new Info()
                .title("Watch Log")
                .version("1.0.0")
                .contact(contact)
                .description("This api exposes end point to handle watch log")
                .license(mitLicense);

        return  new OpenAPI().info(info).servers(List.of(devServer));

    }
}