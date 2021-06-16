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
    
    @Column(name="cus_tel",columnDefinition = "nvarchar(MAX)")
    
    private String CusTel;
    @Column(name="cus_name",columnDefinition = "nvarchar(MAX)")
    
    private String CusName;
    
    @ManyToOne
    private Users User;
    
    @Column(name="status",columnDefinition = "nvarchar(MAX)")
    
    private String Status;
    
    @Column(name="delivery_add",columnDefinition = "nvarchar(MAX)")
    
    private String DeliveryAdd;
    @Column(name="receive_date")
    
    private LocalDate ReceiveDate; // ngay giao
    
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

    public void setCusTel(String CusTel) {
        this.CusTel = CusTel;
    }

    public void setCusName(String CusName) {
        this.CusName = CusName;
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

    
    public Orders() {
    }

    public Orders(int Id, Areas Area, Customer Customer, String OrderCode, String CusTel, String CusName, Users User, String Status, String DeliveryAdd, LocalDate ReceiveDate, LocalDate CreateDate) {
        this.Id = Id;
        this.Area = Area;
        this.Customer = Customer;
        this.OrderCode = OrderCode;
        this.CusTel = CusTel;
        this.CusName = CusName;
        this.User = User;
        this.Status = Status;
        this.DeliveryAdd = DeliveryAdd;
        this.ReceiveDate = ReceiveDate;
        this.CreateDate = CreateDate;
    }

    

    

   
}
