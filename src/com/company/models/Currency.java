package com.company.models;

public class Currency {

    private final String currency_name;
    private final double rate;

    public Currency(String currency_name, double rate) {
        this.currency_name = currency_name;
        this.rate = rate;
    }

    public String getCurrency_name() {
        return currency_name;
    }
    public double getRate() {
        return rate;
    }

}
