package com.pecunia.pbs.dto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="bankdetails")
public class BanksDetails 
{
    @Id
    @Column(name="ifsc_code")
    String ifscCode;
    @Column(name="bank_name")
    String bankName;
    @Column(name="bank_branch")
    String branch;
    public BanksDetails() {}
	public BanksDetails(String ifscCode, String bankName, String branch) 
	{
		this.ifscCode = ifscCode;		this.bankName = bankName;		this.branch = branch;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
}
