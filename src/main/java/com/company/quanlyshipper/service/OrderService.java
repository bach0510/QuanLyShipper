/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.service;

import com.company.quanlyshipper.AppException;
import com.company.quanlyshipper.model.OrderDetail;
import com.company.quanlyshipper.model.Orders;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.repo.OrderDetailRepo;
import com.company.quanlyshipper.repo.OrdersRepo;
import com.microsoft.sqlserver.jdbc.StringUtils;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.quanlyshipper.repo.UsersRepo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Bach
 */
@Service
public class OrderService {
    @Autowired
    private OrdersRepo orderRepo;
    @Autowired
    private OrderDetailRepo orderDetailRepo;
       
    public List<Orders> getAllOrder(String status , String code){
        StringBuffer sb = new StringBuffer(" select o from Orders o where 1=1");
        Map<String,Object> params = new HashMap<>();
        if(!StringUtils.isEmpty(status)){
            sb.append(" and o.Status like concat('%',:status,'%')");
            params.put("status", status);
        }
        if(!StringUtils.isEmpty(code)){
            sb.append(" and o.OrderCode like concat('%',:orderCode,'%')");
            params.put("orderCode", code);
        };
        
        
        return orderRepo.findByQuery(sb.toString(), params);
    }
    
    public List<Orders> getAllAssignedOrder(String status, Users user){
        StringBuffer sb = new StringBuffer(" select o from Orders o where 1=1");
        Map<String,Object> params = new HashMap<>();
        
        if(user != null){
            sb.append("and o.User = :user");
            params.put("user", user);
        }
        if(!StringUtils.isEmpty(status)){
            sb.append(" and o.Status like concat('%',:status,'%')");
            params.put("status", status);
        }
        
        
        return orderRepo.findByQuery(sb.toString(), params);
    }
    public List<OrderDetail> getAllOrderDetail(int orderId){
        StringBuffer sb = new StringBuffer(" select od from OrderDetail od where 1=1");
        Map<String,Object> params = new HashMap<>();
        sb.append("and od.OrderId = :orderId");
        params.put("orderId", orderId);
        return orderDetailRepo.findByQuery(sb.toString(), params);
    }
    
    public Orders searchOrderByCode (String orderCode){
        StringBuffer sb = new StringBuffer(" select o from Orders o where 1=1");
        Map<String,Object> params = new HashMap<>();
        sb.append("and o.OrderCode = :orderCode");
        params.put("orderCode", orderCode);
        return orderRepo.findObjByQuery(sb.toString(), params);
    }
    
    public void save(Orders order){
        orderRepo.save(order);
    }
    
    public void deleteOrder(Orders order){
        orderRepo.delete(order);
    }
    public void saveOrderDetail(OrderDetail orderDetail){
        orderDetailRepo.save(orderDetail);
    }
    
    public void deleteOrderDetail(OrderDetail orderDetail){
        orderDetailRepo.delete(orderDetail);
    }
    
}
