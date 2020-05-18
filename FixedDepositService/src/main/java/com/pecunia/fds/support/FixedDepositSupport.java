package com.pecunia.fds.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pecunia.fds.dto.Customer;

@Component
public class FixedDepositSupport
{
	@Autowired
	RestTemplate restTemplate;
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	@HystrixCommand(fallbackMethod = "fallBackAccount")
	public Customer getCustomerDetails(int cust_id)
	{
		return restTemplate.getForEntity("http://account-service/getCustomer/"+cust_id, Customer.class).getBody();
	}
	public Customer fallBackAccount(int x)
	{
		return new Customer();
	}
}
