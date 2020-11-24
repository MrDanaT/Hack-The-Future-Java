package be.ap.ti.htf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @ComponentScan({ "be.ap.ti.htf" })
// @EntityScan("be.ap.ti.htf")
// @EnableJpaRepositories(basePackages = "be.ap.ti.htf")
public class HtfApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtfApplication.class, args);
	}

}
