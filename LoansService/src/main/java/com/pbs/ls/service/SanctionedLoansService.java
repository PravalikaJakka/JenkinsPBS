package com.pbs.ls.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pbs.ls.dao.SanctionedLoansDAO;
import com.pbs.ls.dto.SanctionedLoans;
@Service
public class SanctionedLoansService 
{
    @Autowired SanctionedLoansDAO sanctionedLoansDao;
    public void setSanctionedLoansDAO(SanctionedLoansDAO sanctionedLoansDao)
    {
    	this.sanctionedLoansDao = sanctionedLoansDao;
    }
    
    @Transactional(readOnly=true)
    public Optional<SanctionedLoans> getSanctionedLoans(int sanctionId)
    {
    	return sanctionedLoansDao.findById(sanctionId);
    }
    
    @Transactional(readOnly=true)
    public List<SanctionedLoans> getAllSanctionedLoans()
    {
    	return sanctionedLoansDao.findAll();
    }
    
    @Transactional
    public void insertSanctionedLoans(SanctionedLoans sanctionedLoans)
    {
    	sanctionedLoansDao.save(sanctionedLoans);
    }
    
    @Transactional
    public void  deleteSanctionedLoans(int sanctionId)
    {
    	sanctionedLoansDao.deleteById(sanctionId);
    }
}