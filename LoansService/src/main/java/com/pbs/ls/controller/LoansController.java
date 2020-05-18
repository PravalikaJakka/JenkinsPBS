package com.pbs.ls.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pbs.ls.service.LoanRePaymentService;
import com.pbs.ls.service.LoanRequestService;
import com.pbs.ls.service.LoansService;
import com.pbs.ls.service.SanctionedLoansService;
import com.pbs.ls.support.LoanSupport;
import com.pbs.ls.dto.Customer;
import com.pbs.ls.dto.LoanRePayment;
import com.pbs.ls.dto.LoanRequest;
import com.pbs.ls.dto.SanctionedLoans;
import com.pbs.ls.dto.Loans;

@RestController
@CrossOrigin
public class LoansController 
{
	@Autowired 
	LoansService loansService;
    public void setLoansService(LoansService loansService)
    {
   	 this.loansService=loansService;
    }
    @Autowired 
    LoanRePaymentService loanRePaymentService;
    public void setLoanRePaymentService(LoanRePaymentService loanRePaymentService)
    {
   	 this.loanRePaymentService=loanRePaymentService;
    }
    @Autowired 
    SanctionedLoansService sanctionedLoansService;
    public void setSanctionedLoansService(SanctionedLoansService sanctionedLoansService)
    {
   	 this.sanctionedLoansService=sanctionedLoansService;
    }
    @Autowired 
    LoanRequestService loanRequestService;
    public void setLoanRequestService(LoanRequestService loanRequestService)
    {
   	 this.loanRequestService=loanRequestService;
    }
    @Autowired 
    LoanSupport loanSupport;
    public void setLoanSupport(LoanSupport loanSupport)
	 {
		this.loanSupport = loanSupport;
	 }
    @GetMapping(value="/getLoans/{loanId}",produces="application/json")
	   public Optional<Loans> getLoans(@PathVariable int loanId)
	   {
	 	  return  loansService.getLoans(loanId);
	   }
	   
	   @GetMapping(value="/getAllLoans",produces="application/json")
	   public List<Loans> getAllLoans()
	   {
	 	  return loansService.getAllLoans();
	   }
	    
	    @PostMapping(value="/insertLoans",consumes="application/json")
	    public ResponseEntity<String> insertLoans(@RequestBody Loans loan)
	    {
	    	try
	    	{
	    		Loans loans = new Loans();
	    		loans.setLoanRoi(loan.getLoanRoi());
	    		loans.setLoanType(loan.getLoanType());
	    		loansService.insertLoans(loans);
	    		System.out.println(loan.getLoanRoi()+"   "+loan.getLoanType());
	    		return new ResponseEntity<String>("Loan Details Added",HttpStatus.OK);
	    	}
	    	catch(Exception ex)
	    	{
	    		return new ResponseEntity<String>("Error in Adding Loan",HttpStatus.BAD_REQUEST);
	    	}
	    }
	    @DeleteMapping("/deleteLoans/{loanId}")
	    public ResponseEntity<String> deleteLoans(@PathVariable int loanId)
	    {
	    	try
	    	{
	    		loansService.deleteLoans(loanId);
	    		return new ResponseEntity<String>("Loan Details Deleted",HttpStatus.OK);
	    	}
	    	catch(Exception ex)
	    	{
	    		return new ResponseEntity<String>("Error in Deleting Loan",HttpStatus.BAD_REQUEST);
	    	}
	    }
	    @GetMapping(value="/getLoanRequest/{requestId}",produces="application/json")
		public Optional<LoanRequest> getLoanRequest(@PathVariable int requestId)
		{
		   return  loanRequestService.getLoanRequest(requestId);
		}
		   
		@GetMapping(value="/getAllLoanRequest",produces="application/json")
		public List<LoanRequest> getAllLoanRequest()
		{
		  return loanRequestService.getAllLoanRequest();
		}
		    
		@PostMapping(value="/insertLoanRequest",consumes="application/json")
		public ResponseEntity<String> insertLoanRequest(@RequestBody LoanRequest loanRequest)
		{
		   	try
		   	{
		   			Customer customer = loanSupport.getCustomer(loanRequest.getCustomer().getCustomerId());
		   			if(customer.getCustomerId() == loanRequest.getCustomer().getCustomerId())
		   			{
		   				loanRequest.setCustomer(customer);
		   				loanRequest.setRequestDate(LocalDate.now());
		   				Loans loans = loansService.getLoans(loanRequest.getLoan().getLoanId()).get();
		   				loanRequest.setLoan(loans);
		   				loanRequestService.insertLoanRequest(loanRequest);
		   				return new ResponseEntity<String>("Loan request Details Added",HttpStatus.OK);
		   			}
		   			else
		   				return new ResponseEntity<String>("Customer Details Not Found",HttpStatus.NOT_FOUND);
		   	}
		   	catch(Exception ex)
		   	{
		    		return new ResponseEntity<String>("Error in Adding Loan request",HttpStatus.BAD_REQUEST);
		   	}
		}
		@DeleteMapping("/deleteLoanRequest/{requestId}")
		public ResponseEntity<String> deleteLoanRequest(@PathVariable int requestId)
		{
		    	try
		    	{
		    		loanRequestService.deleteLoanRequest(requestId);
		    		return new ResponseEntity<String>("Loan Request Details Deleted",HttpStatus.OK);
		    	}
		    	catch(Exception ex)
		    	{
		    		return new ResponseEntity<String>("Error in Deleting Loan Request",HttpStatus.BAD_REQUEST);
		     	}
		}
		@GetMapping(value="/getSanctionedLoans/{sanctionId}",produces="application/json")
		public Optional<SanctionedLoans> getSanctionedLoans(@PathVariable int sanctionId)
		{
		   return  sanctionedLoansService.getSanctionedLoans(sanctionId);
		}
		   
		@GetMapping(value="/getAllSanctionedLoans",produces="application/json")
		public List<SanctionedLoans> getAllSanctionedLoans()
		{
		  return sanctionedLoansService.getAllSanctionedLoans();
		}
		@PostMapping(value="/insertSanctionedLoans",consumes="application/json")
		public ResponseEntity<String> insertSanctionedLoans(@RequestBody SanctionedLoans sanctionedLoans)
		{
			try
		   	{
				Customer customer = loanSupport.getCustomer(sanctionedLoans.getCustomer().getCustomerId());
				Loans loans = loansService.getLoans(sanctionedLoans.getLoan().getLoanId()).get();
	   			if(customer.getCustomerId() == sanctionedLoans.getCustomer().getCustomerId())
	   			{
				sanctionedLoans.setCustomer(customer);
	    		sanctionedLoans.setLoan(loans);
	    		sanctionedLoans.setLoanIssuedDate(LocalDate.now());
	    		sanctionedLoans.setLoanEmi(sanctionedLoans.getLoan().getLoanRoi());
				sanctionedLoansService.insertSanctionedLoans(sanctionedLoans);
	 		    return new ResponseEntity<String>("Sanctioned Loans Details Added",HttpStatus.OK);
	   			}
	   			else
	   				return new ResponseEntity<String>("Customer Details Not Found",HttpStatus.NOT_FOUND);
	    	}
	     	catch(Exception e)
	    	{
	    		return new ResponseEntity<String>("Error in Adding Sanctioned Loans",HttpStatus.BAD_REQUEST);
	    	}
		}
		@DeleteMapping("/deleteSanctionedLoans/{sanctionId}")
		public ResponseEntity<String> deleteSanctionedLoans(@PathVariable int sanctionId)
		{
		    	try
		    	{
		    		sanctionedLoansService.deleteSanctionedLoans(sanctionId);
		    		return new ResponseEntity<String>("sanctioned Loan Details Deleted",HttpStatus.OK);
		    	}
		    	catch(Exception ex)
		    	{
		    		return new ResponseEntity<String>("Error in Deleting Sanctioned Loan",HttpStatus.BAD_REQUEST);
		     	}
		}
		@GetMapping(value="/getLoanRePayment/{loanPayId}",produces="application/json")
		public Optional<LoanRePayment> getLoanRePayment(@PathVariable int loanPayId)
		{
		   return  loanRePaymentService.getLoanRePayment(loanPayId);
		}
		   
		@GetMapping(value="/getAllLoanRePayment",produces="application/json")
		public List<LoanRePayment> getAllLoanRePayment()
		{
		  return loanRePaymentService.getAllLoanRePayment();
		}
		    
		@PostMapping(value="/insertLoanRePayment",consumes="application/json")
		public ResponseEntity<String> insertLoanRePayment(@RequestBody LoanRePayment loanRePayment)
		{
		   	try
		   	{
		   		    loanRePayment.setPayDate(LocalDate.now());
		    		if(sanctionedLoansService.getSanctionedLoans(loanRePayment.getSanctionedLoan().getSanctionId()).isPresent())
					{
						loanRePayment.setSanctionedLoan(sanctionedLoansService.getSanctionedLoans(loanRePayment.getSanctionedLoan().getSanctionId()).get());
		    			loanRePaymentService.insertLoanRePayment(loanRePayment);
		    			return new ResponseEntity<String>("Loan Repayment Details Added",HttpStatus.OK);
					}
		    		else
		    		{
		    			return new ResponseEntity<String>("Loan is not sanctioned",HttpStatus.NOT_ACCEPTABLE);
		    		}
		   	}
		   	catch(Exception ex)
		   	{
		    		return new ResponseEntity<String>("Error in Adding Loan Repayment",HttpStatus.BAD_REQUEST);
		   	}
		}
		@DeleteMapping("/deleteLoanRePayment/{loanPayId}")
		public ResponseEntity<String> deleteLoanRePayment(@PathVariable int loanPayId)
		{
		    	try
		    	{
		    		LoanRePayment loanRePayment = loanRePaymentService.getLoanRePayment(loanPayId).get();
		    		if(sanctionedLoansService.getSanctionedLoans(loanRePayment.getSanctionedLoan().getSanctionId()).isPresent())
					{
		    			loanRePaymentService.deleteLoanRePayment(loanPayId);
		    			return new ResponseEntity<String>("Loan RePayment Details Deleted",HttpStatus.OK);
					}
		    		else
		    			return new ResponseEntity<String>("Could Not Deleting Loan RePayment",HttpStatus.NOT_FOUND);
		    	}
		    	catch(Exception ex)
		    	{
		    		return new ResponseEntity<String>("Error in Deleting Loan RePayment",HttpStatus.BAD_REQUEST);
		     	}
		}
		
}
