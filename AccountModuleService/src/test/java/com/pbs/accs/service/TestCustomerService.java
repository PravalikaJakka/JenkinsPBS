package com.pbs.accs.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.pbs.accs.dto.Address;
import com.pbs.accs.dto.Customer;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestCustomerService 
{
	@Autowired
	CustomerService customerService;
	
	@Test
	public void testAddCustomer_Positive() throws Exception
	{
		Address address=new Address(1000,"22-33","Ramnagar","Hyderabad","Telangana","India");
		Customer customer=new Customer(111111,"Pravalika",9676251906L,"pravalika@gmail.com","ASDFG3456M",address);
		Customer cust=customerService.addCustomer(customer);;
		Assertions.assertEquals(customer.getCustomerName(),cust.getCustomerName());
	}
	
	@Test
	public void testAddCustomer_Negative() throws Exception
	{
		Customer customer=new Customer();
		Customer cust=customerService.addCustomer(customer);
		Assertions.assertEquals(customer,cust);
	}
	
	@Test
	public void testGetCustomer_Positive() throws Exception
	{
		Optional<Customer> customer = customerService.getCustomer(100004);
	    Assertions.assertEquals(true,customer.isPresent());
	}
	
	@Test
	public void testGetAccount_Negative() throws Exception
	{
		Optional<Customer> customer = customerService.getCustomer(100003);
	    Assertions.assertEquals(false,customer.isPresent());
	}
	
	@Test
	public void testDeleteCustomer_Positive() throws Exception
	{
		customerService.deleteCustomer(100004);
		Optional<Customer> customer=customerService.getCustomer(100004);
	    Assertions.assertEquals(false,customer.isPresent());
	}
	
	/*@Test
	public void testDeleteCustomer_Negative() throws Exception
	{
		customerService.deleteCustomer(100003);
	    Assertions.assertEquals(true,);
	}*/
	

}
