package com.pecunia.fds.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pecunia.fds.dao.FixedDepositHoldersDAO;
import com.pecunia.fds.dto.FixedDepositHolders;
@Service
public class FixedDepositHoldersService 
{
    @Autowired FixedDepositHoldersDAO fdhDao;
    public void setFixedDepositHoldersDao(FixedDepositHoldersDAO fdhDao) { this.fdhDao=fdhDao;}
    
    @Transactional(readOnly=true)
    public Optional<FixedDepositHolders> getFixedDepositHolders(int depositHolderId)
    {
	    return  this.fdhDao.findById(depositHolderId); 
    }
    
    @Transactional(readOnly=true)
    public List<FixedDepositHolders> getAllFixedDepositHolders()
    {
	    return  this.fdhDao.findAll(); 
    }
    
    @Transactional
    public FixedDepositHolders insertFixedDepositHolders(FixedDepositHolders fixedDepositHolders)
    {
	    FixedDepositHolders fixedDepositHolders1 = this.fdhDao.save(fixedDepositHolders);
	    this.fdhDao.flush();
	    return fixedDepositHolders1;
    }
    
    @Transactional(readOnly=true)
    public void deleteFixedDepositHolders(int depositHolderId)
    {
	    this.fdhDao.deleteById(depositHolderId); 
    }
}
