package com.pecunia.fds.dto;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@Table(name="fixeddepositholders")
@XmlRootElement
public class FixedDepositHolders 
{
     @Id
     @Column(name="fdh_id")
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fdh_seq")
     @SequenceGenerator(sequenceName = "fixeddepositholders_seq", allocationSize = 1, name = "fdh_seq")
     int depositHolderId;
     @OneToOne
     @JoinColumn(name="cust_id")
     Customer customer;
     @OneToOne
     @JoinColumn(name="fd_id")
     FixedDeposits depositDetails;
     @Column(name="amount")
     double amount;
     @Column(name="deposited_date")
     LocalDate depositedDate;
     public FixedDepositHolders() {}
	public FixedDepositHolders(int depositHolderId, Customer customer, FixedDeposits depositDetails, double amount,	LocalDate depositedDate) 
	{
		this.depositHolderId = depositHolderId;
		this.customer = customer;
		this.depositDetails = depositDetails;
		this.amount = amount;
		this.depositedDate = depositedDate;
	}
	public int getDepositHolderId() {
		return depositHolderId;
	}
	public void setDepositHolderId(int depositHolderId) {
		this.depositHolderId = depositHolderId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public FixedDeposits getDepositDetails() {
		return depositDetails;
	}
	public void setDepositDetails(FixedDeposits depositDetails) {
		this.depositDetails = depositDetails;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getDepositedDate() {
		return depositedDate;
	}
	public void setDepositedDate(LocalDate depositedDate) {
		this.depositedDate = depositedDate;
	} 
}
