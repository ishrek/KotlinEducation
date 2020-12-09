package com.example.myapplication.javaClass;

import com.example.myapplication.commonKotlin.Invoice;

public class Customer {

    private String name;

    public Customer(String s){
        name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void placeOrder() {
        System.out.println("A new order is placed by " + name);
        Invoice invoice = new Invoice(10, "lacazette");
        System.out.println(invoice.getFirstName());
    }
}
