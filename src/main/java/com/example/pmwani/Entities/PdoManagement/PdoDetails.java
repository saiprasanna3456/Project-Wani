package com.example.pmwani.Entities.PdoManagement;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity

public class PdoDetails {
    @Column(name="pdoid")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int PdoId;
    @Column(name="firstname")
    public String FirstName;
    @Column(name="lastname")
    public String LastName;
    @Column(name="email")
    public String Email;
    @Column(name="primarycontact")
    public Long PrimaryContact;
    @Column(name="secondarycontact")
    public Long SecondaryContact;
    @Column(name="addressl1")
    public String AddressL1;
    @Column(name="addressl2")
    public String AddressL2;
    @Column(name="area")
    public String Area;
    @Column(name="region")
    public String Region;
    @Column(name="distict")
    public String Distirct;
    @Column(name="state")
    public String State;
    @Column(name="pincode")
    public int PinCode;
    @Column(name="categoryid")
    public int CategoryId;
}
