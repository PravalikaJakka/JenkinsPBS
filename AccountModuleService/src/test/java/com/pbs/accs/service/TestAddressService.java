package com.pbs.accs.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.pbs.accs.dto.Address;
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestAddressService 
{
	@Autowired
	AddressService addressService;
	
	@Test
	public void testAddAddress_Positive() throws Exception
	{
		Address address=new Address(1000,"22-33","Ramnagar","Hyderabad","Telangana","India");
		Address add=addressService.addAddress(address);
		Assertions.assertEquals(address.getCity(),add.getCity());
	}
	
	@Test
	public void testAddAddress_Negative() throws Exception
	{
		Address address=new Address();
		Address add=addressService.addAddress(address);
		Assertions.assertEquals(address,add);
	}
	
	@Test
	public void testGetAddress_Positive() throws Exception
	{
		Optional<Address> address = addressService.getAddress(1011);
	    Assertions.assertEquals(true,address.isPresent());
	}
	
	@Test
	public void testGetAddress_Negative() throws Exception
	{
		Optional<Address> address = addressService.getAddress(1002);
	    Assertions.assertEquals(false,address.isPresent());
	}
	
	@Test
	public void testDeleteAddress_Positive() throws Exception
	{
		addressService.deleteAddress(1013);
		Optional<Address> address = addressService.getAddress(1013);
	    Assertions.assertEquals(false,address.isPresent());
	}
	
	/*@Test
	public void testDeleteAddress_Negative() throws Exception
	{
		addressService.deleteAddress(1000);
	    Assertions.assertNull(addressService.getAddress(1000));
	}*/
	

}
