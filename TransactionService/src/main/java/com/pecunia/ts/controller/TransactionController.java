package com.pecunia.ts.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pecunia.ts.dto.Account;
import com.pecunia.ts.dto.Cheque;
import com.pecunia.ts.dto.Transaction;
import com.pecunia.ts.dto.Transactions;
import com.pecunia.ts.service.BanksDetailsService;
import com.pecunia.ts.service.ChequeService;
import com.pecunia.ts.service.TransactionService;
import com.pecunia.ts.support.TransactionSupport;

@RestController
@CrossOrigin
public class TransactionController 
{
	@Autowired
	TransactionService transactionService;
	@Autowired
	ChequeService chequeService;
	@Autowired
	BanksDetailsService bankDetailsService;
	@Autowired
	TransactionSupport transactionSupport;
	public void setBankDetailsService(BanksDetailsService bankDetailsService)
	{
		this.bankDetailsService = bankDetailsService;
	}
	public void setChequeService(ChequeService chequeService) {
		this.chequeService = chequeService;
	}
	public void setTransactionService(TransactionService transactionService)
	{
		this.transactionService=transactionService;
	}
	public void setTransactionSupport(TransactionSupport transactionSupport)
	{
		this.transactionSupport = transactionSupport;
	}
	@GetMapping(value="/creditusingslip/accno/{account_No}/amount/{amount}/slipno/{slipno}")
    public ResponseEntity<String> creditUsingSlip(@PathVariable long account_No,@PathVariable double amount,@PathVariable int slipno)
    {
		try
		{
		    Transaction transaction = new Transaction();
		    Account account = transactionSupport.getAccountDetails(account_No);
		    if(account!=null)
		    {
		    account.setBalance(account.getBalance()+amount);
		    if(transactionSupport.updateAccountBalance(account).equalsIgnoreCase("Account Updated"))
		    {
		    	transaction.setAccount(account);
		    	transaction.setChequeDetails(null);
		    	transaction.setSlipNo(slipno);
		    	transaction.setTransactionAmount(amount);
		    	transaction.setTransactionType("Credit");
		    	transaction.setTransactionDate(LocalDate.now());
		    	transactionService.insertTransaction(transaction);
		    	return new ResponseEntity<>("Successful",HttpStatus.OK);
		    }
		    else
		    	return new ResponseEntity<>("Failed",HttpStatus.NOT_ACCEPTABLE);
		    }
		    else
		    	throw new NoSuchElementException();
		}
		catch(NoSuchElementException e)
		{
    		return new ResponseEntity<>("Account Not Found",HttpStatus.NOT_FOUND);
		}
    }
	@PostMapping(value="/creditusingcheque/accountno/{accountNo}",consumes= {"application/json","application/xml"})
	public ResponseEntity<String> creditUsingCheque(@PathVariable long accountNo,@RequestBody() Cheque cheque) 
	{
		try
		{
		    Transaction transaction = new Transaction();	 
		    bankDetailsService.insertBankDetails(cheque.getBank());
		    chequeService.insertCheque(cheque);
		    Account account = transactionSupport.getAccountDetails(accountNo);
		    if(account!=null)
		    {
		    	account.setBalance(account.getBalance()+cheque.getChequeAmount());
		    	if(transactionSupport.updateAccountBalance(account).equalsIgnoreCase("Account Updated"))
			    {
		    		transaction.setAccount(account);
		    		transaction.setChequeDetails(cheque);
		    		transaction.setSlipNo(0);
		    		transaction.setTransactionAmount(cheque.getChequeAmount());
		    		transaction.setTransactionType("Credit");
		    		transaction.setTransactionDate(LocalDate.now());
		    		transactionService.insertTransaction(transaction);
		    		return new ResponseEntity<>("Successful",HttpStatus.OK);
			    }
		    	else
		    		return new ResponseEntity<>("Failed",HttpStatus.NOT_ACCEPTABLE);
		    	}
		    	else
		    		throw new NoSuchElementException();
			}
			catch(NoSuchElementException e)
			{
				return new ResponseEntity<>("Account Not Found",HttpStatus.NOT_FOUND);
			}
	}
	@GetMapping(value="/debitusingslip/accno/{account_No}/amount/{amount}/slipno/{slipno}")
    public ResponseEntity<String> debitUsingSlip(@PathVariable long account_No,@PathVariable double amount,@PathVariable int slipno)
    {
		try
		{
		    Transaction transaction = new Transaction();	
		    Account account = transactionSupport.getAccountDetails(account_No);
		    if(account!=null)
		    {
		    account.setBalance(account.getBalance()-amount);
		    if(transactionSupport.updateAccountBalance(account).equalsIgnoreCase("Account Updated"))
		    {
		    	transaction.setAccount(account);
		    	transaction.setChequeDetails(null);
		    	transaction.setSlipNo(slipno);
		    	transaction.setTransactionAmount(amount);
		    	transaction.setTransactionType("Debit");
		    	transaction.setTransactionDate(LocalDate.now());
		    	transactionService.insertTransaction(transaction);
		    	return new ResponseEntity<>("Successful",HttpStatus.OK);
		    }
		    else
		    	return new ResponseEntity<>("Failed",HttpStatus.NOT_ACCEPTABLE);
		    }
		    else
		    	throw new NoSuchElementException();
		}
		catch(NoSuchElementException e)
		{
    		return new ResponseEntity<>("Account Not Found",HttpStatus.NOT_FOUND);
		}
    }
	@PostMapping(value="/debitusingcheque/accountno/{accountNo}",consumes= {"application/json","application/xml"})
	public ResponseEntity<String> debitUsingCheque(@PathVariable long accountNo,@RequestBody() Cheque cheque) 
	{
		try
		{
		    Transaction transaction = new Transaction();
		    bankDetailsService.insertBankDetails(cheque.getBank());
		    chequeService.insertCheque(cheque);
		    Account account = transactionSupport.getAccountDetails(accountNo);
		    if(account!=null)
		    {
		    	account.setBalance(account.getBalance()+cheque.getChequeAmount());
		    	if(transactionSupport.updateAccountBalance(account).equalsIgnoreCase("Account Updated"))
		    	{
		    		transaction.setAccount(account);
		    		transaction.setChequeDetails(cheque);
		    		transaction.setSlipNo(0);
		    		transaction.setTransactionAmount(cheque.getChequeAmount());
		    		transaction.setTransactionType("Debit");
		    		transaction.setTransactionDate(LocalDate.now());
		    		transactionService.insertTransaction(transaction);
		    		return new ResponseEntity<>("Successful",HttpStatus.OK);
		    	}
		    	else
		    		return new ResponseEntity<>("Failed",HttpStatus.NOT_ACCEPTABLE);
		    }
		    else
		    	throw new NoSuchElementException();
		}
		catch(NoSuchElementException e)
		{
    		return new ResponseEntity<>("Account Not Found",HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping(value="/getTransactionList/accountno/{acc_no}",produces= {"application/json","application/xml"})
	public ResponseEntity<Transactions> getTransactionList(@PathVariable long acc_no)
	{
		try
		{
			Account account = transactionSupport.getAccountDetails(acc_no); 
			if(account!=null)
			{
				Transactions transactions = new Transactions();
				transactions.setTransactions(transactionService.getAllTransactionByAccount(account));
				return new ResponseEntity<>(transactions,HttpStatus.OK);
			}
			else
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
}
