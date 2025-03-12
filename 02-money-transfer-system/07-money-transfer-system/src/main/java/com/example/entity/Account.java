package com.example.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// ORM - Object Relational Mapping

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    private String number;
    private double balance;

    public Account(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    public Account() {
    }

    public String getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setNumber(String number) {
        this.number = number;
    }



}
