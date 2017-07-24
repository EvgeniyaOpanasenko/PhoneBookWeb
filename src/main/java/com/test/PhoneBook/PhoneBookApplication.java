package com.test.PhoneBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
//@ActiveProfiles("test")
//@Value()
@PropertySources({
		@PropertySource(value = "application-embeded.properties")
})
public class PhoneBookApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(PhoneBookApplication.class, args);
	}
}
