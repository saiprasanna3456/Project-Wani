package com.example.pmwani.Entities.PdoManagement;

import jakarta.persistence.Entity;

import jakarta.persistence.*;

@Entity
public class PdoRechargePlans{
    @Id
    @Column(name="planid")
    public int PlanId;
    @Column(name="planname")
    public String PlanName;
    @Column(name="validity")
    public String Validity;
    @Column(name="planprice")
    public double Price;
    @Column(name="datalimitmb")
    public int DataLimitMB;
    @Column(name="datalimitgb")
    public int DataLimitGB;
    @Column(name="plandescription")
    public String PlanDescription;
    @Column(name="isenabled")
    public boolean IsActive;

     

}