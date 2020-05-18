package com.pecunia.ts.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pecunia.ts.dto.Cheque;

@Repository
public interface ChequeDAO extends JpaRepository<Cheque,Integer>
{

}
