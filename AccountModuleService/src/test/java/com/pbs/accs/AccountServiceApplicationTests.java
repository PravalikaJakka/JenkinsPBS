package com.pbs.accs;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.pbs.accs.dto.Account;
import com.pbs.accs.dto.Address;
import com.pbs.accs.dto.Customer;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class AccountServiceApplicationTests 
{
	@Autowired
	TestRestTemplate testRestTemplate;
	public void setTestRestTemplate(TestRestTemplate testRestTemplate)
	{
		this.testRestTemplate=testRestTemplate;
	}
	
	@LocalServerPort
	int localServerPort;
	
	@Test
	public void testAddAccount_Positive() throws Exception
	{
		String url="http://localhost:"+localServerPort+"addAccount";
		Address address=new Address(1000,"22-33","Ramnagar","Hyderabad","Telangana","India");
		Customer customer=new Customer(111111,"Pravalika",9676251906L,"pravalika@gmail.com","ASDFG3456M",address);
		Account account=new Account(111111111111L,"Savings",LocalDate.now(),10000,"Active",customer);
		ResponseEntity<String> response=testRestTemplate.postForEntity(url, account, String.class);
		Assertions.assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void testAddAccount_Negative() throws Exception
	{
		String url="http://localhost:"+localServerPort+"addAccount";
		Account account=null;
		ResponseEntity<String> response=testRestTemplate.postForEntity(url, account, String.class);
		Assertions.assertEquals(400, response.getStatusCodeValue());
	}
	
	@Test
	public void testGetAccount_Positive() throws Exception
	{
		String url="http://localhost:"+localServerPort+"getAccount/100000000003";
		ResponseEntity<Account> account=testRestTemplate.getForEntity(url, Account.class);
		Assertions.assertEquals(200, account.getStatusCodeValue());
	}
	
	@Test
	public void testGetAccount_Negative() throws Exception
	{
		String url="http://localhost:"+localServerPort+"getAccount/100000000010";
		ResponseEntity<Account> account=testRestTemplate.getForEntity(url, Account.class);
		Assertions.assertEquals(404, account.getStatusCodeValue());
	}
	
	@Test
	public void testDeleteAccount_Positive() throws Exception
	{
		String url="http://localhost:"+localServerPort+"deleteAccount/100000000006";
		ResponseEntity<String> response = testRestTemplate.exchange(url,HttpMethod.DELETE,null,String.class);
		 Assertions.assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void testDeleteAccount_Negative() throws Exception
	{
		String url="http://localhost:"+localServerPort+"deleteAccount/100000000015";
		ResponseEntity<String> response = testRestTemplate.exchange(url,HttpMethod.DELETE,null,String.class);
		 Assertions.assertEquals(400, response.getStatusCodeValue());
	}

	@Test
	public void testAddCustomer_Positive() throws Exception
	{
		String url="http://localhost:"+localServerPort+"addCustomer";
		Address address=new Address(2000,"44-55","Parsigutta","Hydbad","telangana","india");
		Customer cust=new Customer(222222,"Bhargavi",8309957852L,"bhargavi@gmail.com","MNHTD6789G",address);
		ResponseEntity<String> response=testRestTemplate.postForEntity(url, cust, String.class);
		Assertions.assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void testAddCustomer_Negative() throws Exception
	{
		String url="http://localhost:"+localServerPort+"addCustomer";
		Customer customer=null;
		ResponseEntity<String> response=testRestTemplate.postForEntity(url, customer, String.class);
		Assertions.assertEquals(400, response.getStatusCodeValue());
	}
	
	@Test
	public void testGetCustomer_Positive() throws Exception
	{
		String url="http://localhost:"+localServerPort+"getCustomer/100005";
		ResponseEntity<Customer> customer =testRestTemplate.getForEntity(url,Customer.class);
		Assertions.assertEquals(200,customer.getStatusCodeValue());
	}
	
	@Test
	public void testGetCustomer_Negative() throws Exception
	{
		String url="http://localhost:"+localServerPort+"getCustomer/100010";
		ResponseEntity<Customer> customer=testRestTemplate.getForEntity(url, Customer.class);
		Assertions.assertEquals(404, customer.getStatusCodeValue());
	}
	
	@Test
	public void testDeleteCustomer_Positive() throws Exception
	{
		String url="http://localhost:"+localServerPort+"deleteCustomer/100003";
		ResponseEntity<String> response = testRestTemplate.exchange(url,HttpMethod.DELETE,null,String.class);
		 Assertions.assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void testDeleteCustomer_Negative() throws Exception
	{
		String url="http://localhost:"+localServerPort+"deleteCustomer/100010";
		ResponseEntity<String> response = testRestTemplate.exchange(url,HttpMethod.DELETE,null,String.class);
		 Assertions.assertEquals(400, response.getStatusCodeValue());
	}
}
