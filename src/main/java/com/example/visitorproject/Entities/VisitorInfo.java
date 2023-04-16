package com.example.visitorproject.Entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="visitorinandout")
public class VisitorInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="visitorid")
    public Integer visitorId;
    @Column(name ="fullname" )
    public String fullName;
    @Column(name ="email" )
    public String email;
    @Column(name ="contactnumber" )
    public long contact;
    @Column(name ="visitingfrom" )
    public String visitingFrom;
    @Column(name ="intime" )
    public Timestamp entryTime;
    @Column(name ="outtime" )
    public Timestamp exitTime;
    @Column(name ="purpose" )
    public String purpose;

}
