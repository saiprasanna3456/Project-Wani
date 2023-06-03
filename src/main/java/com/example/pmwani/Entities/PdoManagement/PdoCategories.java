package com.example.pmwani.Entities.PdoManagement;

import jakarta.persistence.*;

@Entity
@Table(name = "pdo_categories")
public class PdoCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryid")
    public Integer CategoryId;

    @Column(name = "categoryname")
    public String CategoryName;

    @Column(name = "isenabled")
    public boolean isEnabled;
}
