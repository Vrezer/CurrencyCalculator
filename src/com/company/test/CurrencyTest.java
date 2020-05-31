package com.company.test;

import com.company.models.Currency;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyTest {

    Currency p = new Currency("PLN",4.43);

    @Test
    public void getCurrency_name_TRUE() {
        Assert.assertTrue(p.getCurrency_name().equals("PLN"));
    }

    @Test
    public void getCurrency_name_FALSE() {
        Assert.assertFalse(p.getCurrency_name().equals("LKI"));
    }

    @Test
    public void getRate_TRUE() {
        Assert.assertTrue(p.getRate()==4.43);
    }

    @Test
    public void getRate_FALSE() {
        Assert.assertFalse(p.getRate()==4.5);
    }

}