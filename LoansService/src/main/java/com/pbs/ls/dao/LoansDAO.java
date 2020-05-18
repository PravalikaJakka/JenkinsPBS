package com.pbs.ls.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pbs.ls.dto.Loans;
public interface LoansDAO extends JpaRepository<Loans,Integer>
{

}
