package com.pbs.es;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
@EnableEurekaServer
@SpringBootApplication
public class PecuniaEurekaServerApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(PecuniaEurekaServerApplication.class, args);
	}

}
