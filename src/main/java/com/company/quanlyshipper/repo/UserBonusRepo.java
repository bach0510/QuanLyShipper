/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.repo;

import com.company.quanlyshipper.BaseRepository;
import com.company.quanlyshipper.model.UserBonus;

/**
 *
 * @author Admin
 */
public interface UserBonusRepo extends BaseRepository<UserBonus, Integer> {
//    @Query("SELECT u FROM Users u WHERE u.UserName = ?1 AND u.Password = ?2")
//    Users findByNameAndPassword(String username,String password);
}