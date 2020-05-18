package com.pbs.ls.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pbs.ls.dto.LoanRequest;
public interface LoanRequestDAO extends JpaRepository<LoanRequest,Integer>
{
	
}