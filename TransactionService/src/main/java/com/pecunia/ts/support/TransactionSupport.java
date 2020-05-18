package com.pecunia.ts.support;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pecunia.ts.dto.Account;

@Component
public class TransactionSupport 
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
	public Account getAccountDetails(long accNo)
	{
		return restTemplate.getForEntity("http://account-service/getAccount/"+accNo, Account.class).getBody();
	}
	public Account fallBackAccount(long x)
	{
		return new Account();
	}
	@HystrixCommand(fallbackMethod="fallBackUpdateBalance")
	public String updateAccountBalance(Account account)
	{
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<Account> entity = new HttpEntity<Account>(account,headers);
		return restTemplate.exchange("http://account-service/updateAccount",HttpMethod.PUT, entity, String.class).getBody();
	}
	public String fallBackUpdateBalance(Account x)
	{
		return "";
	}
}
