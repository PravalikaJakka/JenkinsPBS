package com.pbs.ls.dto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
@Entity
public class Loans 
{
    @Id
    @Column(name="loan_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="loan_seq")
    @SequenceGenerator(name="loan_seq",sequenceName="loan_seq",allocationSize=1)
    int loanId;
    @Column(name="loan_type")
    String loanType;
    @Column(name="loan_roi")
    double loanRoi;
    public Loans() { }
	public Loans(int loanId, String loanType, double loanRoi)
	{
		this.loanId = loanId;		this.loanType = loanType;		this.loanRoi = loanRoi;
	}
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public double getLoanRoi() {
		return loanRoi;
	}
	public void setLoanRoi(double loanRoi) {
		this.loanRoi = loanRoi;
	}
}
