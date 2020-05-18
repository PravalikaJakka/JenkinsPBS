package com.pecunia.ts.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pecunia.ts.dto.BanksDetails;

@Repository
public interface BankDetailsDAO  extends JpaRepository<BanksDetails,String>
{

}
