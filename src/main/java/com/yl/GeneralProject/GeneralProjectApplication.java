package com.yl.GeneralProject;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class GeneralProjectApplication extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GeneralProjectApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(GeneralProjectApplication.class, args);
		SpringApplication app = new SpringApplication(GeneralProjectApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
