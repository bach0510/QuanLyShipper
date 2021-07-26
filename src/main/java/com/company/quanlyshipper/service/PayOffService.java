/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.service;

import com.company.quanlyshipper.AppException;
import com.company.quanlyshipper.model.Bonus;
import com.company.quanlyshipper.model.OrderDetail;
import com.company.quanlyshipper.model.Orders;
import com.company.quanlyshipper.model.Punish;
import com.company.quanlyshipper.model.Users;
import com.company.quanlyshipper.repo.BonusRepo;
import com.company.quanlyshipper.repo.OrderDetailRepo;
import com.company.quanlyshipper.repo.OrdersRepo;
import com.company.quanlyshipper.repo.PunishRepo;
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
public class PayOffService {
    @Autowired
    private PunishRepo punishRepo;
    @Autowired
    private BonusRepo bonusRepo;
       
    public List<Bonus> getAllBonus(){
        return bonusRepo.findAll();
    }
    public List<Punish> getAllPunish(){
        return punishRepo.findAll();
    }
    
    
    public void savePunish(Punish p){
        punishRepo.save(p);
    }
    
    public void deletePunish(Punish p){
        punishRepo.delete(p);
    }
    public void saveBonus(Bonus b){
        bonusRepo.save(b);
    }
    
    public void deleteBonus(Bonus b){
        bonusRepo.delete(b);
    }
    
}
