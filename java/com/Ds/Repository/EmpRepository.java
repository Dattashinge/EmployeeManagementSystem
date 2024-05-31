package com.Ds.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ds.Entity.Employee;

@Repository
public interface EmpRepository extends JpaRepository<Employee ,Integer  >{

}
