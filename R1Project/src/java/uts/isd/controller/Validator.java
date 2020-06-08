package uts.isd.controller;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.*;
import javax.servlet.http.HttpSession;

public class Validator implements Serializable {
   // generic, common patterns
   private String deviceNamePattern = "(([a-zA-Z0-9]+))";
   private String singleIntPattern = "(\\d{0,20})";
   private String sentencePattern = "(?!^[\\d\\s!\"#$%&'()*+,./:;<=>?@\\^_`{|}~-]+$)^.+$";

   // online-user-access-specific management patterns
   private String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";
   private String passwordPattern = "[a-zA-Z0-9]{4,}";
   private String phonePattern = "0([0-9]{0,10})";      
    
   //Device manager validator
    //type = Word with capital (space) word with capital
    private String deviceTypePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";
    //Numbers with 2 decimals (.00)
    private String deviceCostPattern = "(^-?\\d*\\.\\d{2}$)";
    //Positive integers of undefined length
    private String deviceStockPattern = "(^\\d+$)";
    //Only numbers no spaces
    private String deviceIDPattern = "(\\d+[0-9]*$)";
   
//supplier management validator patterns
    private String contactNamePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";
    private String supplierNamePattern = "(?!^[\\d\\s!\"#$%&'()*+,./:;<=>?@\\^_`{|}~-]+$)^.+$";
    private String supplierEmailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";
   //payment details valdator patterns
    private String methodOfPaymentPattern = "[A-Za-z\\s]*";
    private String hashedCreditedCardNumberPattern = "[0-9]{16}|[0-9]{4}[\\s]?[0-9]{4}[\\s]?[0-9]{4}[\\s]?[0-9]{4}";
    private String cardSecurityCodePattern = "^[0-9]{3}$";
    private String cardExpiryDatePattern = "^[0-9]{4}-[0-9]{2}$";
    
    private String supplierAddressPattern = "[A-Za-z0-9\\s,/]*";

   public Validator() {
   }
   
   public boolean validate(String pattern, String input) {
      Pattern regEx = Pattern.compile(pattern);
      Matcher match = regEx.matcher(input);
      return match.matches();
   }

   // commonly used validators

   // validator - needs to be a valid single string
   public boolean validateSingleString(String string) {
     return validate(deviceNamePattern, string);
   }
   
   // validator - needs to be valid single int
   public boolean validateSingleInt(String number) {
     return validate(singleIntPattern, number);
   }

   // validator - needs to be valid single int
   public boolean validateSentence(String number) {
     return validate(sentencePattern, number);
   }


   // customer validator - needs to have @ sign
   public boolean validateEmail(String email) {
      return validate(emailPattern, email);
   }

   // customer validator - phone needs to start with 0
   public boolean validatePhone(String phoneNumber) {
     return validate(phonePattern, phoneNumber);
   }

   // customer validator - password needs 4 char
   public boolean validatePassword(String password) {
      return validate(passwordPattern, password);
   }
    
    //device validators - check if fields are empty
     public boolean checkDeviceEmpty(String deviceID, String deviceName, String type, String cost, String stockQuantity, String description){       
       return  deviceID.isEmpty()|| deviceName.isEmpty() || type.isEmpty() || cost.isEmpty() || stockQuantity.isEmpty() || description.isEmpty() ;   
    }
     
     public boolean checkDeviceFieldsEmpty(String deviceName, String type, String cost, String stockQuantity, String description){       
       return deviceName.isEmpty() || type.isEmpty() || cost.isEmpty() || stockQuantity.isEmpty() || description.isEmpty() ;   
    }
     
     public boolean validateDeviceID(String deviceID){
         return validate(deviceIDPattern, deviceID);
     }
    
    //device validators - check deviceName
    public boolean validateDeviceName(String deviceName){
         return validate(sentencePattern, deviceName); 
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
         return validate(sentencePattern, description); 
    }
    
    //supplier validators - check contactName
    public boolean validateContactName(String contactName) {
        return validate(contactNamePattern, contactName);
    }
    
    //supplier validators - check supplierName
    public boolean validateSupplierName(String supplierName) {
        return validate(supplierNamePattern, supplierName);
    }
    
    //supplier validators - check supplierEmail
    public boolean validateSupplierEmail(String supplierEmail) {
        return validate(supplierEmailPattern, supplierEmail);
    }
    
    //supplier validators - check supplierAddress
    public boolean validateSupplierAddress(String supplierAddress) {
        return validate(supplierAddressPattern, supplierAddress);
    }
    
    //supplier validators - check if fields are empty
     public boolean checkSupplierEmpty(String contactName, String supplierName, String supplierEmail, String supplierAddress){       
       return  contactName.isEmpty() || supplierName.isEmpty() || supplierEmail.isEmpty()|| supplierAddress.isEmpty();   
    }
    
     //supplier validators - check if search fields for update are empty
     public boolean checkSearchEmpty(String contactName, String supplierName){
         return  contactName.isEmpty() || supplierName.isEmpty();   
    }
    
     public boolean validateMethodOfPayment(String methodOfPayment) {
         return validate(methodOfPaymentPattern,methodOfPayment);
     }
     
    public boolean validatehashedCreditedCardNumber (String cardNumber) {
        return validate(hashedCreditedCardNumberPattern, cardNumber);
    }
    
    public boolean validatecardSecurityCode (String securityCode) {
        return validate(cardSecurityCodePattern, securityCode);
    }
    
    public boolean validatecardExpiryDate (String cardExpiryDate){
        return validate(cardExpiryDatePattern, cardExpiryDate);
    }
    
    public boolean checkPaymentDetailsEmpty(String methodOfPayment, String cardNumber, String securityCode, String expiryDate) {
        return methodOfPayment.isEmpty() || cardNumber.isEmpty() || securityCode.isEmpty() || expiryDate.isEmpty();
    }
    
    public void clear(HttpSession session) {
        // login and register
        session.setAttribute("emailErr", "Enter email");
        session.setAttribute("passErr", "Enter password");
        session.setAttribute("existErr", "");
        session.setAttribute("nameErr", "Enter name");
        session.setAttribute("createMsg", "");

        // edit
        session.setAttribute("updateMsg", "");
        session.setAttribute("existEditErr", "");
        session.setAttribute("emailEditErr", "");
        session.setAttribute("passEditErr", "");
        session.setAttribute("fNameEditErr", "");
        session.setAttribute("lNameEditErr", "");
        session.setAttribute("phoneEditErr", "");
        session.setAttribute("unitEditErr", "");
        session.setAttribute("streetEditErr", "");
        session.setAttribute("cityEditErr", "");
        session.setAttribute("stateEditErr", "");
        session.setAttribute("postEditErr", "");
        session.setAttribute("genderEditErr", "");
        session.setAttribute("managerEditErr", "");
        session.setAttribute("contractTypeEditErr", "");
        session.setAttribute("payHrEditErr", "");
        
        //delete
        session.setAttribute("deleteMsg", "");
        session.setAttribute("userDeleteErr", "");

        //deviceCreation clear()
        session.setAttribute("quantityErr", "");
        session.setAttribute("deviceupdateMsg", "");
        session.setAttribute("deviceDeletedMsg", "");
        session.setAttribute("devicecreatedMsg", "");
        session.setAttribute("deviceIDErr", "Enter device ID");
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
        session.setAttribute("deletedeviceIDErr", "");
        
        //supplierCreation clear()
        session.setAttribute("contactNameErr", "Enter contact name");
        session.setAttribute("supplierNameErr", "Enter company name");
        session.setAttribute("supplierEmailErr", "Enter email");
        session.setAttribute("supplierAddressErr", "Enter address");
        //session.setAttribute("active", "Enter active status");
        session.setAttribute("supplierEmptyErr", "");
        session.setAttribute("exceptionSupplierErr", "");
        session.setAttribute("confirmationCreation", "");
        session.setAttribute("formatErr", "");
        //session.setAttribute("deleteActiveErr", "");
        
        //paymentDetail related errors clear()
        session.setAttribute("paymentDetailsEmptyErr","");
        session.setAttribute("methodFieldErr","");
        session.setAttribute("cardNumberFieldErr","");
        session.setAttribute("cardCodeFieldErr","");
        session.setAttribute("expiryDateFieldErr",""); 
        session.setAttribute("updateSucess","");
    }
}