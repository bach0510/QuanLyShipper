/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.repo;

import com.company.quanlyshipper.BaseRepository;
import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.Users;
import java.util.List;
import javafx.collections.ObservableList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Admin
 */
public interface UsersRepo extends BaseRepository<Users, Integer> {
    @Query("SELECT u FROM Users u WHERE LOWER(u.UserName) = LOWER(?1) AND LOWER(u.Password) = LOWER(?2)")
    Users findByNameAndPassword(String username,String password);
    
    @Query("SELECT u FROM Users u WHERE u.Code = ?1 AND u.RoleId = 2")
    Users findShipperByCode(String code);
    
    @Query("SELECT u FROM Users u WHERE u.Id = ?1")
    Users findInfoById(Integer id);
    
    @Query("SELECT u.Code FROM Users u WHERE u.RoleId = 2")
    ObservableList<String> findAllShippersCode();
    
    @Query(""
            + "SELECT u FROM Users u "
            + "WHERE u.FullName LIKE %?1% "
            + "AND u.Cmnd LIKE %?2%"            
            + "AND u.Tel LIKE %?3%"
            + "AND u.Code LIKE %?4%"            
            + "AND u.Email LIKE %?5%"
            + "AND u.Type LIKE %?6%"            
            + "AND u.Area = ?7"
            + "AND u.RoleId = 2"
            + "")
    List<Users> findShipperByArgument(String fullname,String cmnd,String tel,String code,String email,String type, Areas areaId);
}
