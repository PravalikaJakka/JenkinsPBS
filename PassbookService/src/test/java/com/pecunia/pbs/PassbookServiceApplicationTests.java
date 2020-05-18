package com.pecunia.pbs;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.pecunia.pbs.dto.Transactions;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PassbookServiceApplicationTests 
{
	@Autowired
	TestRestTemplate testRestTemplate;
	public void setTestRestTemplate(TestRestTemplate testRestTemplate)
	{
		this.testRestTemplate = testRestTemplate;
	}
	@LocalServerPort
	int serverPort;
	
	@Test
	public void passbookUpdate_Positive() throws Exception
	{
		String url = "http://localhost:"+serverPort+"passbookUpdate/accountNo/100000000007/lasttransaction/5000";
		ResponseEntity<Transactions> transactions = testRestTemplate.getForEntity(url, Transactions.class);
		Assertions.assertEquals(200, transactions.getStatusCodeValue());
	}
	@Test
	public void passbookUpdate_Negative() throws Exception
	{
		String url = "http://localhost:"+serverPort+"passbookUpdate/accountNo/900000000010/lasttransaction/5000";
		ResponseEntity<String> message = testRestTemplate.getForEntity(url, String.class);
		Assertions.assertEquals(404, message.getStatusCodeValue());
	}
	@Test
	public void accountSummary_Positive() throws Exception
	{
		String url = "http://localhost:"+serverPort+"accountSummary/accountNo/100000000010/startDate/2020-04-01/endDate/2020-05-30";
		ResponseEntity<Transactions> transactions = testRestTemplate.getForEntity(url, Transactions.class);
		Assertions.assertEquals(200, transactions.getStatusCodeValue());
	}
	@Test
	public void accountSummary_Negative() throws Exception
	{
		String url = "http://localhost:"+serverPort+"accountSummary/accountNo/900000000010/startDate/2020-04-01/endDate/2020-05-30";
		ResponseEntity<String> message = testRestTemplate.getForEntity(url, String.class);
		Assertions.assertEquals(404, message.getStatusCodeValue());
	}
}
