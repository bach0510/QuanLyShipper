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

/**
 *
 * @author Bach
 */
@Service
public class LoginService {
    @Autowired
    private UsersRepo userRepo;
       
    public Users login (String userName, String password) {
//        Connection conn = ConnectDb();
        if(StringUtils.isEmpty(userName)){
            throw new AppException("Tên đăng nhập không được để trống");
        }
        if(StringUtils.isEmpty(password)){
            throw new AppException("Mật khẩu không được để trống");
        }
        Users user = userRepo.findByNameAndPassword(userName,password);
                //.orElseThrow(()-> new AppException("Vui lòng check lại thông tin đăng nhập"));
        if(!password.equals(user.getPassword())){
            throw new AppException("Mật khẩu bạn vừa nhập không đúng");
        }  
        return user;
            //.orElseThrow(() -> new AppException("Vui lòng check lại thông tin đăng nhập"));
        
    }
}
