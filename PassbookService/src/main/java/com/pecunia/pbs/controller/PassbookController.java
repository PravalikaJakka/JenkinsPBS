package com.pecunia.pbs.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pecunia.pbs.dto.Account;
import com.pecunia.pbs.dto.Cheque;
import com.pecunia.pbs.dto.Transaction;
import com.pecunia.pbs.dto.Transactions;
import com.pecunia.pbs.support.PassbookSupport;

@RestController
@CrossOrigin
public class PassbookController 
{
	@Autowired
	PassbookSupport passbookSupport;
	public void setPassbookSupport(PassbookSupport passbookSupport)
	{
		this.passbookSupport = passbookSupport;
	}
	@GetMapping(value="/accountSummary/accountNo/{accNo}/startDate/{startDate}/endDate/{endDate}",produces= {"application/json","application/xml"})
	public ResponseEntity<Transactions> accountSummary(@PathVariable long accNo,@PathVariable String startDate,@PathVariable String endDate) throws ParseException
	{
		try 
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate beforeDate = LocalDate.parse(startDate, formatter);
			LocalDate afterDate = LocalDate.parse(endDate,formatter);
			List<Transaction> transactions = passbookSupport.getTransactions(accNo).getTransactions();
			if(transactions.isEmpty())
			{
				return new ResponseEntity("Account Not Found",HttpStatus.NOT_FOUND);
			}
			else
			{
			List<Transaction> transactionNewList = new ArrayList<>();
			for(Transaction transaction : transactions)
			{
				if(transaction.getTransactionDate().isAfter(beforeDate) && transaction.getTransactionDate().isBefore(afterDate))
				{
					if(transaction.getChequeDetails()== null)
					{
						transaction.setChequeDetails(new Cheque());
					}
					transactionNewList.add(transaction);
				}
			}
			Transactions transaction = new Transactions();
			transaction.setTransactions(transactionNewList);
			return new ResponseEntity<>(transaction,HttpStatus.OK);
			}
		}
		catch(Exception e)
		{
			return new ResponseEntity("Account Not Found",HttpStatus.NOT_FOUND);
		}		
	}
	@GetMapping(value="/passbookUpdate/accountNo/{accNo}/lasttransaction/{lastTransaction}",produces= {"application/json","application/xml"})
	public ResponseEntity<Transactions> passbookUpdate(@PathVariable long accNo,@PathVariable int lastTransaction) 
	{
		try
		{
			List<Transaction> transactions = passbookSupport.getTransactions(accNo).getTransactions();
			if(transactions.isEmpty())
			{
				return new ResponseEntity("Account Not Found",HttpStatus.NOT_FOUND);
			}
			else
			{
			List<Transaction> transactionNewList = new ArrayList<>();
			for(Transaction transaction : transactions)
			{
				if(transaction.getTransId() > lastTransaction)
				{
					if(transaction.getChequeDetails() == null)
					{
						transaction.setChequeDetails(new Cheque());
					}
					transactionNewList.add(transaction);
				}
			}
			Transactions transaction = new Transactions();
			transaction.setTransactions(transactionNewList);
			return new ResponseEntity<>(transaction,HttpStatus.OK);
			}
		}
		catch(Exception e)
		{
			return new ResponseEntity("Account Not Found",HttpStatus.NOT_FOUND);
		}	
	}
}
