package com.pbs.ls.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pbs.ls.dto.SanctionedLoans;
public interface SanctionedLoansDAO extends JpaRepository<SanctionedLoans,Integer>
{

}
