package com.pecunia.ts.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pecunia.ts.dao.BankDetailsDAO;
import com.pecunia.ts.dto.BanksDetails;
@Service
public class BanksDetailsService 
{
    @Autowired
    BankDetailsDAO bankDetailsDao;
    public void setBankDetailsDao( BankDetailsDAO bankDetailsDao)
    {
    	this.bankDetailsDao = bankDetailsDao;
    }
    
    @Transactional(readOnly=true)
    public Optional<BanksDetails> getBankDetails(String ifscCode)
    {
    	return  bankDetailsDao.findById(ifscCode);
    }
    
    @Transactional(readOnly=true)
    public List<BanksDetails> getAllBankDetails()
    {
    	return  bankDetailsDao.findAll();
    }
    
    @Transactional
    public void insertBankDetails(BanksDetails bankDetails)
    {
    	bankDetailsDao.save(bankDetails);
    	bankDetailsDao.flush();
    }
    
    @Transactional
    public void deleteBankDetails(String ifscCode)
    {
    	bankDetailsDao.deleteById(ifscCode);
    	bankDetailsDao.flush();
    }
    
}
