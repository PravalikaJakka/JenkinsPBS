package com.pbs.accs.service;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.pbs.accs.dto.Account;
import com.pbs.accs.dto.Address;
import com.pbs.accs.dto.Customer;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestAccountService 
{
	@Autowired
	AccountService accountService;
	
	@Test
	public void testAddAccount_Positive() throws Exception
	{
		Address address=new Address(1000,"22-33","Ramnagar","Hyderabad","Telangana","India");
		Customer customer=new Customer(111111,"Pravalika",9676251906L,"pravalika@gmail.com","ASDFG3456M",address);
		Account account=new Account(111111111111L,"Savings",LocalDate.now(),10000,"Active",customer);
		Account acc=accountService.addAccount(account);
		Assertions.assertEquals(account.getCustomer().getCustomerName(),acc.getCustomer().getCustomerName());
	}
	
	@Test
	public void testAddAccount_Negative() throws Exception
	{
		Account account=new Account();
		Account acc=accountService.addAccount(account);
		Assertions.assertEquals(account,acc);
	}
	
	@Test
	public void testGetAccount_Positive() throws Exception
	{
		Optional<Account> account = accountService.getAccount(100000000008L);
	    Assertions.assertEquals(true,account.isPresent());
	}
	
	@Test
	public void testGetAccount_Negative() throws Exception
	{
		Optional<Account> account = accountService.getAccount(100000000004L);
	    Assertions.assertEquals(false,account.isPresent());
	}
	
	@Test
	public void testDeleteAccount_Positive() throws Exception
	{
		accountService.deleteAccount(100000000008L);
		Optional<Account> account=accountService.getAccount(100000000008L);
	    Assertions.assertEquals(false,account.isPresent());
	}
	
	/*@Test
	public void testDeleteAccount_Negative() throws Exception
	{
		accountService.deleteAccount(100000000007L);
	    Assertions.assertEquals(true,);
	}*/
	
}
