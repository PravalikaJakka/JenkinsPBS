package com.pecunia.fds.dto;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
@Entity
@Table(name="fixeddeposits")
public class FixedDeposits 
{
      @Id
      @Column(name="fd_id")
      @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fd_seq")
      @SequenceGenerator(sequenceName = "fixeddeposits_seq", allocationSize = 1, name = "fd_seq")
      int fixedDepositId;
      @Column(name="duration")
      int depositDuration;
      @Column(name="roi")
      int rateOfInterest;
      public FixedDeposits() {}
	  public FixedDeposits(int fixedDepositId, int depositDuration, int rateOfInterest) 
	  {
		this.fixedDepositId = fixedDepositId;
		this.depositDuration = depositDuration;
		this.rateOfInterest = rateOfInterest;
	  }
	public int getFixedDepositId() {
		return fixedDepositId;
	}
	public void setFixedDepositId(int fixedDepositId) {
		this.fixedDepositId = fixedDepositId;
	}
	public int getDepositDuration() {
		return depositDuration;
	}
	public void setDepositDuration(int depositDuration) {
		this.depositDuration = depositDuration;
	}
	public int getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(int rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}  
}
