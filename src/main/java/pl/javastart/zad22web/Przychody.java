package pl.javastart.zad22web;

import java.sql.Date;


public class Przychody {


    private long id;
    private  String type;
    private String description;
    private long amount;  // kwota transakcji
    private Date date;  // data transakcji


    public Przychody() {
    }

    public long getId() {
        return id;
    }

    public long setId() {
        return id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
