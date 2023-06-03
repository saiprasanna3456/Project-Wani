package com.example.pmwani.Entities.PdoManagement;
import jakarta.persistence.Entity;

import jakarta.persistence.*;

@Entity
public class PdoPlanNames{

    @Id
    @Column(name="planid")
    public String NameId;

    @Column(name="planname")
    public String PlanName;

    @Column(name="planprice")
    public double PlanPrice;

    @Column(name="datalimitgb")
    public int PlanDataVolume;

    @Column(name="dataspeed")
    public int PlanSpeed;

    @Column(name="planvalidity")
    public int PlanValidity;

    @Column(name="plandescription")
    public String PlanDesc;
  
    @Column(name="isenabled")
    public boolean IsEnabled;
}