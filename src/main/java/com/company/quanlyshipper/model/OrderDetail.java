/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.quanlyshipper.model;

import javax.persistence.Basic;
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
@Entity(name = "OrderDetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int Id ;

    @Column(name="order_Id")
    
    private int OrderId;
    @Column(name="name",columnDefinition = "nvarchar(MAX)")
    
    private String Name;
    @Column(name="qty")
    
    private int Qty;
    @Column(name="price")
    
    private double Price;
    @Column(name="sum_rice")
    
    private double SumPrice;

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setQty(int Qty) {
        this.Qty = Qty;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public int getQty() {
        return Qty;
    }

    public double getPrice() {
        return Price;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int OrderId) {
        this.OrderId = OrderId;
    }

    public double getSumPrice() {
        return SumPrice;
    }

    public void setSumPrice(double SumPrice) {
        this.SumPrice = SumPrice;
    }

    public OrderDetail(int Id, int OrderId, String Name, int Qty, double Price, double SumPrice) {
        this.Id = Id;
        this.OrderId = OrderId;
        this.Name = Name;
        this.Qty = Qty;
        this.Price = Price;
        this.SumPrice = SumPrice;
    }
    
    
    public OrderDetail(){}

    
}
