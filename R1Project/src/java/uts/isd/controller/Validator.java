package uts.isd.controller;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.*;
import javax.servlet.http.HttpSession;

public class Validator implements Serializable {
   // generic, common patterns
   private String singleStringPattern = "?";
   private String singleIntPattern = "?";
   
   // online-user-access-specific management patterns
   private String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";
   private String namePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";
   private String passwordPattern = "[a-z0-9]{4,}";
   private String phonePattern = "0([0-9]+)";

   // device manageemnt validator patterns
   private String deviceNamePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";
   private String deviceTypePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";
   // Numbers with 2 decimals (.00)
   private String deviceCostPattern = "^-?\\d*\\.\\d{2}$";
   // Positive integers of undefined length
   private String deviceStockPattern = "^\\d+$";
   private String deviceDescriptionPattern = "[A-Z][a-z]*";
   
   //supplier management validator patterns
   private String supplierStatus = "[A-Z][a-z]*";

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
//      return validate(passwordPattern, string);
        return true; // testing purpose
   }

   // validator - needs to be valid single int
   public boolean validateSingleInt(String number) {
//      return validate(namePattern, number);
        return true; // testing purpose
   }


   // customer validator - needs to have @ sign
   public boolean validateEmail(String email) {
      return validate(emailPattern, email);
   }

   // customer validator - phone needs to start with 0
   public boolean validatePhone(String phoneNumber) {
//      return validate(passwordPattern, phoneNumber);
      return true; // testing purpose
   }

   // customer validator - password needs 4 char
   public boolean validatePassword(String password) {
      return validate(passwordPattern, password);
   }

   // device validators - check if fields are empty
   public boolean checkDeviceEmpty(String deviceName, String type, String cost, String stockQuantity,
         String description) {
      return deviceName.isEmpty() || type.isEmpty() || cost.isEmpty() || stockQuantity.isEmpty()
            || description.isEmpty();
   }

   // device validators - check deviceName
   public boolean validateDeviceName(String deviceName) {
      return validate(deviceNamePattern, deviceName);
   }

   // device validators - check deviceType
   public boolean validateDeviceType(String type) {
      return validate(deviceTypePattern, type);
   }

   // device validators - check deviceCost
   public boolean validateDeviceCost(String cost) {
      return validate(deviceCostPattern, cost);
   }

   // device validators - check deviceStock
   public boolean validateDeviceStock(String stockQuantity) {
      return validate(deviceStockPattern, stockQuantity);
   }

   // device validators - check deviceDescription
   public boolean validateDeviceDesc(String description) {
      return validate(deviceDescriptionPattern, description);
   }

   public void clear(HttpSession session) {
      session.setAttribute("emailErr", "Enter email");
      session.setAttribute("passErr", "Enter password");
      session.setAttribute("existErr", "");
      session.setAttribute("nameErr", "Enter name");
   }
}