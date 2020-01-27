package com.example.mydonationapp;

public class Case {
    private String name;
    private String fullDescription;
    private String iban;
    private String moneyAmount;
    private String expireDate;
    private String briefDescription;
    private int id;

    public Case(int id,String name,String briefDescription, String fullDescription,  String expireDate, String moneyAmount,String iban) {
        this.name = name;
        this.id = id;
        this.fullDescription = fullDescription;
        this.iban = iban;
        this.moneyAmount = moneyAmount;
        this.expireDate = expireDate;
        this.briefDescription = briefDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


    public String getIban() {
        return iban;
    }

    public String getMoneyAmount() {
        return moneyAmount;
    }

    public String getExpireDate() {
        return expireDate;
    }

   /* public String toString(){

        return name +" - "+ description +" - "+ url;
    }*/

}

