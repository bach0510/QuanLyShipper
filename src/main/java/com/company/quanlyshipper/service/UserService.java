/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.service;

import com.company.quanlyshipper.AppException;
import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.Users;
import com.microsoft.sqlserver.jdbc.StringUtils;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.quanlyshipper.repo.UsersRepo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;

/**
 *
 * @author Bach
 */
@Service
public class UserService {
    @Autowired
    private UsersRepo userRepo;
       
    public List<Users> getAllShipperInfo(String fullname,String cmnd,String tel,String code,String email,String type,Areas area){
//        List<Users> shippers = userRepo.findShipperByArgument(fullname, cmnd, tel, code, email,type,areaId);
//        //List<Users> shippers = userRepo.findAll();
//        return shippers;
        StringBuffer sb = new StringBuffer(" select u from Users u where u.RoleId = 2");
        Map<String,Object> params = new HashMap<>();
        if(area != null){
            sb.append("and u.Area = :area");
            params.put("area", area);
        }
        if(!StringUtils.isEmpty(fullname) 
                || !StringUtils.isEmpty(cmnd)
                || !StringUtils.isEmpty(tel)
                || !StringUtils.isEmpty(code)
                || !StringUtils.isEmpty(email)
                || !StringUtils.isEmpty(type)){
            sb.append(" and u.FullName LIKE :fullname"
                    + " and u.Cmnd LIKE :cmnd"
                    + " and u.Email LIKE :email"
                    + " and u.Type LIKE :type"
                    + " and u.Tel LIKE :tel"
                    + " and u.Code LIKE :code");
            params.put("fullname", fullname.concat("%"));
            params.put("email", email.concat("%"));
            params.put("tel", tel.concat("%"));
            params.put("code", code.concat("%"));
            params.put("type", type.concat("%"));
            params.put("cmnd", cmnd.concat("%"));
        }
        
        
        return userRepo.findByQuery(sb.toString(), params);
    }
    
    public ObservableList<String> getAllShipperCode(){
        ObservableList<String> shippersCode = userRepo.findAllShippersCode();
        //List<Users> shippers = userRepo.findAll();
        return shippersCode;
    }
    
    public Users getShipperInfoByCode(String code){
        Users shippers = userRepo.findShipperByCode(code);
        //List<Users> shippers = userRepo.findAll();
        return shippers;
    }
    
    public void save(Users user){
        userRepo.save(user);
    }
    
    public void deleteUser(Users user){
        userRepo.delete(user);
    }
    
}
