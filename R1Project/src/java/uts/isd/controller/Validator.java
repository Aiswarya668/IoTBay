    package uts.isd.controller;

   import java.io.Serializable;
   import java.util.regex.Matcher;
   import java.util.regex.Pattern;
   import java.util.regex.*;
   import javax.servlet.http.HttpSession;


   public class Validator implements Serializable{ 
    private String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";      
    private String namePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";       
    private String passwordPattern = "[a-z0-9]{4,}";       
    
    //device management validator patterns - for server side validation
    private String deviceNamePattern = "(([a-zA-Z0-9]+))";
    //type = Word with capital (space) word with capital
    private String deviceTypePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";
    //Numbers with 2 decimals (.00)
    private String deviceCostPattern = "(^-?\\d*\\.\\d{2}$)";
    //Positive integers of undefined length
    private String deviceStockPattern = "(^\\d+$)";
    private String deviceDescriptionPattern = "(?!^[\\d\\s!\"#$%&'()*+,./:;<=>?@\\^_`{|}~-]+$)^.+$";

    public Validator(){    }       

    public boolean validate(String pattern, String input){       
       Pattern regEx = Pattern.compile(pattern);       
       Matcher match = regEx.matcher(input);       
       return match.matches(); 
    }       

    public boolean checkEmpty(String email, String password){       
       return  email.isEmpty() || password.isEmpty();   
    }


    public boolean validateEmail(String email){                       
       return validate(emailPattern,email);   
    }


    public boolean validateName(String name){
       return validate(namePattern,name); 
    }       


    public boolean validatePassword(String password){
       return validate(passwordPattern,password); 
    }
    
    //device validators - check if fields are empty
     public boolean checkDeviceEmpty(String deviceName, String type, String cost, String stockQuantity, String description){       
       return  deviceName.isEmpty() || type.isEmpty() || cost.isEmpty() || stockQuantity.isEmpty() || description.isEmpty() ;   
    }
    
    //device validators - check deviceName
    public boolean validateDeviceName(String deviceName){
         return validate(deviceNamePattern, deviceName); 
    }
    
    //device validators - check deviceType
    public boolean validateDeviceType(String type){
         return validate(deviceTypePattern, type); 
    }
    
    //device validators - check deviceCost
    public boolean validateDeviceCost(String cost){
         return validate(deviceCostPattern, cost); 
    }
    
    //device validators - check deviceStock
    public boolean validateDeviceStock(String stockQuantity){
         return validate(deviceStockPattern, stockQuantity); 
    }
    
    //device validators - check deviceDescription
    public boolean validateDeviceDesc(String description){
         return validate(deviceDescriptionPattern, description); 
    }
    
    
    public void clear(HttpSession session) {
        session.setAttribute("emailErr", "Enter email");
        session.setAttribute("passErr", "Enter password");
        session.setAttribute("existErr", "");
        session.setAttribute("nameErr", "Enter name");
        //deviceCreation clear()
        session.setAttribute("deviceNameErr", "Enter device name");
        session.setAttribute("typeErr", "Enter device type");
        session.setAttribute("priceErr", "Enter price $0.00");
        session.setAttribute("stockErr", "Enter stock quantity");
        session.setAttribute("descriptionErr", "Enter device description");
        session.setAttribute("deletedeviceNameErr", "");
        session.setAttribute("deletetypeErr", "");
        session.setAttribute("deletepriceErr", "");
        session.setAttribute("deletestockErr", "");
        session.setAttribute("deletedescriptionErr", "");
        session.setAttribute("exceptionErr", "");
        session.setAttribute("deviceEmptyErr", "");
    }
}