package com.daimler.employeeconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.daimler.employeeconsumer.*"})
@EnableDiscoveryClient
@EnableFeignClients
public class EmployeeConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeConsumerApplication.class, args);
	}

}
