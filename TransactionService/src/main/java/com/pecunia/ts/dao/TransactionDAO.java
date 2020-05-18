package com.pecunia.ts.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pecunia.ts.dto.Account;
import com.pecunia.ts.dto.Transaction;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction,Integer>
{
	public List<Transaction>  findAllTransByAccount(Account account);
}
