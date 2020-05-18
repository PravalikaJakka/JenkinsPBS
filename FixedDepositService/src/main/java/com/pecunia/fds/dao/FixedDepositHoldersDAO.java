package com.pecunia.fds.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pecunia.fds.dto.FixedDepositHolders;
@Repository
public interface FixedDepositHoldersDAO extends JpaRepository<FixedDepositHolders,Integer>
{

}
