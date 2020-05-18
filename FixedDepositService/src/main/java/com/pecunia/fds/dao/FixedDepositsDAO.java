package com.pecunia.fds.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pecunia.fds.dto.FixedDeposits;

@Repository
public interface FixedDepositsDAO extends JpaRepository<FixedDeposits,Integer>
{

}
