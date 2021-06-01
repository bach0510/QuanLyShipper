/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.service;

import com.company.quanlyshipper.AppException;
import com.company.quanlyshipper.model.Orders;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.repo.OrdersRepo;
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
public class OrderService {
    @Autowired
    private OrdersRepo orderRepo;
       
    public List<Orders> getAllOrder(){
        List<Orders> orderList = orderRepo.findAll();
        //List<Users> shippers = userRepo.findAll();
        return orderList;
    }
    
    public void save(Orders order){
        orderRepo.save(order);
    }
    
    public void deleteOrder(Orders order){
        orderRepo.delete(order);
    }
    
}
