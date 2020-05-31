package com.company.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    Scanner scanner=new Scanner(System.in);
    public List <Currency> currency_list= new ArrayList<Currency>();
    //constructor
    public Calculator() {}

    // Main Function with switch
    public void Start()
    {
        StartInfo();
        Parser();
        int option;
        double value;
        do
        {
            System.out.println("\nChoose option: ");
            option = scanner.nextInt();
            if (option == 0) break;
            System.out.println("Enter the amount of Euro: ");
            value = scanner.nextDouble();
            switch(option)
            {
                case 1:
                    ChooseCurrency("USD",value);
                    break;
                case 2:
                    ChooseCurrency("JPY",value);
                    break;
                case 3:
                    ChooseCurrency("BGN",value);
                    break;
                case 4:
                    ChooseCurrency("CZK",value);
                    break;
                case 5:
                    ChooseCurrency("DKK",value);
                    break;
                case 6:
                    ChooseCurrency("GBP",value);
                    break;
                case 7:
                    ChooseCurrency("HUF",value);
                    break;
                case 8:
                    ChooseCurrency("PLN",value);
                    break;
                case 9:
                    ChooseCurrency("RON",value);
                    break;
                case 10:
                    ChooseCurrency("SEK",value);
                    break;
                case 11:
                    ChooseCurrency("CHF",value);
                    break;
                case 12:
                    ChooseCurrency("ISK",value);
                    break;
                case 13:
                    ChooseCurrency("NOR",value);
                    break;
                case 14:
                    ChooseCurrency("HRK",value);
                    break;
                case 15:
                    ChooseCurrency("RUB",value);
                    break;
                case 16:
                    ChooseCurrency("TRY",value);
                    break;
                case 17:
                    ChooseCurrency("AUD",value);
                    break;
                case 18:
                    ChooseCurrency("BRL",value);
                    break;
                case 19:
                    ChooseCurrency("CAD",value);
                    break;
                case 20:
                    ChooseCurrency("CNY",value);
                    break;
                case 21:
                    ChooseCurrency("HKD",value);
                    break;
                case 22:
                    ChooseCurrency("IDR",value);
                    break;
                case 23:
                    ChooseCurrency("ILS",value);
                    break;
                case 24:
                    ChooseCurrency("INR",value);
                    break;
                case 25:
                    ChooseCurrency("KRW",value);
                    break;
                case 26:
                    ChooseCurrency("MXN",value);
                    break;
                case 27:
                    ChooseCurrency("MYR",value);
                    break;
                case 28:
                    ChooseCurrency("NZD",value);
                    break;
                case 29:
                    ChooseCurrency("PHP",value);
                    break;
                case 30:
                    ChooseCurrency("SGD",value);
                    break;
                case 31:
                    ChooseCurrency("THB",value);
                    break;
                case 32:
                    ChooseCurrency("ZAR",value);
                    break;
                default:
                    break;
            }

        }

        while (option!=0);
    }

    //displaying possible choices
    private void StartInfo()
    {
        System.out.println("\nWelcome to Currency Calculator");
        System.out.println("Data is taken from European Central Bank\n");
        System.out.println(" 1. USD");
        System.out.println(" 2. JPY");
        System.out.println(" 3. BGN");
        System.out.println(" 4. CZK");
        System.out.println(" 5. DKK");
        System.out.println(" 6. GBP");
        System.out.println(" 7. HUF");
        System.out.println(" 8. PLN");
        System.out.println(" 9. RON");
        System.out.println("10. SEK");
        System.out.println("11. CHF");
        System.out.println("12. ISK");
        System.out.println("13. NOK");
        System.out.println("14. HRK");
        System.out.println("15. RUB");
        System.out.println("16. TRY");
        System.out.println("17. AUD");
        System.out.println("18. BRL");
        System.out.println("19. CAD");
        System.out.println("20. CNY");
        System.out.println("21. HDK");
        System.out.println("22. IDR");
        System.out.println("23. ILS");
        System.out.println("24. INR");
        System.out.println("25. KRW");
        System.out.println("26. MXN");
        System.out.println("27. MYR");
        System.out.println("28. NZD");
        System.out.println("29. PHP");
        System.out.println("30. SGD");
        System.out.println("31. THB");
        System.out.println("32. ZAR");
        System.out.println(" 0. Close Program");
    }

    //parser work in line-by-line search mode
    public List<Currency> Parser()
    {
        try {
            String path = System.getProperty("user.dir"); // get current directory
            InputStream inputStream = new FileInputStream(new File(path+"\\eurofxref-daily.xml")); //path : current_diretory\file_name.xml
            Scanner scanner =new Scanner(inputStream);

            while (scanner.hasNext())
            {
                String line=scanner.nextLine();
                if(line.startsWith("<Cube currency"))
                {
                    Pattern p = Pattern.compile("rate=\"(.+?)\"");
                    Matcher m = p.matcher(line);
                    m.find();

                    int start_index_name=line.indexOf("\"");
                    String name=line.substring(start_index_name+1, start_index_name+4)	;
                    Currency currency = new Currency(name,Double.parseDouble(m.group(1)));
                    currency_list.add(currency);
                }
            }
            scanner.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return currency_list;
    }

    // function enumerating
    public void ChooseCurrency(String name,double value)
    {
        DecimalFormat twoDForm = new DecimalFormat("0.00");

        for (Currency p : currency_list)
        {
            if (p.getCurrency_name().equals(name))
                System.out.println("\n"+twoDForm.format(value) + " EUR = "+twoDForm.format(value*p.getRate())+" "+name);
        }
    }


}
