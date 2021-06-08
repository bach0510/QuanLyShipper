/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.model;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Data
@Entity(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int Id ;
    
    @ManyToOne
    private Areas Area;

    @Column(name="order_code",columnDefinition = "nvarchar(MAX)")
    
    private String OrderCode;
    
    @Column(name="cus_tel",columnDefinition = "nvarchar(MAX)")
    
    private String CusTel;
    @Column(name="cus_name",columnDefinition = "nvarchar(MAX)")
    
    private String CusName;
    
    @Column(name="user_id")
    
    private int UserId;
    @Column(name="status",columnDefinition = "nvarchar(MAX)")
    
    private String Status;
    
    @Column(name="delivery_add",columnDefinition = "nvarchar(MAX)")
    
    private String DeliveryAdd;
    @Column(name="delivery_date")
    
    private LocalDate DeliveryDate; // ngay giao
    
    @Column(name="create_date")
    private LocalDate CreateDate; // ngay giao

    public int getId() {
        return Id;
    }

    public Areas getArea() {
        return Area;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public String getCusTel() {
        return CusTel;
    }

    public String getCusName() {
        return CusName;
    }

    public int getUserId() {
        return UserId;
    }

    public String getStatus() {
        return Status;
    }

    public LocalDate getDeliveryDate() {
        return DeliveryDate;
    }

    public LocalDate getCreateDate() {
        return CreateDate;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setArea(Areas Area) {
        this.Area = Area;
    }

    public void setOrderCode(String OrderCode) {
        this.OrderCode = OrderCode;
    }

    public void setCusTel(String CusTel) {
        this.CusTel = CusTel;
    }

    public void setCusName(String CusName) {
        this.CusName = CusName;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setDeliveryDate(LocalDate DeliveryDate) {
        this.DeliveryDate = DeliveryDate;
    }

    public void setCreateDate(LocalDate CreateDate) {
        this.CreateDate = CreateDate;
    }

    public String getDeliveryAdd() {
        return DeliveryAdd;
    }

    public void setDeliveryAdd(String DeliveryAdd) {
        this.DeliveryAdd = DeliveryAdd;
    }

    public Orders() {
    }

    public Orders(int Id, Areas Area, String OrderCode, String CusTel, String CusName, int UserId, String Status, String DeliveryAdd, LocalDate DeliveryDate, LocalDate CreateDate) {
        this.Id = Id;
        this.Area = Area;
        this.OrderCode = OrderCode;
        this.CusTel = CusTel;
        this.CusName = CusName;
        this.UserId = UserId;
        this.Status = Status;
        this.DeliveryAdd = DeliveryAdd;
        this.DeliveryDate = DeliveryDate;
        this.CreateDate = CreateDate;
    }

    

   
}
