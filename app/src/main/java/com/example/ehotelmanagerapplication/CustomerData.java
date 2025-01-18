package com.example.ehotelmanagerapplication;

public class CustomerData {

    private String FullName;
    private String Email;
    private String Password;

    private String Phone;
    private String Address;

    public CustomerData() {}


    public CustomerData(String FullName, String Email, String Password, String Phone, String Address) {
        this.FullName = FullName;
        this.Email = Email;
        this.Password = Password;
        this.Phone = Phone;
        this.Address = Address;
    }

    public String getFullName() {
        return FullName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAddress() {
        return Address;
    }

}
