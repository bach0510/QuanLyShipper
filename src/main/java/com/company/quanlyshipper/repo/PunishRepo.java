/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.repo;

import com.company.quanlyshipper.BaseRepository;
import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.Bonus;
import com.company.quanlyshipper.model.Punish;
import com.company.quanlyshipper.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Admin
 */
public interface PunishRepo extends BaseRepository<Punish, Integer> {
//    @Query("SELECT u FROM Users u WHERE u.UserName = ?1 AND u.Password = ?2")
//    Users findByNameAndPassword(String username,String password);
}
