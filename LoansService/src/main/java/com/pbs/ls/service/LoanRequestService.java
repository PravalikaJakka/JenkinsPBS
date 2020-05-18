package com.pbs.ls.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pbs.ls.dao.LoanRequestDAO;
import com.pbs.ls.dto.LoanRequest;
@Service
public class LoanRequestService 
{
    @Autowired LoanRequestDAO loanRequestDao;
    public void setLoanRequestDao(LoanRequestDAO loanRequestDao)
    {
    	this.loanRequestDao=loanRequestDao;
    }
    
    @Transactional(readOnly=true)
    public List<LoanRequest> getAllLoanRequest()
    {
    	return loanRequestDao.findAll();
    }
    
    @Transactional
    public LoanRequest insertLoanRequest(LoanRequest loanRequest)
    {
    	 LoanRequest lr = loanRequestDao.save(loanRequest);
    	 this.loanRequestDao.flush();
     	return lr;
    }
    
    @Transactional
    public void deleteLoanRequest(int requestId)
    {
    	 loanRequestDao.deleteById(requestId);
    }

    @Transactional(readOnly=true)
	public Optional<LoanRequest> getLoanRequest(int requestId) 
	{
		
		return loanRequestDao.findById(requestId);
	}
}
