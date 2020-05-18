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
@Table(name="loanrepayment")
public class LoanRePayment 
{
     @Id
     @Column(name="loanpay_id")
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "repayment_seq")
     @SequenceGenerator(sequenceName = "loanrepayment_seq", allocationSize = 1, name = "repayment_seq")
     int loanPayId;
     @OneToOne
     @JoinColumn(name="sloan_id")
     SanctionedLoans sanctionedLoan;
     double amount;
     @Column(name="pay_date")
     LocalDate payDate;
	public LoanRePayment() 
	{
	}
	public LoanRePayment(int loanPayId, SanctionedLoans sanctionedLoan, double amount, LocalDate payDate) 
	{
		this.loanPayId = loanPayId;
		this.sanctionedLoan = sanctionedLoan;
		this.amount = amount;
		this.payDate = payDate;
	}
	public int getLoanPayId() {
		return loanPayId;
	}
	public void setLoanPayId(int loanPayId) {
		this.loanPayId = loanPayId;
	}
	public SanctionedLoans getSanctionedLoan() {
		return sanctionedLoan;
	}
	public void setSanctionedLoan(SanctionedLoans sanctionedLoan) {
		this.sanctionedLoan = sanctionedLoan;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getPayDate() {
		return payDate;
	}
	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	} 
}
