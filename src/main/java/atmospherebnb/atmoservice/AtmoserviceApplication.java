package atmospherebnb.atmoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "atmospherebnb.atmoservice.models")
public class AtmoserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmoserviceApplication.class, args);
	}

}
