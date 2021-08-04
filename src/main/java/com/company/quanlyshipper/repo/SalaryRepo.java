/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.repo;

import com.company.quanlyshipper.BaseRepository;
import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.SalaryDto;
import com.company.quanlyshipper.model.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface SalaryRepo extends BaseRepository<SalaryDto, Integer> {
    @Query(nativeQuery = true,value = "{call sp_GetSalary(? , ?)}")
    //@Procedure(name = "sp_GetSalary")
    List<SalaryDto> getSalary( Integer month, long year);
}
