package com.pbs.ls.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pbs.ls.dao.LoansDAO;
import com.pbs.ls.dto.Loans;
@Service
public class LoansService 
{
    @Autowired LoansDAO loansDao;
    public void setLoansDao(LoansDAO loansDao)
    {
    	this.loansDao = loansDao;
    }
    
    @Transactional(readOnly=true)
    public Optional<Loans> getLoans(int loanId)
    {
    	return loansDao.findById(loanId);
    }
    
    @Transactional(readOnly=true)
    public List<Loans> getAllLoans()
    {
    	return loansDao.findAll();
    }
    
    @Transactional
    public void insertLoans(Loans loans)
    {
    	loansDao.save(loans);
    }
    
    @Transactional
    public void deleteLoans(int loanId)
    {
    	 loansDao.deleteById(loanId);
    }
}