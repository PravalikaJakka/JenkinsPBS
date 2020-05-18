package com.pecunia.ts.dto;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="chequedetails")
public class Cheque 
{
      @Id
      @Column(name="cheque_id")
      int chequeId;
      @Column(name="cheque_no")
      int chequeNo;
      @Column(name="cheque_amount")
      double chequeAmount;
      @Column(name="cheque_dated")
      LocalDate chequeDated;
      @Column(name="cheque_accountno")
      long chequeAccountNo;
      @OneToOne
      @JoinColumn(name="ifsc_code")
      BanksDetails bank;
      public Cheque() { }
	  public Cheque(int chequeId, int chequeNo, double chequeAmount, LocalDate chequeDated, long chequeAccountNo,BanksDetails bank) 
	  {
		 this.chequeId = chequeId;		this.chequeNo = chequeNo;		this.chequeAmount = chequeAmount;
		 this.chequeDated = chequeDated;	this.chequeAccountNo = chequeAccountNo;		this.bank = bank;
	  }
	public int getChequeId() {
		return chequeId;
	}
	public void setChequeId(int chequeId) {
		this.chequeId = chequeId;
	}
	public int getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(int chequeNo) {
		this.chequeNo = chequeNo;
	}
	public double getChequeAmount() {
		return chequeAmount;
	}
	public void setChequeAmount(double chequeAmount) {
		this.chequeAmount = chequeAmount;
	}
	public LocalDate getChequeDated() {
		return chequeDated;
	}
	public void setChequeDated(LocalDate chequeDated) {
		this.chequeDated = chequeDated;
	}
	public long getChequeAccountNo() {
		return chequeAccountNo;
	}
	public void setChequeAccountNo(long chequeAccountNo) {
		this.chequeAccountNo = chequeAccountNo;
	}
	public BanksDetails getBank() {
		return bank;
	}
	public void setBank(BanksDetails bank) {
		this.bank = bank;
	}
}
