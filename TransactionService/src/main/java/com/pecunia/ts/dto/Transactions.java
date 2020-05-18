package com.pecunia.ts.dto;


import java.util.List;

import org.springframework.stereotype.Component;
import javax.xml.bind.annotation.XmlRootElement;

@Component
@XmlRootElement
public class Transactions 
{
    private List<Transaction> transactions;

	public Transactions() 
	{
	}
	public Transactions(List<Transaction> transactions) 
	{
		this.transactions = transactions;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
    
}

