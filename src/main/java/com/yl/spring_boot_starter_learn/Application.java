package com.yl.spring_boot_starter_learn;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application{
	
	
	
	@RequestMapping("/say")
	public String say() {
		return "hello world!";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			protected void postProcessContext(Context context) {
				SecurityConstraint sc = new SecurityConstraint();
				sc.setUserConstraint("CONFIDENTIAL");
				SecurityCollection securityCollection = new SecurityCollection();
				securityCollection.addPattern("/*");
				sc.addCollection(securityCollection);
				context.addConstraint(sc);
			};
		};
		tomcat.addAdditionalTomcatConnectors(httpConnector());
		return tomcat;
	}
	@Bean
	public Connector httpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(8443);
		return connector;
	}
}
