/*
 * User is a Javabean that stores firstname, lastname, email, password, DOB, gender and favouritecolour
  */
package uts.isd.model;

import java.io.Serializable;

import java.util.Date;


/**
 *
 * @author aiswaryarajeev
 */
public class User implements Serializable {
    private String userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private String unitNumber;
    private String streetAddress;
    private String city;
    private String state;
    private String postcode;
    private Date dateRegistered;
    private boolean loginStatus;
    private String phoneNumber;

    public User(String firstName, String lastName, String email, String password, String gender, String unitNumber, String streetAddress, String city, String state, String postcode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.unitNumber = unitNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
