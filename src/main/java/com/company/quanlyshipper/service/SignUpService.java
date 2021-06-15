/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.service;

import com.company.quanlyshipper.AppException;
import com.company.quanlyshipper.model.Role;
import com.company.quanlyshipper.repo.RoleRepo;
import com.microsoft.sqlserver.jdbc.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class SignUpService {
    @Autowired
    private RoleRepo roleRepo;
    
    public List<Role> getAllRole(){
        List<Role> roles = roleRepo.findAll();
        return roles;
    }
       
//    public List<Role> signUp () {
////        Connection conn = ConnectDb();
//        if(StringUtils.isEmpty(userName)){
//            throw new AppException("Tên đăng nhập không được để trống");
//        }
//        if(StringUtils.isEmpty(password)){
//            throw new AppException("Mật khẩu không được để trống");
//        }
//        List<Role> role = roleRepo.findAll();
//                
//        if(!password.equals(user.getPassword())){
//            throw new AppException("Mật khẩu bạn vừa nhập không đúng");
//        }  
//        return user;
//            //.orElseThrow(() -> new AppException("Vui lòng check lại thông tin đăng nhập"));
//        
//           
//    }
}
