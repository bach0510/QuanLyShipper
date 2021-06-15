/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.service;

import com.company.quanlyshipper.AppException;
import com.company.quanlyshipper.model.Areas;
import com.company.quanlyshipper.model.Role;
import com.company.quanlyshipper.repo.AreasRepo;
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
public class AreaService {
    @Autowired
    private AreasRepo areaRepo;
    
    public List<Areas> getAllArea(){
        List<Areas> areas = areaRepo.findAll();
        return areas;
    }
       
}
