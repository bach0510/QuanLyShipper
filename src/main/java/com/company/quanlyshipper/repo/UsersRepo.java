/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.repo;

import com.company.quanlyshipper.model.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Admin
 */
public interface UsersRepo extends JpaRepository<Users, Integer> {
    @Query("SELECT u FROM Users u WHERE u.UserName = ?1 AND u.Password = ?2")
    Users findByNameAndPassword(String username,String password);
    @Query(""
            + "SELECT u FROM Users u "
            + "WHERE u.UserName LIKE %?1% "
            + "AND u.Cmnd LIKE %?2%"            
            + "AND u.Tel LIKE %?3%"
            + "AND u.Code LIKE %?4%"            
            + "AND u.Email LIKE %?5%"
            + "AND u.RoleId = 2"
            + "")
    List<Users> findShipperByArgument(String fullname,String cmnd,String tel,String code,String email);
}
