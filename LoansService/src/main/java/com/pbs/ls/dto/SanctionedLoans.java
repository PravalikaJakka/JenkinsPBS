package com.pbs.ls.dto;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="sanctionedloans")
public class SanctionedLoans 
{
     @Id
     @Column(name="sloan_id")
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sanctioned_seq")
     @SequenceGenerator(sequenceName = "loansanctioned_seq", allocationSize = 1, name = "sanctioned_seq")
     int sanctionId;
     @OneToOne
     @JoinColumn(name="cust_id")
     Customer customer;
     @OneToOne
     @JoinColumn(name="loan_id")
     Loans loan;
     @Column(name="amount_sanctioned")
     double sanctionedAmount;
     @Column(name="loan_emi")
     double loanEmi;
     @Column(name="loan_tenure")
     int loanTenure;
     @Column(name="issued_date")
     LocalDate loanIssuedDate;
     @Column(name="status")
     String status;
     public SanctionedLoans() {}
	public SanctionedLoans(int sanctionId, Customer customer, Loans loan, double sanctionedAmount, double loanEmi,
			int loanTenure, LocalDate loanIssuedDate, String status) 
	{
		this.sanctionId = sanctionId;
		this.customer = customer;
		this.loan = loan;
		this.sanctionedAmount = sanctionedAmount;
		this.loanEmi = loanEmi;
		this.loanTenure = loanTenure;
		this.loanIssuedDate = loanIssuedDate;
		this.status = status;
	}
	public int getSanctionId() {
		return sanctionId;
	}
	public void setSanctionId(int sanctionId) {
		this.sanctionId = sanctionId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Loans getLoan() {
		return loan;
	}
	public void setLoan(Loans loan) {
		this.loan = loan;
	}
	public double getSanctionedAmount() {
		return sanctionedAmount;
	}
	public void setSanctionedAmount(double sanctionedAmount) {
		this.sanctionedAmount = sanctionedAmount;
	}
	public double getLoanEmi() {
		return loanEmi;
	}
	public void setLoanEmi(double loanEmi) {
		this.loanEmi = loanEmi;
	}
	public int getLoanTenure() {
		return loanTenure;
	}
	public void setLoanTenure(int loanTenure) {
		this.loanTenure = loanTenure;
	}
	public LocalDate getLoanIssuedDate() {
		return loanIssuedDate;
	}
	public void setLoanIssuedDate(LocalDate loanIssuedDate) {
		this.loanIssuedDate = loanIssuedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	} 
}
