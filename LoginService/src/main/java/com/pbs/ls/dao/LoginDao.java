package com.pbs.ls.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbs.ls.dto.Login;

@Repository
public interface LoginDao extends JpaRepository<Login,Long>
{

}
