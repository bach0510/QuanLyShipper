/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.model;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="sp_GetSalary")
//@NamedStoredProcedureQuery(name = "sp_GetSalary", procedureName = "sp_GetSalary",
//        resultClasses = SalaryDto.class, parameters = {
//            @StoredProcedureParameter(name = "month", type = Integer.class) , 
//            @StoredProcedureParameter(name = "year", type = Long.class)})
public class SalaryDto {
    @Id
    
    @Column(name="user_id")
    private int UserId;
    
    @Column(name="full_name",columnDefinition = "nvarchar(50)")
    private String FullName;
    
    @Column(name="so_don")
    private int SoDon;
    
    @Column(name="tien_thuong")
    private double TienThuong;
    @Column(name="tien_phat")
    private double TienPhat;
    @Column(name="tien_luong")
    private double TienLuong;
    
    public SalaryDto(){
        
    }

    public SalaryDto( int UserId, String FullName, int SoDon, double TienThuong, double TienPhat, double TienLuong) {
        this.UserId = UserId;
        this.FullName = FullName;
        this.SoDon = SoDon;
        this.TienThuong = TienThuong;
        this.TienPhat = TienPhat;
        this.TienLuong = TienLuong;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public int getUserId() {
        return UserId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public int getSoDon() {
        return SoDon;
    }

    public void setSoDon(int SoDon) {
        this.SoDon = SoDon;
    }

    public double getTienThuong() {
        return TienThuong;
    }

    public void setTienThuong(double TienThuong) {
        this.TienThuong = TienThuong;
    }

    public double getTienPhat() {
        return TienPhat;
    }

    public void setTienPhat(double TienPhat) {
        this.TienPhat = TienPhat;
    }

    public double getTienLuong() {
        return TienLuong;
    }

    public void setTienLuong(double TienLuong) {
        this.TienLuong = TienLuong;
    }
    
}
