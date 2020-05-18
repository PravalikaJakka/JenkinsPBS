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
@Table(name="loanrequest")
public class LoanRequest 
{
    @Id
    @Column(name="request_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_seq")
    @SequenceGenerator(sequenceName = "loanrequest_seq", allocationSize = 1, name = "request_seq")
    int requestId;
    @Column(name="requested_date")
    LocalDate requestDate;
    @Column(name="annual_income")
    double customerIncome;
    @OneToOne
    @JoinColumn(name="cust_id")
    Customer customer;
    @OneToOne
    @JoinColumn(name="loan_id")
    Loans loan;
    @Column(name="status")
    String status;
    public LoanRequest() { }
	public LoanRequest(int requestId, LocalDate requestDate, double customerIncome, Customer customes, Loans loan,	String status) 
	{
		this.requestId = requestId;
		this.requestDate = requestDate;
		this.customerIncome = customerIncome;
		this.customer = customes;
		this.loan = loan;
		this.status = status;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public LocalDate getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}
	public double getCustomerIncome() {
		return customerIncome;
	}
	public void setCustomerIncome(double customerIncome) {
		this.customerIncome = customerIncome;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customes) {
		this.customer = customes;
	}
	public Loans getLoan() {
		return loan;
	}
	public void setLoan(Loans loan) {
		this.loan = loan;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
