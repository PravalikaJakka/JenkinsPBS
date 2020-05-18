package com.pecunia.ts.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pecunia.ts.dao.TransactionDAO;
import com.pecunia.ts.dto.Account;
import com.pecunia.ts.dto.Transaction;
@Service
public class TransactionService 
{
    @Autowired TransactionDAO transactionDao;
    public void setTransactionDao(TransactionDAO transactionDao)
    {
    	this.transactionDao = transactionDao;
    }
    
    @Transactional(readOnly=true)
    public Optional<Transaction> getTransaction(int transId)
    {
    	return this.transactionDao.findById(transId);
    }
    
    @Transactional(readOnly=true)
    public List<Transaction> getAllTransaction()
    {
    	return this.transactionDao.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactionByAccount(Account account)
    {
    	return transactionDao.findAllTransByAccount(account);
    }
    @Transactional
    public Transaction  insertTransaction(Transaction transaction)
    {
    	Transaction t = this.transactionDao.save(transaction);
    	this.transactionDao.flush();
    	return t;
    }
    
    @Transactional
    public void deleteTransaction(int transId)
    {
    	this.transactionDao.deleteById(transId);
    	this.transactionDao.flush();
    }
}
