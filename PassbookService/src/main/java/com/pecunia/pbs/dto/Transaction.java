package com.pecunia.pbs.dto;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.pecunia.pbs.dto.Cheque;
@Entity
public class Transaction 
{
    @Id
    @Column(name="trans_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_seq")
    @SequenceGenerator(sequenceName = "trans_seq", allocationSize = 1, name = "trans_seq")
    int transId;
    @Column(name="trans_date")
    LocalDate transactionDate;
    @Column(name="trans_type")
    String transactionType;
    @Column(name="trans_amount")
    double transactionAmount;
    @OneToOne
    @JoinColumn(name="cheque_id")
    Cheque chequeDetails;
    @Column(name="slip_no")
    int slipNo;
    @OneToOne
    @JoinColumn(name="acc_no")
    Account account;
    public Transaction() {  }
	public Transaction(int transId, LocalDate transactionDate, String transactionType, double transactionAmount,Cheque chequeDetails, int slipNo, Account account) 
	{
		this.transId = transId;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.chequeDetails = chequeDetails;
		this.slipNo = slipNo;
		this.account = account;
	}
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public Cheque getChequeDetails() {
		return chequeDetails;
	}
	public void setChequeDetails(Cheque chequeDetails) {
		this.chequeDetails = chequeDetails;
	}
	public int getSlipNo() {
		return slipNo;
	}
	public void setSlipNo(int slipNo) {
		this.slipNo = slipNo;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	} 
}
