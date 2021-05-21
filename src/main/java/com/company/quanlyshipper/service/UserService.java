/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.service;

import com.company.quanlyshipper.AppException;
import com.company.quanlyshipper.model.Users;
import com.microsoft.sqlserver.jdbc.StringUtils;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.quanlyshipper.repo.UsersRepo;
import java.util.List;

/**
 *
 * @author Bach
 */
@Service
public class UserService {
    @Autowired
    private UsersRepo userRepo;
       
    public List<Users> getAllShipperInfo(String fullname,String cmnd,String tel,String code,String email){
        List<Users> shippers = userRepo.findShipperByArgument(fullname, cmnd, tel, code, email);
        //List<Users> shippers = userRepo.findAll();
        return shippers;
    }
    
    public void save(Users user){
        userRepo.save(user);
    }
}
