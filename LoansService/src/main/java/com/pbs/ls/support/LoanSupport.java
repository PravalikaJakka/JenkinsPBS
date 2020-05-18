package com.pbs.ls.support;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.pbs.ls.dto.Customer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@Component
public class LoanSupport 
{
	@Autowired
	RestTemplate restTemplate;
	@Bean
	@LoadBalanced
	public RestTemplate RestTemplate()
	{
		return new RestTemplate();
	}
	@HystrixCommand(fallbackMethod = "fallBackCustomer")
	public Customer getCustomer(int customerId)
	{
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<Customer> entity = new HttpEntity<Customer>(headers);
		Customer customer = restTemplate.exchange("http://account-service/getCustomer/"+customerId,HttpMethod.GET,entity,Customer.class).getBody();
   	return customer;
	}
	public Customer fallBackCustomer(int x)
	{
		System.out.println("from fall back method of loansupport");
		return new Customer();
	}
	
	@HystrixCommand(fallbackMethod = "fallBackDeleteCustomer")
	public String deleteCustomer(int customerId)
	{
		HttpHeaders headers = new HttpHeaders();
	    HttpEntity<Customer> entity = new HttpEntity<Customer>(headers);
		String message = restTemplate.exchange("http://account-service/deleteCustomer/"+customerId,HttpMethod.DELETE,entity,String.class).getBody();
   	return message;
	}
	public String fallBackDeleteCustomer(int x)
	{
		System.out.println("from fall back delete method of loansupport");
		return "";
	}
}
