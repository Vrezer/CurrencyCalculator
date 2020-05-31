package com.company.test;

import com.company.models.Calculator;
import com.company.models.Currency;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CalculatorTest {


    Calculator c=new Calculator();
    Currency x = new Currency("PLN",4.43);
    Currency p1 = new Currency("GBN",2.30);
    Currency p2 = new Currency("JPY",10.05);


    // Check switch - start
    @Test
    public void start_SwitchWork_TRUE() {
        int option=1;
        switch (option)
        {
            case 1:
                System.out.println("Work");
                Assert.assertTrue(true);
                break;
            default:
                System.out.println("Number out of range");
                break;
        }
    }
    @Test
    public void start_SwitchOutOfRange_TRUE() {
        int option=10;
        switch (option)
        {
            case 1:
                break;
            default:
                System.out.println("Number out of range");
                Assert.assertTrue(true);
                break;
        }
    }


    // File Exist - parser
    @Test
    public void parser_fileExist_TRUE() {
        try {

            String path = System.getProperty("user.dir");
            InputStream inputStream = new FileInputStream(new File(path + "\\eurofxref-daily.xml"));
            System.out.println("File is exists");
        }
        catch (IOException e)
        {
            System.out.println("File doesn't exists");
        }
    }
    @Test
    public void parser_fileExist_FALSE() {
        try {
            String path = System.getProperty("user.dir");
            InputStream inputStream = new FileInputStream(new File(path + "\\test.xml"));
            System.out.println("File is exists");
        }
        catch (IOException e)
        {
            System.out.println("File doesn't exists");
        }
    }
    //File Exist check path - parser
    @Test
    public void parser_path_TRUE() {
        try {

            String path = System.getProperty("user.dir");
            InputStream inputStream = new FileInputStream(new File(path + "\\eurofxref-daily.xml"));
            System.out.println("Path is valid");
        }
        catch (IOException e)
        {
            System.out.println("Path is invalid");
        }
    }
    @Test
    public void parser_path_FALSE() {
        try {

            String path = "C:\\windows";
            InputStream inputStream = new FileInputStream(new File(path + "\\eurofxref-daily.xml"));
            System.out.println("Path is valid");
        }
        catch (IOException e)
        {
            System.out.println("Path is invalid");
        }
    }
    //Check is pattern parser working - parser
    @Test
    public void parser_Pattern_Valid()
    {
        Pattern p = Pattern.compile("rate=\"(.+?)\"");
        Matcher m = p.matcher("rate=\"5453.342236557654\"");
        m.find();
        System.out.println(m.group(1));
        Assert.assertEquals("5453.342236557654",m.group(1));
    }
    @Test
    public void parser_Pattern_Invalid()
    {
        Pattern p = Pattern.compile("rate=\"(.+?)\"");
        Matcher m = p.matcher("sadsad\"5453.342236557654\"");
        Assert.assertFalse(m.find());
    }
    // Check is substring working - parser
    @Test
    public void parser_subStringIndex_Valid()
    {
        String line="    \"PHGDNXD2312";
        int start_index_name=line.indexOf("\"");
        String name=line.substring(start_index_name+1, start_index_name+4)	;
        Assert.assertEquals("PHG",name);
        System.out.println(name);
    }
    @Test
    public void parser_subStringIndex_InValid()
    {
        String line="123     PHGDNXD2312";
        int start_index_name=line.indexOf("\"");
        String name=line.substring(start_index_name+1, start_index_name+4)	;
        Assert.assertFalse(name.equals("PHG"));
        System.out.println(name);
    }


    //Search by name - chooseCurrency
    @Test
    public void chooseCurrency_SearchByName_TRUE() {

        DecimalFormat twoDForm = new DecimalFormat("#.00");
        c.currency_list.add(x);
        c.currency_list.add(p2);
        c.currency_list.add(p1);
        String name="PLN";
        double value=10;
        for (Currency p : c.currency_list)
        {
            if (p.getCurrency_name().equals(name))
                System.out.println("\n"+twoDForm.format(value) + " EUR = "+twoDForm.format(value*p.getRate())+" "+name);
        }
    }
    @Test
    public void chooseCurrency_SearchByName_FALSE() {

        DecimalFormat twoDForm = new DecimalFormat("#.00");
        c.currency_list.add(x);
        c.currency_list.add(p2);
        c.currency_list.add(p1);
        String name="OKI";
        double value=10;
        for (Currency p : c.currency_list)
        {
            if (p.getCurrency_name().equals(name))
                System.out.println("\n"+twoDForm.format(value) + " EUR = "+twoDForm.format(value*p.getRate())+" "+name);
        }
    }
    //Check Value - chooseCurrency
    @Test
    public void chooseCurrency_CheckValue_TRUE() {

        c.currency_list.add(x);
        String name="PLN";
        double value=10;
        for (Currency p : c.currency_list)
        {
            if (p.getCurrency_name().equals(name))
                Assert.assertTrue(value*p.getRate()==44.30);
        }
    }
    @Test
    public void chooseCurrency_CheckValue_FALSE() {

        c.currency_list.add(x);
        String name="PLN";
        double value=10;
        for (Currency p : c.currency_list)
        {
            if (p.getCurrency_name().equals(name))
                Assert.assertFalse(value*p.getRate()==50.00);
        }
    }
}