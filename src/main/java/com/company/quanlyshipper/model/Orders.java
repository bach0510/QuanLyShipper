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
    
    @ManyToOne
    private Customer Customer;

    @Column(name="order_code",columnDefinition = "nvarchar(MAX)")
    
    private String OrderCode;
    
    @Column(name="order_name",columnDefinition = "nvarchar(MAX)")
    
    private String OrderName;
    
    @ManyToOne
    private Users User;
    
    @Column(name="status",columnDefinition = "nvarchar(MAX)")
    
    private String Status;
    
    @Column(name="err_status",columnDefinition = "nvarchar(MAX)")
    
    private String ErrStatus;
    
    @Column(name="delivery_add",columnDefinition = "nvarchar(MAX)")
    
    private String DeliveryAdd;
    @Column(name="receive_date")
    
    private LocalDate ReceiveDate; 
    
    @Column(name="create_date")
    private LocalDate CreateDate; 
    
    @Column(name="price")
    private double Price;
    
    @Column(name="weight")
    private double Weight;

    public int getId() {
        return Id;
    }

    public Areas getArea() {
        return Area;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public Users getUser() {
        return User;
    }

    public String getStatus() {
        return Status;
    }

    public LocalDate getReceiveDate() {
        return ReceiveDate;
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

    public void setUser(Users User) {
        this.User = User;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setReceiveDate(LocalDate ReceiveDate) {
        this.ReceiveDate = ReceiveDate;
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

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer Customer) {
        this.Customer = Customer;
    }

    public double getPrice() {
        return Price;
    }

    public double getWeight() {
        return Weight;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public void setWeight(double Weight) {
        this.Weight = Weight;
    }

    public String getErrStatus() {
        return ErrStatus;
    }

    public void setErrStatus(String ErrStatus) {
        this.ErrStatus = ErrStatus;
    }

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String OrderName) {
        this.OrderName = OrderName;
    }

    public Orders(int Id, Areas Area, Customer Customer, String OrderCode, String OrderName, Users User, String Status, String ErrStatus, String DeliveryAdd, LocalDate ReceiveDate, LocalDate CreateDate, double Price, double Weight) {
        this.Id = Id;
        this.Area = Area;
        this.Customer = Customer;
        this.OrderCode = OrderCode;
        this.OrderName = OrderName;
        this.User = User;
        this.Status = Status;
        this.ErrStatus = ErrStatus;
        this.DeliveryAdd = DeliveryAdd;
        this.ReceiveDate = ReceiveDate;
        this.CreateDate = CreateDate;
        this.Price = Price;
        this.Weight = Weight;
    }


    
    
    public Orders() {
    }

    

    

    

   
}
