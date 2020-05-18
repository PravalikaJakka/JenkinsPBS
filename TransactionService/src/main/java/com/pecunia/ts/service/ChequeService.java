package com.pecunia.ts.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pecunia.ts.dao.ChequeDAO;
import com.pecunia.ts.dto.Cheque;
@Service
public class ChequeService 
{
     @Autowired ChequeDAO chequeDao;
     public void setChequeDao(ChequeDAO chequeDao) { this.chequeDao=chequeDao; }
     
     @Transactional(readOnly=true)
     public Optional<Cheque> getCheque(int chequeId)
     {
    	 return this.chequeDao.findById(chequeId);
     }
     
     @Transactional(readOnly=true)
     public List<Cheque> getCheque()
     {
    	 return this.chequeDao.findAll();
     }
     
     @Transactional
     public void insertCheque(Cheque cheque)
     {
    	 this.chequeDao.save(cheque);
    	 this.chequeDao.flush();
     }
     
     @Transactional
     public void deleteCheque(int chequeId)
     {
    	 this.chequeDao.deleteById(chequeId);
    	 this.chequeDao.flush();
     }
}
