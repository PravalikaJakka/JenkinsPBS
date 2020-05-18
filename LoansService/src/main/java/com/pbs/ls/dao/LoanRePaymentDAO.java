package com.pbs.ls.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pbs.ls.dto.LoanRePayment;
public interface LoanRePaymentDAO extends JpaRepository<LoanRePayment,Integer>
{

}