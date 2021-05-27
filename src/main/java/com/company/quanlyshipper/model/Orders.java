/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @Column(name="area_id")
    
    private int AreaId;

    @Column(name="order_code",columnDefinition = "nvarchar(MAX)")
    
    private String OrderCode;
    
    @Column(name="user_id")
    
    private int UserId;
    @Column(name="status")
    
    private int Status;
    @Column(name="delivery_date")
    
    private Date DeliveryDate; // ngay giao

    public Orders(int Id, int AreaId, int UserId, int Status, Date DeliveryDate) {
        this.Id = Id;
        this.AreaId = AreaId;
        this.UserId = UserId;
        this.Status = Status;
        this.DeliveryDate = DeliveryDate;
    }

    public Orders(){}
    
    public void setId(int Id) {
        this.Id = Id;
    }

    public void setAreaId(int AreaId) {
        this.AreaId = AreaId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public void setDeliveryDate(Date DeliveryDate) {
        this.DeliveryDate = DeliveryDate;
    }
    
    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String OrderCode) {
        this.OrderCode = OrderCode;
    }
    
    public int getId() {
        return Id;
    }

    public int getAreaId() {
        return AreaId;
    }

    public int getUserId() {
        return UserId;
    }

    public int getStatus() {
        return Status;
    }

    public Date getDeliveryDate() {
        return DeliveryDate;
    }
    
    
}
