package com.pecunia.pbs.support;


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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pecunia.pbs.dto.Transactions;

@Component
public class PassbookSupport
{
	@Autowired
	RestTemplate restTemplate;
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	@HystrixCommand(fallbackMethod = "fallBackTransactions")
	public Transactions getTransactions(long accNo)
	{
		System.out.println("HI");
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<Transactions> entity = new HttpEntity<>(headers);
		return restTemplate.exchange("http://transaction-service/getTransactionList/accountno/"+accNo,HttpMethod.GET, entity, Transactions.class).getBody();
	}
	public Transactions fallBackTransactions(long x)
	{
		return new Transactions();
	}
}
