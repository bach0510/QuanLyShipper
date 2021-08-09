/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.service;

import com.company.quanlyshipper.AppException;
import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.Customer;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.repo.CusRepo;
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
public class CustomerService {
    @Autowired
    private CusRepo cusRepo;
       
    public List<Customer> getAllCustomerInfo(String fullname,String cmnd,String tel,String email,Areas area){
//        List<Users> shippers = userRepo.findShipperByArgument(fullname, cmnd, tel, code, email,type,areaId);
//        //List<Users> shippers = userRepo.findAll();
//        return shippers;
        StringBuffer sb = new StringBuffer(" select c from Customer c where 1=1");
        Map<String,Object> params = new HashMap<>();
        if(area != null && !area.getAreaName().equals("Tất cả")){
            sb.append("and c.Area = :area");
            params.put("area", area);
        }
        if(!StringUtils.isEmpty(fullname) 
                || !StringUtils.isEmpty(cmnd)
                || !StringUtils.isEmpty(tel)
                || !StringUtils.isEmpty(email)){
            sb.append(" and c.CusName like concat('%',:fullname,'%')"
                    + " and c.CusCmnd like concat('%',:cmnd,'%')"
                    + " and c.CusEmail like concat('%',:email,'%')"
                    + " and c.CusTel like concat('%',:tel,'%')");
            params.put("fullname", fullname);
            params.put("email", email);
            params.put("tel", tel);
            params.put("cmnd", cmnd);
        };
        
        
        return cusRepo.findByQuery(sb.toString(), params);
    }
    
//    public ObservableList<String> getAllShipperCode(){
//        ObservableList<String> shippersCode = cusRepo.findAllShippersCode();
//        //List<Users> shippers = userRepo.findAll();
//        return shippersCode;
//    }
//    
    public Customer getCusByTel(String tel){
        Customer cus = cusRepo.findCustomerByTel(tel);
        //List<Users> shippers = userRepo.findAll();
        return cus;
    }
    public List<Customer> getAllCus(){
        StringBuffer sb = new StringBuffer(" select c from Customer c where 1= 1");
        Map<String,Object> params = new HashMap<>();
        return cusRepo.findByQuery(sb.toString(), params);

    }
    
    public void save(Customer cus){
        cusRepo.save(cus);
    }
    
    public void deleteCus(Customer cus){
        cusRepo.delete(cus);
    }
    
}
