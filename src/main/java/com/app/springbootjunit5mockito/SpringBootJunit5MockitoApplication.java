package com.app.springbootjunit5mockito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@PropertySource("file:${user.dir}/.env")
public class SpringBootJunit5MockitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJunit5MockitoApplication.class, args);
		System.out.println("!!!!!!..........Running.........!!!!!!");
	}

}
