package com.pbs.ls.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pbs.ls.dao.LoanRePaymentDAO;
import com.pbs.ls.dto.LoanRePayment;

@Service
public class LoanRePaymentService 
{
    @Autowired LoanRePaymentDAO loanRePaymentDao;
    public void setLoanRePaymentDao(LoanRePaymentDAO loanRePaymentDao)
    {
    	this.loanRePaymentDao = loanRePaymentDao;
    }
     
    @Transactional(readOnly=true)
    public Optional<LoanRePayment> getLoanRePayment(int loanPayId)
    {
    	return loanRePaymentDao.findById(loanPayId);
    }
 
    @Transactional(readOnly=true)
    public List<LoanRePayment> getAllLoanRePayment()
    {
    	return loanRePaymentDao.findAll();
    }
    
    @Transactional
    public void insertLoanRePayment(LoanRePayment loanRePayment)
    {
    	this.loanRePaymentDao.save(loanRePayment);
    }
    
    @Transactional
    public void deleteLoanRePayment(int loanPayId)
    {
    	loanRePaymentDao.deleteById(loanPayId);
    }
}